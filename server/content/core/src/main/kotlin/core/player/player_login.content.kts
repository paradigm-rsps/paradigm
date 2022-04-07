import org.paradigm.api.onEvent
import org.paradigm.api.updateAppearance
import org.paradigm.engine.event.impl.PlayerLoginEvent
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.ui.GameInterface

onEvent<PlayerLoginEvent> {
    player.updateAppearance()
    player.openGameFrame()
}

fun Player.openGameFrame() {
    ui.openTopInterface(displayMode.interfaceId)
    GameInterface.values().filter { it.interfaceId != -1 }.forEach { gameInterface ->
        ui.openInterface(gameInterface)
    }
}