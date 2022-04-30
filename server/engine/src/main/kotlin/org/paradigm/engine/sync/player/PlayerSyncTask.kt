package org.paradigm.engine.sync.player

import io.netty.buffer.ByteBuf
import org.paradigm.common.inject
import org.paradigm.engine.Engine
import org.paradigm.engine.model.MovementState
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.list.PlayerList
import org.paradigm.engine.model.map.Scene
import org.paradigm.engine.model.world.World
import org.paradigm.engine.net.packet.server.UpdatePlayersPacket
import org.paradigm.engine.sync.SyncTask
import org.paradigm.util.buffer.BIT_MODE
import org.paradigm.util.buffer.BYTE_MODE
import org.paradigm.util.buffer.JagByteBuf
import org.paradigm.util.buffer.toJagBuf

class PlayerSyncTask : SyncTask {

    private val engine: Engine by inject()
    private val world: World by inject()

    override suspend fun execute() {
        world.players.forEach { player ->
            player.session.write(UpdatePlayersPacket(player.encodeSync()))

            player.gpi.localPlayerCount = 0
            player.gpi.externalPlayerCount = 0

            for (i in 1 until PlayerList.MAX_PLAYERS) {
                player.gpi.skipFlags[i] = player.gpi.skipFlags[i] shr 1
                val localPlayer = player.gpi.localPlayers[i]
                if (localPlayer != null) {
                    player.gpi.localPlayerIndexes[player.gpi.localPlayerCount++] = i
                } else {
                    player.gpi.externalPlayerIndexes[player.gpi.externalPlayerCount++] = i
                }
            }
        }
    }

    private fun Player.encodeSync(): ByteBuf {
        val buf = session.ctx.alloc().buffer().toJagBuf()
        val maskBuf = session.ctx.alloc().buffer().toJagBuf()

        var skipCount = 0

        buf.switchWriteMode(BIT_MODE)
        for (i in 0 until PlayerList.MAX_PLAYERS) {
            val index = gpi.localPlayerIndexes[i]
            if ((gpi.skipFlags[index] and 1) == 0) {
                if (skipCount > 1) {
                    --skipCount
                    gpi.skipFlags[index] = gpi.skipFlags[index] or 2
                } else {
                    val shouldUpdate = buf.readBit()
                    if (shouldUpdate == 0) {
                        skipCount = buf.readSkipCount()
                        gpi.skipFlags[index] = gpi.skipFlags[index] or 2
                    } else {
                        buf.encodeLocalGpiUpdates(index)
                    }
                }
            }
        }
        buf.switchWriteMode(BYTE_MODE)

        buf.switchWriteMode(BIT_MODE)
        for (i in 0 until PlayerList.MAX_PLAYERS) {
            val index = gpi.localPlayerIndexes[i]
            if ((gpi.skipFlags[index] and 1) != 0) {
                if (skipCount > 0) {
                    --skipCount
                    gpi.skipFlags[index] = gpi.skipFlags[index] or 2
                } else {
                    val shouldUpdate = buf.readBit()
                    if (shouldUpdate == 0) {
                        skipCount = buf.readSkipCount()
                        gpi.skipFlags[index] = gpi.skipFlags[index] or 2
                    } else {
                        buf.encodeLocalGpiUpdates(index)
                    }
                }
            }
        }
        buf.switchWriteMode(BYTE_MODE)

        buf.switchWriteMode(BIT_MODE)
        for (i in 0 until PlayerList.MAX_PLAYERS) {
            val index = gpi.externalPlayerIndexes[i]
            if ((gpi.skipFlags[index] and 1) != 0) {
                if (skipCount > 0) {
                    --skipCount
                    gpi.skipFlags[index] = gpi.skipFlags[index] or 2
                } else {
                    val shouldUpdate = buf.readBit()
                    if (shouldUpdate == 0) {
                        skipCount = buf.readSkipCount()
                        gpi.skipFlags[index] = gpi.skipFlags[index] or 2
                    } else if (buf.encodeExternalGpiUpdates(index)) {
                        gpi.skipFlags[index] = gpi.skipFlags[index] or 2
                    }
                }
            }
        }
        buf.switchWriteMode(BYTE_MODE)

        buf.switchWriteMode(BIT_MODE)
        for (i in 0 until PlayerList.MAX_PLAYERS) {
            val index = gpi.externalPlayerIndexes[i]
            if ((gpi.skipFlags[index] and 1) == 0) {
                if (skipCount > 0) {
                    --skipCount
                    gpi.skipFlags[index] = gpi.skipFlags[index] or 2
                } else {
                    val shouldUpdate = buf.readBit()
                    if (shouldUpdate == 0) {
                        skipCount = buf.readSkipCount()
                        gpi.skipFlags[index] = gpi.skipFlags[index] or 2
                    } else if (buf.encodeExternalGpiUpdates(index)) {
                        gpi.skipFlags[index] = gpi.skipFlags[index] or 2
                    }
                }
            }
        }
        buf.switchWriteMode(BYTE_MODE)

        buf.writeBytes(maskBuf.toByteBuf())
        maskBuf.release()

        return buf.toByteBuf()
    }

    private fun Player.encodeLocalGpiUpdates(buf: JagByteBuf, localIndex: Int) {
        fun shouldUpdate(player: Player, other: Player): Boolean {
            return other.updateFlags.isNotEmpty()
                    || !player.tile.isWithinRadius(other.tile, Scene.RENDER_DISTANCE)
                    || other.movementState != MovementState.NONE
        }


    }

    private fun JagByteBuf.encodeExternalGpiUpdates(index: Int): Boolean {
        return true
    }

    private fun JagByteBuf.readSkipCount() = when (readBits(2)) {
        0 -> 0
        1 -> readBits(5)
        2 -> readBits(8)
        else -> readBits(11)
    }

    companion object {
        private const val EXCESS_MASK = 0x40
        private val MOVE_DIR = arrayOf(
            intArrayOf(11, 12, 13, 14, 15),
            intArrayOf(9, 5, 6, 7, 10),
            intArrayOf(7, 3, -1, 4, 8),
            intArrayOf(5, 0, 1, 2, 6),
            intArrayOf(0, 1, 2, 3, 4)
        )
    }
}