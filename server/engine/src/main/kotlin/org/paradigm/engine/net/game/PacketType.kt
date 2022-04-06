package org.paradigm.engine.net.game

enum class PacketType(val length: Int) {
    VARIABLE_BYTE(-1),
    VARIABLE_SHORT(-2),
    FIXED(-3);

    companion object {

        fun fromLength(length: Int) = when(length) {
            -1 -> VARIABLE_BYTE
            -2 -> VARIABLE_SHORT
            else -> FIXED
        }
    }
}