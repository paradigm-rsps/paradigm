package org.paradigm.engine.net.js5

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

class JS5Decoder : ByteToMessageDecoder() {

    override fun decode(ctx: ChannelHandlerContext, buf: ByteBuf, out: MutableList<Any>) {
        buf.markReaderIndex()
        val opcode = buf.readByte().toInt()
        when (JS5RequestType.fromOpcode(opcode)) {
            JS5RequestType.NORMAL_REQUEST, JS5RequestType.PRIORITY_REQUEST -> buf.readFileRequest(out)
            JS5RequestType.ENCRYPTION_KEY_UPDATE -> buf.readEncryptKeyUpdate(out)
        }
    }

    private fun ByteBuf.readFileRequest(out: MutableList<Any>) {
        if (readableBytes() < Byte.SIZE_BYTES + Short.SIZE_BYTES) {
            resetReaderIndex()
            return
        }
        val archive = readUnsignedByte().toInt()
        val group = readUnsignedShort()
        val request = JS5Request.FileData(archive, group)
        out.add(request)
    }

    private fun ByteBuf.readEncryptKeyUpdate(out: MutableList<Any>) {
        val encryptionKey = readByte().toInt()
        val offset = readUnsignedShort()
        val request = JS5Request.EncryptKeyUpdate(encryptionKey, offset)
        out.add(request)
    }
}