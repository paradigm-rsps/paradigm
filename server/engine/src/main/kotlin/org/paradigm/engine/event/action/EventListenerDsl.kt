package org.paradigm.engine.event.action

import org.paradigm.engine.event.Event

@DslMarker
private annotation class EventListenerBuilderDsl

@EventListenerBuilderDsl
class EventListenerBuilder<T : Event>(private val listeners: MutableList<EventListener<*>>) {
    private var condition: (T).() -> Boolean = { true }

    lateinit var listener: EventListener<T> private set

    fun where(predicate: (T).() -> Boolean): EventListenerBuilder<T> {
        this.condition = predicate
        return this
    }

    fun then(action: (T).() -> Unit): EventListenerBuilder<T> {
        listener = EventListener(condition, action)
        listeners.add(listener)
        return this
    }
}