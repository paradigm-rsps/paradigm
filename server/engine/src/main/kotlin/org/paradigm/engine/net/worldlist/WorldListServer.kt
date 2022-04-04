package org.paradigm.engine.net.worldlist

import io.netty.channel.ChannelFuture
import org.paradigm.common.inject
import org.paradigm.engine.net.NetworkServer
import org.paradigm.engine.net.worldlist.pipeline.WorldListChannelInitializer
import org.tinylog.kotlin.Logger

class WorldListServer {

    private val networkServer: NetworkServer by inject()

    val worlds = mutableListOf(
        WorldEntry(1, "127.0.0.1", "-", listOf(WorldType.MEMBERS), WorldLocation.UNITED_STATES, 0),
        WorldEntry(2, "127.0.0.1", "-", listOf(WorldType.MEMBERS, WorldType.TOURNAMENT), WorldLocation.UNITED_STATES, 0)
    )

    private lateinit var channelFuture: ChannelFuture

    fun start() {
        Logger.info("Starting world list network server.")

        val bootstrap = networkServer.bootstrap.clone()
        bootstrap.childHandler(WorldListChannelInitializer())
        channelFuture = bootstrap.bind(PORT).syncUninterruptibly()
    }

    fun stop() {
        Logger.info("Stopping world list network server.")
        channelFuture.channel().close().syncUninterruptibly()
    }

    companion object {
        private const val PORT = 80
    }
}