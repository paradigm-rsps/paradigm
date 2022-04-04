package org.paradigm.engine

import org.koin.dsl.module
import org.paradigm.engine.net.NetworkServer
import org.paradigm.engine.net.worldlist.WorldListServer

val ENGINE_MODULE = module {
    single { Engine() }
    single { NetworkServer() }
    single { WorldListServer() }
}