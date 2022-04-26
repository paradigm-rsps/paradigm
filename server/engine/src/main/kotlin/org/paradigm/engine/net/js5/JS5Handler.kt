package org.paradigm.engine.net.js5

import com.google.common.primitives.Ints
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import net.runelite.cache.fs.Container
import net.runelite.cache.fs.jagex.CompressionType
import net.runelite.cache.fs.jagex.DiskStorage
import org.paradigm.cache.GameCache
import org.paradigm.common.inject

/**
 * This JS5 handler needs to be rewritten so that the inbound request's response data is cachd into memory.
 *
 * Additionally, the handling of JS5 uses IO and should be queued on a different thread than the networking / game threads
 * to prevent DOS attacks or IO latency.
 */

class JS5Handler : SimpleChannelInboundHandler<JS5Request>() {

    private val cache: GameCache by inject()

    override fun channelRead0(ctx: ChannelHandlerContext, msg: JS5Request) {
        if (msg.archive == 255) ctx.writeIndex(msg)
        else ctx.writeData(msg)
    }

    private fun ChannelHandlerContext.writeIndex(msg: JS5Request) {
        val data: ByteArray
        if (msg.group == 255) {
            if (cachedIdxData == null) {
                val buf = alloc().heapBuffer(cache.store.indexes.size * 8)

                cache.store.indexes.forEach { idx ->
                    buf.writeInt(idx.crc)
                    buf.writeInt(idx.revision)
                }

                val container = Container(CompressionType.NONE, -1)
                container.compress(buf.array().copyOf(buf.readableBytes()), null)
                cachedIdxData = container.data
                buf.release()
            }
            data = cachedIdxData!!
        } else {
            val storage = cache.store.storage as DiskStorage
            data = storage.readIndex(msg.group)
        }

        val response = JS5Response(
            archive = msg.archive,
            group = msg.group,
            data = data
        )

        writeAndFlush(response)
    }

    private fun ChannelHandlerContext.writeData(msg: JS5Request) {
        val archive = cache.store.findIndex(msg.archive)!!
        val group = archive.getArchive(msg.group)!!
        var data = cache.disk.loadArchive(group)

        if (data != null) {
            val compressionType = data[0]
            val compressedLength = Ints.fromBytes(data[1], data[2], data[3], data[4])
            val length = compressedLength + (if (compressionType.toInt() != CompressionType.NONE) 9 else 5)
            if (length != compressedLength && data.size - length == 2) {
                data = data.copyOf(data.size - 2)
            }

            val response = JS5Response(
                archive = msg.archive,
                group = msg.group,
                data = data
            )

            writeAndFlush(response)
        }
    }

    companion object {
        private var cachedIdxData: ByteArray? = null
    }
}