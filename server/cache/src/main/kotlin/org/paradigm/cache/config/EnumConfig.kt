package org.paradigm.cache.config

import net.runelite.cache.ConfigType
import net.runelite.cache.IndexType
import net.runelite.cache.definitions.EnumDefinition
import net.runelite.cache.definitions.loaders.EnumLoader
import org.paradigm.cache.GameCache
import org.paradigm.common.inject

class EnumConfig(
    entries: MutableMap<Int, EnumDefinition> = mutableMapOf()
) : Map<Int, EnumDefinition> by entries {
    companion object {
        private val cache: GameCache by inject()

        fun load(): EnumConfig {
            val entries = mutableMapOf<Int, EnumDefinition>()

            val archive = cache.store.getIndex(IndexType.CONFIGS)
            val group = archive.getArchive(ConfigType.ENUM.id)
            val files = group.getFiles(cache.disk.loadArchive(group)).files
            val loader = EnumLoader()

            files.forEach { file ->
                val data = file.contents
                val def = loader.load(file.fileId, data)
                if (def != null) {
                    entries[file.fileId] = def
                }
            }

            return EnumConfig(entries)
        }
    }
}