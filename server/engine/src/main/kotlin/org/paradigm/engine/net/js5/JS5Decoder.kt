package org.paradigm.engine.net.js5

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

class JS5Decoder : ByteToMessageDecoder() {

    override fun decode(ctx: ChannelHandlerContext, buf: ByteBuf, out: MutableList<Any>) {
        if(!buf.isReadable(4)) return

        val opcode = buf.readUnsignedByte().toInt()
        when(val type = JS5RequestType.fromOpcode(opcode)) {
            JS5RequestType.NORMAL,
            JS5RequestType.PRIORITY -> {
                val archive = buf.readUnsignedByte().toInt()
                val group = buf.readUnsignedShort()
                val priority = type == JS5RequestType.PRIORITY
                out.add(JS5Request(archive, group, priority))
            }

            JS5RequestType.LOGGED_IN,
            JS5RequestType.LOGGED_OUT,
            JS5RequestType.ENCRYPT_KEY_UPDATE -> {
                buf.skipBytes(3)
            }

            else -> throw IllegalStateException("Unexpected JS5 request type opcode: $opcode.")
        }
    }
}