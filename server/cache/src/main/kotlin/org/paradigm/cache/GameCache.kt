package org.paradigm.cache

import net.runelite.cache.fs.Store
import net.runelite.cache.fs.jagex.DiskStorage
import java.io.File

class GameCache {

    lateinit var store: Store private set

    val disk: DiskStorage get() = store.storage as DiskStorage
    val archiveCount get() = store.indexes.size

    /*
     * Cache Archive Data Models
     */
    lateinit var configs: ConfigArchive private set
    lateinit var maps: MapArchive private set

    fun load(directory: File) {
        store = Store(directory)
        store.load()

        /*
         * Load Archive Data
         */
        configs = ConfigArchive.load()
        maps = MapArchive.load()
    }

}