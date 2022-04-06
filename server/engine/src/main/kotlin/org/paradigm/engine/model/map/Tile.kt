package org.paradigm.engine.model.map

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.sqrt

@JvmInline
value class Tile(val packed: Int) {

    val x: Int get() = packed and 0x7FFF

    val y: Int get() = (packed shr 15) and 0x7FFF

    val plane: Int get() = (packed shr 30) and 0x3

    fun to30BitInteger(): Int = (y and 0x3FFF) or ((x and 0x3FFF) shl 14) or ((plane and 0x3) shl 28)

    fun to18BitInteger(): Int = (y shr 13) or ((x shr 13) shl 8) or ((plane and 0x3) shl 16)

    constructor(x: Int, y: Int, plane: Int = 0) : this(
        (x and 0x7FFF) or ((y and 0x7FFF) shl 15) or (plane shl 30)
    )

    fun isWithinRadius(x: Int, y: Int, plane: Int, radius: Int): Boolean {
        if (this.plane != plane) return false
        val dx = abs(this.x - x)
        val dy = abs(this.y - y)
        return dx <= radius && dy <= radius
    }

    fun isWithinRadius(other: Tile, radius: Int): Boolean = isWithinRadius(other.x, other.y, other.plane, radius)

    fun distanceTo(x: Int, y: Int): Int {
        val dx = this.x - x
        val dy = this.y - y
        return ceil(sqrt((dx * dx + dy * dy).toDouble())).toInt()
    }

    fun distanceTo(other: Tile): Int {
        if (this.plane != other.plane) throw IllegalArgumentException("Can not calculate distance to tiles on different planes.")
        return distanceTo(other.x, other.y)
    }

    fun deltaTo(x: Int, y: Int): Int = abs(this.x - x) + abs(this.y - y)

    fun deltaTo(other: Tile): Int = deltaTo(other.x, other.y)

    fun toChunk() = Chunk(
        x / Chunk.SIZE,
        y / Chunk.SIZE,
        plane
    )

    fun toRegion() = Region(
        x / Region.SIZE,
        y / Region.SIZE
    )

    fun toScene() = Scene(
        x / Scene.SIZE,
        y / Scene.SIZE
    )

    companion object {
        const val SIZE = 1
    }
}