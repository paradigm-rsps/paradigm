package org.paradigm.engine.net.packet.server

import org.paradigm.engine.net.Session
import org.paradigm.engine.net.game.Codec
import org.paradigm.engine.net.game.Packet
import org.paradigm.engine.net.game.PacketType
import org.paradigm.engine.net.game.ServerPacket
import org.paradigm.util.buffer.ADD
import org.paradigm.util.buffer.JagByteBuf

@ServerPacket(opcode = 28, type = PacketType.FIXED)
class IfOpenTopPacket(val interfaceId: Int) : Packet {
    companion object : Codec<IfOpenTopPacket> {
        override fun encode(session: Session, packet: IfOpenTopPacket, out: JagByteBuf) {
            out.writeShort(packet.interfaceId, transform = ADD)
        }
    }
}