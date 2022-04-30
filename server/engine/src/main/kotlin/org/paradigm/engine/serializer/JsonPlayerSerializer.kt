package org.paradigm.engine.serializer

import org.json.JSONObject
import org.paradigm.engine.model.Appearance
import org.paradigm.engine.model.Direction
import org.paradigm.engine.model.Gender
import org.paradigm.engine.model.Privilege
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.map.Tile
import org.paradigm.engine.model.ui.DisplayMode
import org.paradigm.engine.net.Session
import org.paradigm.util.Base37
import org.paradigm.util.SHA256
import org.tinylog.kotlin.Logger
import java.io.File
import java.io.FileWriter

class JsonPlayerSerializer : PlayerSerializer {

    override fun loadPlayer(session: Session, username: String, password: String): Player? {
        val file = File("data/players/${username.toBase37()}.json")

        if (!file.exists()) {
            return createNewPlayer(session, username, password)
        }

        val player = readPlayerData(session, username)

        /*
         * Validate the loaded player's provided username and password.
         */
        val sanitizedUsername = username.toBase37().fromBase37()
        val hashedPassword = SHA256.hash(password)

        if (player.username != sanitizedUsername || player.passwordHash != hashedPassword) {
            Logger.info("Invalid login attempt for username: ${player.username}.")
            return null
        }

        return player
    }

    override fun savePlayer(player: Player) = writePlayerData(player)

    private fun createNewPlayer(session: Session, username: String, password: String): Player {
        Logger.info("Creating new player save for username: '$username'.")

        val player = Player(session)
        player.username = username.sanitize()
        player.passwordHash = SHA256.hash(password)

        /*
         * Save the newly created player to the disk.
         */
        savePlayer(player)

        return player
    }

    private fun readPlayerData(session: Session, username: String): Player {
        val file = File("data/players/${username.toBase37()}.json")
        val json = JSONObject(file.readText(Charsets.UTF_8))

        val player = Player(session)

        /*
         * Read the fields which are needed to create a fresh player instance.
         */
        player.username = json.getString("username")
        player.passwordHash = json.getString("password")
        player.displayName = json.getString("display-name")
        player.privilege = json.getEnum(Privilege::class.java, "privilege")
        player.displayMode = json.getEnum(DisplayMode::class.java, "display-mode")
        player.combatLevel = json.getInt("combat-level")
        player.running = json.getBoolean("running")
        player.invisible = json.getBoolean("invisible")
        player.direction = json.getEnum(Direction::class.java, "direction")
        json.getJSONObject("tile").also {
            player.tile = Tile(it.getInt("x"), it.getInt("y"), it.getInt("plane"))
        }
        json.getJSONObject("appearance").also {
            val styles = it.getJSONArray("styles").map { it as Int }.toIntArray()
            val colors = it.getJSONArray("colors").map { it as Int }.toIntArray()
            val gender = it.getEnum(Gender::class.java, "gender")
            player.appearance = Appearance(styles, colors, gender)
        }

        return player
    }

    private fun writePlayerData(player: Player) {
        val file = File("data/players/${player.username.toBase37()}.json")
        if (file.exists()) file.deleteRecursively()

        val json = JSONObject()

        json.put("username", player.username.sanitize())
        json.put("password", player.passwordHash)
        json.put("display-name", player.displayName)
        json.put("privilege", player.privilege)
        json.put("display-mode", player.displayMode)
        json.put("combat-level", player.combatLevel)
        json.put("running", player.running)
        json.put("invisible", player.invisible)
        json.put("direction", player.direction)
        JSONObject().also {
            it.put("x", player.tile.x)
            it.put("y", player.tile.y)
            it.put("plane", player.tile.plane)
            json.put("tile", it)
        }
        JSONObject().also {
            it.put("styles", player.appearance.styles)
            it.put("colors", player.appearance.colors)
            it.put("gender", player.appearance.gender)
            json.put("appearance", it)
        }

        FileWriter(file).use { it.write(json.toString()) }
    }

    private fun String.toBase37(): Long = Base37.encode(this)
    private fun Long.fromBase37(): String = Base37.decode(this) ?: ""

    private fun String.sanitize(): String = this.toBase37().fromBase37()
}