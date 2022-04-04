package org.paradigm.engine.net.login

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import org.paradigm.engine.net.StatusResponse
import org.tinylog.kotlin.Logger

class LoginDecoder : ByteToMessageDecoder() {

    private var stage = Stage.Handshake
    private var payloadSize = 0

    private lateinit var username: String
    private lateinit var password: String
    private var reconnecting = false

    override fun handlerAdded(ctx: ChannelHandlerContext) {
        ctx.write(StatusResponse.SUCCESSFUL)
        ctx.write(ctx.alloc().buffer(Long.SIZE_BYTES).writeLong(0L))
        ctx.flush()
    }

    override fun decode(ctx: ChannelHandlerContext, buf: ByteBuf, out: MutableList<Any>) {
        try {
            when(stage) {
                Stage.Handshake -> ctx.readHandshake(buf, out)
                Stage.Header -> ctx.readHeader(buf, out)
                Stage.Payload -> ctx.readPayload(buf, out)
            }
        } catch (e : LoginError) {
            ctx.writeAndFlush(e.status).addListener {
                if(it.isSuccess) ctx.channel().disconnect()
            }
        } catch (e : Exception) {
            Logger.error(e) { "An error occurred during login of connection." }
            ctx.channel().disconnect()
        }
    }

    private fun ChannelHandlerContext.readHandshake(buf: ByteBuf, out: MutableList<Any>) {
        val opcode = buf.readUnsignedByte().toInt()
        if(opcode != LOGIN_OPCODE && opcode != RECONNECT_OPCODE) {
            Logger.error("Received invalid login type opcode. (opcode=$opcode).")
            throw LoginError(StatusResponse.FAILED_TO_LOGIN)
        }

        reconnecting = opcode == RECONNECT_OPCODE
        stage = Stage.Header
    }

    private fun ChannelHandlerContext.readHeader(buf: ByteBuf, out: MutableList<Any>) {
        if(buf.readableBytes() < Short.SIZE_BYTES) return
        payloadSize = buf.readUnsignedShort()
        stage = Stage.Payload
    }

    private fun ChannelHandlerContext.readPayload(buf: ByteBuf, out: MutableList<Any>) {
        if(buf.readableBytes() < payloadSize) return

        val revision = buf.readInt()
        Logger.info("rev: $revision")
    }

    private enum class Stage {
        Handshake,
        Header,
        Payload
    }

    private class LoginError(val status: StatusResponse) : Exception()

    companion object {
        private const val LOGIN_OPCODE = 16
        private const val RECONNECT_OPCODE = 18
    }
}