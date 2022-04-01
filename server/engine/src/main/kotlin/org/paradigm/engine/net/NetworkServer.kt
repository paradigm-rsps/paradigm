package org.paradigm.engine.net

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import org.paradigm.config.ServerConfig
import org.tinylog.kotlin.Logger
import java.net.InetSocketAddress
import kotlin.system.exitProcess

class NetworkServer {

    private val bootstrap = ServerBootstrap()
    private val bossGroup = NioEventLoopGroup(2)
    private val workerGroup = NioEventLoopGroup(1)

    private val channelInitializer = NetworkChannelInitializer()

    init {
        bootstrap.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel::class.java)
            .childHandler(channelInitializer)
            .childOption(ChannelOption.TCP_NODELAY, true)
            .childOption(ChannelOption.SO_KEEPALIVE, true)
    }

    fun start() {
        Logger.info("Starting networking server.")

        val socketAddress = InetSocketAddress(ServerConfig.NETWORK.ADDRESS, ServerConfig.NETWORK.PORT)
        bootstrap.bind(socketAddress).addListener {
            if(it.isSuccess) {
                onBindSuccess(socketAddress)
            } else {
                onBindFailure(socketAddress, it.cause())
            }
        }
    }

    fun stop() {
        Logger.info("Stopping networking server.")

        bossGroup.shutdownGracefully()
        workerGroup.shutdownGracefully()
    }

    private fun onBindSuccess(address: InetSocketAddress) {
        Logger.info("Network server listening for connections on ${address.hostString}:${address.port}...")
    }

    private fun onBindFailure(address: InetSocketAddress, cause: Throwable) {
        Logger.error(cause) { "An error occurred while binding network server to address: ${address.hostString}:${address.port}. Exiting process." }
        exitProcess(0)
    }

    class NetworkChannelInitializer : ChannelInitializer<SocketChannel>() {
        override fun initChannel(ch: SocketChannel) {

        }
    }
}