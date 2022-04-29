package org.paradigm.api

import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.entity.update.PlayerUpdateFlag
import org.paradigm.engine.model.map.Tile

fun Player.updateAppearance() {
    updateFlags.add(PlayerUpdateFlag.APPEARANCE)
}

fun Player.updateMovement() {
    updateFlags.add(PlayerUpdateFlag.MOVEMENT)
}

fun Player.updateMovementMode() {
    updateFlags.add(PlayerUpdateFlag.MOVEMENT_MODE)
}

fun Player.toggleRun() {
    running = !running
    updateMovementMode()
}

fun Player.teleport(tile: Tile) {
    teleportTile = tile
    updateMovement()
}

fun Player.move(tile: Tile) {
    path = pathfinder.findPath(this.tile, tile)
}