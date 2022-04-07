package org.paradigm.engine.net.game

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import org.paradigm.common.inject
import org.paradigm.engine.net.Session
import org.paradigm.util.buffer.toJagBuf
import org.tinylog.kotlin.Logger

class GamePacketDecoder(private val session: Session) : ByteToMessageDecoder() {

    private val gamePackets: GamePackets by inject()

    private enum class Stage {
        OPCODE,
        LENGTH,
        PAYLOAD
    }

    private var stage = Stage.OPCODE
    private var opcode = -1
    private var length = 0
    private var unknown = false

    override fun decode(ctx: ChannelHandlerContext, buf: ByteBuf, out: MutableList<Any>) {
        try {
            when (stage) {
                Stage.OPCODE -> buf.readOpcode()
                Stage.LENGTH -> buf.readLength()
                Stage.PAYLOAD -> buf.readPayload(out)
            }
        } catch (e: Exception) {
            Logger.error(e) { "An error occurred while decoding client packet with opcode: $opcode." }
            buf.skipBytes(buf.readableBytes())
            session.player.logout()
            return
        }
    }

    private fun ByteBuf.readOpcode() {
        opcode = (readUnsignedByte().toInt() - session.decodeIsaac.nextInt()) and 0xFF
        length =
            PACKET_LENGTHS[opcode] ?: throw IllegalStateException("No client packet length found for opcode: $opcode.")
        unknown = gamePackets.clientPackets.isUnknown(opcode)
        stage = when {
            length < 0 -> Stage.LENGTH
            else -> Stage.PAYLOAD
        }
    }

    private fun ByteBuf.readLength() {
        length = when (length) {
            -1 -> readUnsignedByte().toInt()
            -2 -> readUnsignedShort()
            else -> throw IllegalStateException("Invalid variable length of $length for opcode $opcode.")
        }
        stage = Stage.PAYLOAD
    }

    private fun ByteBuf.readPayload(out: MutableList<Any>) {
        if (readableBytes() >= length) {
            val payload = readBytes(length)
            stage = Stage.OPCODE

            if (!unknown) {
                val codec = gamePackets.clientPackets.getCodec(opcode)
                val packet = codec.decode(session, payload.toJagBuf())
                out.add(packet)
            } else {
                Logger.warn("Received unknown client packet. [opcode: $opcode, length: $length, remaining: ${readableBytes()}].")
            }
        }
    }

    companion object {
        /**
         * The lengths of the client packets keyed by it's opcode.
         * These are needed to skip potential variable length packets which are not yet
         * implemented.
         *
         * Updated: Revision 204
         */
        private val PACKET_LENGTHS = mapOf(
            0 to 8,
            1 to 15,
            2 to 9,
            3 to 8,
            4 to 7,
            5 to 4,
            6 to -2,
            7 to 15,
            8 to 7,
            9 to 3,
            10 to 3,
            11 to -2,
            12 to 0,
            13 to 8,
            14 to 3,
            15 to 8,
            16 to 8,
            17 to -1,
            18 to -1,
            19 to -1,
            20 to 3,
            21 to 7,
            22 to 7,
            23 to 3,
            24 to 8,
            25 to 8,
            26 to 16,
            27 to 3,
            28 to -1,
            29 to 7,
            30 to 3,
            31 to 15,
            32 to 3,
            33 to 5,
            34 to 7,
            35 to -1,
            36 to -1,
            37 to 11,
            38 to -1,
            39 to 16,
            40 to 7,
            41 to 7,
            42 to -1,
            43 to -1,
            44 to 4,
            45 to 8,
            46 to 3,
            47 to 3,
            48 to 8,
            49 to 7,
            50 to 8,
            51 to 8,
            52 to 8,
            53 to -1,
            54 to 11,
            55 to 8,
            56 to 2,
            57 to 0,
            58 to 7,
            59 to -1,
            60 to -1,
            61 to -1,
            62 to 6,
            63 to -1,
            64 to 3,
            65 to 6,
            66 to 14,
            67 to 8,
            68 to -1,
            69 to 2,
            70 to -1,
            71 to 3,
            72 to -1,
            73 to 8,
            74 to -1,
            75 to -1,
            76 to 8,
            77 to -1,
            78 to 16,
            79 to 3,
            80 to 0,
            81 to -2,
            82 to 4,
            83 to 11,
            84 to 7,
            85 to 1,
            86 to 0,
            87 to 4,
            88 to 3,
            89 to 8,
            90 to 10,
            91 to 13,
            92 to 11,
            93 to -1,
            94 to -1,
            95 to 2,
            96 to 0,
            97 to 2,
            98 to -1,
            99 to 4,
            100 to 2,
            101 to 15,
            102 to 8,
            103 to 9,
            104 to 8,
            105 to 8,
            106 to 22
        )
    }
}