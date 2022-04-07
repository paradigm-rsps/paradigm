@file:Suppress("UNCHECKED_CAST")

package org.paradigm.engine.net.game

import io.github.classgraph.ClassGraph
import org.tinylog.kotlin.Logger
import kotlin.reflect.KClass
import kotlin.reflect.full.companionObjectInstance

class GamePackets {

    val clientPackets = PacketRegistry()
    val serverPackets = PacketRegistry()

    internal fun loadPackets() {
        Logger.info("Loading game packets.")
        loadServerPackets()
        loadClientPackets()
    }

    private fun loadServerPackets() {
        ClassGraph()
            .enableClassInfo()
            .enableAnnotationInfo()
            .acceptPackages("org.paradigm.engine.net.packet.server")
            .scan()
            .use {
                it.getClassesWithAnnotation(ServerPacket::class.qualifiedName).forEach { classInfo ->
                    val annotation = classInfo.getAnnotationInfo(ServerPacket::class.qualifiedName)
                        .loadClassAndInstantiate() as ServerPacket
                    val opcode = annotation.opcode
                    val type = annotation.type
                    val packet = classInfo.loadClass().kotlin as KClass<Packet>
                    val codec = packet.companionObjectInstance as Codec<Packet>
                    val length = type.length
                    serverPackets.register(packet, opcode, codec, length)
                }
            }

        Logger.info("Registered ${serverPackets.size} server packets.")
    }

    private fun loadClientPackets() {
        ClassGraph()
            .enableClassInfo()
            .enableAnnotationInfo()
            .acceptPackages("org.paradigm.engine.net.packet.client")
            .scan()
            .use {
                it.getClassesWithAnnotation(ClientPacket::class.qualifiedName).forEach { classInfo ->
                    val annotation = classInfo.getAnnotationInfo(ClientPacket::class.qualifiedName)
                        .loadClassAndInstantiate() as ClientPacket
                    val opcode = annotation.opcode
                    val type = annotation.type
                    val packet = classInfo.loadClass().kotlin as KClass<Packet>
                    val codec = packet.companionObjectInstance as Codec<Packet>
                    val length = type.length
                    clientPackets.register(packet, opcode, codec, length)
                }
            }

        Logger.info("Registered ${clientPackets.size} client packets.")
    }
}