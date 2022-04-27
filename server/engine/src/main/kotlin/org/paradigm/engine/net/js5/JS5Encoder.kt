package org.paradigm.engine.net.js5

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

class JS5Encoder : MessageToByteEncoder<JS5Response>() {

    override fun encode(ctx: ChannelHandlerContext, msg: JS5Response, out: ByteBuf) {
        out.writeByte(msg.archive)
        out.writeShort(msg.group)

        msg.data.forEach { byte ->
            if (out.writerIndex() % 512 == 0) {
                out.writeByte(-1)
            }
            out.writeByte(byte.toInt())
        }
    }
}