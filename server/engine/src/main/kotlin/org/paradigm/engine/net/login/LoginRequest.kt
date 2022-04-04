package org.paradigm.engine.net.login

import io.netty.channel.ChannelHandlerContext
import org.paradigm.engine.net.Message

data class LoginRequest(
    val ctx: ChannelHandlerContext,
    val username: String,
    val password: String
) : Message