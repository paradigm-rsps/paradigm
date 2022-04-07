package org.paradigm.engine

import org.koin.dsl.module
import org.paradigm.engine.model.World
import org.paradigm.engine.net.NetworkServer
import org.paradigm.engine.net.game.GamePackets
import org.paradigm.engine.net.http.HttpServer
import org.paradigm.engine.net.worldlist.WorldList
import org.paradigm.engine.service.ServiceManager
import org.paradigm.util.RSA

val ENGINE_MODULE = module {
    single { RSA() }
    single { Engine() }
    single { NetworkServer() }
    single { World() }
    single { ServiceManager() }
    single { GamePackets() }
    single { HttpServer() }
    single { WorldList() }
}