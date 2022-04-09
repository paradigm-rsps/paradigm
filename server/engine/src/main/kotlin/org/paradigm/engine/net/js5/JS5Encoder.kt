package org.paradigm.engine.net.js5

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

class JS5Encoder : MessageToByteEncoder<JS5Response>() {

    companion object {
        private const val SECTOR_SIZE = 512
    }

    override fun encode(ctx: ChannelHandlerContext, msg: JS5Response, out: ByteBuf) {
        out.writeByte(msg.archive)
        out.writeShort(msg.group)
        out.writeByte(msg.compressionType)
        out.writeInt(msg.compressedSize)

        var dataSize = msg.data.readableBytes()
        if(dataSize > SECTOR_SIZE - 8) {
            dataSize = SECTOR_SIZE - 8
        }

        out.writeBytes(msg.data.slice(msg.data.readerIndex(), dataSize))
        msg.data.readerIndex(msg.data.readerIndex() + dataSize)

        while(msg.data.readableBytes() > 0) {
            dataSize = msg.data.readableBytes()
            if(dataSize > SECTOR_SIZE - 1) {
                dataSize = SECTOR_SIZE - 1
            }

            out.writeByte(-1)
            out.writeBytes(msg.data.slice(msg.data.readerIndex(), dataSize))
            msg.data.readerIndex(msg.data.readerIndex() + dataSize)
        }
    }
}