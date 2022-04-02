package org.paradigm.engine.net.handshake

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.paradigm.config.ServerConfig
import org.paradigm.engine.net.StatusResponse
import org.tinylog.kotlin.Logger

class HandshakeHandler : SimpleChannelInboundHandler<HandshakeRequest>() {

    override fun channelRead0(ctx: ChannelHandlerContext, msg: HandshakeRequest) {
        when(msg) {
            is HandshakeRequest.Js5 -> msg.handle(ctx)
            is HandshakeRequest.Login -> msg.handle(ctx)
        }
    }

    private fun HandshakeRequest.Js5.handle(ctx: ChannelHandlerContext) {
        /*
         * Check revision.
         */
        val revision = ServerConfig.REVISION
        if(this.revision != revision) {
            Logger.info("Connected client revision out of date (client rev: ${this.revision}). Expected rev: $revision.")
            ctx.channel().close()
            return
        }

        ctx.write(StatusResponse.SUCCESSFUL)
        Logger.info("Switch to js5 protocol.")
    }

    private fun HandshakeRequest.Login.handle(ctx: ChannelHandlerContext) {

    }
}