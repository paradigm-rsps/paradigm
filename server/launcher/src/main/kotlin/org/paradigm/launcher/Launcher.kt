package org.paradigm.launcher

import org.koin.core.context.startKoin
import org.paradigm.common.inject
import org.paradigm.engine.ENGINE_MODULE
import org.paradigm.engine.Engine
import org.tinylog.kotlin.Logger
import java.io.File

object Launcher {

    private val engine: Engine by inject()

    private val DI_MODULES = listOf(
        ENGINE_MODULE
    )

    @JvmStatic
    fun main(args: Array<String>) {
        this.init()
        this.launch()
    }

    private fun init() {
        Logger.info("Initializing...")

        this.checkDirs()

        /*
         * Start dependency injector.
         */
        startKoin { modules(DI_MODULES) }
    }

    private fun launch() {
        Logger.info("Launching Paradigm...")

        /*
         * Start the server engine.
         */
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
}