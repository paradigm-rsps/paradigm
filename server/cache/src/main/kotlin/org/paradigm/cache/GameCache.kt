package org.paradigm.cache

import io.guthix.js5.Js5Cache
import io.guthix.js5.container.Js5Container
import io.guthix.js5.container.disk.Js5DiskStore
import io.guthix.js5.store.Js5CachedStore
import java.io.File

class GameCache(private val directory: File) : AutoCloseable {
    lateinit var cache: Js5Cache
    lateinit var disk: Js5DiskStore
    lateinit var store: Js5CachedStore
    lateinit var archiveCrcs: List<Int> private set

    lateinit var configArchive: ConfigArchive private set
    lateinit var mapArchive: MapArchive private set

    val archiveCount: Int get() = cache.archiveCount

    fun load() {
        directory.mkdirs()

        disk = Js5DiskStore.open(directory.toPath())
        store = Js5CachedStore.create(disk)
        cache = Js5Cache(store)

        loadArchives()
    }

    private fun loadArchives() {
        configArchive = ConfigArchive.load(cache.readArchive(ConfigArchive.id))
        mapArchive = MapArchive.load(cache.readArchive(MapArchive.id))
    }

    override fun close() {
        cache.close()
        store.close()
    }
}