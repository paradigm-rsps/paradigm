package org.paradigm.engine.serializer

import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.net.Session

interface PlayerSerializer {
    fun loadPlayer(session: Session, username: String, password: String): Player?
    fun savePlayer(player: Player)
}