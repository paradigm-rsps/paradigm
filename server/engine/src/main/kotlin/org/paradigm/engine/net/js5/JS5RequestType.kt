package org.paradigm.engine.net.js5

enum class JS5RequestType(val opcode: Int) {
    NORMAL_REQUEST(0),
    PRIORITY_REQUEST(1),
    ENCRYPTION_KEY_UPDATE(3);

    companion object {
        fun fromOpcode(opcode: Int): JS5RequestType = values().first { it.opcode == opcode }
    }
}