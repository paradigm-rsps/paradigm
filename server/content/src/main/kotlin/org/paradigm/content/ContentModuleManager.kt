package org.paradigm.content

import org.tinylog.kotlin.Logger
import java.io.File

class ContentModuleManager {

    companion object {
        private val MODULE_DIR = File("data/modules/")
    }

    private val modules = hashMapOf<String, ContentModule>()

    fun loadModules() {
        Logger.info("Loading content modules.")

        MODULE_DIR.listFiles()?.filter { it.name.endsWith(".jar") }?.forEach {
            val module = ContentModule(it.nameWithoutExtension)
            module.load()
            modules[module.name] = module
        }

        Logger.info("Loaded ${modules.size} content modules.")
    }

    fun enableModules() {
        modules.values.forEach { it.enable() }
    }

    fun disableModules() {
        modules.values.forEach { it.disable() }
    }

    fun getModule(name: String): ContentModule? = modules[name]

    fun reloadModule(name: String) {
        val module = getModule(name) ?: throw IllegalStateException("Could not find content module: $name.")
        module.reload()
    }

}