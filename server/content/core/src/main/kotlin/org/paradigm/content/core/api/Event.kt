package org.paradigm.content.core.api

import org.paradigm.api.event.onEvent
import org.paradigm.content.ContentModuleScript
import org.paradigm.engine.event.impl.CheatCommandEvent
import org.paradigm.engine.event.impl.LoginEvent
import org.paradigm.engine.event.impl.LogoutEvent
import org.paradigm.engine.event.impl.MoveGameClickEvent
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.map.Tile

fun ContentModuleScript.on_login(block: (player: Player) -> Unit) {
    onEvent<LoginEvent> { block(player) }
}

fun ContentModuleScript.on_logout(block: (player: Player) -> Unit) {
    onEvent<LogoutEvent> { block(player) }
}

fun ContentModuleScript.on_move_click(block: (player: Player, dest: Tile, clickType: Int) -> Unit) {
    onEvent<MoveGameClickEvent> { block(this.player, this.tile, this.type) }
}

fun ContentModuleScript.on_command(block: (player: Player, command: String, args: List<String>) -> Unit) {
    onEvent<CheatCommandEvent> { block(player, command, args) }
}