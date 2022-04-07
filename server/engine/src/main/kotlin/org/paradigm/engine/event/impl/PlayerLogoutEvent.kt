package org.paradigm.engine.event.impl

import org.paradigm.engine.event.Event
import org.paradigm.engine.model.entity.Player

class PlayerLogoutEvent(val player: Player) : Event