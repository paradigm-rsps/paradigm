package org.paradigm.engine.net.game

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.paradigm.engine.net.Session
import org.tinylog.kotlin.Logger

class GamePacketHandler(private val session: Session) : SimpleChannelInboundHandler<Packet>() {

    override fun channelRead0(ctx: ChannelHandlerContext, msg: Packet) {

    }

    override fun channelInactive(ctx: ChannelHandlerContext) {
        if(!ctx.channel().isActive) return
        session.player.logout()
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        if(cause.stackTrace.isEmpty() || cause.stackTrace[0].methodName != "read0") {
            Logger.error(cause) { "An error occurred in the networking thread." }
            ctx.channel().disconnect()
        }
    }
}