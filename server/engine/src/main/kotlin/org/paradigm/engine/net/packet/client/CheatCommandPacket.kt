package org.paradigm.engine.net.packet.client

import org.paradigm.engine.event.EventBus
import org.paradigm.engine.event.impl.PlayerCheatCommandEvent
import org.paradigm.engine.net.Session
import org.paradigm.engine.net.game.ClientPacket
import org.paradigm.engine.net.game.Codec
import org.paradigm.engine.net.game.Packet
import org.paradigm.engine.net.game.PacketType
import org.paradigm.util.buffer.JagByteBuf

@ClientPacket(opcode = 94, type = PacketType.VARIABLE_BYTE)
class CheatCommandPacket(val command: String, val args: List<String>) : Packet {

    override fun handle(session: Session) {
        EventBus.publish(PlayerCheatCommandEvent(session.player, command, args))
    }

    companion object : Codec<CheatCommandPacket> {
        override fun decode(session: Session, buf: JagByteBuf): CheatCommandPacket {
            val strings = buf.readString().split(" ")
            val command = strings.first()
            val args = strings.drop(1).toList()
            return CheatCommandPacket(command, args)
        }
    }
}