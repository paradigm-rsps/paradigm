package org.paradigm.engine.sync.player

import org.paradigm.common.inject
import org.paradigm.engine.model.MovementState
import org.paradigm.engine.model.world.World
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.sync.SyncTask

class PlayerPostSyncTask : SyncTask {

    private val world: World by inject()

    private var prevVarps = mutableMapOf<Int, Int>()

    override suspend fun execute() {
        world.players.forEach { player ->
            player.removeVarps()
            player.updateVarps()
            player.updateFlags.clear()
            player.clearMovement()
        }
    }

    private fun Player.removeVarps() {

    }

    private fun Player.updateVarps() {

    }

    private fun Player.clearMovement() {
        teleportTile = null
        movementState = MovementState.NONE
    }
}