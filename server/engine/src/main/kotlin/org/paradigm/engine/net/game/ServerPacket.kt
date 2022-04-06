package org.paradigm.engine.net.game

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ServerPacket(val opcode: Int, val type: PacketType = PacketType.FIXED)
