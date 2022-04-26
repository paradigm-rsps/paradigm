package org.paradigm.launcher

import org.paradigm.config.CONFIG_MODULE
import org.paradigm.content.CONTENT_MODULE
import org.paradigm.engine.ENGINE_MODULE
import org.paradigm.engine.coroutine.COROUTINE_MODULE

val DI_MODULES = listOf(
    ENGINE_MODULE,
    COROUTINE_MODULE,
    CONFIG_MODULE,
    CONTENT_MODULE
)