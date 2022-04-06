package org.paradigm.engine.net.game

import org.paradigm.engine.net.Session
import org.paradigm.util.buffer.JagByteBuf

interface Codec<T : Packet> {

    fun encode(session: Session, packet: T, out: JagByteBuf) {
        throw UnsupportedOperationException()
    }

    fun decode(session: Session, buf: JagByteBuf): T {
        throw UnsupportedOperationException()
    }
}