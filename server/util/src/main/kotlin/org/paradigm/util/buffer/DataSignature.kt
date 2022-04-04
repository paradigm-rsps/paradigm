package org.paradigm.util.buffer

sealed class DataSignature

object UNSIGNED : DataSignature()
object SIGNED : DataSignature()