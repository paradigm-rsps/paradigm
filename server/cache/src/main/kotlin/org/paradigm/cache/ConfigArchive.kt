package org.paradigm.cache

import org.paradigm.cache.config.EnumConfig
import org.paradigm.cache.config.ObjectConfig
import org.paradigm.cache.config.VarbitConfig
import org.paradigm.cache.config.VarpConfig

class ConfigArchive private constructor(
    val enums: EnumConfig,
    val objects: ObjectConfig,
    val varbits: VarbitConfig,
    val varps: VarpConfig
) {
    companion object {
        fun load(): ConfigArchive = ConfigArchive(
            EnumConfig.load(),
            ObjectConfig.load(),
            VarbitConfig.load(),
            VarpConfig.load()
        )
    }
}