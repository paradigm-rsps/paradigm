package org.paradigm.engine.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executor

data class EngineCoroutineScope(private val executor: Executor)
    : CoroutineScope by CoroutineScope(executor.asCoroutineDispatcher())