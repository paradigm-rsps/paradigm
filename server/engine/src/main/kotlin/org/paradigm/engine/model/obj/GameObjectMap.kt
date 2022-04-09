package org.paradigm.engine.model.obj

import org.paradigm.engine.model.map.Tile

class GameObjectMap(
    private val objects: MutableMap<Tile, GameObjectList> = mutableMapOf(),
) {

    fun addStatic(obj: GameObject) {
        objects.put(obj)
    }

    fun remove(obj: GameObject) {
        objects.remove(obj.tile, obj.shape)
    }

    operator fun get(tile: Tile): Collection<GameObject> {
        return objects[tile]?.values ?: emptyList()
    }

    fun get(tile: Tile, shape: Int): GameObject? {
        return objects[tile]?.values?.firstOrNull { it.shape == shape }
    }

    private fun MutableMap<Tile, GameObjectList>.put(obj: GameObject) {
        val list = getOrPut(obj.tile) { GameObjectList() }
        list[obj.shape] = obj
    }

    private fun MutableMap<Tile, GameObjectList>.remove(tile: Tile, shape: Int): GameObject? {
        val objects = this[tile] ?: return null
        return objects.remove(shape)
    }
}