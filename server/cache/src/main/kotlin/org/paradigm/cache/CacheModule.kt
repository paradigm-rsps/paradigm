package org.paradigm.cache

import org.koin.dsl.module
import java.io.File

val CACHE_MODULE = module {
    single { GameCache(File("data/cache/")) }
}