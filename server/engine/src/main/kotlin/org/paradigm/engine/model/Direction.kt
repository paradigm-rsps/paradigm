package org.paradigm.engine.model

import org.paradigm.engine.model.map.Tile

enum class Direction(val mask: Int, val stepX: Int, val stepY: Int) {
    NONE(0x0, 0, 0),
    NORTH(0x1, 0, 1),
    EAST(0x2, 1, 0),
    SOUTH(0x4, 0, -1),
    WEST(0x8, -1, 0),
    NORTH_EAST(NORTH.mask or EAST.mask, EAST.stepX, NORTH.stepY),
    SOUTH_EAST(SOUTH.mask or EAST.mask, EAST.stepX, SOUTH.stepY),
    NORTH_WEST(NORTH.mask or WEST.mask, WEST.stepX, NORTH.stepY),
    SOUTH_WEST(SOUTH.mask or WEST.mask, WEST.stepX, SOUTH.stepY);

    companion object {

        fun between(source: Tile, target: Tile): Direction {
            val dx = target.x - source.x
            when (target.y - source.y) {
                1 -> when (dx) {
                    1 -> return NORTH_EAST
                    0 -> return NORTH
                    -1 -> return NORTH_WEST
                }
                -1 -> when (dx) {
                    1 -> return SOUTH_EAST
                    0 -> return SOUTH
                    -1 -> return SOUTH_WEST
                }
                0 -> when (dx) {
                    1 -> return EAST
                    0 -> return NONE
                    -1 -> return WEST
                }
            }
            throw IllegalArgumentException("Unable to get a direction between the provided tiles.")
        }
    }
}