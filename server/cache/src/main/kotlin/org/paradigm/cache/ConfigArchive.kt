package org.paradigm.cache

import org.paradigm.cache.config.EnumConfig

class ConfigArchive(
    enums: EnumConfig
) {
    companion object {
        fun load(): ConfigArchive = ConfigArchive(
            EnumConfig.load()
        )
    }
}