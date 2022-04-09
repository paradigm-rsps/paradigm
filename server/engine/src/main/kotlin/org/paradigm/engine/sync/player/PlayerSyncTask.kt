package org.paradigm.engine.sync.player

import io.netty.buffer.ByteBuf
import kotlinx.coroutines.*
import org.paradigm.common.inject
import org.paradigm.engine.Engine
import org.paradigm.engine.model.MovementState
import org.paradigm.engine.model.world.World
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.entity.update.PlayerUpdateFlag
import org.paradigm.engine.model.list.PlayerList
import org.paradigm.engine.model.map.Scene
import org.paradigm.engine.model.map.Tile
import org.paradigm.engine.net.packet.server.UpdatePlayersPacket
import org.paradigm.engine.sync.SyncTask
import org.paradigm.util.buffer.BIT_MODE
import org.paradigm.util.buffer.BYTE_MODE
import org.paradigm.util.buffer.JagByteBuf
import org.paradigm.util.buffer.toJagBuf
import java.util.*
import kotlin.math.abs

class PlayerSyncTask : SyncTask {

    private val engine: Engine by inject()
    private val world: World by inject()

    override suspend fun execute() {
        engine.ioCoroutine.launch { runSync().joinAll() }.join()
    }

    private fun CoroutineScope.runSync(): List<Job> {
        val jobs = mutableListOf<Job>()

        world.players.forEach { player ->
            jobs += launch {
                val buf = player.encodeSync()
                player.session.write(UpdatePlayersPacket(buf))
            }
        }

        return jobs
    }

    private fun Player.encodeSync(): ByteBuf {
        val mainBuf = session.ctx.alloc().buffer().toJagBuf()
        val maskBuf = session.ctx.alloc().buffer().toJagBuf()

        var local = 0
        var added = 0

        local += writeLocalPlayers(mainBuf, maskBuf, true)
        local += writeLocalPlayers(mainBuf, maskBuf, false)
        added += writeExternalPlayers(mainBuf, maskBuf, false, local, added)
        added += writeExternalPlayers(mainBuf, maskBuf, true, local, added)

        gpi.localPlayerCount = 0
        gpi.externalPlayerCount = 0

        for (index in 1 until PlayerList.MAX_PLAYERS) {
            gpi.skipFlags[index] = gpi.skipFlags[index] shr 1
            if (gpi.localPlayers[index] != null) {
                gpi.localPlayerIndexes[gpi.localPlayerCount++] = index
            } else {
                gpi.externalPlayerIndexes[gpi.externalPlayerCount++] = index
            }
        }

        mainBuf.writeBytes(maskBuf.toByteBuf())
        maskBuf.release()

        return mainBuf.toByteBuf()
    }

    private fun Player.writeLocalPlayers(buf: JagByteBuf, maskBuf: JagByteBuf, activeMode: Boolean): Int {
        var local = 0
        var skipCount = 0

        fun shouldUpdate(player: Player, other: Player): Boolean {
            return other.updateFlags.isNotEmpty()
                    || !player.tile.isWithinRadius(other.tile, Scene.RENDER_DISTANCE)
                    || other.movementState != MovementState.NONE
        }

        fun updatePlayer(localPlayer: Player, buf: JagByteBuf, maskBuf: JagByteBuf) {
            val shouldUpdate = localPlayer.updateFlags.isNotEmpty()
            buf.writeBoolean(shouldUpdate)

            if (localPlayer.movementState == MovementState.TELEPORT) {
                buf.writeBits(3, 2)
                val dx = localPlayer.tile.x - localPlayer.prevTile.x
                val dy = localPlayer.tile.y - localPlayer.prevTile.y
                val dz = localPlayer.tile.plane - localPlayer.prevTile.plane
                if (abs(dx) <= Scene.RENDER_DISTANCE && abs(dy) <= Scene.RENDER_DISTANCE) {
                    buf.writeBoolean(false)
                    buf.writeBits(dz and 0x3, 2)
                    buf.writeBits(dx and 0x1F, 5)
                    buf.writeBits(dy and 0x1f, 5)
                } else {
                    buf.writeBoolean(true)
                    buf.writeBits(dz and 0x3, 2)
                    buf.writeBits(dx and 0x3FFF, 14)
                    buf.writeBits(dy and 0x3FFF, 14)
                }
            } else if (!this.tile.isWithinRadius(localPlayer.tile, Scene.RENDER_DISTANCE)) {
                buf.writeBits(0, 2)
                gpi.localPlayers[localPlayer.index] = null
            } else if (localPlayer.movementState == MovementState.WALK) {
                buf.writeBits(1, 2)
                buf.writeBits(getMoveDirection(localPlayer), 3)
            } else if (localPlayer.movementState == MovementState.RUN) {
                buf.writeBits(2, 2)
                buf.writeBits(getMoveDirection(localPlayer), 4)
            } else if (shouldUpdate) {
                buf.writeBits(0, 2)
            }

            if (shouldUpdate) {
                encodeUpdateFlags(localPlayer, maskBuf)
            }
        }

        fun skipPlayers(buf: JagByteBuf, index: Int, activeMode: Boolean) {
            ((index + 1) until gpi.localPlayerCount)
                .asSequence()
                .map { gpi.localPlayerIndexes[it] }
                .filter { wasSkipped(it, activeMode) }
                .map { gpi.localPlayers[it] }
                .map { it != null && shouldUpdate(this, it) }
                .takeWhile { !it }
                .toList()
                .forEach { _ -> skipCount++ }
            buf.writeSkipCount(skipCount)
        }

        skipCount = 0
        buf.switchWriteMode(BIT_MODE)

        for (i in 0 until gpi.localPlayerCount) {
            val localIndex = gpi.localPlayerIndexes[i]
            if (wasSkipped(localIndex, activeMode)) {
                if (skipCount > 0) {
                    skipCount--
                    markSkipped(localIndex)
                } else {
                    val localPlayer = gpi.localPlayers[localIndex]
                    val shouldUpdate = localPlayer != null && shouldUpdate(this, localPlayer)
                    buf.writeBoolean(shouldUpdate)
                    if (!shouldUpdate) {
                        skipPlayers(buf, i, activeMode)
                        markSkipped(localIndex)
                    } else {
                        updatePlayer(localPlayer!!, buf, maskBuf)
                    }
                }
            }
        }

        buf.switchWriteMode(BYTE_MODE)
        return local
    }

    private fun Player.writeExternalPlayers(
        buf: JagByteBuf,
        maskBuf: JagByteBuf,
        activeMode: Boolean,
        local: Int,
        added: Int
    ): Int {
        var additions = 0
        var skipCount = 0

        fun shouldUpdate(player: Player, other: Player): Boolean {
            return player.tile.isWithinRadius(other.tile, Scene.RENDER_DISTANCE)
                    || player.gpi.tiles[other.index] != other.tile.to18BitInteger()
        }

        fun updatePlayer(externalPlayer: Player, buf: JagByteBuf, maskBuf: JagByteBuf) {
            if (this.tile.isWithinRadius(externalPlayer.tile, Scene.RENDER_DISTANCE)) {
                buf.writeBits(0, 2)
                if (gpi.tiles[externalPlayer.index] != externalPlayer.tile.to18BitInteger()) {
                    buf.writeBoolean(true)
                    writeTileUpdate(externalPlayer, buf)
                } else {
                    buf.writeBoolean(false)
                }
                buf.writeBits(externalPlayer.tile.x, 13)
                buf.writeBits(externalPlayer.tile.y, 13)
                buf.writeBoolean(true)
                encodeUpdateFlags(
                    externalPlayer,
                    maskBuf,
                    sortedSetOf(PlayerUpdateFlag.APPEARANCE, PlayerUpdateFlag.MOVEMENT_MODE)
                )
                gpi.localPlayers[externalPlayer.index] = externalPlayer
            } else {
                writeTileUpdate(externalPlayer, buf)
            }
        }

        fun skipPlayers(buf: JagByteBuf, index: Int, activeMode: Boolean) {
            ((index + 1) until gpi.externalPlayerCount)
                .asSequence()
                .map { gpi.externalPlayerIndexes[it] }
                .filter { wasSkipped(it, activeMode) }
                .map { world.players[it] }
                .map { it != null && shouldUpdate(this, it) }
                .takeWhile { !it }
                .toList()
                .forEach { _ -> skipCount++ }
            buf.writeSkipCount(skipCount)
        }

        skipCount = 0
        buf.switchWriteMode(BIT_MODE)

        for (i in 0 until gpi.externalPlayerCount) {
            val externalIndex = gpi.externalPlayerIndexes[i]
            if (wasSkipped(externalIndex, activeMode)) {
                if (skipCount > 0) {
                    skipCount--
                    markSkipped(externalIndex)
                } else {
                    val externalPlayer = world.players[externalIndex]
                    val shouldUpdate = externalPlayer != null && shouldUpdate(this, externalPlayer)

                    buf.writeBoolean(shouldUpdate)

                    if (!shouldUpdate) {
                        skipPlayers(buf, i, activeMode)
                        markSkipped(externalIndex)
                    } else {
                        updatePlayer(externalPlayer!!, buf, maskBuf)
                        markSkipped(externalIndex)
                    }
                }
            }
        }

        buf.switchWriteMode(BYTE_MODE)
        return additions
    }

    private fun Player.wasSkipped(index: Int, activeMode: Boolean) = when (activeMode) {
        true -> gpi.skipFlags[index] and 0x1 == 0
        false -> gpi.skipFlags[index] and 0x1 != 0
    }

    private fun JagByteBuf.writeSkipCount(count: Int) {
        when {
            count == 0 -> {
                writeBits(0, 2)
            }
            count < 32 -> {
                writeBits(1, 2)
                writeBits(count, 5)
            }
            count < 256 -> {
                writeBits(2, 2)
                writeBits(count, 8)
            }
            count < 2048 -> {
                writeBits(3, 2)
                writeBits(count, 11)
            }
        }
    }

    private fun Player.markSkipped(index: Int) {
        gpi.skipFlags[index] = gpi.skipFlags[index] or 0x2
    }

    private fun getDirectionId(dx: Int, dy: Int) = MOVE_DIR[2 - dy][dx + 2]

    private fun getMoveDirection(player: Player): Int {
        val dx = player.tile.x - player.prevTile.x
        val dy = player.tile.y - player.prevTile.y
        return getDirectionId(dx, dy)
    }

    private fun Player.writeTileUpdate(player: Player, buf: JagByteBuf) {
        val prev = gpi.tiles[player.index]
        val curr = player.tile.to18BitInteger()

        val px = (prev shr 8) and 0xFF
        val py = prev and 0xFF
        val pz = prev shr 16

        val cx = (curr shr 8) and 0xFF
        val cy = curr and 0xFF
        val cz = curr shr 16

        val dx = cx - px
        val dy = cy - py
        val dz = (cz - pz) and 0x3

        if (dx == 0 && dy == 0) {
            buf.writeBits(1, 2)
            buf.writeBits(dz, 2)
        } else if (abs(dx) <= 1 && abs(dy) <= 1) {
            buf.writeBits(2, 2)
            buf.writeBits((dz shl 3) or getDirectionId(dx, dy), 5)
        } else {
            buf.writeBits(3, 2)
            buf.writeBits(Tile(dx, dy, dz).to18BitInteger(), 18)
        }
        gpi.tiles[player.index] = curr
    }

    private fun encodeUpdateFlags(
        localPlayer: Player,
        maskBuf: JagByteBuf,
        updateFlags: SortedSet<PlayerUpdateFlag> = sortedSetOf()
    ) {
        var mask = 0

        updateFlags.addAll(localPlayer.updateFlags)
        updateFlags.forEach {
            mask = mask or it.mask
        }

        if (mask >= 0xFF) {
            maskBuf.writeByte(mask or EXCESS_MASK)
            maskBuf.writeByte(mask shr 8)
        } else {
            maskBuf.writeByte(mask)
        }

        updateFlags.forEach { updateFlag ->
            updateFlag.encode(maskBuf, localPlayer)
        }
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