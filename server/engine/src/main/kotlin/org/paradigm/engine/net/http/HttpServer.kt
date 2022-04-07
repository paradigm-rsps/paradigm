package org.paradigm.engine.net.http

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.http.HttpObjectAggregator
import io.netty.handler.codec.http.HttpServerCodec
import org.tinylog.kotlin.Logger
import kotlin.system.exitProcess

class HttpServer {

    private val handler = HttpRequestHandler()

    private val bootstrap = ServerBootstrap()
        .group(NioEventLoopGroup())
        .channel(NioServerSocketChannel::class.java)
        .childHandler(HttpChannelInitializer(handler))
        .childOption(ChannelOption.SO_REUSEADDR, true)
        .childOption(ChannelOption.TCP_NODELAY, true)
        .childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30_000)

    fun start() {
        Logger.info("Starting HTTP network server.")
        bootstrap.bind("0.0.0.0", 80).sync().addListener {
            if (!it.isSuccess) {
                Logger.error(it.cause()) { "Failed to bind HTTP network server." }
                exitProcess(1)
            }
        }
    }

    private class HttpChannelInitializer(val handler: HttpRequestHandler) : ChannelInitializer<SocketChannel>() {
        override fun initChannel(ch: SocketChannel) {
            ch.pipeline().addLast("codec", HttpServerCodec())
            ch.pipeline().addLast("aggregator", HttpObjectAggregator(2048))
            ch.pipeline().addLast("handler", handler)
        }
    }
}