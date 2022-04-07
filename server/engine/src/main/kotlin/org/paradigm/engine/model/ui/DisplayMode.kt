@file:Suppress("UNCHECKED_CAST")

package org.paradigm.engine.model.ui

import org.paradigm.cache.GameCache
import org.paradigm.cache.config.EnumConfig
import org.paradigm.common.inject

private val cache: GameCache by inject()

private val Int.enum: Map<EnumConfig.Component, EnumConfig.Component?>
    get() {
        return cache.configArchive.enumConfigs[this]!! as Map<EnumConfig.Component, EnumConfig.Component?>
    }

enum class DisplayMode(val interfaceId: Int, val enum: Map<EnumConfig.Component, EnumConfig.Component?>) {
    FIXED(interfaceId = 548, enum = 1129.enum),
    RESIZABLE_CLASSIC(interfaceId = 161, enum = 1130.enum),
    RESIZABLE_MODERN(interfaceId = 164, enum = 1131.enum),
    FULLSCREEN(interfaceId = 165, enum = 1132.enum),
    MOBILE(interfaceId = 601, enum = 1745.enum),
    TOUR(interfaceId = 80, enum = 139.enum);

    fun isResizable(): Boolean = this == RESIZABLE_CLASSIC || this == RESIZABLE_MODERN
}