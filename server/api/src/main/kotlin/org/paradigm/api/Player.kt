package org.paradigm.api

import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.entity.update.PlayerUpdateFlag

fun Player.updateAppearance() {
    updateFlags.add(PlayerUpdateFlag.APPEARANCE)
}