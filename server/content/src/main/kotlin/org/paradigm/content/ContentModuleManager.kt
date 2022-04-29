package org.paradigm.content

import org.tinylog.kotlin.Logger
import java.io.File
import java.io.FileNotFoundException
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

class ContentModuleManager {

    private val classloader = ClassLoader.getSystemClassLoader()
    private val modules = hashMapOf<String, ContentModule>()


    fun loadModuleDirectory() {
        Logger.info("Loading content modules.")
        MODULE_DIR.listFiles()?.filter { it.name.endsWith(".jar") }?.forEach { loadModule(it) }
        Logger.info("Found ${modules.size} content modules.")
    }

    fun loadModule(file: File) {
        Logger.info("Loading content module: ${file.nameWithoutExtension}.")

        val moduleClassLoader = ContentModuleClassLoader(classloader, file)
        val module = moduleClassLoader.init()

        module.enable()
        modules[module.name] = module
    }

    fun unloadModule(module: ContentModule) {

    }

    fun reloadModule(module: ContentModule) {

    }

    companion object {
        private val MODULE_DIR = File("data/modules/")
    }
}