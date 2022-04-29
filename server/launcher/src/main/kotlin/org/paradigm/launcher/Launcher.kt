package org.paradigm.launcher

import org.koin.core.context.startKoin
import org.paradigm.cache.GameCache
import org.paradigm.common.inject
import org.paradigm.config.ServerConfig
import org.paradigm.config.XteaConfig
import org.paradigm.engine.Engine
import org.paradigm.util.RSA
import org.tinylog.kotlin.Logger
import java.io.File

object Launcher {

    private val engine: Engine by inject()
    private val serverConfig: ServerConfig by inject()
    private val cache: GameCache by inject()
    private val rsa: RSA by inject()

    @JvmStatic
    fun main(args: Array<String>) {
        this.init()
        this.launch()
    }

    private fun init() {
        Logger.info("Initializing...")

        startKoin { modules(DI_MODULES) }

        /*
         * Execute all initialization logics.
         */
        this.checkDirs()
        this.loadConfigs()
        this.loadCache()
        this.loadRSA()
    }

    private fun launch() {
        Logger.info("Launching Paradigm...")
        engine.start()
    }

    private fun checkDirs() {
        listOf(
            "data/",
            "data/cache/",
            "data/configs/",
            "data/logs/",
            "data/players/",
            "data/rsa/",
            "data/modules/"
        ).map { File(it) }.forEach { dir ->
            if(!dir.exists()) {
                Logger.info("Creating required directory: ${dir.path}.")
                dir.mkdirs()
            }
        }
    }

    private fun loadConfigs() {
        Logger.info("Loading configurations files.")
        serverConfig.load()
        XteaConfig.load()
    }

    private fun loadCache() {
        Logger.info("Loading game cache files.")
        cache.load(File("data/cache/"))
        Logger.info("Successfully loaded ${cache.archiveCount} cache archives.")
    }

    private fun loadRSA() {
        Logger.info("Loading RSA private key.")
        rsa.load()
    }
}