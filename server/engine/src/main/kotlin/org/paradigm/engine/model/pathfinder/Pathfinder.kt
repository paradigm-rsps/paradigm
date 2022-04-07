package org.paradigm.engine.model.pathfinder

import org.paradigm.engine.model.map.Tile

interface Pathfinder {

    fun calculatePath(start: Tile, dest: Tile): MutableList<Tile>
}