package org.paradigm.engine.event.impl

import org.paradigm.engine.event.Event
import org.paradigm.engine.model.entity.Player

class LoginEvent(val player: Player) : Event