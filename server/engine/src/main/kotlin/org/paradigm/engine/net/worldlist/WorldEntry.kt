package org.paradigm.engine.net.worldlist

data class WorldEntry(
    val id: Int,
    val address: String,
    val activity: String = "-",
    val types: List<WorldType> = listOf(WorldType.FREE),
    val location: WorldLocation = WorldLocation.UNITED_STATES,
    var players: Int = 0
)