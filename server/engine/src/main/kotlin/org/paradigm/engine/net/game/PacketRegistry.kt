package org.paradigm.engine.net.game

import kotlin.reflect.KClass

class PacketRegistry {

    private val packets = mutableListOf<PacketInfo>()

    val size: Int get() = packets.size

    internal fun register(packet: KClass<Packet>, opcode: Int, codec: Codec<Packet>, length: Int,) {
        val info = PacketInfo(packet, opcode, codec, length)
        packets.add(info)
    }

    fun getOpcode(packet: KClass<out Packet>): Int = packets.first {
        it.packet == packet
    }.opcode

    fun getCodec(opcode: Int): Codec<Packet> = packets.first {
        it.opcode == opcode
    }.codec

    fun getLength(opcode: Int): Int = packets.first {
        it.opcode == opcode
    }.length

    fun getType(opcode: Int): PacketType = when(getLength(opcode)) {
        -1 -> PacketType.VARIABLE_BYTE
        -2 -> PacketType.VARIABLE_SHORT
        else -> PacketType.FIXED
    }

    fun isUnknown(opcode: Int): Boolean = packets.firstOrNull {
        it.opcode == opcode
    }?.codec?.let { true } ?: false

    private data class PacketInfo(
        val packet: KClass<Packet>,
        val opcode: Int,
        val codec: Codec<Packet>,
        val length: Int
    )
}