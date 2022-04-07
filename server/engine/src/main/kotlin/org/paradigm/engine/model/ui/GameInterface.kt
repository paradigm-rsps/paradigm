package org.paradigm.engine.model.ui

import org.paradigm.cache.config.EnumConfig

enum class GameInterface(val interfaceId: Int, val child: Int, val type: InterfaceType = InterfaceType.OVERLAY) {
    CHAT_BOX(interfaceId = 162, child = 94, type = InterfaceType.OVERLAY),
    PRIVATE_CHAT(interfaceId = 163, child = 91, type = InterfaceType.OVERLAY),
    MINI_MAP(interfaceId = 160, child = 32, type = InterfaceType.OVERLAY),
    XP_TRACKER(interfaceId = 122, child = 9, type = InterfaceType.OVERLAY),
    SKILLS(interfaceId = 320, child = 76, type = InterfaceType.OVERLAY),
    QUESTS(interfaceId = 629, child = 77, type = InterfaceType.OVERLAY),
    INVENTORY(interfaceId = 149, child = 78, type = InterfaceType.OVERLAY),
    EQUIPMENT(interfaceId = 387, child = 79, type = InterfaceType.OVERLAY),
    PRAYER(interfaceId = 541, child = 80, type = InterfaceType.OVERLAY),
    SPELL_BOOK(interfaceId = 218, child = 81, type = InterfaceType.OVERLAY),
    ACCOUNT(interfaceId = 109, child = 83, type = InterfaceType.OVERLAY),
    SOCIAL(interfaceId = 429, child = 84, type = InterfaceType.OVERLAY),
    LOG_OUT(interfaceId = 182, child = 85, type = InterfaceType.OVERLAY),
    SETTINGS(interfaceId = 116, child = 86, type = InterfaceType.OVERLAY),
    EMOTES(interfaceId = 216, child = 87, type = InterfaceType.OVERLAY),
    MUSIC(interfaceId = 239, child = 88, type = InterfaceType.OVERLAY),
    CLANS(interfaceId = 707, child = 82, type = InterfaceType.OVERLAY),
    COMBAT(interfaceId = 593, child = 75, type = InterfaceType.OVERLAY);

    fun child(displayMode: DisplayMode): Int =
        displayMode.enum[EnumConfig.Component(DisplayMode.RESIZABLE_CLASSIC.interfaceId, child)]?.child
            ?: throw IllegalArgumentException("Failed to find child component id for display mode: ${displayMode.name}.")
}