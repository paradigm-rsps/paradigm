package org.paradigm.engine.model.world

import org.paradigm.engine.model.collision.CollisionMap
import org.paradigm.engine.model.map.Chunk
import org.paradigm.engine.model.obj.GameObjectMap

class WorldChunk(val packed: Int) {

    constructor(x: Int, y: Int, plane: Int) : this(
        (x and 0x7FF) or ((y and 0x7FF) shl 11) or (plane shl 22)
    )

    val x: Int get() = packed and 0x7FF

    val y: Int get() = (packed shr 11) and 0x7FF

    val plane: Int get() = (packed shr 22) and 0x3

    val collision = CollisionMap()

    val objects = GameObjectMap()

    fun toChunk() = Chunk(packed)
}