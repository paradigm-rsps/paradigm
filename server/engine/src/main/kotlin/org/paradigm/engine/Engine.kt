package org.paradigm.engine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.paradigm.common.inject
import org.paradigm.config.ServerConfig
import org.paradigm.engine.coroutine.EngineCoroutineScope
import org.paradigm.engine.model.World
import org.paradigm.engine.net.NetworkServer
import org.paradigm.engine.service.ServiceManager
import org.paradigm.engine.sync.SyncTaskList
import org.tinylog.kotlin.Logger
import java.util.concurrent.TimeUnit
import kotlin.system.measureNanoTime

class Engine {

    private val engineCoroutine: EngineCoroutineScope by inject()
    private val networkServer: NetworkServer by inject()
    private val serviceManager: ServiceManager by inject()
    private val world: World by inject()

    private val updateTasks = SyncTaskList()
    private var running = false
    private var prevCycleNanos = 0L

    internal val ioCoroutine = CoroutineScope(Dispatchers.IO)

    fun start() {
        Logger.info("Starting server engine.")

        running = true

        serviceManager.start()
        updateTasks.init()
        engineCoroutine.start()
        networkServer.start()
    }

    fun stop() {
        Logger.info("Stopping server engine.")

        running = false

        serviceManager.stop()
        networkServer.stop()
    }

    private fun CoroutineScope.start() = launch {
        while(running) {
            val activeNanos = measureNanoTime { cycle() } + prevCycleNanos
            val activeMillis = TimeUnit.NANOSECONDS.toMillis(activeNanos)
            val idleMillis = if (activeMillis > ServerConfig.TICK_RATE) {
                val curCycle = activeMillis / ServerConfig.TICK_RATE
                (curCycle + 1) * ServerConfig.TICK_RATE - activeMillis
            } else {
                ServerConfig.TICK_RATE - activeMillis
            }
            if (activeMillis > ServerConfig.TICK_RATE) {
                Logger.warn("Tick took longer than expected. Is the server overloaded? (active: ${activeMillis}ms, idle: ${idleMillis}ms).")
            }
            prevCycleNanos = activeNanos - TimeUnit.MILLISECONDS.toNanos(activeMillis)
            delay(idleMillis)
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
        world.players.forEach { it.session.cycle() }
        world.players.forEach { it.cycle() }
        updateTasks.forEach { it.execute() }
        world.players.forEach { it.session.flush() }
    }
}