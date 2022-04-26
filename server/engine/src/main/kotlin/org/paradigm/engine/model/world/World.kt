package org.paradigm.engine.model.world

import org.paradigm.cache.GameCache
import org.paradigm.common.inject
import org.paradigm.engine.model.collision.CollisionMap
import org.paradigm.engine.model.list.PlayerList
import org.paradigm.engine.queue.QueueList

class World {

    private val cache: GameCache by inject()

    val players = PlayerList()

    val collision = CollisionMap()

    internal val queue = QueueList()

    fun queue(block: suspend () -> Unit) = queue.queue(block)

    internal fun init() {
        /*
         * Load the world map data from the game cache.
         * This data contains the terrain collision along with the
         * map static objects which are used to build the collision map.
         */
        WorldLoader.loadRegions()
    }
}