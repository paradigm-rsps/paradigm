@file:Suppress("UNCHECKED_CAST")

package org.paradigm.engine.model.ui

import org.paradigm.cache.GameCache
import org.paradigm.cache.config.EnumConfig
import org.paradigm.common.inject

private val cache: GameCache by inject()

val Int.enum: Map<EnumConfig.Component, EnumConfig.Component?>
    get() {
        return cache.configArchive.enumConfigs[this]!! as Map<EnumConfig.Component, EnumConfig.Component?>
    }

enum class DisplayMode(val interfaceId: Int, val moves: Map<EnumConfig.Component, EnumConfig.Component?>) {
    FIXED(interfaceId = 548, moves = 1129.enum),
    RESIZABLE_CLASSIC(interfaceId = 161, moves = 1130.enum),
    RESIZABLE_MODERN(interfaceId = 164, moves = 1131.enum),
    FULLSCREEN(interfaceId = 165, moves = 1132.enum),
    MOBILE(interfaceId = 601, moves = 1745.enum);
}