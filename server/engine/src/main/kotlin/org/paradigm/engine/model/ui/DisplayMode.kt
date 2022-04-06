package org.paradigm.engine.model.ui

enum class DisplayMode(val id: Int, val enum: Int) {
    FIXED(id = 548, enum = 1129),
    RESIZABLE_CLASSIC(id = 161, enum = 1130),
    RESIZABLE_MODERN(id = 164, enum = 1131),
    FULLSCREEN(id = 165, enum = 1132),
    MOBILE(id = 601, enum = 1745);

    fun isResizable(): Boolean = (this == RESIZABLE_CLASSIC || this == RESIZABLE_MODERN)

    companion object {
        fun fromId(id: Int) = values().first { it.id == id }
    }
}