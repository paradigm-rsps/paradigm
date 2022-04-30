package org.paradigm.engine

import org.koin.dsl.module
import org.paradigm.cache.GameCache
import org.paradigm.content.ContentModuleManager
import org.paradigm.engine.model.world.World
import org.paradigm.engine.net.NetworkServer
import org.paradigm.engine.net.game.GamePackets
import org.paradigm.engine.net.http.HttpServer
import org.paradigm.engine.model.worldlist.WorldList
import org.paradigm.engine.serializer.JsonPlayerSerializer
import org.paradigm.engine.serializer.PlayerSerializer
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
    single { GameCache() }
    single { ContentModuleManager() }
    single<PlayerSerializer> { JsonPlayerSerializer() }
}