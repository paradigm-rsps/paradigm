package org.paradigm.engine.queue

import kotlinx.coroutines.withContext
import org.paradigm.engine.coroutine.EngineCoroutineTask
import java.util.*

class PriorityQueueList(
    private var currentQueue: QueueTask? = null,
    private var currentPriority: QueuePriority = QueuePriority.WEAK,
    private val pending: LinkedList<QueueBlock> = LinkedList()
) {

    val size: Int get() = pending.size + (if (currentQueue != null) 1 else 0)

    val paused: Boolean get() = currentQueue == null

    internal fun queue(priority: QueuePriority, block: suspend () -> Unit) {
        if (!changePriority(priority)) return
        if (size >= MAX_ACTIVE_QUEUES) {
            pending.removeLast()
        }
        val queueBlock = QueueBlock(block)
        pending.add(queueBlock)
    }

    suspend fun pollPending() {
        if (currentQueue != null) return
        val ctx = pending.poll() ?: return
        val task = EngineCoroutineTask()
        val block = suspend { withContext(task) { ctx.block() } }
        task.launch(block)
        currentQueue = QueueTask(task)
    }

    internal fun clear() {
        currentQueue = null
        currentPriority = QueuePriority.WEAK
        pending.clear()
    }

    fun processCurrent() {
        val queue = currentQueue ?: return
        queue.task.cycle()
        if (queue.task.paused) {
            discardCurrent()
        }
    }

    fun discardCurrent() {
        currentQueue = null
        if (pending.isEmpty()) {
            currentPriority = QueuePriority.WEAK
        }
    }

    private fun changePriority(priority: QueuePriority): Boolean {
        if (priority == currentPriority) return true
        if (!priority.isStronger(currentPriority)) return false
        if (priority != currentPriority) {
            clear()
            currentPriority
        }
        return true
    }

    private fun QueuePriority.isStronger(other: QueuePriority): Boolean = when (this) {
        QueuePriority.NORMAL -> other == QueuePriority.WEAK
        QueuePriority.STRONG -> true
        else -> false
    }

    companion object {
        private const val MAX_ACTIVE_QUEUES = 2
    }
}