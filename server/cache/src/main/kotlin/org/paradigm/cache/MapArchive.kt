package org.paradigm.cache

import io.guthix.js5.Js5Archive
import org.paradigm.cache.map.RegionDefinition
import org.paradigm.config.XteaConfig
import java.io.FileNotFoundException

class MapArchive(val regions: Map<Int, RegionDefinition>) {
    companion object {
        const val id: Int = 5

        fun load(archive: Js5Archive): MapArchive {
            val regions = mutableMapOf<Int, RegionDefinition>()

            XteaConfig.regions.forEach { (regionId, xteaKey) ->
                val regionX = regionId shr 8
                val regionY = regionId and 0xFF

                val mapFile = archive.readGroup("m${regionX}_${regionY}")[0]
                    ?: throw FileNotFoundException("Failed to read region map data from group: 'm${regionX}_${regionY}'.")

                val locFile = archive.readGroup("l${regionX}_${regionY}", xteaKey)[0]
                    ?: throw FileNotFoundException("Failed to read region loc data from group: 'l${regionX}_${regionY}'.")

                regions[regionId] = RegionDefinition.decode(mapFile.data, locFile.data, regionX, regionY)
            }

            return MapArchive(regions)
        }
    }
}