package org.paradigm.engine.net.worldlist

enum class WorldType(val mask: Int) {
    FREE(0),
    MEMBERS(1),
    PVP(1 shl 2),
    BOUNTY_HUNTER(1 shl 5),
    SKILL_TOTAL(1 shl 7),
    PVP_HIGH_RISK(1 shl 10),
    UNKNOWN1(1 shl 14),
    TOURNAMENT(1 shl 25),
    UNKNOWN2(1 shl 26),
    DEADMAN(1 shl 29),
    LEAGUES(1 shl 30);
}