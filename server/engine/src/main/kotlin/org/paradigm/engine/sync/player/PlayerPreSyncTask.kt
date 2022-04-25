package org.paradigm.engine.sync.player

import org.paradigm.common.inject
import org.paradigm.engine.model.Direction
import org.paradigm.engine.model.MovementState
import org.paradigm.engine.model.world.World
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.entity.update.PlayerUpdateFlag
import org.paradigm.engine.sync.SyncTask

class PlayerPreSyncTask : SyncTask {

    private val world: World by inject()

    override suspend fun execute() {
        world.players.forEach { player ->
            player.updateMovement()
            player.scene.cycle()
        }
    }

    private fun Player.updateMovement() {
        prevTile = tile
        when {
            teleportTile != null -> doTeleport()
            path.isNotEmpty() -> doStep()
        }
    }

    private fun Player.doStep() {
        when {
            running -> when {
                path.size == 1 -> {
                    movementState = MovementState.WALK
                    updateFlags.add(PlayerUpdateFlag.MOVEMENT)
                    followTile = tile
                    tile = path.removeAt(0)
                }
                path.size > 1 && tile.isWithinRadius(path[1], 1) -> {
                    movementState = MovementState.WALK
                    followTile = path.removeAt(0)
                    tile = path.removeAt(0)
                }
                else -> {
                    movementState = MovementState.RUN
                    followTile = path.removeAt(0)
                    tile = path.removeAt(0)
                }
            }
            else -> {
                movementState = MovementState.WALK
                followTile = tile
                tile = path.removeAt(0)
            }
        }
        direction = followTile.directionTo(tile)
    }

    private fun Player.doTeleport() {
        path.clear()
        movementState = MovementState.TELEPORT
        updateFlags.add(PlayerUpdateFlag.MOVEMENT)
        tile = teleportTile!!
        followTile = teleportTile!!.translate(Direction.SOUTH)
    }
}