package org.paradigm.engine.net.packet.server

import org.paradigm.engine.model.ui.InterfaceType
import org.paradigm.engine.net.Session
import org.paradigm.engine.net.game.Codec
import org.paradigm.engine.net.game.Packet
import org.paradigm.engine.net.game.PacketType
import org.paradigm.engine.net.game.ServerPacket
import org.paradigm.util.buffer.ADD
import org.paradigm.util.buffer.JagByteBuf
import org.paradigm.util.buffer.LITTLE

@ServerPacket(opcode = 81, type = PacketType.FIXED)
class IfOpenSubPacket(
    val parent: Int,
    val child: Int,
    val interfaceId: Int,
    val type: InterfaceType
) : Packet {
    companion object : Codec<IfOpenSubPacket> {
        override fun encode(session: Session, packet: IfOpenSubPacket, out: JagByteBuf) {
            out.writeByte(packet.type.id)
            out.writeInt((packet.parent shl 16) or packet.child)
            out.writeShort(packet.interfaceId, endian = LITTLE, transform = ADD)
        }
    }
}