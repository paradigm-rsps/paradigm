package org.paradigm.cache

import io.guthix.js5.Js5Archive
import org.paradigm.cache.config.*

class ConfigArchive(
    val enumConfigs: Map<Int, EnumConfig<Any, Any>>,
    val objectConfigs: Map<Int, ObjectConfig>,
    val varbitConfigs: Map<Int, VarbitConfig>,
    val varClientConfigs: Map<Int, VarClientConfig>,
    val varPlayerConfigs: Map<Int, VarPlayerConfig>
) {
    companion object {
        const val id: Int = 2

        fun load(archive: Js5Archive) = ConfigArchive(
            EnumConfig.load(archive.readGroup(EnumConfig.id)),
            ObjectConfig.load(archive.readGroup(ObjectConfig.id)),
            VarbitConfig.load(archive.readGroup(VarbitConfig.id)),
            VarClientConfig.load(archive.readGroup(VarClientConfig.id)),
            VarPlayerConfig.load(archive.readGroup(VarPlayerConfig.id))
        )
    }
}