package org.paradigm.content

import kotlin.script.experimental.annotations.KotlinScript

@KotlinScript(
    fileExtension = "content.kts"
)
abstract class ContentModuleScript {
    internal var enableBlock: () -> Unit = {}
    internal var disableBlock: () -> Unit = {}

    fun onEnable(block: () -> Unit) {
        enableBlock = block
    }

    fun onDisable(block: () -> Unit) {
        disableBlock = block
    }
}