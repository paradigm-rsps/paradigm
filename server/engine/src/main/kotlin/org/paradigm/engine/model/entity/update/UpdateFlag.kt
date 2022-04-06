package org.paradigm.engine.model.entity.update

abstract class UpdateFlag(val order: Int, val mask: Int) : Comparable<UpdateFlag> {

    override fun compareTo(other: UpdateFlag): Int = when {
        order < other.order -> -1
        order > other.order -> 1
        else -> 0
    }
}