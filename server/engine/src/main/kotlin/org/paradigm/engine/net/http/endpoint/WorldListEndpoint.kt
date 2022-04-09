package org.paradigm.engine.net.http.endpoint

import io.guthix.buffer.writeString
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.http.*
import io.netty.handler.codec.http.HttpHeaderNames.*
import org.paradigm.common.inject
import org.paradigm.engine.model.worldlist.WorldList
import org.tinylog.kotlin.Logger

object WorldListEndpoint {

    private val worldList: WorldList by inject()

    fun handle(ctx: ChannelHandlerContext) {
        val worldsBuf = ctx.encodeWorlds()
        val buf = ctx.alloc().buffer()

        buf.writeInt(worldsBuf.readableBytes())
        buf.writeBytes(worldsBuf)

        val response = DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf)
        val headers = response.headers()

        headers[CONTENT_TYPE] = "application/octet-stream"
        headers[CONTENT_LENGTH] = buf.readableBytes()
        headers[CONNECTION] = HttpHeaderValues.KEEP_ALIVE

        ctx.writeAndFlush(response)
    }

    private fun ChannelHandlerContext.encodeWorlds(): ByteBuf {
        val buf = this.alloc().buffer()
        buf.writeShort(worldList.size)
        worldList.forEach { worldEntry ->
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