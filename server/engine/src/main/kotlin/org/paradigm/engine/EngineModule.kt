package org.paradigm.engine

import org.koin.dsl.module
import org.paradigm.engine.model.World
import org.paradigm.engine.net.NetworkServer
import org.paradigm.engine.net.worldlist.WorldListServer
import org.paradigm.engine.service.ServiceManager

val ENGINE_MODULE = module {
    single { Engine() }
    single { NetworkServer() }
    single { WorldListServer() }
    single { World() }
    single { ServiceManager() }
}