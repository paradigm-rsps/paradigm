package org.paradigm.content.core

import org.paradigm.api.move
import org.paradigm.api.teleport
import org.paradigm.api.toggleRun
import org.paradigm.api.updateAppearance
import org.paradigm.common.inject
import org.paradigm.content.ContentModuleManager
import org.paradigm.content.core.api.on_command
import org.paradigm.content.core.api.on_login
import org.paradigm.content.core.api.on_move_click
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.ui.GameInterface

private val moduleManager: ContentModuleManager by inject()

on_login { player ->
    player.updateAppearance()
    player.openGameFrame()
    player.toggleRun()
}

on_move_click { player, dest, clickType ->
    when (clickType) {
        0 -> player.move(dest)
        else -> {
            if (player.privilege.id >= 2) player.teleport(dest) else return@on_move_click
        }
    }
}

on_command { player, command, args ->
    if (player.privilege.id < 2) return@on_command
    when (command) {
        "reload" -> moduleManager.reloadModule(args.firstOrNull() ?: "")
        else -> return@on_command
    }
}

fun Player.openGameFrame() {
    ui.openTopInterface(displayMode.interfaceId)
    GameInterface.values().filter { it.interfaceId != -1 }.forEach {
        ui.openInterface(it)
    }
}