package org.paradigm.nxtclient.util

inline fun <T> retry(delay: Long, noinline handler: ((Exception) -> Unit)? = null, action: () -> T) {
    while (!Thread.interrupted()) {
        try {
            action()
            break
        } catch (e: Exception) {
            handler?.invoke(e)
            Thread.sleep(delay)
        }
    }
}