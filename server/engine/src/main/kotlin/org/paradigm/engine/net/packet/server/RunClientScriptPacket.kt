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
            val types = CharArray(packet.params.size) { i -> if (packet.params[i] is String) 's' else 'i' }
            out.writeString(String(types))
            for (i in packet.params.size - 1 downTo 0) {
                val param = packet.params[i]
                if (param is String) {
                    out.writeString(param)
                } else if (param is Number) {
                    out.writeInt(param.toInt())
                }
            }
            out.writeInt(packet.id)
        }
    }
}