package org.paradigm.engine.net.login

import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.net.Message

data class LoginResponse(val player: Player) : Message