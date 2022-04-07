package org.paradigm.content

import org.paradigm.content.annotation.Import
import java.io.File
import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.*
import kotlin.script.experimental.host.FileBasedScriptSource
import kotlin.script.experimental.host.FileScriptSource
import kotlin.script.experimental.jvm.dependenciesFromClassContext
import kotlin.script.experimental.jvm.jvm

@DslMarker
annotation class ContentScriptDsl

@KotlinScript(
    fileExtension = "content.kts",
    compilationConfiguration = ContentScriptCompilationConfiguration::class,
    evaluationConfiguration = ContentScriptEvaluationConfiguration::class
)
abstract class ContentScript {
    internal var enableBlock: ContentScript.() -> Unit = {}
    internal var disableBlock: ContentScript.() -> Unit = {}

    @ContentScriptDsl
    fun onEnable(block: ContentScript.() -> Unit) {
        enableBlock = block
    }

    @ContentScriptDsl
    fun onDisable(block: ContentScript.() -> Unit) {
        disableBlock = block
    }
}

class ContentScriptCompilationConfiguration : ScriptCompilationConfiguration({
    defaultImports(Import::class)
    defaultImports("org.paradigm.*")
    jvm {
        dependenciesFromClassContext(ContentScriptCompilationConfiguration::class, wholeClasspath = true)
    }
    refineConfiguration {
        onAnnotations(Import::class, handler = ContentScriptCompilationConfigurationHandler())
    }
    ide {
        acceptedLocations(ScriptAcceptedLocation.Everywhere)
    }
})

object ContentScriptEvaluationConfiguration : ScriptEvaluationConfiguration({
    scriptsInstancesSharing(true)
})

class ContentScriptCompilationConfigurationHandler : RefineScriptCompilationConfigurationHandler {
    override fun invoke(context: ScriptConfigurationRefinementContext): ResultWithDiagnostics<ScriptCompilationConfiguration> {
        val annotations = context.collectedData?.get(ScriptCollectedData.foundAnnotations)?.takeIf { it.isNotEmpty() }
            ?: return context.compilationConfiguration.asSuccess()
        val scriptBaseDir = (context.script as? FileBasedScriptSource)?.file?.parentFile
        val importedSources = annotations.flatMap {
            (it as? Import)?.paths?.map { sourceName ->
                FileScriptSource(scriptBaseDir?.resolve(sourceName) ?: File(sourceName))
            } ?: emptyList()
        }
        return ScriptCompilationConfiguration(context.compilationConfiguration) {
            if (importedSources.isNotEmpty()) importScripts.append(importedSources)
        }.asSuccess()
    }
}