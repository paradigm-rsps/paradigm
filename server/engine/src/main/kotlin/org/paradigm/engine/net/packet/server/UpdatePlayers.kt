package org.paradigm.engine.net.packet.server

import io.netty.buffer.ByteBuf
import org.paradigm.engine.net.Session
import org.paradigm.engine.net.game.Codec
import org.paradigm.engine.net.game.Packet
import org.paradigm.engine.net.game.PacketType
import org.paradigm.engine.net.game.ServerPacket
import org.paradigm.util.buffer.JagByteBuf

@ServerPacket(opcode = 37, type = PacketType.VARIABLE_SHORT)
class UpdatePlayers(val buf: ByteBuf) : Packet {
    companion object : Codec<UpdatePlayers> {
        override fun encode(session: Session, packet: UpdatePlayers, out: JagByteBuf) {
            out.writeBytes(packet.buf)
        }
    }
}