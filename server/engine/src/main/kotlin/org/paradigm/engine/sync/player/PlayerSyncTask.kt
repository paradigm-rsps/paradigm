package org.paradigm.engine.sync.player

import io.netty.buffer.ByteBuf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.paradigm.common.inject
import org.paradigm.engine.coroutine.EngineCoroutineScope
import org.paradigm.engine.model.World
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.entity.update.PlayerUpdateFlag
import org.paradigm.engine.model.list.PlayerList
import org.paradigm.engine.model.map.Scene
import org.paradigm.engine.net.packet.server.UpdatePlayers
import org.paradigm.engine.sync.SyncTask
import org.paradigm.util.buffer.BIT_MODE
import org.paradigm.util.buffer.BYTE_MODE
import org.paradigm.util.buffer.JagByteBuf
import org.paradigm.util.buffer.toJagBuf
import java.util.*

class PlayerSyncTask : SyncTask {

    private val engineCoroutine: EngineCoroutineScope by inject()
    private val world: World by inject()

    override suspend fun execute() {
        val sync = engineCoroutine.launch { launchSync() }
        sync.join()
    }

    private fun CoroutineScope.launchSync() {
        world.players.forEach { player ->
            launch {
                val buf = player.encodeSync()
                player.session.write(UpdatePlayers(buf))
            }
        }
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

        val buf = session.ctx.alloc().buffer()

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
        maskBuf.toByteBuf().release()
        buf.writeBytes(mainBuf.toByteBuf())
        return buf
    }

    private fun Player.writeLocalPlayers(buf: JagByteBuf, maskBuf: JagByteBuf, activeMode: Boolean): Int {
        var local = 0
        var skipCount = 0

        fun shouldUpdate(player: Player, other: Player): Boolean {
            return other.updateFlags.isNotEmpty()
                    || !player.tile.isWithinRadius(other.tile, Scene.RENDER_DISTANCE)
        }

        fun updatePlayer(localPlayer: Player, buf: JagByteBuf, maskBuf: JagByteBuf) {
            val shouldUpdate = localPlayer.updateFlags.isNotEmpty()

            buf.writeBoolean(shouldUpdate)

            if (!this.tile.isWithinRadius(localPlayer.tile, Scene.RENDER_DISTANCE)) {
                buf.writeBits(0, 2)
                gpi.localPlayers[localPlayer.index] = null
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
                encodeUpdateFlags(externalPlayer, maskBuf, sortedSetOf(PlayerUpdateFlag.APPEARANCE))
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

    private fun Player.writeTileUpdate(other: Player, buf: JagByteBuf) {

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
    }
}