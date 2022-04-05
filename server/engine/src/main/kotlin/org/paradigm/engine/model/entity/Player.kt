package org.paradigm.engine.model.entity

import org.paradigm.config.ServerConfig
import org.paradigm.engine.model.Privilege
import org.paradigm.engine.model.map.Tile
import org.paradigm.engine.net.Session

class Player internal constructor(val session: Session) : LivingEntity() {

    var username: String = ""
    var passwordHash: String = ""

    var displayName: String = ""

    var privilege: Privilege = Privilege.DEVELOPER

    var pid: Int = -1

    override var tile = Tile(
        ServerConfig.HOME_TILE.x,
        ServerConfig.HOME_TILE.y,
        ServerConfig.HOME_TILE.plane
    )

    internal fun init() {

    }

}