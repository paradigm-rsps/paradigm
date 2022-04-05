package org.paradigm.engine.net.login

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.paradigm.common.inject
import org.paradigm.engine.service.ServiceManager
import org.paradigm.engine.service.auth.LoginService
import org.tinylog.kotlin.Logger

class LoginHandler : SimpleChannelInboundHandler<LoginRequest>() {

    private val serviceManager: ServiceManager by inject()

    private val loginService = serviceManager[LoginService::class]

    override fun channelRead0(ctx: ChannelHandlerContext, msg: LoginRequest) {
        loginService.queue(msg)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        if(cause.stackTrace.isEmpty() || cause.stackTrace[0].methodName != "read0") {
            Logger.error(cause) { "An error occurred in the networking thread." }
            ctx.channel().disconnect()
        }
    }
}