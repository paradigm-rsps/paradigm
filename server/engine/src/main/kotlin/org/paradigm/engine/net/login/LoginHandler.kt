package org.paradigm.engine.net.login

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

class LoginHandler : SimpleChannelInboundHandler<LoginRequest>() {

    override fun channelRead0(ctx: ChannelHandlerContext, msg: LoginRequest) {

    }
}