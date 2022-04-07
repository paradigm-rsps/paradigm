package org.paradigm.engine.event.action

import org.paradigm.engine.event.Event

@DslMarker
private annotation class EventListenerBuilderDsl

@EventListenerBuilderDsl
class EventListenerBuilder<T : Event>(private val events: MutableList<EventListener<*>>) {
    private var condition: (T).() -> Boolean = { true }

    fun where(predicate: (T).() -> Boolean): EventListenerBuilder<T> {
        this.condition = predicate
        return this
    }

    fun then(action: (T).() -> Unit) {
        events.add(EventListener(condition, action))
    }
}