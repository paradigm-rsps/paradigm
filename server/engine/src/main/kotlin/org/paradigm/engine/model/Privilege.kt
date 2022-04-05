package org.paradigm.engine.model

enum class Privilege(val id: Int) {
    PLAYER(0),
    MODERATOR(1),
    ADMINISTRATOR(2),
    DEVELOPER(3);
}