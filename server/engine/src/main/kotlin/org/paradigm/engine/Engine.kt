package org.paradigm.engine

import org.paradigm.cache.GameCache
import org.paradigm.common.inject
import org.paradigm.engine.net.NetworkServer
import org.tinylog.kotlin.Logger

class Engine {

    private val cache: GameCache by inject()
    private val networkServer: NetworkServer by inject()

    fun start() {
        Logger.info("Starting server engine.")

        /*
         * Load game cache files.
         */
        cache.load()
        Logger.info("Loaded ${cache.archiveCount} game cache archives.")

        networkServer.start()
    }

    fun stop() {
        Logger.info("Stopping server engine.")

        networkServer.stop()
    }
}