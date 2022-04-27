package org.paradigm.engine.model.collision

import org.paradigm.engine.model.map.Tile
import org.paradigm.engine.model.obj.ObjectShape
import org.rsmod.pathfinder.flag.CollisionFlag

class CollisionMap(
    val flags: MutableMap<Tile, Int> = mutableMapOf()
) {

    fun add(tile: Tile, mask: Int) {
        val old = flags[tile] ?: 0
        this[tile] = old or mask
    }

    fun remove(tile: Tile, mask: Int) {
        val old = flags[tile] ?: 0
        this[tile] = old and mask.inv()
    }

    operator fun get(tile: Tile): Int? {
        return flags[tile]
    }

    operator fun set(tile: Tile, flags: Int) {
        this.flags[tile] = flags
    }

    private fun changeNormal(
        coords: Tile,
        width: Int, length: Int,
        blockPath: Boolean,
        blockProjectile: Boolean,
        add: Boolean
    ) {
        for (x in 0 until width) {
            for (y in 0 until length) {
                val translate = coords.translate(x, y)
                change(translate, CollisionFlag.OBJECT, add)
                if (blockProjectile) {
                    change(translate, CollisionFlag.OBJECT_PROJECTILE_BLOCKER, add)
                }
                if (blockPath) {
                    change(translate, CollisionFlag.OBJECT_ROUTE_BLOCKER, add)
                }
            }
        }
    }

    private fun changeWall(
        coords: Tile,
        rotation: Int,
        shape: Int,
        blockProjectile: Boolean,
        add: Boolean
    ) {
        if (shape == ObjectShape.WALL) {
            when (rotation) {
                0 -> {
                    change(coords, CollisionFlag.WALL_WEST, add)
                    change(coords.translate(-1, 0), CollisionFlag.WALL_EAST, add)
                    if (blockProjectile) {
                        change(coords, CollisionFlag.WALL_WEST_PROJECTILE_BLOCKER, add)
                        change(coords.translate(-1, 0), CollisionFlag.WALL_EAST_PROJECTILE_BLOCKER, add)
                    }
                }
                1 -> {
                    change(coords, CollisionFlag.WALL_NORTH, add)
                    change(coords.translate(0, 1), CollisionFlag.WALL_SOUTH, add)
                    if (blockProjectile) {
                        change(coords, CollisionFlag.WALL_NORTH_PROJECTILE_BLOCKER, add)
                        change(coords.translate(0, 1), CollisionFlag.WALL_SOUTH_PROJECTILE_BLOCKER, add)
                    }
                }
                2 -> {
                    change(coords, CollisionFlag.WALL_EAST, add)
                    change(coords.translate(1, 0), CollisionFlag.WALL_WEST, add)
                    if (blockProjectile) {
                        change(coords, CollisionFlag.WALL_EAST_PROJECTILE_BLOCKER, add)
                        change(coords.translate(1, 0), CollisionFlag.WALL_WEST_PROJECTILE_BLOCKER, add)
                    }
                }
                3 -> {
                    change(coords, CollisionFlag.WALL_SOUTH, add)
                    change(coords.translate(0, -1), CollisionFlag.WALL_NORTH, add)
                    if (blockProjectile) {
                        change(coords, CollisionFlag.WALL_SOUTH_PROJECTILE_BLOCKER, add)
                        change(coords.translate(0, -1), CollisionFlag.WALL_NORTH_PROJECTILE_BLOCKER, add)
                    }
                }
            }
        } else if (shape == ObjectShape.WALL_CORNER_DIAG || shape == ObjectShape.WALL_CORNER) {
            when (rotation) {
                0 -> {
                    change(coords, CollisionFlag.WALL_NORTH_WEST, add)
                    change(coords.translate(-1, 1), CollisionFlag.WALL_SOUTH_EAST, add)
                    if (blockProjectile) {
                        change(coords, CollisionFlag.WALL_NORTH_WEST_PROJECTILE_BLOCKER, add)
                        change(coords.translate(-1, 1), CollisionFlag.WALL_SOUTH_EAST_PROJECTILE_BLOCKER, add)
                    }
                }
                1 -> {
                    change(coords, CollisionFlag.WALL_NORTH_EAST, add)
                    change(coords.translate(1, 1), CollisionFlag.WALL_SOUTH_WEST, add)
                    if (blockProjectile) {
                        change(coords, CollisionFlag.WALL_NORTH_EAST_PROJECTILE_BLOCKER, add)
                        change(coords.translate(1, 1), CollisionFlag.WALL_SOUTH_WEST_PROJECTILE_BLOCKER, add)
                    }
                }
                2 -> {
                    change(coords, CollisionFlag.WALL_SOUTH_EAST, add)
                    change(coords.translate(1, -1), CollisionFlag.WALL_NORTH_WEST, add)
                    if (blockProjectile) {
                        change(coords, CollisionFlag.WALL_SOUTH_EAST_PROJECTILE_BLOCKER, add)
                        change(coords.translate(1, -1), CollisionFlag.WALL_NORTH_WEST_PROJECTILE_BLOCKER, add)
                    }
                }
                3 -> {
                    change(coords, CollisionFlag.WALL_SOUTH_WEST, add)
                    change(coords.translate(-1, -1), CollisionFlag.WALL_NORTH_EAST, add)
                    if (blockProjectile) {
                        change(coords, CollisionFlag.WALL_SOUTH_WEST_PROJECTILE_BLOCKER, add)
                        change(coords.translate(-1, -1), CollisionFlag.WALL_NORTH_EAST_PROJECTILE_BLOCKER, add)
                    }
                }
            }
        } else if (shape == ObjectShape.UNFINISHED_WALL) {
            when (rotation) {
                0 -> {
                    change(coords, CollisionFlag.WALL_WEST or CollisionFlag.WALL_NORTH, add)
                    change(coords.translate(-1, 0), CollisionFlag.WALL_EAST, add)
                    change(coords.translate(0, 1), CollisionFlag.WALL_SOUTH, add)
                    if (blockProjectile) {
                        val flag = CollisionFlag.WALL_WEST_PROJECTILE_BLOCKER or
                                CollisionFlag.WALL_NORTH_PROJECTILE_BLOCKER
                        change(coords, flag, add)
                        change(coords.translate(-1, 0), CollisionFlag.WALL_EAST_PROJECTILE_BLOCKER, add)
                        change(coords.translate(0, 1), CollisionFlag.WALL_SOUTH_PROJECTILE_BLOCKER, add)
                    }
                }
                1 -> {
                    change(coords, CollisionFlag.WALL_NORTH or CollisionFlag.WALL_EAST, add)
                    change(coords.translate(0, 1), CollisionFlag.WALL_SOUTH, add)
                    change(coords.translate(1, 0), CollisionFlag.WALL_WEST, add)
                    if (blockProjectile) {
                        val flag = CollisionFlag.WALL_NORTH_PROJECTILE_BLOCKER or
                                CollisionFlag.WALL_EAST_PROJECTILE_BLOCKER
                        change(coords, flag, add)
                        change(coords.translate(0, 1), CollisionFlag.WALL_SOUTH_PROJECTILE_BLOCKER, add)
                        change(coords.translate(1, 0), CollisionFlag.WALL_WEST_PROJECTILE_BLOCKER, add)
                    }
                }
                2 -> {
                    change(coords, CollisionFlag.WALL_EAST or CollisionFlag.WALL_SOUTH, add)
                    change(coords.translate(1, 0), CollisionFlag.WALL_WEST, add)
                    change(coords.translate(0, -1), CollisionFlag.WALL_NORTH, add)
                    if (blockProjectile) {
                        val flag = CollisionFlag.WALL_EAST_PROJECTILE_BLOCKER or
                                CollisionFlag.WALL_SOUTH_PROJECTILE_BLOCKER
                        change(coords, flag, add)
                        change(coords.translate(1, 0), CollisionFlag.WALL_WEST_PROJECTILE_BLOCKER, add)
                        change(coords.translate(0, -1), CollisionFlag.WALL_NORTH_PROJECTILE_BLOCKER, add)
                    }
                }
                3 -> {
                    change(coords, CollisionFlag.WALL_SOUTH or CollisionFlag.WALL_WEST, add)
                    change(coords.translate(0, -1), CollisionFlag.WALL_NORTH, add)
                    change(coords.translate(-1, 0), CollisionFlag.WALL_EAST, add)
                    if (blockProjectile) {
                        val flag = CollisionFlag.WALL_SOUTH_PROJECTILE_BLOCKER or
                                CollisionFlag.WALL_WEST_PROJECTILE_BLOCKER
                        change(coords, flag, add)
                        change(coords.translate(0, -1), CollisionFlag.WALL_NORTH_PROJECTILE_BLOCKER, add)
                        change(coords.translate(-1, 0), CollisionFlag.WALL_EAST_PROJECTILE_BLOCKER, add)
                    }
                }
            }
        }
    }

    private fun changeFloorDecor(coords: Tile, add: Boolean) {
        change(coords, CollisionFlag.FLOOR_DECORATION, add)
    }

    private fun change(coords: Tile, mask: Int, add: Boolean) {
        if (add) {
            add(coords, mask)
        } else {
            remove(coords, mask)
        }
    }
}