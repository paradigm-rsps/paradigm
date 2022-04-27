package org.paradigm.cache

import net.runelite.cache.IndexType
import net.runelite.cache.definitions.LocationsDefinition
import net.runelite.cache.definitions.MapDefinition
import net.runelite.cache.definitions.loaders.LocationsLoader
import net.runelite.cache.definitions.loaders.MapLoader
import org.paradigm.common.inject
import org.paradigm.config.XteaConfig

class MapArchive private constructor(
    entries: MutableMap<Int, Pair<MapDefinition, LocationsDefinition>> = mutableMapOf()
) : Map<Int, Pair<MapDefinition, LocationsDefinition>> by entries {

    companion object {
        private val cache: GameCache by inject()

        const val MAX_REGIONS = 32768

        fun load(): MapArchive {
            val entries = hashMapOf<Int, Pair<MapDefinition, LocationsDefinition>>()

            val archive = cache.store.getIndex(IndexType.MAPS)
            for (regionId in 0 until MAX_REGIONS) {
                val x = regionId shr 8
                val y = regionId and 0xFF

                val map = archive.findArchiveByName("m${x}_${y}") ?: continue
                val loc = archive.findArchiveByName("l${x}_${y}") ?: continue

                val mapData = map.decompress(cache.disk.loadArchive(map))
                val mapDef = MapLoader().load(x, y, mapData)

                val locData = try {
                    loc.decompress(cache.disk.loadArchive(loc), XteaConfig.getRegionKey(regionId))
                } catch (e: Exception) {
                    continue
                }

                val locDef = LocationsLoader().load(x, y, locData)

                entries[regionId] = mapDef to locDef
            }

            return MapArchive(entries)
        }
    }
}