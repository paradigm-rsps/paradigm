package org.paradigm.engine.model.world

import net.runelite.cache.definitions.LocationsDefinition
import net.runelite.cache.definitions.MapDefinition
import org.paradigm.cache.GameCache
import org.paradigm.common.inject
import org.paradigm.config.XteaConfig
import org.paradigm.engine.model.collision.CollisionMap
import org.paradigm.engine.model.list.PlayerList
import org.paradigm.engine.model.map.Tile
import org.paradigm.engine.queue.QueueList
import net.runelite.cache.region.Region
import org.paradigm.engine.model.obj.GameObject
import org.rsmod.pathfinder.flag.CollisionFlag
import org.tinylog.kotlin.Logger
import java.io.FileNotFoundException

class World {

    private val cache: GameCache by inject()

    val players = PlayerList()

    val collision = CollisionMap()

    internal val queue = QueueList()

    fun queue(block: suspend () -> Unit) = queue.queue(block)

    internal fun load() {
        /*
         * Load World map collision and objects.
         */
        Logger.info("Loading world map data.")

        XteaConfig.regions.forEach { (regionId, keys) ->
            val (map, loc) = cache.maps[regionId] ?: return@forEach
            Region(regionId).loadCollision(map, loc)
        }
    }

    private fun Region.loadCollision(map: MapDefinition, loc: LocationsDefinition) {
        val floorMask = mutableMapOf<Tile, Int>()

        loadTerrain(map)
        loadLocations(loc)

        for (plane in 0 until MAX_PLANE) {
            for (x in 0 until 64) {
                for (y in 0 until 64) {
                    floorMask[Tile(x, y, plane)] = getTileSetting(plane, x, y).toInt()
                }
            }
        }

        for (plane in 0 until MAX_PLANE) {
            for (x in 0 until 64) {
                for (y in 0 until 64) {
                    val localTile = Tile(x, y, plane)
                    val localMask = floorMask[localTile] ?: 0
                    if ((localMask and BLOCKED_TILE_FLAG) != BLOCKED_TILE_FLAG) {
                        continue
                    }
                    var adjustedPlane = plane
                    val bridgeTile = Tile(x, y, 1)
                    val bridgeMask = floorMask[bridgeTile] ?: 0
                    if ((bridgeMask and BRIDGE_TILE_FLAG) == BRIDGE_TILE_FLAG) {
                        adjustedPlane--
                    }
                    if (adjustedPlane >= 0) {
                        val tile = org.paradigm.engine.model.map.Region(this.regionID).toTile(plane).translate(x, y)
                        collision.add(tile, CollisionFlag.FLOOR)
                    }
                }
            }
        }

        loc.locations.forEach {
            val localX = it.position.x
            val localY = it.position.y
            if (localX !in 0 until 64 || localY !in 0 until 64) {
                return@forEach
            }
            val shape = it.type
            val rotation = it.orientation
            var plane = it.position.z
            val localTile = Tile(localX, localY, 1)
            val floor = floorMask[localTile] ?: 0
            if ((floor and BRIDGE_TILE_FLAG) == BRIDGE_TILE_FLAG) {
                plane--
            }
            if (plane >= 0) {
                val data = cache.configs.objects[it.id]
                    ?: throw FileNotFoundException("Failed to find cache object config for object ID: ${it.id}.")
                val tile = org.paradigm.engine.model.map.Region(this.regionID).toTile(plane).translate(localX, localY)
                val obj = GameObject(data, tile, shape, rotation)
                collision.addObject(obj)
            }
        }
    }

    companion object {
        private const val MAX_PLANE = 4
        private const val BLOCKED_TILE_FLAG = 0x1
        private const val BRIDGE_TILE_FLAG = 0x2
    }
}