package org.paradigm.config

import org.koin.dsl.module

val CONFIG_MODULE = module {
    single { ServerConfig() }
    single { XteaConfig() }
}