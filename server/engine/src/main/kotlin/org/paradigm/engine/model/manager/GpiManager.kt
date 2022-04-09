package org.paradigm.engine.model.manager

import org.paradigm.common.inject
import org.paradigm.engine.model.world.World
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.list.PlayerList

class GpiManager(private val player: Player) {

    private val world: World by inject()

    var localPlayerCount = 0
    val localPlayerIndexes = IntArray(PlayerList.MAX_PLAYERS)
    val localPlayers = arrayOfNulls<Player>(PlayerList.MAX_PLAYERS)

    var externalPlayerCount = 0
    val externalPlayerIndexes = IntArray(PlayerList.MAX_PLAYERS)

    val tiles: IntArray = IntArray(PlayerList.MAX_PLAYERS)
    val skipFlags = IntArray(PlayerList.MAX_PLAYERS)

    fun init() {
        localPlayers[player.index] = player
        localPlayerIndexes[localPlayerCount++] = player.index

        for(index in 1 until PlayerList.MAX_PLAYERS) {
            if(index == player.index) continue
            externalPlayerIndexes[externalPlayerCount++] = index
            tiles[index] = world.players[index]?.tile?.to18BitInteger() ?: 0
        }
    }
}