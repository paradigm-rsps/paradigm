package org.paradigm.engine.event.impl

import org.paradigm.engine.event.Event
import org.paradigm.engine.model.entity.Player

class PlayerCheatCommandEvent(
    val player: Player,
    val command: String,
    val args: List<String>
) : Event