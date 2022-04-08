import org.paradigm.api.onEvent
import org.paradigm.api.type.ClientScript
import org.paradigm.api.updateAppearance
import org.paradigm.engine.event.impl.LoginEvent
import org.paradigm.engine.event.impl.MoveGameClickEvent
import org.paradigm.engine.model.MovementState
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.entity.update.PlayerUpdateFlag
import org.paradigm.engine.model.map.Tile
import org.paradigm.engine.model.ui.GameInterface

onEvent<LoginEvent> {
    player.updateAppearance()
    player.ui.openTopInterface(player.displayMode.interfaceId)
    GameInterface.values().filter { it.interfaceId != -1 && it != GameInterface.CHAT_BOX }.forEach { gameInterface ->
        player.ui.openInterface(gameInterface)
    }
    player.runClientScript(ClientScript.SET_RESIZABLE_MODE, 1)
    player.toggleRun()
}

onEvent<MoveGameClickEvent> {
    when (type) {
        0 -> player.moveTo(tile)
        else -> {
            if (player.privilege.id >= 2) {
                player.teleport(tile)
            } else {
                player.moveTo(tile)
            }
        }
    }
}

fun Player.teleport(tile: Tile) {
    updateFlags.add(PlayerUpdateFlag.MOVEMENT)
    teleportTile = tile
    movementState = MovementState.TELEPORT
}

fun Player.moveTo(tile: Tile) {
    pathfinder.cancelled = true
    path = pathfinder.calculatePath(this.tile, tile)
}

fun Player.toggleRun() {
    running = !running
    updateFlags.add(PlayerUpdateFlag.MOVEMENT_MODE)
}