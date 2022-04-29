package org.paradigm.content

import io.github.classgraph.ClassGraph
import java.io.File
import java.io.FileNotFoundException
import java.net.URLClassLoader
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

@Suppress("UNCHECKED_CAST")
class ContentModule(val name: String) {
    private val file = File("data/modules/$name.jar")
    private lateinit var classLoader: URLClassLoader
    private val scripts = hashSetOf<ContentModuleScript>()

    fun load() {
        if (!file.exists()) throw FileNotFoundException()

        this.classLoader = URLClassLoader(arrayOf(file.toURI().toURL()))

        ClassGraph()
            .enableAllInfo()
            .addClassLoader(classLoader)
            .scan().use {
                it.getSubclasses(ContentModuleScript::class.qualifiedName).forEach { classInfo ->
                    val cls = classInfo.loadClass().kotlin as KClass<ContentModuleScript>
                    val script = cls.createInstance()
                    scripts.add(script)
                }
            }

        classLoader.close()
    }

    fun enable() {
        scripts.forEach { it.enableBlock() }
    }

    fun disable() {
        scripts.forEach { it.disableBlock() }
        scripts.clear()
    }

    fun reload() {
        disable()
        load()
        enable()
    }
}