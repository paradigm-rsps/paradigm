package org.paradigm.content

import org.tinylog.kotlin.Logger
import java.io.File
import java.io.FileNotFoundException
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

class ContentModuleManager {

    private val modules = hashMapOf<String, ContentModule>()

    fun loadModuleDirectory() {
        Logger.info("Loading content modules.")

        val moduleJars = MODULE_DIR.listFiles()?.filter { it.name.endsWith(".jar") } ?: emptyList()
        Logger.info("Found ${moduleJars.size} content modules.")

        moduleJars.forEach { loadModule(it) }
    }

    fun loadModule(file: File) {
        Logger.info("Loading content module: ${file.nameWithoutExtension}.")

        val tmpFile = File.createTempFile("paradigm_module_", "")
        tmpFile.deleteOnExit()

        file.inputStream().use { input ->
            tmpFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        val moduleClassLoader = ContentModuleClassLoader(file.nameWithoutExtension, tmpFile)
        val module = moduleClassLoader.init()

        module.enable()
        modules[module.name] = module
    }

    fun unloadModule(module: ContentModule) {
        Logger.info("Unloading content module: ${module.name}.")

        module.disable()
        module.classloader.clearClasses()
        modules.remove(module.name)
    }

    fun reloadModule(module: ContentModule) {
        val file = MODULE_DIR.resolve(module.name + ".jar")
        val moduleName = module.name

        unloadModule(module)
        loadModule(file)

        Logger.info("Successfully reloaded content module: $moduleName.")
    }

    fun getModule(name: String) = modules[name]

    companion object {
        private val MODULE_DIR = File("data/modules/")
    }
}