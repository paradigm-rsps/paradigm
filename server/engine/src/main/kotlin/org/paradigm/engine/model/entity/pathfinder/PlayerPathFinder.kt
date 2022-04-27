package org.paradigm.engine.model.entity.pathfinder

import org.paradigm.common.inject
import org.paradigm.engine.model.collision.CollisionMap
import org.paradigm.engine.model.world.World
import org.paradigm.engine.model.map.Tile
import org.rsmod.pathfinder.SmartPathFinder
import org.rsmod.pathfinder.ZoneCoords
import org.rsmod.pathfinder.ZoneFlags

class PlayerPathFinder : PathFinder {

    private val world: World by inject()

    override fun findPath(src: Tile, dest: Tile): List<Tile> {
        val pf = SmartPathFinder(
            defaultFlag = 0,
            flags = world.chunks.map { it.collision.flags.values.toIntArray() }.toTypedArray()
        )
        return pf.findPath(src.x, src.y, dest.x, dest.y, src.plane).pathCoords.map {
            Tile(it.x, it.y, src.plane)
        }.toList()
    }

}