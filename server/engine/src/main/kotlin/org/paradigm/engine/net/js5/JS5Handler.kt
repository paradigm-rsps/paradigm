package org.paradigm.engine.net.js5

import io.netty.channel.*
import org.paradigm.cache.GameCache
import org.paradigm.common.inject
import org.tinylog.kotlin.Logger
import kotlin.experimental.xor

class JS5Handler : ChannelInboundHandlerAdapter() {

    private val cache: GameCache by inject()

    private var encryptionKey = 0

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) = when (msg) {
        is JS5Request.FileData -> ctx.requestFileData(msg.archive, msg.group)
        is JS5Request.EncryptKeyUpdate -> ctx.updateEncryptKey(msg.key, msg.offset)
        else -> throw IllegalStateException()
    }

    private fun ChannelHandlerContext.updateEncryptKey(key: Int, offset: Int) {
        encryptionKey = key
    }

    private fun ChannelHandlerContext.requestFileData(archive: Int, group: Int) {
        val buf = cache.store.read(archive, group).retain()

        val compressionType = buf.readUnsignedByte().toInt()
        val compressedSize = buf.readInt()

        val data = ByteArray(buf.writerIndex() - Byte.SIZE_BYTES - Int.SIZE_BYTES)
        buf.readBytes(data)

        val response = JS5Response(
            archive = archive,
            group = group,
            compressionType = compressionType,
            compressedSize = compressedSize,
            data = data,
            encryptionKey = encryptionKey
        )
        writeAndFlush(response)
    }
}