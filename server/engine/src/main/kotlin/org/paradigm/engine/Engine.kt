package org.paradigm.engine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.paradigm.common.inject
import org.paradigm.config.ServerConfig
import org.paradigm.engine.coroutine.EngineCoroutineScope
import org.paradigm.engine.net.NetworkServer
import org.tinylog.kotlin.Logger
import java.sql.Time
import java.util.concurrent.TimeUnit
import kotlin.system.measureNanoTime

class Engine {

    private val engineCoroutine: EngineCoroutineScope by inject()
    private val networkServer: NetworkServer by inject()

    private var running = false
    private var prevCycleNanos = 0L

    fun start() {
        Logger.info("Starting server engine.")

        running = true
        engineCoroutine.start()
        networkServer.start()
    }

    fun stop() {
        Logger.info("Stopping server engine.")

        running = false
        networkServer.stop()
    }

    private fun CoroutineScope.start() = launch {
        while(running) {
            val curNanos = measureNanoTime { cycle() } + prevCycleNanos
            val curMillis = TimeUnit.NANOSECONDS.toMillis(curNanos)
            val sleepMillis = if(curMillis > ServerConfig.TICK_RATE) {
                val curCycle = curMillis / ServerConfig.TICK_RATE
                (curCycle + 1) * ServerConfig.TICK_RATE - curMillis
            } else {
                ServerConfig.TICK_RATE - curMillis
            }
            if(curMillis > ServerConfig.TICK_RATE) {
                Logger.warn("Tick took longer than expected. Is the server overloaded? (active: ${curMillis}ms, idle: ${sleepMillis}ms).")
            }
            prevCycleNanos = curNanos - TimeUnit.NANOSECONDS.toNanos(curMillis)
            delay(sleepMillis)
        }
    }

    /**
     * The root most game engine cycle method.
     *
     * This method gets executed every server game tick and is
     * responsible for dispatching out calls to all other parts of
     * the engine that execute every tick/cycle.
     */
    private suspend fun cycle() {

    }
}