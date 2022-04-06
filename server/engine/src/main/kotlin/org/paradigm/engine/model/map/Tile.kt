package org.paradigm.engine.model.map

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