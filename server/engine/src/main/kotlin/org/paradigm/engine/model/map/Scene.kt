package org.paradigm.engine.model.map

@JvmInline
value class Scene(private val packed: Int) {

    val x: Int get() = packed and 0xFFFF

    val y: Int get() = (packed shr 16) and 0xFFFF

    constructor(x: Int, y: Int) : this(
        (x and 0xFFFF) or ((y and 0xFFFF) shl 16)
    )

    companion object {
        const val SIZE = 104
    }
}