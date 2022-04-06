package org.paradigm.engine.model.entity

import org.paradigm.config.ServerConfig
import org.paradigm.engine.model.manager.GpiManager
import org.paradigm.engine.model.manager.SceneManager
import org.paradigm.engine.model.Privilege
import org.paradigm.engine.model.manager.InterfaceManager
import org.paradigm.engine.model.map.Tile
import org.paradigm.engine.model.ui.DisplayMode
import org.paradigm.engine.net.Session
import org.tinylog.kotlin.Logger

class Player internal constructor(val session: Session) : LivingEntity() {

    init { session.player = this }

    val gpi = GpiManager(this)
    val scene = SceneManager(this)
    val ui = InterfaceManager(this)

    var username: String = ""
    var passwordHash: String = ""
    var displayName: String = ""
    var privilege: Privilege = Privilege.DEVELOPER
    var displayMode: DisplayMode = DisplayMode.FIXED
    var pid: Int = -1

    override var tile = Tile(
        ServerConfig.HOME_TILE.x,
        ServerConfig.HOME_TILE.y,
        ServerConfig.HOME_TILE.plane
    )

    internal fun init() {
        gpi.init()
        scene.init()
        ui.init()
    }

    fun logout() {
        Logger.info("[$username] has disconnected from the server.")
        world.players.removePlayer(this)
        session.channel.disconnect()
    }
}