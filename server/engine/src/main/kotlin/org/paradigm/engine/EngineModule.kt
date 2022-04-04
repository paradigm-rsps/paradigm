package org.paradigm.engine

import org.koin.dsl.module
import org.paradigm.engine.model.World
import org.paradigm.engine.net.NetworkServer
import org.paradigm.engine.net.worldlist.WorldListServer
import org.paradigm.engine.service.ServiceManager
import org.paradigm.util.RSA

val ENGINE_MODULE = module {
    single { RSA() }
    single { Engine() }
    single { NetworkServer() }
    single { WorldListServer() }
    single { World() }
    single { ServiceManager() }
}