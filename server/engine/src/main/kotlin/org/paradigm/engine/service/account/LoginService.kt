package org.paradigm.engine.service.account

import com.google.common.util.concurrent.ThreadFactoryBuilder
import org.paradigm.common.inject
import org.paradigm.engine.event.EventBus
import org.paradigm.engine.event.impl.LoginEvent
import org.paradigm.engine.model.world.World
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.net.Session
import org.paradigm.engine.net.StatusResponse
import org.paradigm.engine.net.game.GamePacketDecoder
import org.paradigm.engine.net.game.GamePacketEncoder
import org.paradigm.engine.net.game.GamePacketHandler
import org.paradigm.engine.net.login.LoginRequest
import org.paradigm.engine.net.login.LoginResponse
import org.paradigm.engine.service.Service
import org.paradigm.engine.serializer.PlayerSerializer
import org.tinylog.kotlin.Logger
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue

class LoginService : Service {

    private val world: World by inject()
    private val playerSerializer: PlayerSerializer by inject()

    private val executor = Executors.newFixedThreadPool(
        LOGIN_THREADS, ThreadFactoryBuilder()
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

            val session = Session(request.ctx)
            val player = playerSerializer.loadPlayer(session, request.username, request.password ?: "")

            /*
             * If the player is null, this indicates that the serialization / authentication
             * to get the player object failed.
             */
            if (player == null) {
                session.writeAndFlush(StatusResponse.INVALID_CREDENTIALS)
                continue
            }

            /*
             * The login was successfully. Hand off to login the player into
             * the world.
             */
            player.login(request)
        }
    }

    private fun Player.login(request: LoginRequest) {
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
            EventBus.publish(LoginEvent(this))
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