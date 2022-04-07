package org.paradigm.engine.net.http

import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.FullHttpRequest
import io.netty.handler.codec.http.HttpMethod
import io.netty.handler.codec.http.HttpResponseStatus
import io.netty.handler.codec.http.QueryStringDecoder
import org.paradigm.engine.net.http.endpoint.JavConfigEndpoint
import org.paradigm.engine.net.http.endpoint.WorldListEndpoint

@ChannelHandler.Sharable
class HttpRequestHandler : SimpleChannelInboundHandler<FullHttpRequest>() {

    override fun channelRead0(ctx: ChannelHandlerContext, msg: FullHttpRequest) {
        if (!msg.decoderResult().isSuccess) {
            ctx.sendHttpError(HttpResponseStatus.BAD_REQUEST)
            return
        }

        if (msg.method() != HttpMethod.GET) {
            ctx.sendHttpError(HttpResponseStatus.METHOD_NOT_ALLOWED)
            return
        }

        val uri = msg.uri()
        val query = QueryStringDecoder(uri)

        when {
            query.path() == "/jav_config.ws" -> JavConfigEndpoint.handle(ctx, msg, query)
            query.path() == "/world_list.ws" -> WorldListEndpoint.handle(ctx)
            else -> ctx.sendHttpError(HttpResponseStatus.NOT_FOUND)
        }
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        cause.printStackTrace()
        if (ctx.channel().isActive) {
            ctx.sendHttpError(HttpResponseStatus.INTERNAL_SERVER_ERROR)
        }
    }
}