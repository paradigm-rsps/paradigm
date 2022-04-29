package org.paradigm.content

import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

@Suppress("UNCHECKED_CAST")
class ContentModule(val name: String, val classloader: ContentModuleClassLoader) {

    internal val scriptClasses = hashSetOf<String>()
    private val scripts = mutableMapOf<String, ContentScript>()

    var enabled: Boolean = false
        private set

    fun enable() {
        if (enabled) throw IllegalStateException("Content module '$name' is already enabled.")
        loadContentScripts()
        scripts.values.forEach { it.enableBlock() }
        enabled = true
    }

    fun disable() {
        if (!enabled) throw IllegalStateException("Content module '$name' is already disabled.")
        scripts.values.forEach { it.disableBlock() }
        scripts.clear()
        enabled = false
    }

    private fun loadContentScripts() {
        scriptClasses.forEach { className ->
            val cls = Class.forName(className, true, classloader)?.kotlin as? KClass<out ContentScript>
                ?: return@forEach
            val script = cls.createInstance()
            scripts[className] = script
        }
    }
}