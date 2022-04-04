package org.paradigm.engine.net.worldlist.pipeline

import io.guthix.buffer.writeString
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageEncoder
import io.netty.handler.codec.http.*
import io.netty.handler.codec.http.HttpHeaderNames.*
import org.paradigm.common.inject
import org.paradigm.engine.net.worldlist.WorldEntry
import org.paradigm.engine.net.worldlist.WorldListServer

class WorldListEncoder : MessageToMessageEncoder<List<WorldEntry>>() {

    private val worldListServer: WorldListServer by inject()

    override fun encode(ctx: ChannelHandlerContext, msg: List<WorldEntry>, out: MutableList<Any>) {
        val worldEntriesBuf = encodeWorldEntries()

        val buf = Unpooled.buffer()
        buf.writeInt(worldEntriesBuf.readableBytes())
        buf.writeBytes(worldEntriesBuf)

        val response = DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf)
        val headers = response.headers()
        headers[CONTENT_TYPE] = "application/octet-stream"
        headers[CONTENT_LENGTH] = buf.readableBytes()
        headers[CONNECTION] = HttpHeaderValues.KEEP_ALIVE

        out.add(response)
    }

    private fun encodeWorldEntries(): ByteBuf {
        val buf = Unpooled.buffer()

        buf.writeShort(worldListServer.worlds.size)
        worldListServer.worlds.forEach { worldEntry ->
            var mask = 0
            worldEntry.types.forEach { mask = mask or it.mask }
            buf.writeShort(worldEntry.id)
            buf.writeInt(mask)
            buf.writeString(worldEntry.address)
            buf.writeString(worldEntry.activity)
            buf.writeByte(worldEntry.location.id)
            buf.writeShort(worldEntry.players)
        }

        return buf
    }
}