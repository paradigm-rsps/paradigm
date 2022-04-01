package org.paradigm.engine

import org.koin.dsl.module

val ENGINE_MODULE = module {
    single { Engine() }
}