package org.paradigm.engine.model.manager

import org.paradigm.cache.GameCache
import org.paradigm.common.inject
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.net.packet.server.VarpLargePacket
import org.paradigm.engine.net.packet.server.VarpSmallPacket
import kotlin.math.pow

class VarpManager(private val player: Player) {

    private val cache: GameCache by inject()
    private val varbitConfigs = cache.configArchive.varbitConfigs

    val varps = mutableMapOf<Int, Int>()
    private val changes = mutableMapOf<Int, Int>()

    fun updateVarp(id: Int, value: Int) {
        varps[id] = value
        changes[id] = value
    }

    fun updateVarbit(id: Int, value: Int) {
        val varbit = varbitConfigs[id] ?: throw IllegalArgumentException("Unknown varbit with id: $id.")
        val bitsize = (varbit.msb - varbit.lsb) + 1

        if (value > 2.0.pow(bitsize) - 1) {
            throw IllegalArgumentException("Value $value is too large for varbit with id: $id.")
        }

        var currentVarp = varps[varbit.varpId] ?: 0
        currentVarp = currentVarp.clearBits(varbit.lsb.toInt(), varbit.msb.toInt())
        currentVarp = currentVarp or value shl varbit.lsb.toInt()
        varps[varbit.varpId] = currentVarp
        changes[varbit.varpId] = currentVarp
    }

    internal fun cycle() {
        changes.forEach { (id, value) ->
            if (value <= Byte.MIN_VALUE || value >= Byte.MAX_VALUE) {
                player.session.write(VarpLargePacket(id, value))
            } else {
                player.session.write(VarpSmallPacket(id, value))
            }
        }
        changes.clear()
    }

    companion object {

        private fun Int.setBits(lsb: Int, msb: Int): Int = this xor ((1 shl (msb + 1)) - 1) xor ((1 shl lsb) - 1)

        @Suppress("INTEGER_OVERFLOW")
        private fun Int.clearBits(lsb: Int, msb: Int): Int = ((1 shl 4 * 8 - 1) - 1).setBits(lsb, msb) and this


    }
}