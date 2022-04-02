package org.paradigm.engine.net.handshake

enum class HandshakeType(val opcode: Int) {
    LOGIN(opcode = 14),
    JS5(opcode = 15);

    companion object {
        private val values = values()
        fun fromOpcode(opcode: Int) = values.firstOrNull { it.opcode == opcode }
    }
}