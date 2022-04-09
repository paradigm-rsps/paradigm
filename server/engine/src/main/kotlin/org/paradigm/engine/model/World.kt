package org.paradigm.engine.model

import org.paradigm.common.inject
import org.paradigm.engine.Engine
import org.paradigm.engine.model.collision.CollisionMap
import org.paradigm.engine.model.list.PlayerList
import org.paradigm.engine.queue.QueueList

class World {

    val engine: Engine by inject()

    val players = PlayerList()
    val collisionMap = CollisionMap()

    internal val queue = QueueList()

    fun queue(block: suspend () -> Unit) = queue.queue(block)

    internal fun init() {

    }
}