package org.paradigm.cache.config

import net.runelite.cache.ConfigType
import net.runelite.cache.IndexType
import org.paradigm.cache.GameCache
import org.paradigm.cache.impl.VarPlayerDefinition
import org.paradigm.common.inject

class VarpConfig private constructor(
    entries: MutableMap<Int, VarPlayerDefinition> = mutableMapOf()
) : Map<Int, VarPlayerDefinition> by entries {
    companion object {
        private val cache: GameCache by inject()

        fun load(): VarpConfig {
            val entries = mutableMapOf<Int, VarPlayerDefinition>()

            val archive = cache.store.getIndex(IndexType.CONFIGS)
            val group = archive.getArchive(ConfigType.VARPLAYER.id)
            val files = group.getFiles(cache.disk.loadArchive(group)).files

            files.forEach { file ->
                val loader = VarPlayerDefinition.Loader()
                val def = loader.load(file.fileId, file.contents)
                entries[def.id] = def
            }

            return VarpConfig(entries)
        }
    }
}