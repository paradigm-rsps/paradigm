package org.paradigm.engine.model.obj

import net.runelite.cache.definitions.ObjectDefinition
import org.paradigm.engine.model.map.Tile

class GameObject(
    val data: ObjectDefinition,
    val tile: Tile,
    val attributes: Int
) {

    constructor(
        data: ObjectDefinition,
        tile: Tile,
        shape: Int,
        rotation: Int
    ) : this(data, tile, (shape shl 2) or rotation)

    val id: Int get() = data.id

    val shape: Int get() = attributes shr 2

    val rotation: Int get() = attributes and 0x3

}