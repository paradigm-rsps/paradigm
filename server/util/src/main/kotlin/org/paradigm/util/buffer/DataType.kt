package org.paradigm.util.buffer

sealed class DataType(val size: Int)

object BYTE : DataType(1)
object SHORT : DataType(2)
object MEDIUM : DataType(3)
object INT : DataType(4)
object LONG : DataType(8)
object BYTES : DataType(-1)
object SMART : DataType(-1)
object STRING : DataType(-1)