package org.paradigm.engine.model.map

@JvmInline
value class Chunk(val packed: Int) {

    val x: Int get() = packed and 0x7FF

    val y: Int get() = (packed shr 11) and 0x7FF

    val plane: Int get() = (packed shr 22) and 0x3

    constructor(x: Int, y: Int, plane: Int = 0) : this(
        (x and 0x7FF) or ((y and 0x7FF) shl 11) or (plane shl 22)
    )

    fun toTile() = Tile(
        x * SIZE,
        y * SIZE,
        plane
    )

    fun toRegion() = Region(
        x / (Region.SIZE / SIZE),
        y / (Region.SIZE / SIZE)
    )

    fun toScene() = Scene(
        x / (Scene.SIZE / SIZE),
        y / (Scene.SIZE / SIZE)
    )

    companion object {
        const val SIZE = 8
    }
}