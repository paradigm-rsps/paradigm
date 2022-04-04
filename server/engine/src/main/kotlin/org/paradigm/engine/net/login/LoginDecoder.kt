package org.paradigm.engine.net.login

import io.guthix.buffer.readString
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import org.paradigm.common.inject
import org.paradigm.config.ServerConfig
import org.paradigm.engine.net.StatusResponse
import org.paradigm.util.RSA
import org.paradigm.util.Xtea
import org.tinylog.kotlin.Logger
import java.math.BigInteger

class LoginDecoder : ByteToMessageDecoder() {

    private val rsa: RSA by inject()

    private var stage = Stage.Handshake
    private var payloadSize = 0

    private lateinit var username: String
    private var password: String? = null
    private var authCode: Int? = null
    private var reconnecting = false
    private var seed: Long = (Math.random() * Long.MAX_VALUE).toLong()
    private lateinit var xteas: IntArray
    private var reconnectXteas: IntArray? = null

    override fun handlerAdded(ctx: ChannelHandlerContext) {
        ctx.write(StatusResponse.SUCCESSFUL)
        ctx.write(ctx.alloc().buffer(Long.SIZE_BYTES).writeLong(seed))
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

        buf.skipBytes(Int.SIZE_BYTES)
        buf.skipBytes(Byte.SIZE_BYTES)

        if(revision != ServerConfig.REVISION) {
            throw LoginError(StatusResponse.OUT_OF_DATE)
        }

        buf.skipBytes(Byte.SIZE_BYTES)

        /*
         * === RSA ENCRYPTED BUFFER ===
         */
        val secureBuf = run {
            val length = buf.readUnsignedShort()
            val data = ByteArray(length)
            buf.readBytes(data)
            val rsaData = BigInteger(data).modPow(rsa.privateExponent, rsa.privateModulus)
            Unpooled.wrappedBuffer(rsaData.toByteArray())
        }

        val payloadData = ByteArray(buf.readableBytes())
        buf.readBytes(payloadData)

        /*
         * Read the data from the RSA secure buffer.
         */
        readSecureBuf(secureBuf)

        /*
         * === XTEA ENCRYPTED BUFFER ===
         */
        val xteaData = Xtea.decipher(payloadData, xteas)
        val xteaBuf = Unpooled.wrappedBuffer(xteaData)

        /*
         * Read the data from the XTEA encrypted buffer.
         */
        readXteaBuf(xteaBuf)

        val request = LoginRequest(
            this,
            username,
            password,
            authCode,
            reconnecting,
            seed,
            xteas,
            reconnectXteas
        )
        out.add(request)
    }

    private fun readSecureBuf(buf: ByteBuf) {
        /*
         * Read the RSA decryption check value. If this value of the byte is not equal to '1'
         * than we can assume that the RSA was not able to successfully decrypt the buffer.
         */
        val decryptionCheck = buf.readUnsignedByte().toInt()
        if(decryptionCheck != 1) {
            Logger.error("Invalid RSA public/private keys. Failed to decrypt the RSA buffer sent from client connection.")
            throw LoginError(StatusResponse.BAD_SESSION_ID)
        }

        xteas = IntArray(4) { buf.readInt() }
        val clientSeed = buf.readLong()

        if(clientSeed != seed) {
            throw LoginError(StatusResponse.MALFORMED_PACKET)
        }

        if(reconnecting) {
            reconnectXteas = IntArray(4) { buf.readInt() }
            password = null
            authCode = null
        } else {
            authCode = when(buf.readByte().toInt()) {
                1, 3 -> {
                    val value = buf.readUnsignedMedium()
                    buf.skipBytes(Byte.SIZE_BYTES)
                    value
                }
                2 -> {
                    buf.skipBytes(Int.SIZE_BYTES)
                    -1
                }
                else -> buf.readInt()
            }
            buf.skipBytes(Byte.SIZE_BYTES)
            password = buf.readString()
        }
    }

    private fun readXteaBuf(buf: ByteBuf) {
        username = buf.readString()

        if(username.isBlank()) {
            throw LoginError(StatusResponse.INVALID_CREDENTIALS)
        }

        buf.skipBytes(buf.readableBytes())
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