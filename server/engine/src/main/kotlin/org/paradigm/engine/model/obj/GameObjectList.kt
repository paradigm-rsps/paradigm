package org.paradigm.engine.model.obj

class GameObjectList(
    private val objects: MutableMap<Int, GameObject> = mutableMapOf()
) : MutableMap<Int, GameObject> by objects