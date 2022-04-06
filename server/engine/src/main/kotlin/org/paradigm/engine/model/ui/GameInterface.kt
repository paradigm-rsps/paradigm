package org.paradigm.engine.model.ui

import org.paradigm.engine.model.entity.Player
import org.paradigm.engine.net.packet.server.IfOpenSub
import org.paradigm.engine.net.packet.server.IfOpenTop
import kotlin.properties.Delegates

class GameInterface(private val player: Player) {

    private var topInterface: Component by Delegates.notNull()
    private var modal: Int? = null

    fun init() {
        openTopInterface(player.displayMode.interfaceId)
        topInterface.openInterface(162, 10, InterfaceType.OVERLAY)
    }

    fun openTopInterface(interfaceId: Int) {
        topInterface = Component(player.displayMode.interfaceId, InterfaceType.TOP)
        topInterface.parent = topInterface
        player.session.write(IfOpenTop(interfaceId))
    }

    inner class Component(
        val interfaceId: Int,
        val type: InterfaceType,
        val children: HashMap<Int, Component> = hashMapOf()
    ) {

        var parent: Component by Delegates.notNull()
            internal set

        fun openInterface(interfaceId: Int, child: Int, type: InterfaceType) {
            val component = Component(interfaceId, type)
            children[child] = component
            player.session.write(IfOpenSub(parent.interfaceId, child, interfaceId, type))
        }
    }
}