package org.paradigm.engine.queue

import kotlinx.coroutines.withContext
import org.paradigm.engine.coroutine.EngineCoroutineTask
import java.util.*
import java.util.Queue

class Queue internal constructor(
    private val tasks: MutableList<QueueTask> = mutableListOf(),
    private val actions: Queue<QueueAction> = LinkedList()
) : List<QueueTask> by tasks {

    fun queue(action: suspend () -> Unit) {
        val queueAction = QueueAction(action)
        actions.add(queueAction)
    }

    fun cycle() {
        tasks.forEach { it.task.cycle() }
        tasks.removeIf { it.task.paused }
        while(actions.isNotEmpty()) {
            val ctx = actions.poll() ?: break
            val task = EngineCoroutineTask()
            val action = suspend { withContext(task) { ctx.action() } }
            val queueTask = QueueTask(task)
            task.launch(action)
            if(!queueTask.task.paused) {
                tasks.add(queueTask)
            }
        }
    }
}