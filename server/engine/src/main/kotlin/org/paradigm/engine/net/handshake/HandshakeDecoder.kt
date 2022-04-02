package org.paradigm.engine.net.handshake

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import org.tinylog.kotlin.Logger

class HandshakeDecoder : ByteToMessageDecoder() {

    init {
        isSingleDecode = true
    }

    override fun decode(ctx: ChannelHandlerContext, buf: ByteBuf, out: MutableList<Any>) {
        val opcode = buf.readUnsignedByte().toInt()
        val type = HandshakeType.fromOpcode(opcode)

        if(type == null) {
            Logger.error("Received unknown handshake opcode: $opcode.")
            buf.skipBytes(buf.readableBytes())
            ctx.channel().close()
        } else {
            val request = when(type) {
                HandshakeType.JS5 -> HandshakeRequest.Js5(buf.readInt())
                HandshakeType.LOGIN -> HandshakeRequest.Login()
            }
            
            out.add(request)
        }
    }
}