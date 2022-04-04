package org.paradigm.engine.net.worldlist.pipeline

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.http.HttpRequestDecoder
import io.netty.handler.codec.http.HttpResponseEncoder

class WorldListChannelInitializer : ChannelInitializer<SocketChannel>() {

    override fun initChannel(ch: SocketChannel) {
        val p = ch.pipeline()
        p.addLast(HttpRequestDecoder())
        p.addLast(HttpResponseEncoder())
        p.addLast(WorldListEncoder())
        p.addLast(WorldListChannelHandler())
    }
}