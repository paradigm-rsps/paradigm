package org.paradigm.engine.model.world

import org.paradigm.cache.GameCache
import org.paradigm.common.inject

import org.paradigm.engine.Engine
import org.tinylog.kotlin.Logger

object WorldLoader {

    private val engine: Engine by inject()
    private val world: World by inject()
    private val cache: GameCache by inject()

    private const val MAX_PLANE = 4
    private const val BLOCKED_TILE_FLAG = 0x1
    private const val BRIDGE_TILE_FLAG = 0x2

    internal fun loadRegions() {
        Logger.info("Loading game world data from cache.")
    }

    /*private fun Region.load(map: ByteBuf, loc: ByteBuf) {
        val floorMask = mutableMapOf<Tile, Int>()

        /*
         * Decode the region cache map opcodes for floor mask values.
         */
        for (plane in 0 until MAX_PLANE) {
            for (x in 0 until Region.SIZE) {
                for (y in 0 until Region.SIZE) {
                    while (map.isReadable) {
                        val opcode = map.readUnsignedByte().toInt()
                        if (opcode == 0) {
                            break
                        }
                        if (opcode == 1) {
                            map.readByte()
                            break
                        }
                        if (opcode <= 49) {
                            map.readByte()
                        } else if (opcode <= 89) {
                            floorMask[Tile(x, y, plane)] = (opcode - 49)
                        }
                    }
                }
            }
        }

        /*
         * Construct the region terrain collision from tile blocking flags.
         */
        for (plane in 0 until MAX_PLANE) {
            for (x in 0 until Region.SIZE) {
                for (y in 0 until Region.SIZE) {
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
                        val tile = this.toTile(plane).translate(x, y)
                        world.collision.add(tile, CollisionFlag.FLOOR)
                    }
                }
            }
        }

        /*
         * Decode the region static game objects from the cache loc file data.
         * Constructs the collision of the static objects in the collision map of the
         * world as well.
         */
        var objectId = -1
        while (loc.isReadable) {
            val offset = loc.readIncrShortSmart()
            if (offset == 0) {
                return
            }
            var packed = 0
            objectId += offset
            while (loc.isReadable) {
                val diff = loc.readUnsignedShortSmart().toInt()
                if (diff == 0) {
                    break
                }
                packed += diff - 1
                val attributes = loc.readUnsignedByte().toInt()
                val localX = (packed shr 6) and 0x3F
                val localY = packed and 0x3F
                if (localX !in 0 until Region.SIZE || localY !in 0 until Region.SIZE) {
                    continue
                }
                val shape = attributes shr 2
                val rotation = attributes and 0x3
                var plane = (packed shr 12) and 0x3
                val localTile = Tile(localX, localY, 1)
                val floor = floorMask[localTile] ?: 0
                if ((floor and BRIDGE_TILE_FLAG) == BRIDGE_TILE_FLAG) {
                    plane--
                }
                if (plane >= 0) {
                    val config = cache.configArchive.objectConfigs[objectId]
                        ?: throw FileNotFoundException("Failed to find cache object config for object ID: $objectId.")
                    val tile = this.toTile(plane).translate(localX, localY)
                    val obj = GameObject(config, tile, shape, rotation)
                    world.collision.addObject(obj)
                    world.objects.addStatic(obj)
                }
            }
        }
    }*/

}