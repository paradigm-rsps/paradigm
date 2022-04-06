package org.paradigm.engine.model.entity

import org.paradigm.config.ServerConfig
import org.paradigm.engine.model.Appearance
import org.paradigm.engine.model.manager.GpiManager
import org.paradigm.engine.model.manager.SceneManager
import org.paradigm.engine.model.Privilege
import org.paradigm.engine.model.entity.update.PlayerUpdateFlag
import org.paradigm.engine.model.map.Tile
import org.paradigm.engine.model.ui.DisplayMode
import org.paradigm.engine.model.ui.GameInterface
import org.paradigm.engine.net.Session
import org.tinylog.kotlin.Logger

class Player internal constructor(val session: Session) : LivingEntity() {

    init {
        session.player = this
    }

    val gpi = GpiManager(this)
    val scene = SceneManager(this)
    val ui = GameInterface(this)

    override val size: Int = 1

    var username: String = ""
    var passwordHash: String = ""
    var displayName: String = ""
    var privilege: Privilege = Privilege.DEVELOPER
    var displayMode: DisplayMode = DisplayMode.FIXED
    var pid: Int = -1
    var skullIcon: Int = -1
    var prayerIcon: Int = -1
    var appearance: Appearance = Appearance.DEFAULT
    var stanceAnimations: IntArray = intArrayOf(808, 823, 819, 820, 821, 822, 824)
    var combatLevel: Int = 3

    val updateFlags = sortedSetOf<PlayerUpdateFlag>()

    override var tile = Tile(
        ServerConfig.HOME_TILE.x,
        ServerConfig.HOME_TILE.y,
        ServerConfig.HOME_TILE.plane
    )

    override var prevTile: Tile = tile
    override var followTile: Tile = tile

    internal fun init() {
        gpi.init()
        scene.init()
        ui.init()
        updateFlags.add(PlayerUpdateFlag.APPEARANCE)
    }

    fun logout() {
        Logger.info("[$username] has disconnected from the server.")
        world.players.removePlayer(this)
    }

    override suspend fun cycle() {
        queueCycle()
    }

}