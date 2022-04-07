package org.paradigm.engine.service.auth

import com.google.common.util.concurrent.ThreadFactoryBuilder
import org.paradigm.common.inject
import org.paradigm.engine.event.EventBus
import org.paradigm.engine.event.impl.PlayerLoginEvent
import org.paradigm.engine.model.World
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.net.Session
import org.paradigm.engine.net.game.GamePacketDecoder
import org.paradigm.engine.net.game.GamePacketEncoder
import org.paradigm.engine.net.game.GamePacketHandler
import org.paradigm.engine.net.login.LoginRequest
import org.paradigm.engine.net.login.LoginResponse
import org.paradigm.engine.net.packet.server.RebuildRegionNormal
import org.paradigm.engine.service.Service
import org.paradigm.util.SHA256
import org.tinylog.kotlin.Logger
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue

class LoginService : Service {

    private val world: World by inject()

    private val executor = Executors.newFixedThreadPool(LOGIN_THREADS, ThreadFactoryBuilder()
        .setDaemon(false)
        .setNameFormat("login-thread")
        .build()
    )

    private val queue = LinkedBlockingQueue<LoginRequest>()

    override fun start() {
        repeat(LOGIN_THREADS) {
            executor.execute { this.processLogins() }
        }
    }

    override fun stop() {
        executor.shutdownNow()
    }

    fun queue(request: LoginRequest) {
        this.queue.add(request)
    }

    private fun processLogins() {
        while(true) {
            val request = queue.take()

            /*
             * Process login request.
             */

            /*
             * Successful login.
             */
            Player(Session(request.ctx)).login(request)
        }
    }

    private fun Player.login(request: LoginRequest) {
        this.username = request.username
        this.passwordHash = request.password?.let { SHA256.hash("salt" + request.password) } ?: ""
        this.displayName = request.username

        session.seed = request.seed
        session.xteas = request.xteas
        session.reconnectXteas = request.reconnectXteas

        session.encodeIsaac.init(IntArray(4) { session.xteas[it] + 50 })
        session.decodeIsaac.init(session.xteas)

        if(world.players.firstOrNull { it.username == username } != null) {
            world.players.first { it.username == username }.logout()
        }

        world.players.addPlayer(this)

        session.writeAndFlush(LoginResponse(this)).addListener {
            val p = session.channel.pipeline()
            p.replace("login-encoder", "packet-encoder", GamePacketEncoder(session))
            p.replace("login-decoder", "packet-decoder", GamePacketDecoder(session))
            p.replace("login-handler", "packet-handler", GamePacketHandler(session))

            this.init()
            EventBus.publish(PlayerLoginEvent(this))
            Logger.info("[$username] has connected to the server.")
        }
    }

    companion object {
        /**
         * The number of threads to process incoming login requests on. This will be
         * the max number of logins accepted at a time.
         */
        private const val LOGIN_THREADS = 4
    }
}