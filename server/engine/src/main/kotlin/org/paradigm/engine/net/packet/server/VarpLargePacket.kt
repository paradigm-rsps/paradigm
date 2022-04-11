package org.paradigm.engine.net.packet.server

import org.paradigm.engine.net.Session
import org.paradigm.engine.net.game.Codec
import org.paradigm.engine.net.game.Packet
import org.paradigm.engine.net.game.PacketType
import org.paradigm.engine.net.game.ServerPacket
import org.paradigm.util.buffer.JagByteBuf
import org.paradigm.util.buffer.MIDDLE

@ServerPacket(opcode = 8, type = PacketType.FIXED)
class VarpLargePacket(
    val id: Int,
    val value: Int
) : Packet {
    companion object : Codec<VarpLargePacket> {
        override fun encode(session: Session, packet: VarpLargePacket, out: JagByteBuf) {
            out.writeInt(packet.value, endian = MIDDLE)
            out.writeShort(packet.id)
        }
    }
}