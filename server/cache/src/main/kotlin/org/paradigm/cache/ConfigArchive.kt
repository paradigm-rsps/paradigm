package org.paradigm.cache

import org.paradigm.cache.config.EnumConfig
import org.paradigm.cache.config.ObjectConfig

class ConfigArchive private constructor(
    val enums: EnumConfig,
    val objects: ObjectConfig
) {
    companion object {
        fun load(): ConfigArchive = ConfigArchive(
            EnumConfig.load(),
            ObjectConfig.load()
        )
    }
}