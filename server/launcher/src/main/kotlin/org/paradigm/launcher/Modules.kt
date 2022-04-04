package org.paradigm.launcher

import org.paradigm.cache.CACHE_MODULE
import org.paradigm.config.CONFIG_MODULE
import org.paradigm.engine.ENGINE_MODULE

val DI_MODULES = listOf(
    ENGINE_MODULE,
    CONFIG_MODULE,
    CACHE_MODULE
)