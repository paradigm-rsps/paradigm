package org.paradigm.engine.net.packet.client

import org.paradigm.engine.event.EventBus
import org.paradigm.engine.event.impl.MoveGameClickEvent
import org.paradigm.engine.model.map.Tile
import org.paradigm.engine.net.Session
import org.paradigm.engine.net.game.ClientPacket
import org.paradigm.engine.net.game.Codec
import org.paradigm.engine.net.game.Packet
import org.paradigm.engine.net.game.PacketType
import org.paradigm.util.buffer.JagByteBuf
import org.paradigm.util.buffer.LITTLE
import org.paradigm.util.buffer.SUB

@ClientPacket(opcode = 18, type = PacketType.VARIABLE_BYTE)
class MoveGameClickPacket(
    val tileX: Int,
    val tileY: Int,
    val type: Int
) : Packet {
    override fun handle(session: Session) {
        EventBus.publish(MoveGameClickEvent(session.player, Tile(tileX, tileY, session.player.tile.plane), type))
    }

    companion object : Codec<MoveGameClickPacket> {
        override fun decode(session: Session, buf: JagByteBuf): MoveGameClickPacket {
            val tileX = buf.readShort(endian = LITTLE).toInt()
            val tileY = buf.readShort().toInt()
            val type = buf.readByte(transform = SUB).toInt()
            return MoveGameClickPacket(tileX, tileY, type)
        }
    }
}