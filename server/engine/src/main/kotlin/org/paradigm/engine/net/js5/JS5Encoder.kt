package org.paradigm.engine.net.js5

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

class JS5Encoder : MessageToByteEncoder<JS5Response>() {

    override fun encode(ctx: ChannelHandlerContext, msg: JS5Response, out: ByteBuf) {
        out.writeByte(msg.archive xor msg.encryptionKey)
        out.writeShort(msg.group xor msg.encryptionKey)
        out.writeByte(msg.compressionType xor msg.encryptionKey)
        out.writeInt(msg.compressedSize xor msg.encryptionKey)
        msg.data.forEach { byte ->
            if (out.writerIndex() % BLOCK_SIZE == 0) {
                out.writeByte(-1 xor msg.encryptionKey)
            }
            out.writeByte(byte.toInt() xor msg.encryptionKey)
        }
    }

    companion object {
        private const val BLOCK_SIZE = 512
    }
}