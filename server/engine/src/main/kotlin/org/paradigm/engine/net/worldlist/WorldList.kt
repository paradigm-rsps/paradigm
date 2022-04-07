package org.paradigm.engine.net.worldlist

class WorldList(
    private val worlds: MutableList<WorldEntry> = mutableListOf()
) : MutableList<WorldEntry> by worlds {
    init {
        worlds.add(WorldEntry(1, "127.0.0.1", "-", listOf(WorldType.MEMBERS), WorldLocation.UNITED_STATES, 0))
    }
}