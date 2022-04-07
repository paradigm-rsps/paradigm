package org.paradigm.engine.model.manager

import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.map.Chunk
import org.paradigm.engine.model.map.Region
import org.paradigm.engine.model.map.Scene
import org.paradigm.engine.model.map.Tile
import org.paradigm.engine.net.packet.server.RebuildRegionNormalPacket
import kotlin.math.abs

class SceneManager(private val player: Player) {

    var baseTile: Tile = player.tile
        private set

    val regions: List<Region> get() {
        val result = mutableListOf<Region>()
        val baseChunk = baseTile.toChunk()

        val lx = (baseChunk.x - 6) / Chunk.SIZE
        val ly = (baseChunk.y - 6) / Chunk.SIZE
        val rx = (baseChunk.x + 6) / Chunk.SIZE
        val ry = (baseChunk.y + 6) / Chunk.SIZE

        for(x in lx..rx) {
            for(y in ly..ry) {
                result.add(Region(x, y))
            }
        }

        return result
    }

    fun init() {
        baseTile = player.tile
        player.session.write(RebuildRegionNormalPacket(player, gpi = true))
    }

    fun cycle() {
        if (shouldRebuild()) {
            baseTile = player.tile
            player.session.write(RebuildRegionNormalPacket(player, gpi = false))
        }
    }

    private fun shouldRebuild(): Boolean {
        return abs(baseTile.x - player.tile.x) > Scene.REBUILD_DISTANCE
                || abs(baseTile.y - player.tile.y) > Scene.REBUILD_DISTANCE
    }
}