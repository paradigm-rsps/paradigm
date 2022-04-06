package org.paradigm.engine.net.game

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder
import org.paradigm.common.inject
import org.paradigm.engine.net.Session
import org.paradigm.util.buffer.toJagBuf

class GamePacketEncoder(private val session: Session) : MessageToByteEncoder<Packet>() {

    private val gamePackets: GamePackets by inject()

    override fun encode(ctx: ChannelHandlerContext, msg: Packet, out: ByteBuf) {
        val opcode = gamePackets.serverPackets.getOpcode(msg::class)
        val type = gamePackets.serverPackets.getType(opcode)
        val codec = gamePackets.serverPackets.getCodec(opcode)

        val buf = ctx.alloc().buffer().toJagBuf()
        codec.encode(session, msg, buf)

        out.writeByte((opcode + session.encodeIsaac.nextInt()) and 0xFF)

        when(type) {
            PacketType.VARIABLE_BYTE -> out.writeByte(buf.writerIndex())
            PacketType.VARIABLE_SHORT -> out.writeShort(buf.writerIndex())
            else -> { /* Do nothing */ }
        }

        out.writeBytes(buf.toByteBuf())
        buf.toByteBuf().release()
    }

}