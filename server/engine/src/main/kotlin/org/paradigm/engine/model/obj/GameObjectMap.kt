package org.paradigm.engine.model.obj

import org.paradigm.engine.model.map.Tile

class GameObjectMap private constructor(
    private val objects: MutableMap<Tile, MutableMap<Int, GameObject>>
) {

    constructor() : this(mutableMapOf())

    fun addStatic(obj: GameObject) {

    }

    fun addDynamic(obj: GameObject) {

    }

    fun remove(obj: GameObject) {

    }

    fun get(tile: Tile, shape: Int): GameObject? {
        return objects[tile]?.values?.firstOrNull { it.shape == shape }
    }

    operator fun get(tile: Tile): Collection<GameObject> {
        return objects[tile]?.values ?: emptyList()
    }

    private fun put(obj: GameObject) {
        val objs = objects.getOrPut(obj.tile) { mutableMapOf() }
        objs[obj.shape] = obj
    }

    private fun remove(tile: Tile, shape: Int): GameObject? {
        val objs = objects[tile] ?: return null
        return objs.remove(shape)
    }
}