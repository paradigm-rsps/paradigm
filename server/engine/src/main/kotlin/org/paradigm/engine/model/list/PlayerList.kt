package org.paradigm.engine.model.list

import org.paradigm.engine.model.entity.Player
import java.util.*
import kotlin.random.Random

class PlayerList : Iterable<Player> {

    private val players = arrayOfNulls<Player>(MAX_PLAYERS)
    private val activeIndexes = mutableListOf<Int>()
    private val inactiveIndexes = Stack<Int>()
    private val iterator = PidIterator()

    init {
        for(index in MAX_PLAYERS downTo 1) {
            inactiveIndexes.push(index)
        }
    }

    val size get() = activeIndexes.size

    override fun iterator(): Iterator<Player> = PidIterator()

    fun addPlayer(player: Player) {
        player.index = inactiveIndexes.pop()
        player.pid = Random.nextInt(size + 1)
        players[player.index] = player
        activeIndexes.add(player.pid, player.index)
    }

    fun removePlayer(player: Player) {
        players[player.index] = null
        activeIndexes.remove(player.index)
        inactiveIndexes.add(player.index)
        player.index = -1
        player.pid = -1
    }

    fun randomizePid() {
        activeIndexes.shuffle()
        activeIndexes.forEach { index ->
            players[index]!!.pid = iterator.currentIndex
        }
    }

    operator fun get(index: Int): Player? = players[index]

    operator fun get(username: String): Player? = this.firstOrNull { it.username == username }

    inner class PidIterator : MutableIterator<Player> {
        internal var currentIndex = 0

        override fun hasNext(): Boolean {
            return currentIndex < this@PlayerList.size
        }

        override fun next(): Player {
            return players[activeIndexes[currentIndex++]] ?: next()
        }

        override fun remove() {
            players[currentIndex] = null
            activeIndexes.remove(currentIndex)
            inactiveIndexes.add(currentIndex)
        }
    }

    companion object {
        const val MAX_PLAYERS = 2048
    }
}