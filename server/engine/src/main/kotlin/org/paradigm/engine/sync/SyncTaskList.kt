package org.paradigm.engine.sync

import org.paradigm.engine.sync.player.PlayerPostSyncTask
import org.paradigm.engine.sync.player.PlayerPreSyncTask
import org.paradigm.engine.sync.player.PlayerSyncTask

class SyncTaskList(
    private val tasks: MutableList<SyncTask> = mutableListOf()
) : List<SyncTask> by tasks {

    internal fun init() {
        tasks.add(PlayerPreSyncTask())
        tasks.add(PlayerSyncTask())
        tasks.add(PlayerPostSyncTask())
    }

}