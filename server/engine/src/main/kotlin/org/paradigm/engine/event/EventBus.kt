package org.paradigm.engine.event

import org.paradigm.engine.event.action.EventListener
import org.paradigm.engine.event.action.EventListenerBuilder
import kotlin.reflect.KClass

object EventBus {
    val events = mutableMapOf<KClass<out Event>, MutableList<EventListener<*>>>()

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T : Event> publish(event: T): Boolean {
        val events = (events[event::class] as? List<EventListener<T>>) ?: return false
        val filtered = events.filter { it.condition(event) }
        filtered.forEach {
            it.action(event)
        }
        return filtered.isNotEmpty()
    }

    inline fun <reified T : Event> subscribe(): EventListenerBuilder<T> =
        EventListenerBuilder(events.computeIfAbsent(T::class) { mutableListOf() })

}