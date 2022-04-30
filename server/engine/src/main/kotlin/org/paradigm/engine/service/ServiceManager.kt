package org.paradigm.engine.service

import org.paradigm.engine.service.account.LoginService
import org.tinylog.kotlin.Logger
import kotlin.reflect.KClass

class ServiceManager {

    private val services = mutableMapOf<KClass<out Service>, Service>()

    init {
        register<LoginService>()
    }

    fun start() {
        services.values.forEach { service ->
            Logger.info("Starting engine service: ${service::class.simpleName}.")
            service.start()
        }
    }

    fun stop() {
        services.values.forEach { service ->
            Logger.info("Stopping engine service: ${service::class.simpleName}.")
            service.stop()
        }
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T : Service> get(service: KClass<T>): T {
        return services[service] as? T
            ?: throw IllegalArgumentException("Unknown service with type: ${service.simpleName}.")
    }

    private inline fun <reified T : Service> register() {
        services[T::class] = T::class.java.getDeclaredConstructor().newInstance() as T
    }
}