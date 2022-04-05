package org.paradigm.config

import com.uchuhimo.konf.Config
import com.uchuhimo.konf.ConfigSpec
import com.uchuhimo.konf.Item
import com.uchuhimo.konf.source.toml
import com.uchuhimo.konf.source.toml.toToml
import org.paradigm.common.inject
import java.io.File

class ServerConfig {

    private val file = File("data/configs/server.toml")
    private var config = Config()

    fun load() {
        if(!file.exists()) {
            config = Config { addSpec(Spec) }
            save()
        }

        config = Config { addSpec(Spec) }.from.toml.file(file)
        save()
    }

    fun save() {
        config.toToml.toFile(file)
    }

    operator fun <T> get(item: Item<T>): T = config[item]

    operator fun <T> set(item: Item<T>, value: T) {
        config[item] = value
    }

    object Spec : ConfigSpec("server") {
        val name by optional("Paradigm", "name")
        val revision by optional(204, "revision")
        val devMode by optional(true, "dev-mode")
        val tickRate by optional(600L, "tick-rate")

        object Network : ConfigSpec("network") {
            val address by optional("0.0.0.0", "address")
            val port by optional(43594, "port")
        }

        object HomeTile : ConfigSpec("home-tile") {
            val x by optional(3221, "x")
            val y by optional(3218, "y")
            val plane by optional(0, "plane")
        }
    }

    companion object {
        private val config: ServerConfig by inject()

        init {
            config.load()
        }

        val NAME get() = config[Spec.name]
        val REVISION get() = config[Spec.revision]
        val DEV_MODE get() = config[Spec.devMode]
        val TICK_RATE get() = config[Spec.tickRate]

        val NETWORK = NetworkCompanion(config)
        class NetworkCompanion(private val config: ServerConfig) {
            val ADDRESS get() = config[Spec.Network.address]
            val PORT get() = config[Spec.Network.port]
        }

        val HOME_TILE = HomeTileCompanion(config)
        class HomeTileCompanion(private val config: ServerConfig) {
            val x get() = config[Spec.HomeTile.x]
            val y get() = config[Spec.HomeTile.y]
            val plane get() = config[Spec.HomeTile.plane]
        }
    }
}