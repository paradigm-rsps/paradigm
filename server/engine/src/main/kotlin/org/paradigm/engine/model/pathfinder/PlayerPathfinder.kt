package org.paradigm.engine.model.pathfinder

import org.paradigm.engine.model.Direction
import org.paradigm.engine.model.map.Tile
import java.util.*
import kotlin.properties.Delegates

class PlayerPathfinder : Pathfinder {

    private val queue = LinkedList<Node>()
    private val visited = LinkedList<Node>()

    private var start: Tile by Delegates.notNull()
    private var dest: Tile by Delegates.notNull()

    var cancelled: Boolean = false

    private var current: Node? = null
    private var successful: Boolean = false

    override fun calculatePath(start: Tile, dest: Tile): MutableList<Tile> {
        cancelled = false

        this.start = start
        this.dest = dest

        queue.clear()
        visited.clear()

        var searchLimit = 2048

        val directions = arrayOf(
            Direction.WEST,
            Direction.EAST,
            Direction.NORTH,
            Direction.SOUTH,
            Direction.SOUTH_WEST,
            Direction.SOUTH_EAST,
            Direction.NORTH_WEST,
            Direction.NORTH_EAST
        )

        queue.add(Node(start))
        while (queue.isNotEmpty() && !cancelled) {
            if (searchLimit-- == 0) break

            val node = queue.removeFirst()
            if (dest.sameAs(node.tile)) {
                return backtraceSolution(node)
            }

            directions.sortBy {
                node.tile.translate(it).deltaTo(dest) + node.tile.translate(it).deltaTo(node.tile)
            }

            directions.forEach { direction ->
                val tile = node.tile.translate(direction)
                val newNode = Node(tile, node)

                if (!visited.contains(newNode)
                    && start.isWithinRadius(tile, MAX_PATH)
                    && node.tile.directionTo(tile) != Direction.NONE
                ) {
                    newNode.cost = node.cost + 1
                    queue.add(newNode)
                    visited.add(newNode)
                }
            }

            current = node
        }

        return backtraceSolution(current)
    }

    private fun backtraceSolution(node: Node?): MutableList<Tile> {
        val path = ArrayDeque<Tile>()
        var last: Node? = node

        if (last != null && visited.isNotEmpty()) {
            val best = visited.minByOrNull { it.tile.distanceTo(dest) }!!
            val sorted = visited.filter { it.tile.distanceTo(dest) <= best.tile.distanceTo(dest) }
            if (sorted.isNotEmpty()) {
                last = sorted.minByOrNull { it.tile.deltaTo(start) }
            }
        }

        while (last?.parent != null) {
            path.addFirst(last.tile)
            last = last.parent
        }

        return if (path.size > MAX_PATH) {
            path.toMutableList().dropLast(path.size - MAX_PATH).toMutableList()
        } else {
            path.toMutableList()
        }
    }

    private data class Node(val tile: Tile, var parent: Node? = null) {
        var cost = 0

        override fun equals(other: Any?): Boolean {
            return (other as? Node)?.tile?.sameAs(tile) ?: false
        }

        override fun hashCode(): Int {
            return tile.packed
        }
    }

    companion object {
        private const val MAX_PATH = 100
    }
}