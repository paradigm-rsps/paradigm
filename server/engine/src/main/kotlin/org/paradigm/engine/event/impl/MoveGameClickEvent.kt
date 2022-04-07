package org.paradigm.engine.event.impl

import org.paradigm.engine.event.Event
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.map.Tile

class MoveGameClickEvent(
    val player: Player,
    val tile: Tile,
    val type: Int
) : Event