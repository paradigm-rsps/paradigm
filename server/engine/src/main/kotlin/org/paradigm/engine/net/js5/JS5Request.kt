package org.paradigm.engine.net.js5

import org.paradigm.engine.net.Message

data class JS5Request(val archive: Int, val group: Int, val priority: Boolean) : Message