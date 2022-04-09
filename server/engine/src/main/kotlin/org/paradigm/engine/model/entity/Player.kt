package org.paradigm.engine.model.entity

import org.paradigm.config.ServerConfig
import org.paradigm.engine.event.EventBus
import org.paradigm.engine.event.impl.LogoutEvent
import org.paradigm.engine.model.Appearance
import org.paradigm.engine.model.manager.GpiManager
import org.paradigm.engine.model.manager.SceneManager
import org.paradigm.engine.model.Privilege
import org.paradigm.engine.model.entity.update.PlayerUpdateFlag
import org.paradigm.engine.model.manager.InterfaceManager
import org.paradigm.engine.model.map.Tile
import org.paradigm.engine.model.pathfinder.PlayerPathFinder
import org.paradigm.engine.model.ui.DisplayMode
import org.paradigm.engine.net.Session
import org.paradigm.engine.net.packet.server.RunClientScriptPacket
import org.tinylog.kotlin.Logger

class Player internal constructor(val session: Session) : LivingEntity() {

    init {
        session.player = this
    }

    val gpi = GpiManager(this)
    val scene = SceneManager(this)
    val ui = InterfaceManager(this)

    override val size: Int = 1

    var username: String = ""
    var passwordHash: String = ""
    var displayName: String = ""
    var privilege: Privilege = Privilege.DEVELOPER
    var displayMode: DisplayMode = DisplayMode.RESIZABLE_CLASSIC
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

    override val pathfinder = PlayerPathFinder()

    internal fun init() {
        gpi.init()
        scene.init()
    }

    fun logout() {
        Logger.info("[$username] has disconnected from the server.")
        world.players.removePlayer(this)
        EventBus.publish(LogoutEvent(this))
    }

    override suspend fun cycle() {
        queueCycle()
    }

    fun runClientScript(id: Int, vararg params: Any) {
        session.write(RunClientScriptPacket(id, params))
    }
}