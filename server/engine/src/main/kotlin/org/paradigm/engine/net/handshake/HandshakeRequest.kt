package org.paradigm.engine.net.handshake

import org.paradigm.engine.net.Message

sealed class HandshakeRequest(val type: HandshakeType) : Message {

    data class Js5(val revision: Int) : HandshakeRequest(HandshakeType.JS5)
    class Login : HandshakeRequest(HandshakeType.LOGIN)

}