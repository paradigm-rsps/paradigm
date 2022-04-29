package org.paradigm.engine.event.action

import org.paradigm.engine.event.Event
import kotlin.reflect.KClass

class EventListener<T : Event>(
    val condition: (T).() -> Boolean,
    val action: (T).() -> Unit
)