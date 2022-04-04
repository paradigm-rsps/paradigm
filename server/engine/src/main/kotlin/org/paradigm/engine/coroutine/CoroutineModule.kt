package org.paradigm.engine.coroutine

import com.google.common.util.concurrent.ThreadFactoryBuilder
import org.koin.dsl.module
import java.util.concurrent.Executors

val COROUTINE_MODULE = module {
    single {
        ThreadFactoryBuilder()
            .setDaemon(false)
            .setNameFormat("engine-thread")
            .build()
            .let { EngineCoroutineScope(Executors.newSingleThreadExecutor(it)) }
    }
}