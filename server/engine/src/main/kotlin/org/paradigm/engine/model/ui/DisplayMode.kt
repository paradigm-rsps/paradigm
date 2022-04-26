@file:Suppress("UNCHECKED_CAST")

package org.paradigm.engine.model.ui

import org.paradigm.cache.GameCache
import org.paradigm.common.inject

private val cache: GameCache by inject()

enum class DisplayMode(val interfaceId: Int) {
    FIXED(interfaceId = 548),
    RESIZABLE_CLASSIC(interfaceId = 161),
    RESIZABLE_MODERN(interfaceId = 164),
    FULLSCREEN(interfaceId = 165),
    MOBILE(interfaceId = 601),
    TOUR(interfaceId = 80);

    fun isResizable(): Boolean = this == RESIZABLE_CLASSIC || this == RESIZABLE_MODERN
}