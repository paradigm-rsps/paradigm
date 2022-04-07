package org.paradigm.engine.model.manager

import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.ui.GameInterface
import org.paradigm.engine.model.ui.InterfaceType
import org.paradigm.engine.net.packet.server.IfOpenSub
import org.paradigm.engine.net.packet.server.IfOpenTop

class InterfaceManager(private val player: Player) {

    private val interfaces = hashMapOf<Int, Int>()
    private var modal: Int = -1

    internal fun init() {}

    private fun open(parent: Int, child: Int, interfaceId: Int) {
        val component = (parent shl 16) or child
        if (interfaces.containsKey(component)) close(component)
        interfaces[component] = interfaceId
    }

    private fun close(component: Int) {
        interfaces.remove(component)
    }

    private fun openModal(parent: Int, child: Int, interfaceId: Int) {
        open(parent, child, interfaceId)
        modal = interfaceId
    }

    fun openTopInterface(interfaceId: Int) {
        open(interfaceId, 0, interfaceId)
        player.session.write(IfOpenTop(interfaceId))
    }

    fun openInterface(parent: Int, child: Int, interfaceId: Int, type: InterfaceType) {
        if (type == InterfaceType.MODAL) {
            openModal(parent, child, interfaceId)
        } else {
            open(parent, child, interfaceId)
        }
        player.session.write(IfOpenSub(parent, child, interfaceId, type))
    }

    fun openInterface(interfaceId: Int, gameInterface: GameInterface) {
        val parent = player.displayMode.interfaceId
        val child = gameInterface.child(player.displayMode)
        openInterface(parent, child, interfaceId, InterfaceType.MODAL)
    }

    fun openInterface(gameInterface: GameInterface) {
        val parent = player.displayMode.interfaceId
        val child = gameInterface.child
        openInterface(parent, child, gameInterface.interfaceId, gameInterface.type)
    }
}