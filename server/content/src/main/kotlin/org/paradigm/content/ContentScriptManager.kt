@file:Suppress("UNCHECKED_CAST")

package org.paradigm.content

import io.github.classgraph.ClassGraph
import org.tinylog.kotlin.Logger
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class ContentScriptManager {

    private val loadedScripts = mutableMapOf<KClass<out ContentScript>, ContentScript>()
    private val enabledScripts = mutableListOf<KClass<out ContentScript>>()
    private val disabledScripts = mutableListOf<KClass<out ContentScript>>()

    fun loadScripts() {
        Logger.info("Loading content scripts.")
        ClassGraph()
            .enableClassInfo()
            .scan(4).use { result ->
                result.getSubclasses(ContentScript::class.java).forEach { classInfo ->
                    val cls = classInfo.loadClass().kotlin as KClass<ContentScript>
                    val inst = cls.createInstance()
                    loadedScripts[cls] = inst
                }
            }
        Logger.info("Found ${loadedScripts.keys.size} content scripts.")
    }

    fun enableScripts() {
        loadedScripts.keys.forEach { scriptClass ->
            enableScript(scriptClass)
        }
    }

    fun disableScripts() {
        loadedScripts.keys.forEach { scriptClass ->
            disableScript(scriptClass)
        }
    }

    fun <T : ContentScript> enableScript(scriptClass: KClass<T>) {
        if (enabledScripts.contains(scriptClass)) {
            Logger.error("Content script: ${scriptClass.simpleName} is already enabled.")
            return
        }

        enabledScripts.add(scriptClass)
        disabledScripts.removeIf { it == scriptClass }

        val script = loadedScripts[scriptClass]!!
        script.enableBlock(script)
    }

    fun <T : ContentScript> disableScript(scriptClass: KClass<T>) {
        if (disabledScripts.contains(scriptClass)) {
            Logger.error("Content script: ${scriptClass.simpleName} is already disabled.")
            return
        }

        disabledScripts.add(scriptClass)
        enabledScripts.removeIf { it == scriptClass }

        val script = loadedScripts[scriptClass]!!
        script.disableBlock(script)
    }
}