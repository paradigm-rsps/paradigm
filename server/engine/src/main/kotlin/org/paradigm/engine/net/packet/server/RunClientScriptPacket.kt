package org.paradigm.engine.net.packet.server

import org.paradigm.engine.net.Session
import org.paradigm.engine.net.game.Codec
import org.paradigm.engine.net.game.Packet
import org.paradigm.engine.net.game.PacketType
import org.paradigm.engine.net.game.ServerPacket
import org.paradigm.util.buffer.JagByteBuf

@ServerPacket(opcode = 35, type = PacketType.VARIABLE_SHORT)
class RunClientScriptPacket(val id: Int, vararg val params: Any) : Packet {
    companion object : Codec<RunClientScriptPacket> {
        override fun encode(session: Session, packet: RunClientScriptPacket, out: JagByteBuf) {
            val builder = StringBuilder()
            packet.params.reversed().forEach { param ->
                if (param is String) {
                    builder.append("s")
                } else {
                    builder.append("i")
                }
            }
            out.writeString(builder.toString())
            packet.params.forEach { param ->
                if (param is String) {
                    out.writeString(param)
                } else {
                    out.writeInt(param as Int)
                }
            }
            out.writeInt(packet.id)
        }
    }
}