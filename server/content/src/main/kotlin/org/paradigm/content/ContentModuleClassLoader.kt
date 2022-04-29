package org.paradigm.content

import io.github.classgraph.ClassGraph
import java.io.File
import java.net.URLClassLoader
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName

@Suppress("UNCHECKED_CAST")
class ContentModuleClassLoader(
    private val moduleName: String,
    private val file: File
) : URLClassLoader(
    arrayOf(file.toURI().toURL()),
    ContentModuleManager::class.java.classLoader
) {
    private val classes = ConcurrentHashMap<String, KClass<*>>()
    lateinit var module: ContentModule private set

    fun init(): ContentModule {
        module = ContentModule(moduleName, this)

        ClassGraph()
            .enableAllInfo()
            .overrideClassLoaders(this)
            .scan().use { result ->
                result.getSubclasses(ContentScript::class.qualifiedName).forEach { classInfo ->
                    val cls = classInfo.loadClass().kotlin as KClass<out ContentScript>
                    val subCls = cls.java.asSubclass(ContentScript::class.java).kotlin
                    classes[subCls.qualifiedName!!] = subCls
                    module.scriptClasses.add(cls.qualifiedName!!)
                }
            }

        return module
    }

    override fun findClass(name: String): Class<*> {
        if (classes.containsKey(name)) {
            return classes[name]!!.java
        } else {
            return super.findClass(name)
        }
    }

    fun clearClasses() {
        classes.clear()
    }
}