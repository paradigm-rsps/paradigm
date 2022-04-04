package org.paradigm.util.buffer

sealed class AccessMode

object BIT_ACCESS : AccessMode()
object BYTE_ACCESS : AccessMode()