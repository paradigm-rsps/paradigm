package org.paradigm.launcher

import org.koin.core.context.startKoin
import org.paradigm.common.inject
import org.paradigm.config.ServerConfig
import org.paradigm.util.RSA
import org.tinylog.kotlin.Logger
import java.io.File
import java.util.zip.ZipFile

object Setup {

    private val serverConfig: ServerConfig by inject()

    @JvmStatic
    fun main(args: Array<String>) {
        Logger.info("Setting up Paradigm server...")

        /*
         * Start dependency injector.
         */
        startKoin { modules(DI_MODULES) }

        /*
         * Execute setup tasks.
         */
        this.createDirs()
        this.createConfigs()
        this.createRSA()
        this.extractCache()
    }

    private fun createDirs() {
        Logger.info("Creating required directories.")

        val dirs = listOf(
            "data/",
            "data/cache/",
            "data/configs/",
            "data/logs/",
            "data/rsa/",
            "data/players/",
        ).map { File(it) }

        /*
         * Delete data directory if it exists.
         */
        if(File("data/").exists()) {
            File("data/").deleteRecursively()
        }

        dirs.forEach { dir ->
            if(!dir.exists()) {
                Logger.info("Creating empty directory: ${dir.path}.")
                dir.mkdirs()
            }
        }
    }

    private fun createConfigs() {
        Logger.info("Creating default configuration files.")

        /*
         * Server Config
         */
        serverConfig.load()
    }

    private fun createRSA() {
        RSA.generateKeyPair()

        /*
         * Copy the modulus.txt to the client in the project.
         */
        Logger.info("Copying RSA public modulus into client.")

        val modulusFile = File("data/rsa/modulus.txt")

        modulusFile.inputStream().use { input ->
            File("client/src/main/resources/modulus.txt").outputStream().use { output ->
                input.copyTo(output)
            }
        }

        modulusFile.inputStream().use { input ->
            File("nxt-client/src/main/resources/modulus.txt").outputStream().use { output ->
                input.copyTo(output)
            }
        }
    }

    private fun extractCache() {
        Logger.info("Extracting cache files...")

        val file = File("cache.zip")
        ZipFile(file).use { zip ->
            zip.entries().asSequence().forEach { entry ->
                if(!entry.isDirectory) {
                    zip.getInputStream(entry).use { input ->
                        File("data/cache/${entry.name}").outputStream().use { output ->
                            Logger.info("Copying file: ${entry.name}.")
                            input.copyTo(output)
                        }
                    }
                }
            }
        }
    }
}