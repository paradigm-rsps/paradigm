package org.paradigm.content.core

import org.paradigm.api.event.onEvent
import org.paradigm.api.event.unregisterEvents
import org.paradigm.api.moduleManager
import org.paradigm.api.toggleRun
import org.paradigm.api.updateAppearance
import org.paradigm.engine.event.impl.CheatCommandEvent
import org.paradigm.engine.event.impl.LoginEvent
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.ui.GameInterface
import org.tinylog.kotlin.Logger

onDisable { unregisterEvents() }

onEvent<LoginEvent> {
    player.ui.openTopInterface(player.displayMode.interfaceId)
    GameInterface.values().filter { it.interfaceId != -1 }.forEach { gameInterface ->
        player.ui.openInterface(gameInterface)
    }
    player.updateAppearance()
    player.toggleRun()
}

onEvent<CheatCommandEvent> {
    if (player.privilege.id == 0) return@onEvent
    when (command) {
        "reload" -> reloadCommand(args[0])
        else -> return@onEvent
    }
}

fun reloadCommand(module: String) {
    Logger.info("Reloading module: $module.")
    moduleManager.reloadModule(module)
    Logger.info("Successfully reloaded module: $module.")
}