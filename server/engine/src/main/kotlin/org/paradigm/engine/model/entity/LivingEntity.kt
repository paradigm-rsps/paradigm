package org.paradigm.engine.model.entity

import org.paradigm.engine.model.Direction
import org.paradigm.engine.model.MovementState
import org.paradigm.engine.model.map.Tile

abstract class LivingEntity : Entity() {
    var index: Int = -1
    abstract val size: Int
    abstract override var tile: Tile
    abstract var prevTile: Tile
    abstract var followTile: Tile
    var teleportTile: Tile? = null
    val path = mutableListOf<Tile>()
    var interacting: LivingEntity? = null
    var running: Boolean = false
    var movementState: MovementState = MovementState.NONE
    var faceDirection: Direction = Direction.NORTH
    var invisible: Boolean = false

    abstract fun cycle()

}