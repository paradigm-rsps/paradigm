package org.paradigm.engine.model.collision

import org.paradigm.engine.model.map.Chunk
import org.paradigm.engine.model.map.Tile
import org.paradigm.engine.model.obj.GameObject
import org.paradigm.engine.model.obj.ObjectShape
import org.rsmod.pathfinder.flag.CollisionFlag

class CollisionMap {

    val flags: Array<IntArray?> = arrayOfNulls(2048 * 2048 * 4)

    fun alloc(chunk: Chunk): IntArray {
        val packed = chunk.packed
        val current = flags[packed]
        if (current != null) return current
        val new = IntArray(8 * 8)
        flags[packed] = new
        return new
    }

    fun destroy(chunk: Chunk) {
        flags[chunk.packed] = null
    }

    operator fun get(x: Int, y: Int, plane: Int, default: Int = 0): Int {
        val chunk = Chunk(x shr 3, y shr 3, plane)
        val array = flags[chunk.packed] ?: return default
        return array[chunkIndex(x, y)]
    }

    operator fun get(tile: Tile, default: Int = 0): Int = get(tile.x, tile.y, tile.plane, default)

    operator fun set(x: Int, y: Int, plane: Int, flag: Int) {
        alloc(Chunk(x shr 3, y shr 3, plane))[chunkIndex(x, y)] = flag
    }

    operator fun set(tile: Tile, flag: Int) {
        set(tile.x, tile.y, tile.plane, flag)
    }

    fun add(x: Int, y: Int, plane: Int, flag: Int) {
        val flags = alloc(Chunk(x shr 3, y shr 3, plane))
        val index = chunkIndex(x, y)
        val current = flags[index]
        flags[index] = current or flag
    }

    fun add(tile: Tile, flag: Int) {
        add(tile.x, tile.y, tile.plane, flag)
    }

    fun remove(x: Int, y: Int, plane: Int, flag: Int) {
        val flags = alloc(Chunk(x shr 3, y shr 3, plane))
        val index = chunkIndex(x, y)
        val current = flags[index]
        flags[index] = current and flag.inv()
    }

    fun remove(tile: Tile, flag: Int) {
        remove(tile.x, tile.y, tile.plane, flag)
    }

    private fun chunkIndex(x: Int, y: Int): Int = (x and 0x7) or ((y and 0x7) shl 3)

    /**
     * === OBJECT COLLISION METHODS ===
     */

    fun addObject(obj: GameObject) {
        changeObject(obj, add = true)
    }

    fun removeObject(obj: GameObject) {
        changeObject(obj, add = false)
    }

    fun addFloorDecor(tile: Tile) {
        changeFloorDecor(tile, true)
    }

    private fun changeObject(obj: GameObject, add: Boolean) {
        val config = obj.config
        val tile = obj.tile
        val shape = obj.shape
        val rotation = obj.rotation
        val clipType = config.clipType
        val blockPath = config.blockPath
        val blockProjectile = config.blockProjectile

        if (shape in ObjectShape.WALL_SHAPES && clipType != 0) {
            changeWall(tile, rotation, shape, blockProjectile, add)
        } else if (shape in ObjectShape.NORMAL_SHAPES && clipType != 0) {
            var width = config.width.toInt()
            var length = config.length.toInt()
            if (rotation == 1 || rotation == 3) {
                width = config.length.toInt()
                length = config.width.toInt()
            }
            changeNormal(tile, width, length, blockPath, blockProjectile, add)
        } else if (shape in ObjectShape.GROUND_DECOR_SHAPES && clipType == 1) {
            changeFloorDecor(tile, add)
        }
    }

    private fun changeWall(
        tile: Tile,
        rotation: Int,
        shape: Int,
        blockProjectile: Boolean,
        add: Boolean
    ) {
        when (shape) {
            ObjectShape.WALL -> {
                when (rotation) {
                    0 -> {
                        change(tile, CollisionFlag.WALL_WEST, add)
                        change(tile.translate(-1, 0), CollisionFlag.WALL_EAST, add)
                        if (blockProjectile) {
                            change(tile, CollisionFlag.WALL_WEST_PROJECTILE_BLOCKER, add)
                            change(tile.translate(-1, 0), CollisionFlag.WALL_EAST_PROJECTILE_BLOCKER, add)
                        }
                    }
                    1 -> {
                        change(tile, CollisionFlag.WALL_NORTH, add)
                        change(tile.translate(0, 1), CollisionFlag.WALL_SOUTH, add)
                        if (blockProjectile) {
                            change(tile, CollisionFlag.WALL_NORTH_PROJECTILE_BLOCKER, add)
                            change(tile.translate(0, 1), CollisionFlag.WALL_SOUTH_PROJECTILE_BLOCKER, add)
                        }
                    }
                    2 -> {
                        change(tile, CollisionFlag.WALL_EAST, add)
                        change(tile.translate(1, 0), CollisionFlag.WALL_WEST, add)
                        if (blockProjectile) {
                            change(tile, CollisionFlag.WALL_EAST_PROJECTILE_BLOCKER, add)
                            change(tile.translate(1, 0), CollisionFlag.WALL_WEST_PROJECTILE_BLOCKER, add)
                        }
                    }
                    3 -> {
                        change(tile, CollisionFlag.WALL_SOUTH, add)
                        change(tile.translate(0, -1), CollisionFlag.WALL_NORTH, add)
                        if (blockProjectile) {
                            change(tile, CollisionFlag.WALL_SOUTH_PROJECTILE_BLOCKER, add)
                            change(tile.translate(0, -1), CollisionFlag.WALL_NORTH_PROJECTILE_BLOCKER, add)
                        }
                    }
                }
            }
            ObjectShape.WALL_CORNER_DIAG, ObjectShape.WALL_CORNER -> {
                when (rotation) {
                    0 -> {
                        change(tile, CollisionFlag.WALL_NORTH_WEST, add)
                        change(tile.translate(-1, 1), CollisionFlag.WALL_SOUTH_EAST, add)
                        if (blockProjectile) {
                            change(tile, CollisionFlag.WALL_NORTH_WEST_PROJECTILE_BLOCKER, add)
                            change(tile.translate(-1, 1), CollisionFlag.WALL_SOUTH_EAST_PROJECTILE_BLOCKER, add)
                        }
                    }
                    1 -> {
                        change(tile, CollisionFlag.WALL_NORTH_EAST, add)
                        change(tile.translate(1, 1), CollisionFlag.WALL_SOUTH_WEST, add)
                        if (blockProjectile) {
                            change(tile, CollisionFlag.WALL_NORTH_EAST_PROJECTILE_BLOCKER, add)
                            change(tile.translate(1, 1), CollisionFlag.WALL_SOUTH_WEST_PROJECTILE_BLOCKER, add)
                        }
                    }
                    2 -> {
                        change(tile, CollisionFlag.WALL_SOUTH_EAST, add)
                        change(tile.translate(1, -1), CollisionFlag.WALL_NORTH_WEST, add)
                        if (blockProjectile) {
                            change(tile, CollisionFlag.WALL_SOUTH_EAST_PROJECTILE_BLOCKER, add)
                            change(tile.translate(1, -1), CollisionFlag.WALL_NORTH_WEST_PROJECTILE_BLOCKER, add)
                        }
                    }
                    3 -> {
                        change(tile, CollisionFlag.WALL_SOUTH_WEST, add)
                        change(tile.translate(-1, -1), CollisionFlag.WALL_NORTH_EAST, add)
                        if (blockProjectile) {
                            change(tile, CollisionFlag.WALL_SOUTH_WEST_PROJECTILE_BLOCKER, add)
                            change(tile.translate(-1, -1), CollisionFlag.WALL_NORTH_EAST_PROJECTILE_BLOCKER, add)
                        }
                    }
                }
            }
            ObjectShape.UNFINISHED_WALL -> {
                when (rotation) {
                    0 -> {
                        change(tile, CollisionFlag.WALL_WEST or CollisionFlag.WALL_NORTH, add)
                        change(tile.translate(-1, 0), CollisionFlag.WALL_EAST, add)
                        change(tile.translate(0, 1), CollisionFlag.WALL_SOUTH, add)
                        if (blockProjectile) {
                            val flag =
                                CollisionFlag.WALL_WEST_PROJECTILE_BLOCKER or CollisionFlag.WALL_NORTH_PROJECTILE_BLOCKER
                            change(tile, flag, add)
                            change(tile.translate(-1, 0), CollisionFlag.WALL_EAST_PROJECTILE_BLOCKER, add)
                            change(tile.translate(0, 1), CollisionFlag.WALL_SOUTH_PROJECTILE_BLOCKER, add)
                        }
                    }
                    1 -> {
                        change(tile, CollisionFlag.WALL_NORTH or CollisionFlag.WALL_EAST, add)
                        change(tile.translate(0, 1), CollisionFlag.WALL_SOUTH, add)
                        change(tile.translate(1, 0), CollisionFlag.WALL_WEST, add)
                        if (blockProjectile) {
                            val flag =
                                CollisionFlag.WALL_NORTH_PROJECTILE_BLOCKER or CollisionFlag.WALL_EAST_PROJECTILE_BLOCKER
                            change(tile, flag, add)
                            change(tile.translate(0, 1), CollisionFlag.WALL_SOUTH_PROJECTILE_BLOCKER, add)
                            change(tile.translate(1, 0), CollisionFlag.WALL_WEST_PROJECTILE_BLOCKER, add)
                        }
                    }
                    2 -> {
                        change(tile, CollisionFlag.WALL_EAST or CollisionFlag.WALL_SOUTH, add)
                        change(tile.translate(1, 0), CollisionFlag.WALL_WEST, add)
                        change(tile.translate(0, -1), CollisionFlag.WALL_NORTH, add)
                        if (blockProjectile) {
                            val flag =
                                CollisionFlag.WALL_EAST_PROJECTILE_BLOCKER or CollisionFlag.WALL_SOUTH_PROJECTILE_BLOCKER
                            change(tile, flag, add)
                            change(tile.translate(1, 0), CollisionFlag.WALL_WEST_PROJECTILE_BLOCKER, add)
                            change(tile.translate(0, -1), CollisionFlag.WALL_NORTH_PROJECTILE_BLOCKER, add)
                        }
                    }
                    3 -> {
                        change(tile, CollisionFlag.WALL_SOUTH or CollisionFlag.WALL_WEST, add)
                        change(tile.translate(0, -1), CollisionFlag.WALL_NORTH, add)
                        change(tile.translate(-1, 0), CollisionFlag.WALL_EAST, add)
                        if (blockProjectile) {
                            val flag =
                                CollisionFlag.WALL_SOUTH_PROJECTILE_BLOCKER or CollisionFlag.WALL_WEST_PROJECTILE_BLOCKER
                            change(tile, flag, add)
                            change(tile.translate(0, -1), CollisionFlag.WALL_NORTH_PROJECTILE_BLOCKER, add)
                            change(tile.translate(-1, 0), CollisionFlag.WALL_EAST_PROJECTILE_BLOCKER, add)
                        }
                    }
                }
            }
        }
    }

    private fun changeNormal(
        tile: Tile,
        width: Int,
        length: Int,
        blockPath: Boolean,
        blockProjectile: Boolean,
        add: Boolean
    ) {
        for (x in 0 until width) {
            for (y in 0 until length) {
                val translatedTile = tile.translate(x, y)
                change(translatedTile, CollisionFlag.OBJECT, add)
                if (blockProjectile) {
                    change(translatedTile, CollisionFlag.OBJECT_PROJECTILE_BLOCKER, add)
                }
                if (blockPath) {
                    change(translatedTile, CollisionFlag.OBJECT_ROUTE_BLOCKER, add)
                }
            }
        }
    }

    private fun changeFloorDecor(tile: Tile, add: Boolean) {
        change(tile, CollisionFlag.FLOOR_DECORATION, add)
    }

    private fun change(tile: Tile, mask: Int, add: Boolean) = when (add) {
        true -> add(tile, mask)
        false -> remove(tile, mask)
    }
}