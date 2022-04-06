package org.paradigm.engine.sync

interface SyncTask {
    suspend fun execute()
}