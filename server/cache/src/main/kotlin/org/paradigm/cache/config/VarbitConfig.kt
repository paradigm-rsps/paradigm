package org.paradigm.cache.config

import net.runelite.cache.ConfigType
import net.runelite.cache.IndexType
import net.runelite.cache.definitions.VarbitDefinition
import net.runelite.cache.definitions.loaders.VarbitLoader
import org.paradigm.cache.GameCache
import org.paradigm.common.inject

class VarbitConfig private constructor(
    entries: MutableMap<Int, VarbitDefinition> = mutableMapOf()
) : Map<Int, VarbitDefinition> by entries {
    companion object {
        private val cache: GameCache by inject()

        fun load(): VarbitConfig {
            val entries = mutableMapOf<Int, VarbitDefinition>()

            val archive = cache.store.getIndex(IndexType.CONFIGS)
            val group = archive.getArchive(ConfigType.VARBIT.id)
            val files = group.getFiles(cache.disk.loadArchive(group)).files

            files.forEach { file ->
                val loader = VarbitLoader()
                val varbitDef = loader.load(file.fileId, file.contents)
                entries[varbitDef.id] = varbitDef
            }

            return VarbitConfig(entries)
        }
    }
}