package org.paradigm.api

import org.paradigm.cache.GameCache
import org.paradigm.common.inject
import org.paradigm.content.ContentModuleManager
import org.paradigm.engine.Engine
import org.paradigm.engine.model.world.World
import org.paradigm.engine.service.ServiceManager

val engine: Engine by inject()
val world: World by inject()
val cache: GameCache by inject()
val serviceManager: ServiceManager by inject()
val moduleManager: ContentModuleManager by inject()