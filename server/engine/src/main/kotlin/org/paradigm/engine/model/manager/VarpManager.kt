package org.paradigm.engine.model.manager

import org.paradigm.cache.GameCache
import org.paradigm.common.inject
import org.paradigm.engine.model.entity.LivingEntity

class VarpManager(private val entity: LivingEntity) {

    private val cache: GameCache by inject()

    private val varps = mutableMapOf<Int, Int>()
    internal val changed = hashSetOf<Int>()

    fun getVarp(id: Int): Int {
        return this[id] ?: 0
    }

    fun setVarp(id: Int, value: Int) {
        this[id] = value
    }

    fun setVarp(id: Int, flag: Boolean, falseValue: Int = 0, trueValue: Int = 1) {
        setVarp(id, if (flag) trueValue else falseValue)
    }

    fun toggleVarp(id: Int, value1: Int = 0, value2: Int = 1) {
        val newValue = if (getVarp(id) == value1) value2 else value1
        setVarp(id, newValue)
    }

    fun getVarbit(id: Int): Int {
        val def = cache.configs.varbits[id]!!
        val value = this[def.index] ?: 0
        return value.getBitsValue(def.leastSignificantBit, def.mostSignificantBit)
    }

    fun setVarbit(id: Int, value: Int) {
        val def = cache.configs.varbits[id]!!
        val curValue = this[def.index] ?: 0
        val newValue = curValue.setBitsValue(def.leastSignificantBit, def.mostSignificantBit, value)
        this[def.index] = newValue
    }

    fun setVarbit(id: Int, flag: Boolean, falseValue: Int = 0, trueValue: Int = 1) {
        setVarbit(id, if (flag) trueValue else falseValue)
    }

    fun toggleVarbit(id: Int, value1: Int = 0, value2: Int = 1) {
        val newValue = if (getVarbit(id) == value1) value2 else value1
        setVarbit(id, newValue)
    }

    private operator fun get(key: Int): Int? = varps[key]

    private operator fun set(key: Int, value: Int): Int? {
        if ((varps[key] ?: -1) != value) changed.add(key)
        if (value == 0) {
            return varps.remove(key)
        }
        return varps.put(key, value)
    }

    private fun Int.getBitsValue(start: Int, end: Int): Int {
        val size = BIT_SIZES[end - start]
        return (this shr start) and size
    }

    private fun Int.setBitsValue(start: Int, end: Int, value: Int): Int {
        val size = BIT_SIZES[end - start] shl start
        return (this and size.inv()) or ((value shl start) and size)
    }

    companion object {
        private val BIT_SIZES = IntArray(Int.SIZE_BYTES).apply {
            var size = 2
            for (i in indices) {
                this[i] = size - 1
                size += size
            }
        }
    }
}