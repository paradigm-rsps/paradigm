package org.paradigm.api

import org.paradigm.cache.GameCache
import org.paradigm.common.inject
import org.paradigm.content.ContentModuleManager
import org.paradigm.content.annotation.ContentScriptDsl
import org.paradigm.engine.Engine
import org.paradigm.engine.event.Event
import org.paradigm.engine.event.EventBus
import org.paradigm.engine.event.action.EventListener
import org.paradigm.engine.model.world.World
import org.paradigm.engine.service.ServiceManager
import kotlin.reflect.KClass

val engine: Engine by inject()
val world: World by inject()
val cache: GameCache by inject()
val serviceManager: ServiceManager by inject()
val moduleManager: ContentModuleManager by inject()

val eventSubscribers = hashMapOf<KClass<*>, MutableList<EventListener<*>>>()

fun Any.unregisterEvents() {
    eventSubscribers[this::class]?.forEach { EventBus.unsubscribe(it) }
    eventSubscribers[this::class]?.clear()
}

@ContentScriptDsl
inline fun <reified T : Event> Any.onEvent(noinline block: (T).() -> Unit) {
    val listener = EventBus.subscribe<T>().then(block).listener
    eventSubscribers.getOrPut(this::class) { mutableListOf() }.add(listener)
}