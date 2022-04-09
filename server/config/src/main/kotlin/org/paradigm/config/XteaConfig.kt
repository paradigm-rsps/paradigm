package org.paradigm.config

import com.uchuhimo.konf.Config
import com.uchuhimo.konf.toValue
import org.paradigm.common.inject
import org.tinylog.kotlin.Logger
import java.io.File
import java.io.FileNotFoundException

class XteaConfig {

    private var config = Config()
    private val file = File("data/cache/xteas.json")

    private val xteaKeys = mutableMapOf<Int, IntArray>()

    fun load() {
        /*
         * Don't load XTEA keys again if already loaded.
         */
        if (xteaKeys.isNotEmpty()) {
            return
        }

        if (!file.exists()) {
            throw FileNotFoundException("Could not load region XTEA keys config file from: data/cache/xteas.json.")
        }

        val entries = config.from.json.file(file).toValue<Array<XteaEntry>>()
        entries.forEach { xteaKeys[it.mapsquare] = it.key }

        Logger.info("Loaded ${xteaKeys.keys.size} region XTEA keys.")
    }

    operator fun get(regionId: Int): IntArray = xteaKeys[regionId] ?: IntArray(4) { 0 }

    data class XteaEntry(val mapsquare: Int, val key: IntArray) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as XteaEntry

            if (mapsquare != other.mapsquare) return false
            if (!key.contentEquals(other.key)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = mapsquare
            result = 31 * result + key.contentHashCode()
            return result
        }
    }

    companion object {
        private val config: XteaConfig by inject()

        init {
            config.load()
        }

        val regions get() = config.xteaKeys

        fun getRegionKey(regionId: Int) = config[regionId]
    }
}