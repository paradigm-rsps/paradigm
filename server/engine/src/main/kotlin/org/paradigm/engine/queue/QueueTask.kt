package org.paradigm.engine.queue

import org.paradigm.engine.coroutine.EngineCoroutineTask

@JvmInline
value class QueueTask(val task: EngineCoroutineTask)