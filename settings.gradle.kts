import groovy.util.FileNameFinder
import java.nio.file.Path
import java.nio.file.Paths

plugins {
    id("de.fayard.refreshVersions") version "0.40.1"
}

rootProject.name = "paradigm"

/*
 * ===== CLIENT MODULES =====
 */
include(":client")

/*
 * ===== SERVER MODULES =====
 */
include(":server")
include(":server:launcher")
include(":server:common")
include(":server:util")
include(":server:logger")
include(":server:engine")
include(":server:config")
include(":server:cache")
include(":server:api")
includeModules(":server:content")

@Suppress("DEPRECATION")
fun includeModules(module: String) {
    val pluginRelativePath = module.substring(1).replace(":", "/")
    val pluginRootDir: Path = rootProject.projectDir.toPath().resolve(pluginRelativePath)
    if (pluginRootDir.toFile().exists()) {
        val gradleBuildFiles = FileNameFinder().getFileNames("$pluginRootDir", "**/*.gradle.kts")
        gradleBuildFiles.forEach { filename ->
            val buildFilePath = Paths.get(filename)
            val moduleDir = buildFilePath.parent
            val relativePath = pluginRootDir.relativize(moduleDir)
            val pluginName = "$relativePath".replace(File.separator, ":")
            include("$module:$pluginName")
        }
    }
}