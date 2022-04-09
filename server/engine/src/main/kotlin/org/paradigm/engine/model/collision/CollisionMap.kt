package org.paradigm.engine.model.collision

import org.paradigm.engine.model.map.Tile

class CollisionMap(
    private val tiles: MutableMap<Tile, CollisionTile> = mutableMapOf()
) {

    fun add(tile: Tile, mask: Int) {
        this[tile] = (this[tile] ?: 0) or mask
    }

    fun remove(tile: Tile, mask: Int) {
        this[tile] = (this[tile] ?: 0) and mask.inv()
    }

    operator fun get(tile: Tile): Int? {
        return tiles[tile]?.flags
    }

    operator fun set(tile: Tile, flags: Int) {
        tiles[tile] = CollisionTile(flags)
    }

    fun buildFlags(center: Tile, size: Int): IntArray {
        val halfSize = size / 2
        val flags = IntArray(size * size)
        val rangeX = center.x - halfSize until center.x + halfSize
        val rangeY = center.y - halfSize until center.y + halfSize
        for (y in rangeY) {
            for (x in rangeX) {
                val tile = Tile(x, y, center.plane)
                val flag = this[tile] ?: 0
                val localX = x - (center.x - halfSize)
                val localY = y - (center.y - halfSize)
                val index = (localY * size) + localX
                flags[index] = flag
            }
        }
        return flags
    }
}