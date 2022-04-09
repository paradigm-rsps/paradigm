package org.paradigm.cache

import io.guthix.js5.Js5Archive
import org.paradigm.cache.config.EnumConfig
import org.paradigm.cache.config.ObjectConfig

class ConfigArchive(
    val enumConfigs: Map<Int, EnumConfig<Any, Any>>,
    val objectConfigs: Map<Int, ObjectConfig>
) {
    companion object {
        const val id: Int = 2

        fun load(archive: Js5Archive) = ConfigArchive(
            EnumConfig.load(archive.readGroup(EnumConfig.id)),
            ObjectConfig.load(archive.readGroup(ObjectConfig.id))
        )
    }
}