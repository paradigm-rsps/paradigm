package org.paradigm.engine.net.worldlist

data class WorldEntry(
    val id: Int,
    val address: String,
    val activity: String,
    val types: List<WorldType>,
    val location: WorldLocation,
    var players: Int = 0
)