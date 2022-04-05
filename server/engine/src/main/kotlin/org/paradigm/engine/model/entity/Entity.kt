package org.paradigm.engine.model.entity

import org.paradigm.engine.model.map.Tile

abstract class Entity {

    var index: Int = -1

    open var tile: Tile = Tile(0, 0, 0)
}