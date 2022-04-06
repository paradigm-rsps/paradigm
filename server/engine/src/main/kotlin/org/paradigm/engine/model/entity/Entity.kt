package org.paradigm.engine.model.entity

import org.paradigm.common.inject
import org.paradigm.engine.Engine
import org.paradigm.engine.model.World
import org.paradigm.engine.model.map.Tile

abstract class Entity {

    val engine: Engine by inject()
    val world: World by inject()

    var index: Int = -1

    open var tile: Tile = Tile(0, 0, 0)

}