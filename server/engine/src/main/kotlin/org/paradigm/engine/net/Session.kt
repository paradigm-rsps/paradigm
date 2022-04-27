package org.paradigm.engine.net

import io.netty.channel.ChannelHandlerContext
import org.paradigm.engine.model.entity.Player
import org.paradigm.util.IsaacRandom
import kotlin.random.Random
import kotlin.random.nextLong

class Session(val ctx: ChannelHandlerContext) {

    lateinit var player: Player

    val channel get() = ctx.channel()

    var seed = Random.nextLong(0..Long.MAX_VALUE)

    var xteas = IntArray(4) { 0 }
    var reconnectXteas: IntArray? = null

    val encodeIsaac = IsaacRandom()
    val decodeIsaac = IsaacRandom()

    internal fun cycle() {

    }

    fun write(message: Message) = ctx.write(message)

    fun writeAndFlush(message: Message) = ctx.writeAndFlush(message)

    fun flush() = ctx.flush()
}