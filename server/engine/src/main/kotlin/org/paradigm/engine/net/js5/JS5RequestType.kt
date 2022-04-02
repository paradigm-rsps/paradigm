package org.paradigm.engine.net.js5

enum class JS5RequestType(val opcode: Int) {
    NORMAL(0),
    PRIORITY(1),
    LOGGED_IN(2),
    LOGGED_OUT(3),
    ENCRYPT_KEY_UPDATE(4);

    companion object {
        private val values = values()
        fun fromOpcode(opcode: Int) = values.firstOrNull { it.opcode == opcode }
    }
}