package org.paradigm.cache

import io.guthix.js5.Js5Cache
import io.guthix.js5.container.Js5Container
import io.guthix.js5.container.disk.Js5DiskStore
import java.io.File

class GameCache(private val directory: File) : AutoCloseable {
    lateinit var cache: Js5Cache
    lateinit var store: Js5DiskStore
    lateinit var archiveCrcs: List<Int> private set

    lateinit var configArchive: ConfigArchive private set

    val archiveCount: Int get() = cache.archiveCount

    fun load() {
        directory.mkdirs()

        store = Js5DiskStore.open(directory.toPath())
        cache = Js5Cache(store)

        val validator = cache.generateValidator(includeWhirlpool = false, includeSizes = false)
        val container = Js5Container(validator.encode())
        store.write(255, 255, container.encode())
        archiveCrcs = validator.archiveValidators.map { it.crc }.toList()

        loadArchives()
    }

    private fun loadArchives() {
        configArchive = ConfigArchive.load(cache.readArchive(ConfigArchive.id))
    }

    override fun close() {
        cache.close()
        store.close()
    }
}