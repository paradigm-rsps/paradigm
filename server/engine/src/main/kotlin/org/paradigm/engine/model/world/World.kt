package org.paradigm.engine.model.world

import net.runelite.cache.definitions.LocationsDefinition
import net.runelite.cache.definitions.MapDefinition
import org.paradigm.cache.GameCache
import org.paradigm.common.inject
import org.paradigm.config.XteaConfig
import org.paradigm.engine.model.collision.CollisionMap
import org.paradigm.engine.model.list.PlayerList
import org.paradigm.engine.queue.QueueList

class World {

    private val cache: GameCache by inject()

    val players = PlayerList()

    val chunks = mutableListOf<WorldChunk>()

    internal val queue = QueueList()

    fun queue(block: suspend () -> Unit) = queue.queue(block)

    internal fun load() {
        /*
         * Load World map collision and objects.
         */
        XteaConfig.regions.forEach { (regionId, keys) ->
            val (map, loc) = cache.maps[regionId] ?: return@forEach
            for (plane in 0 until 4) {
                for (x in 0 until 8) {
                    for (y in 0 until 8) {

                    }
                }
            }
        }
    }

    private fun WorldChunk.loadCollision(mapDef: MapDefinition, locDef: LocationsDefinition) {

    }
}