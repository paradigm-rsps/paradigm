package org.paradigm.engine.service.login

import com.google.common.util.concurrent.ThreadFactoryBuilder
import org.paradigm.engine.net.login.LoginRequest
import org.paradigm.engine.service.Service
import org.tinylog.kotlin.Logger
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue

class LoginService : Service {

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