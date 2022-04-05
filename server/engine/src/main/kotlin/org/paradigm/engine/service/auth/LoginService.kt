package org.paradigm.engine.service.auth

import com.google.common.util.concurrent.ThreadFactoryBuilder
import org.paradigm.common.inject
import org.paradigm.engine.model.World
import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.net.Session
import org.paradigm.engine.net.login.LoginRequest
import org.paradigm.engine.net.login.LoginResponse
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
            val session = Session(request.ctx)
            val player = Player(session)

            player.username = request.username
            player.passwordHash = request.password?.let { SHA256.hash(it) } ?: ""
            player.displayName = request.username

            session.xteas = request.xteas
            session.reconnectXteas = request.reconnectXteas
            session.seed = request.seed

            session.encodeIsaac.init(IntArray(4) { session.xteas[it] + 50 })
            session.decodeIsaac.init(session.xteas)

            world.players.addPlayer(player)
            player.init()

            /*
             * Send the login response.
             */
            val response = LoginResponse(player)
            session.writeAndFlush(response)

            Logger.info("[${request.username}] has connected to the server.")
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