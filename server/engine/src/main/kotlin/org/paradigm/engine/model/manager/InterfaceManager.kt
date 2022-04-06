package org.paradigm.engine.model.manager

import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.model.ui.GameInterface
import org.paradigm.engine.model.ui.InterfaceType
import org.paradigm.engine.net.packet.server.IfOpenSub
import org.paradigm.engine.net.packet.server.IfOpenTop

class InterfaceManager(private val player: Player) {

    private val interfaceMap = hashMapOf<Int, Int>()
    private var modal: Int = -1

    fun init() {
        openRootInterface(player.displayMode.id)
        GameInterface.values().forEach {
            val parent = player.displayMode.id
            val child = it.child
            openInterface(parent, child, it.interfaceId, it.type)
        }
    }

    private fun open(parent: Int, child: Int, interfaceId: Int) {
        val component = (parent shl 16) or child
        if(interfaceMap.containsKey(component)) {
            closeComponent(component)
        }
        interfaceMap[component] = interfaceId
    }

    private fun openModal(parent: Int, child: Int, interfaceId: Int) {
        open(parent, child, interfaceId)
        modal = interfaceId
    }

    private fun closeComponent(component: Int) {
        interfaceMap.remove(component)
    }

    private fun close(parent: Int) {
        val component = interfaceMap.filterValues { it == parent }.keys.firstOrNull()
        if(component != null) {
            closeComponent(component)
        }
    }

    private fun close(parent: Int, child: Int) = closeComponent((parent shl 16) or child)

    fun openRootInterface(interfaceId: Int) {
        open(interfaceId, 0, interfaceId)
        player.session.write(IfOpenTop(interfaceId))
    }

    fun openInterface(parent: Int, child: Int, interfaceId: Int, type: InterfaceType) {
        if(type == InterfaceType.MODAL) {
            openModal(parent, child, interfaceId)
        } else {
            open(parent, child, interfaceId)
        }
        player.session.write(IfOpenSub(parent, child, interfaceId, type))
    }
}