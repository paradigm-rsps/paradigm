package org.paradigm.api

import org.paradigm.cache.GameCache
import org.paradigm.common.inject
import org.paradigm.engine.Engine
import org.paradigm.engine.event.Event
import org.paradigm.engine.event.EventBus
import org.paradigm.engine.model.World
import org.paradigm.engine.service.ServiceManager

val engine: Engine by inject()
val world: World by inject()
val cache: GameCache by inject()
val serviceManager: ServiceManager by inject()

inline fun <reified T : Event> onEvent(noinline action: (T).() -> Unit) = EventBus.subscribe<T>().then(action)
inline fun <reified T : Event> onEvent() = EventBus.subscribe<T>()