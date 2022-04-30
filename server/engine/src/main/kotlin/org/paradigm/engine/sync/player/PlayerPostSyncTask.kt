package org.paradigm.engine.sync.player

import org.paradigm.common.inject
import org.paradigm.engine.model.MovementState
import org.paradigm.engine.model.world.World
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.net.packet.server.VarpLargePacket
import org.paradigm.engine.net.packet.server.VarpSmallPacket
import org.paradigm.engine.sync.SyncTask

class PlayerPostSyncTask : SyncTask {

    private val world: World by inject()

    override suspend fun execute() {
        world.players.forEach { player ->
            player.updateVarps()
            player.updateFlags.clear()
            player.clearMovement()
        }
    }

    private fun Player.updateVarps() {
        varps.changed.forEach { id ->
            writeVarp(id, varps.getVarp(id))
        }

        varps.changed.clear()
    }

    private fun Player.writeVarp(id: Int, value: Int) {
        val packet = when (value) {
            in Byte.MIN_VALUE..Byte.MAX_VALUE -> VarpSmallPacket(id, value)
            else -> VarpLargePacket(id, value)
        }
        session.write(packet)
    }

    private fun Player.clearMovement() {
        teleportTile = null
        movementState = MovementState.NONE
    }
}