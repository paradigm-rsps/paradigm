package org.paradigm.engine.model.entity.update

import io.netty.buffer.ByteBuf
import org.paradigm.engine.model.entity.Player

class PlayerUpdateFlag(
    order: Int,
    mask: Int,
    val encode: ByteBuf.(Player) -> Unit
) : UpdateFlag(order, mask) {
    companion object {

    }
}