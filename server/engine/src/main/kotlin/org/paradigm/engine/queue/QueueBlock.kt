package org.paradigm.engine.queue

@JvmInline
value class QueueBlock(val block: suspend () -> Unit)