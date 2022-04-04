package org.paradigm.engine.net.login

import io.netty.channel.ChannelHandlerContext
import org.paradigm.engine.net.Message

data class LoginRequest(
    val ctx: ChannelHandlerContext,
    val username: String,
    val password: String?,
    val authCode: Int?,
    val reconnecting: Boolean,
    val seed: Long,
    val xteas: IntArray,
    val reconnectXteas: IntArray?,
) : Message {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LoginRequest

        if (ctx != other.ctx) return false
        if (username != other.username) return false
        if (password != other.password) return false
        if (authCode != other.authCode) return false
        if (reconnecting != other.reconnecting) return false
        if (seed != other.seed) return false
        if (!xteas.contentEquals(other.xteas)) return false
        if (reconnectXteas != null) {
            if (other.reconnectXteas == null) return false
            if (!reconnectXteas.contentEquals(other.reconnectXteas)) return false
        } else if (other.reconnectXteas != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = ctx.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + (password?.hashCode() ?: 0)
        result = 31 * result + (authCode ?: 0)
        result = 31 * result + reconnecting.hashCode()
        result = 31 * result + seed.hashCode()
        result = 31 * result + xteas.contentHashCode()
        result = 31 * result + (reconnectXteas?.contentHashCode() ?: 0)
        return result
    }
}