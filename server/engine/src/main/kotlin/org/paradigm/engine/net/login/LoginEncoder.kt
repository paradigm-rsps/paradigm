package org.paradigm.engine.net.login

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

class LoginEncoder : MessageToByteEncoder<LoginResponse>() {

    override fun encode(ctx: ChannelHandlerContext, msg: LoginResponse, out: ByteBuf) {
        out.writeByte(2)
        out.writeByte(21)
        out.writeBoolean(false)
        out.writeInt(0)
        out.writeByte(msg.player.privilege.id)
        out.writeBoolean(msg.player.privilege.id > 0)
        out.writeShort(msg.player.index)
        out.writeBoolean(true)
        out.writeLong(0L)
    }
}