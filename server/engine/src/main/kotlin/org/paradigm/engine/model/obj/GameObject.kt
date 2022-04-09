package org.paradigm.engine.model.obj

import org.paradigm.cache.config.ObjectConfig
import org.paradigm.engine.model.map.Tile

class GameObject(
    val config: ObjectConfig,
    val tile: Tile,
    val attributes: Int
) {
    val id: Int get() = config.id
    val shape: Int get() = attributes shr 2
    val rotation: Int get() = attributes and 0x3

    constructor(
        config: ObjectConfig,
        tile: Tile,
        shape: Int,
        rotation: Int
    ) : this(config, tile, (shape shl 2) or rotation)


}