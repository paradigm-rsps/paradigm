package org.paradigm.engine.queue

@JvmInline
value class QueueAction(val action: suspend () -> Unit)