package org.paradigm.engine.net.packet.server

import io.netty.buffer.ByteBuf
import org.paradigm.engine.net.Session
import org.paradigm.engine.net.game.Codec
import org.paradigm.engine.net.game.Packet
import org.paradigm.engine.net.game.PacketType
import org.paradigm.engine.net.game.ServerPacket
import org.paradigm.util.buffer.JagByteBuf

@ServerPacket(opcode = 37, type = PacketType.VARIABLE_SHORT)
class UpdatePlayersPacket(val buf: ByteBuf) : Packet {
    companion object : Codec<UpdatePlayersPacket> {
        override fun encode(session: Session, packet: UpdatePlayersPacket, out: JagByteBuf) {
            out.writeBytes(packet.buf)
            packet.buf.release()
        }
    }
}