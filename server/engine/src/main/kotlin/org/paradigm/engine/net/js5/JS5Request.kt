package org.paradigm.engine.net.js5

import org.paradigm.engine.net.Message

sealed class JS5Request : Message {
    class FileData(val archive: Int, val group: Int) : JS5Request()
    class EncryptKeyUpdate(val key: Int, val offset: Int) : JS5Request()
}
