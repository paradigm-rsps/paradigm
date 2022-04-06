package org.paradigm.engine.net.game

import org.paradigm.engine.net.Message
import org.paradigm.engine.net.Session

interface Packet : Message {

    fun handle(session: Session) { /* No Default Implementation */ }

}