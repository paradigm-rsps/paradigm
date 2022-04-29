package org.paradigm.content

import org.paradigm.content.annotation.ContentScriptDsl
import org.paradigm.content.annotation.Import
import java.io.File
import java.net.JarURLConnection
import java.net.URL
import java.security.MessageDigest
import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.*
import kotlin.script.experimental.host.FileBasedScriptSource
import kotlin.script.experimental.host.FileScriptSource
import kotlin.script.experimental.jvm.dependenciesFromClassContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvm.mainArguments

@Suppress("unused")
@KotlinScript(
    fileExtension = "content.kts",
    compilationConfiguration = ContentScriptCompilerDefinition::class,
    evaluationConfiguration = ContentScriptEvaluatorDefinition::class
)
open class ContentScript {
    internal var enableBlock: () -> Unit = { }
    internal var disableBlock: () -> Unit = { }

    @ContentScriptDsl
    fun onEnable(block: () -> Unit) {
        this.enableBlock = block
    }

    @ContentScriptDsl
    fun onDisable(block: () -> Unit) {
        this.disableBlock = block
    }
}

object ContentScriptCompilerDefinition : ScriptCompilationConfiguration({
    defaultImports(Import::class)
    jvm {
        dependenciesFromClassContext(ContentScriptCompilerDefinition::class, wholeClasspath = true)
    }
    refineConfiguration {
        onAnnotations(Import::class, handler = ContentScriptConfigurator())
    }
    ide {
        acceptedLocations(ScriptAcceptedLocation.Everywhere)
    }
})

object ContentScriptEvaluatorDefinition : ScriptEvaluationConfiguration({
    scriptsInstancesSharing(true)
    refineConfigurationBeforeEvaluate(::configureConstructorArgsFromMainArgs)
})

fun configureConstructorArgsFromMainArgs(context: ScriptEvaluationConfigurationRefinementContext): ResultWithDiagnostics<ScriptEvaluationConfiguration> {
    val mainArgs = context.evaluationConfiguration[ScriptEvaluationConfiguration.jvm.mainArguments]
    val res =
        if (context.evaluationConfiguration[ScriptEvaluationConfiguration.constructorArgs] == null && mainArgs != null) {
            context.evaluationConfiguration.with {
                constructorArgs(mainArgs)
            }
        } else context.evaluationConfiguration
    return res.asSuccess()
}

class ContentScriptConfigurator : RefineScriptCompilationConfigurationHandler {
    override operator fun invoke(context: ScriptConfigurationRefinementContext): ResultWithDiagnostics<ScriptCompilationConfiguration> {
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

private fun compiledScriptUniqueName(
    script: SourceCode,
    scriptCompilationConfiguration: ScriptCompilationConfiguration
): String {
    val digestWrapper = MessageDigest.getInstance("MD5")
    digestWrapper.update(script.text.toByteArray())
    scriptCompilationConfiguration.notTransientData.entries
        .sortedBy { it.key.name }
        .forEach {
            digestWrapper.update(it.key.name.toByteArray())
            digestWrapper.update(it.value.toString().toByteArray())
        }
    return digestWrapper.digest().toHexString()
}

private fun ByteArray.toHexString(): String = joinToString("", transform = { "%02x".format(it) })

internal fun URL.toContainingJarOrNull(): File? =
    if (protocol == "jar") {
        (openConnection() as? JarURLConnection)?.jarFileURL?.toFileOrNull()
    } else null

internal fun URL.toFileOrNull() =
    try {
        File(toURI())
    } catch (e: IllegalArgumentException) {
        null
    } catch (e: java.net.URISyntaxException) {
        null
    } ?: run {
        if (protocol != "file") null
        else File(file)
    }