package org.paradigm.engine.model.entity.pathfinder

import org.paradigm.engine.model.map.Tile

interface PathFinder {

    fun findPath(src: Tile, dest: Tile): List<Tile>

}