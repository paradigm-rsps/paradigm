package org.paradigm.engine.model.entity.update

import org.paradigm.engine.model.MovementState
import org.paradigm.engine.model.entity.Player
import org.paradigm.util.buffer.JagByteBuf
import org.paradigm.util.buffer.NEG
import org.paradigm.util.buffer.SUB
import org.paradigm.util.buffer.toJagBuf
import kotlin.math.max

class PlayerUpdateFlag(
    order: Int,
    mask: Int,
    val encode: JagByteBuf.(Player) -> Unit
) : UpdateFlag(order, mask) {
    companion object {

        /**
         * Movement Flag
         */
        val MOVEMENT = PlayerUpdateFlag(order = 1, mask = 0x100) { player ->
            writeByte(if (player.movementState == MovementState.TELEPORT) 127 else 0, transform = SUB)
        }

        /**
         * Appearance Flag
         */
        val APPEARANCE = PlayerUpdateFlag(order = 5, mask = 0x80) { player ->
            val appBuf = player.session.ctx.alloc().buffer().toJagBuf()

            appBuf.writeByte(player.appearance.gender.id)
            appBuf.writeByte(player.skullIcon)
            appBuf.writeByte(player.prayerIcon)

            for (i in 0 until 12) {
                if (STYLE_OFFSETS[i] == -1) {
                    appBuf.writeByte(0)
                } else {
                    appBuf.writeShort(0x100 + player.appearance.styles[STYLE_OFFSETS[i]])
                }
            }

            for (i in 0 until 5) {
                appBuf.writeByte(max(0, player.appearance.colors[i]))
            }

            player.stanceAnimations.forEach { animation ->
                appBuf.writeShort(animation)
            }

            appBuf.writeString(player.displayName)
            appBuf.writeByte(player.combatLevel)
            appBuf.writeShort(0)
            appBuf.writeByte(if (player.invisible) 1 else 0)

            this.writeByte(appBuf.writerIndex(), transform = SUB)
            this.writeBytesReversed(appBuf.toByteBuf())
            appBuf.release()
        }

        /**
         * Movement Type Flag
         */
        val MOVEMENT_TYPE = PlayerUpdateFlag(order = 10, mask = 0x4000) { player ->
            writeByte(if (player.running) 2 else 1, transform = NEG)
        }

        /*
         * Internal static constants
         */
        private val STYLE_OFFSETS = intArrayOf(-1, -1, -1, -1, 2, -1, 3, 5, 0, 4, 6, 1)
    }
}