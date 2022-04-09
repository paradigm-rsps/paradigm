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
            val dy = target.y - source.y
            return when {
                dx == 0 && dy == 0 -> NONE
                dx > 0 && dy > 0 -> NORTH_EAST
                dx > 0 && dy == 0 -> EAST
                dx > 0 && dy < 0 -> SOUTH_EAST
                dx < 0 && dy > 0 -> NORTH_WEST
                dx < 0 && dy == 0 -> WEST
                dx < 0 && dy < 0 -> SOUTH_WEST
                dx == 0 && dy > 0 -> NORTH
                else -> SOUTH
            }
        }
    }
}