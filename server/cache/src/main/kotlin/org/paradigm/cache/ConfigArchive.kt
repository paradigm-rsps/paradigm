package org.paradigm.cache

import io.guthix.js5.Js5Archive
import org.paradigm.cache.config.EnumConfig
import org.paradigm.cache.config.ObjConfig

class ConfigArchive(
    val enumConfigs: Map<Int, EnumConfig<Any, Any>>,
    val objectConfigs: Map<Int, ObjConfig>
) {
    companion object {
        const val id: Int = 2

        fun load(archive: Js5Archive) = ConfigArchive(
            EnumConfig.load(archive.readGroup(EnumConfig.id)),
            ObjConfig.load(archive.readGroup(ObjConfig.id))
        )
    }
}