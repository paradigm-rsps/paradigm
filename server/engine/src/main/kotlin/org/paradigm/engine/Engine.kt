package org.paradigm.engine

import org.paradigm.common.inject
import org.paradigm.engine.net.NetworkServer
import org.tinylog.kotlin.Logger

class Engine {

    private val networkServer: NetworkServer by inject()

    fun start() {
        Logger.info("Starting server engine.")

        networkServer.start()
    }

    fun stop() {
        Logger.info("Stopping server engine.")

        networkServer.stop()
    }
}