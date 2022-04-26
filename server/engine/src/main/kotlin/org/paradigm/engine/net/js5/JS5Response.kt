package org.paradigm.engine.net.js5

import org.paradigm.engine.net.Message

class JS5Response(val archive: Int, val group: Int, val data: ByteArray) : Message