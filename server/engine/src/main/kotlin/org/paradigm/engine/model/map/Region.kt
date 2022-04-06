package org.paradigm.engine.model.map

import org.paradigm.config.XteaConfig

@JvmInline
value class Region(val id: Int) {

    val x: Int get() = id shr 8

    val y: Int get() = id and 0xFF

    val xteas get() = XteaConfig.getRegionKey(id)

    constructor(x: Int, y: Int) : this(
        (x shl 8) or y
    )

    fun toTile(plane: Int = 0) = Tile(
        x * SIZE,
        y * SIZE,
        plane
    )

    fun toChunk(plane: Int = 0) = Chunk(
        x * (SIZE / Chunk.SIZE),
        y * (SIZE / Chunk.SIZE),
        plane
    )

    fun toScene() = Scene(
        x / (Scene.SIZE / SIZE),
         y / (Scene.SIZE / SIZE)
    )

    companion object {
        const val SIZE = 64
    }
}