package org.paradigm.launcher

import org.koin.core.context.startKoin
import org.paradigm.cache.GameCache
import org.paradigm.common.inject
import org.paradigm.config.ServerConfig
import org.paradigm.engine.Engine
import org.tinylog.kotlin.Logger
import java.io.File

object Launcher {

    private val engine: Engine by inject()
    private val serverConfig: ServerConfig by inject()
    private val cache: GameCache by inject()

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
            "data/rsa/"
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
    }

    private fun loadCache() {
        Logger.info("Loading game cache files.")
        cache.load()
        Logger.info("Successfully loaded ${cache.archiveCount} cache archives.")
    }

}