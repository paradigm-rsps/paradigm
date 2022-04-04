package org.paradigm.engine.net.handshake

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.paradigm.config.ServerConfig
import org.paradigm.engine.net.StatusResponse
import org.paradigm.engine.net.js5.JS5Decoder
import org.paradigm.engine.net.js5.JS5Encoder
import org.paradigm.engine.net.js5.JS5Handler
import org.paradigm.engine.net.login.LoginDecoder
import org.paradigm.engine.net.login.LoginEncoder
import org.paradigm.engine.net.login.LoginHandler
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

        ctx.writeAndFlush(StatusResponse.SUCCESSFUL)

        val p = ctx.pipeline()
        p.addAfter("status-encoder", "js5-encoder", JS5Encoder())
        p.replace("handshake-handler", "js5-handler", JS5Handler())
        p.replace("handshake-decoder", "js5-decoder", JS5Decoder())
    }

    private fun HandshakeRequest.Login.handle(ctx: ChannelHandlerContext) {
        val p = ctx.pipeline()
        p.addAfter("status-encoder", "login-encoder", LoginEncoder())
        p.replace("handshake-handler", "login-handler", LoginHandler())
        p.replace("handshake-decoder", "login-decoder", LoginDecoder())
    }
}