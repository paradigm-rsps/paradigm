package org.paradigm.engine.net.js5

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.paradigm.cache.GameCache
import org.paradigm.common.inject
import org.tinylog.kotlin.Logger

class JS5Handler : SimpleChannelInboundHandler<JS5Request>() {

    private val cache: GameCache by inject()

    override fun channelRead0(ctx: ChannelHandlerContext, msg: JS5Request) {
        val data = cache.store.read(msg.archive, msg.group).retain()
        val compressionType = data.readUnsignedByte().toInt()
        val compressedSize = data.readInt()
        val response = JS5Response(
            msg.archive,
            msg.group,
            compressionType,
            compressedSize,
            data.copy()
        )
        ctx.writeAndFlush(response)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        if(cause.stackTrace.isEmpty() || cause.stackTrace[0].methodName != "read0") {
            Logger.error(cause) { "An error occurred in the networking thread." }
            ctx.channel().disconnect()
        }
    }
}