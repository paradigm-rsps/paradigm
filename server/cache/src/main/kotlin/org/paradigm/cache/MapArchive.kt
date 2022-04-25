package org.paradigm.cache

import io.guthix.js5.Js5Archive
import io.guthix.js5.Js5File
import org.paradigm.config.XteaConfig
import org.tinylog.kotlin.Logger
import java.io.FileNotFoundException

class MapArchive(val regions: Map<Int, RegionMapFiles>) {

    class RegionMapFiles(val map: Js5File, val loc: Js5File)

    companion object {
        const val id: Int = 5

        fun load(archive: Js5Archive): MapArchive {
            val regions = mutableMapOf<Int, RegionMapFiles>()
            XteaConfig.regions.forEach { (regionId, xteaKey) ->
                try {
                    val regionX = regionId shr 8
                    val regionY = regionId and 0xFF

                    val mapFile = archive.readGroup("m${regionX}_$regionY")[0]
                        ?: throw FileNotFoundException("Failed to load map data for region: $regionId.")

                    val locFile = archive.readGroup("l${regionX}_$regionY", xteaKey)[0]
                        ?: throw FileNotFoundException("Failed to load loc data for region: $regionId.")

                    regions[regionId] = RegionMapFiles(mapFile, locFile)

                } catch (e: Exception) {
                    Logger.error(
                        "Failed to load file data for region ID: $regionId with XTEA key: [${
                            xteaKey.joinToString(
                                ", "
                            )
                        }]."
                    )
                }
            }
            return MapArchive(regions)
        }
    }
}