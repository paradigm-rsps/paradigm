package org.paradigm.engine.net.js5

enum class JS5RequestType(val opcode: Int) {
    NORMAL(0),
    PRIORITY(1);

    companion object {
        fun fromOpcode(opcode: Int) = values().firstOrNull { it.opcode == opcode }
    }
}