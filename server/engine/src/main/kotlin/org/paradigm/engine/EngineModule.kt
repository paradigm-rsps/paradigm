package org.paradigm.engine

import org.koin.dsl.module
import org.paradigm.engine.net.NetworkServer

val ENGINE_MODULE = module {
    single { Engine() }
    single { NetworkServer() }
}