package org.paradigm.engine

import org.koin.dsl.module
import org.paradigm.cache.GameCache
import org.paradigm.engine.net.NetworkServer
import java.io.File

val ENGINE_MODULE = module {
    single { Engine() }
    single { NetworkServer() }
    single { GameCache(File("data/cache/")) }
}