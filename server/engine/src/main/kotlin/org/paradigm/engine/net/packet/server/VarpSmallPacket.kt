package org.paradigm.engine.net.packet.server

import org.paradigm.engine.net.Session
import org.paradigm.engine.net.game.Codec
import org.paradigm.engine.net.game.Packet
import org.paradigm.engine.net.game.PacketType
import org.paradigm.engine.net.game.ServerPacket
import org.paradigm.util.buffer.ADD
import org.paradigm.util.buffer.JagByteBuf

@ServerPacket(opcode = 10, type = PacketType.FIXED)
class VarpSmallPacket(
    val id: Int,
    val value: Int
) : Packet {
    companion object : Codec<VarpSmallPacket> {
        override fun encode(session: Session, packet: VarpSmallPacket, out: JagByteBuf) {
            out.writeShort(packet.id)
            out.writeByte(packet.value, transform = ADD)
        }
    }
}