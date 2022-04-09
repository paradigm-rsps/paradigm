package org.paradigm.engine.model.entity.pathfinder

import org.paradigm.common.inject
import org.paradigm.engine.model.World
import org.paradigm.engine.model.map.Tile
import org.rsmod.pathfinder.SmartPathFinder
import org.rsmod.pathfinder.ZoneCoords
import org.rsmod.pathfinder.ZoneFlags

class PlayerPathFinder : PathFinder {

    private val world: World by inject()

    override fun findPath(src: Tile, dest: Tile): List<Tile> {
        val flags = ZoneFlags().let {
            it.alloc(ZoneCoords(src.toChunk().x, src.toChunk().y, src.plane))
            it.flags
        }

        val pf = SmartPathFinder(defaultFlag = 0, flags = flags)
        return pf.findPath(src.x, src.y, dest.x, dest.y, src.plane).pathCoords.map {
            Tile(it.x, it.y, src.plane)
        }.toList()
    }

}