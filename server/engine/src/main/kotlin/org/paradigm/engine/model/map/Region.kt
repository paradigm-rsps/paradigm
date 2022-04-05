package org.paradigm.engine.model.map

@JvmInline
value class Region(val id: Int) {

    val x: Int get() = id shr 8

    val y: Int get() = id and 0xFF

    constructor(x: Int, y: Int) : this(
        (x shl 8) or y
    )

    companion object {
        const val SIZE = 64
    }
}