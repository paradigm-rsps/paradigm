import org.paradigm.api.event.onEvent
import org.paradigm.api.event.unregisterEvents
import org.paradigm.api.move
import org.paradigm.api.teleport
import org.paradigm.engine.event.impl.MoveGameClickEvent

onDisable { unregisterEvents() }

onEvent<MoveGameClickEvent> {
    when (type) {
        0 -> player.move(this.tile)
        else -> player.teleport(this.tile)
    }
}