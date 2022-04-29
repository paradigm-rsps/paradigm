import org.paradigm.api.*
import org.paradigm.engine.event.impl.CheatCommandEvent
import org.paradigm.engine.event.impl.LoginEvent
import org.paradigm.engine.event.impl.MoveGameClickEvent
import org.paradigm.engine.model.map.Tile
import org.paradigm.engine.model.ui.GameInterface
import org.tinylog.kotlin.Logger

onEnable { println("enabled! fdsfasfddafd") }
onDisable { unregisterEvents() }

onEvent<LoginEvent> {
    /*
     * Open the initial gameframe interfaces
     */
    player.ui.openTopInterface(player.displayMode.interfaceId)
    GameInterface.values().filter { it.interfaceId != -1 }.forEach { gameInterface ->
        player.ui.openInterface(gameInterface)
    }

    /*
     * Update the initial appearance of the player.
     */
    player.updateAppearance()

    // Enable running by default for now.
    player.toggleRun()
}

onEvent<MoveGameClickEvent> {
    when (type) {
        0 -> player.move(tile)
        else -> player.teleport(tile)
    }
}

onEvent<CheatCommandEvent> {
    when (command) {
        "reload" -> reloadCommand()
        "tele" -> teleCommand()
    }
}

fun CheatCommandEvent.reloadCommand() {
    if (args.isEmpty()) return
    val module = moduleManager.getModule(args[0])
    if (module == null) {
        Logger.error("Failed to reload content module: ${args[0]}. Module not found.")
        return
    }
    moduleManager.reloadModule(module)
}

fun CheatCommandEvent.teleCommand() {
    if (args.isEmpty()) return
    val dest = Tile(args[0].toInt(), args[1].toInt(), if (args.size == 3) args[2].toInt() else player.tile.plane)
    player.teleport(dest)
}