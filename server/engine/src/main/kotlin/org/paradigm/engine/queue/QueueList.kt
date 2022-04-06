package org.paradigm.engine.queue

import kotlinx.coroutines.withContext
import org.paradigm.engine.coroutine.EngineCoroutineTask
import java.util.*

class QueueList internal constructor(
    private val queues: MutableList<QueueTask> = mutableListOf(),
    private val pending: Queue<QueueBlock> = LinkedList()
) : List<QueueTask> by queues {

    internal fun queue(block: suspend () -> Unit) {
        val queueBlock = QueueBlock(block)
        pending.add(queueBlock)
    }

    internal suspend fun cycle() {

    }

    private fun cycleQueues() {
        queues.forEach { it.task.cycle() }
        queues.removeIf { it.task.paused }
    }

    private fun addPending() {
        while (pending.isNotEmpty()) {
            val ctx = pending.poll() ?: break
            val task = EngineCoroutineTask()
            val block = suspend { withContext(task) { ctx.block() } }
            val queueTask = QueueTask(task)
            task.launch(block)
            if (!queueTask.task.paused) {
                queues.add(queueTask)
            }
        }
    }
}