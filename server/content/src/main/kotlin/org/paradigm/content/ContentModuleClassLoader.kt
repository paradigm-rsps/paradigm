package org.paradigm.content

import io.github.classgraph.ClassGraph
import java.io.File
import java.net.URLClassLoader
import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName

@Suppress("UNCHECKED_CAST")
class ContentModuleClassLoader(private val parentClassLoader: ClassLoader, private val file: File) : URLClassLoader(
    arrayOf(file.toURI().toURL()),
    parentClassLoader
) {

    lateinit var module: ContentModule private set

    fun init(): ContentModule {
        module = ContentModule(file.nameWithoutExtension, this)

        ClassGraph()
            .enableAllInfo()
            .overrideClassLoaders(this)
            .scan().use { result ->
                result.getSubclasses(ContentScript::class.qualifiedName).forEach { classInfo ->
                    val cls = classInfo.loadClass().kotlin as KClass<out ContentScript>
                    module.scriptClasses.add(cls.qualifiedName!!)
                }
            }

        return module
    }
}