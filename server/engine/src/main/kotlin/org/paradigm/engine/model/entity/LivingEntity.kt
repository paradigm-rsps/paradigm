package org.paradigm.engine.model.entity

import org.paradigm.engine.model.Direction
import org.paradigm.engine.model.MovementState
import org.paradigm.engine.model.map.Tile
import org.paradigm.engine.model.pathfinder.Pathfinder
import org.paradigm.engine.queue.PriorityQueueList
import org.tinylog.kotlin.Logger

abstract class LivingEntity : Entity() {
    var index: Int = -1

    abstract val size: Int

    abstract override var tile: Tile

    abstract var prevTile: Tile

    abstract var followTile: Tile

    var teleportTile: Tile? = null

    var path = mutableListOf<Tile>()

    var interacting: LivingEntity? = null

    var running: Boolean = false

    var movementState: MovementState = MovementState.NONE

    var faceDirection: Direction = Direction.NORTH

    var invisible: Boolean = false

    abstract val pathfinder: Pathfinder

    internal val queue = PriorityQueueList()

    abstract suspend fun cycle()

    internal suspend fun queueCycle() {
        val shouldPollPending = queue.paused
        try {
            queue.processCurrent()
        } catch (e: Throwable) {
            queue.discardCurrent()
            Logger.error(e) { "An error occurred in queue." }
        }
        if (shouldPollPending) {
            try {
                queue.pollPending()
            } catch (e: Throwable) {
                Logger.error(e) { "An error occurred in queue." }
            }
        }
    }
}