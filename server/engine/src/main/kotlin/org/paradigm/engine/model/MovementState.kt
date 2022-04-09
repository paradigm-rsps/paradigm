package org.paradigm.engine.model

enum class MovementState(val id: Int) {
    NONE(-1),
    CRAWL(0),
    WALK(1),
    RUN(2),
    TELEPORT(127);
}