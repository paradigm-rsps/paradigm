package org.paradigm.cache.impl

import net.runelite.cache.io.InputStream
import java.io.IOException

class VarPlayerDefinition(
    var id: Int = 0,
    var type: Int = 0
) {

    class Loader {
        fun load(id: Int, data: ByteArray): VarPlayerDefinition {
            val def = VarPlayerDefinition()
            val buf = InputStream(data)
            def.id = id
            while (true) {
                when (buf.readUnsignedByte()) {
                    0 -> break
                    5 -> def.type = buf.readUnsignedShort()
                    else -> throw IOException()
                }
            }
            return def
        }
    }
}