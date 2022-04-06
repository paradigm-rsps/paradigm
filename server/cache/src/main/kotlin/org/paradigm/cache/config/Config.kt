package org.paradigm.cache.config

import io.guthix.buffer.readString
import io.guthix.buffer.writeString
import io.guthix.js5.Js5Group
import io.netty.buffer.ByteBuf

abstract class Config(open val id: Int) {

    internal fun ByteBuf.writeOpcode(opcode: Int): ByteBuf = writeByte(opcode)

    internal fun ByteBuf.writeParams(params: MutableMap<Int, Any>) {
        writeByte(params.size)
        for((key, value) in params) {
            val isString = value is String
            writeByte(if(isString) 1 else 0)
            writeMedium(key)
            if(isString) writeString(value as String) else writeInt(value as Int)
        }
    }
}

abstract class ConfigCompanion<out T : Config> {

    abstract val id: Int

    abstract fun decode(id: Int, data: ByteBuf): T

    fun load(group: Js5Group): Map<Int, T> {
        val configs = mutableMapOf<Int, T>()
        group.forEach { (id, file) ->
            configs[id] = decode(id, file.data)
        }
        return configs
    }

    internal fun ByteBuf.readParams(): MutableMap<Int, Any> {
        val amount = readUnsignedByte()
        val params = mutableMapOf<Int, Any>()
        for(i in 0 until amount) {
            val isString = readUnsignedByte().toInt() == 1
            val index = readUnsignedMedium()
            val value: Any = if(isString) readString() else readInt()
            params[index] =  value
        }
        return params
    }
}