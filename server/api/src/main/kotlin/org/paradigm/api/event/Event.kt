package org.paradigm.api.event

import org.paradigm.engine.event.Event
import org.paradigm.engine.event.EventBus
import org.paradigm.engine.event.action.EventListener
import kotlin.reflect.KClass

val eventSubscribers = hashMapOf<KClass<*>, MutableList<EventListener<*>>>()

fun Any.unregisterEvents() {
    eventSubscribers[this::class]?.forEach { EventBus.unsubscribe(it) }
    eventSubscribers[this::class]?.clear()
}

inline fun <reified T : Event> Any.onEvent(noinline block: (T).() -> Unit) {
    val listener = EventBus.subscribe<T>().then(block).listener
    eventSubscribers.getOrPut(this::class) { mutableListOf() }.add(listener)
}