import org.paradigm.api.onEvent
import org.paradigm.api.type.ClientScript
import org.paradigm.api.type.Varbit
import org.paradigm.api.updateAppearance
import org.paradigm.engine.event.impl.LoginEvent
import org.paradigm.engine.event.impl.MoveGameClickEvent
import org.paradigm.engine.event.impl.CheatCommandEvent
import org.paradigm.engine.model.MovementState
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.entity.update.PlayerUpdateFlag
import org.paradigm.engine.model.map.Tile
import org.paradigm.engine.model.ui.GameInterface

onEvent<LoginEvent> {
    player.updateAppearance()
    player.ui.openTopInterface(player.displayMode.interfaceId)
    GameInterface.values().filter { it.interfaceId != -1 }.forEach { gameInterface ->
        player.ui.openInterface(gameInterface)
    }
    player.varps.updateVarbit(Varbit.HAS_DISPLAY_NAME, 1)
    player.runClientScript(ClientScript.SET_RESIZABLE_MODE, 1)
    player.toggleRun()
}

onEvent<MoveGameClickEvent> {
    when (type) {
        0 -> player.moveTo(tile)
        else -> {
            player.teleport(tile)
        }
    }
}

onEvent<CheatCommandEvent>()
    .where { command == "tele" && player.privilege.id > 1 }
    .then {
        val targetTile = Tile(args[0].toInt(), args[1].toInt(), if (args.size == 3) args[2].toInt() else 0)
        player.teleport(targetTile)
    }

fun Player.teleport(tile: Tile) {
    teleportTile = tile
    movementState = MovementState.TELEPORT
}

fun Player.moveTo(tile: Tile) {
    path = pathfinder.findPath(this.tile, tile).toMutableList()
}

fun Player.toggleRun() {
    running = !running
    updateFlags.add(PlayerUpdateFlag.MOVEMENT_MODE)
}