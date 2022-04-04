package org.paradigm.engine.net.worldlist.pipeline

import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import org.paradigm.common.inject
import org.paradigm.engine.net.worldlist.WorldListServer

@ChannelHandler.Sharable
class WorldListChannelHandler : ChannelInboundHandlerAdapter() {

    private val worldListServer: WorldListServer by inject()

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        ctx.writeAndFlush(worldListServer.worlds)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        ctx.close()
    }
}