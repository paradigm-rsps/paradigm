package org.paradigm.engine.net.js5

import io.netty.buffer.ByteBuf
import io.netty.buffer.DefaultByteBufHolder
import org.paradigm.engine.net.Message

data class JS5Response(
    val archive: Int,
    val group: Int,
    val compressionType: Int,
    val compressedSize: Int,
    val data: ByteBuf
) : DefaultByteBufHolder(data), Message