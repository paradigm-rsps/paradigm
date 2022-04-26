package org.paradigm.engine.net.js5

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

class JS5Decoder : ByteToMessageDecoder() {

    override fun decode(ctx: ChannelHandlerContext, buf: ByteBuf, out: MutableList<Any>) {
        if (!buf.isReadable) return
        buf.markReaderIndex()

        when (JS5RequestType.fromOpcode(buf.readByte().toInt())) {
            JS5RequestType.NORMAL,
            JS5RequestType.PRIORITY -> buf.readRequest(out)
            else -> buf.skipBytes(3)
        }
    }

    private fun ByteBuf.readRequest(out: MutableList<Any>) {
        if (readableBytes() >= 3) {
            val archive = readUnsignedByte().toInt()
            val group = readUnsignedShort()
            val request = JS5Request(archive, group)
            out.add(request)
        } else {
            resetReaderIndex()
        }
    }

}