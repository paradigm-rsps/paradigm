package org.paradigm.nxtclient

import org.jire.arrowhead.Module
import org.jire.arrowhead.get
import org.jire.arrowhead.processByID
import org.paradigm.nxtclient.util.every
import org.paradigm.nxtclient.util.retry
import org.tinylog.kotlin.Logger
import java.io.File
import java.util.zip.ZipFile
import kotlin.system.exitProcess

object Launcher {

    private lateinit var proc: Process
    internal lateinit var process: org.jire.arrowhead.Process
    internal lateinit var module: Module

    private val baseDir = File(System.getProperty("user.home")).resolve(".paradigm/")

    /**
     * === OFFSETS ====
     */
    private val dwLoginPage = 0x635ED8

    @JvmStatic
    fun main(args: Array<String>) {
        this.init()
        this.launch()
    }

    private fun init() {
        Logger.info("Initializing...")

        if (!baseDir.resolve("osclient.exe").exists()) {
            /*
             * Patch the embedded original osclient.exe binary.
             */
            Patcher.main(arrayOf())
        }

        if (!baseDir.resolve("steam_api64.dll").exists()) {
            Logger.info("Extracting Old School RuneScape NXT dependencies...")
            val bytes = Launcher::class.java.getResourceAsStream("/deps.zip")!!.readAllBytes()

            val zip = baseDir.resolve("deps.zip")
            zip.createNewFile()
            zip.outputStream().use { it.write(bytes) }

            ZipFile(zip).use { z ->
                z.entries().asSequence().forEach { entry ->
                    if (entry.isDirectory) {
                        if (!entry.name.isBlank()) {
                            baseDir.resolve(entry.name).mkdirs()
                        }
                    } else {
                        z.getInputStream(entry).use { input ->
                            baseDir.resolve(entry.name).outputStream().use { output ->
                                input.copyTo(output)
                            }
                        }
                    }
                }
            }
            zip.deleteRecursively()
        }
    }

    private fun launch() {
        Logger.info("Launching Old Schoold RuneScape NXT client.")

        proc = ProcessBuilder(baseDir.resolve("osclient.exe").absolutePath).start()

        retry(128L) {
            process = processByID(proc.pid().toInt())!!
        }

        retry(128L) {
            process.loadModules()
            module = process.modules["osclient.exe"]!!
        }

        Logger.info("Client started with process id: ${proc.pid()}.")
        run()

        proc.waitFor()

        Logger.info("Shutting down Old School RuneScape NXT client.")

        ProcessBuilder("cmd", "/c", "taskkill /F /PID ${proc.pid().toInt()}")
        exitProcess(0)
    }

    private fun run() {
        /*
         * Check every 1ms if the LoginPage is ID: 26 and change it to the
         * desktop login ID.
         */
        every(1L) {
            val loginPage: Int = process[module.address + dwLoginPage]
            if (loginPage == 26 || loginPage == 0) {
                process[module.address + dwLoginPage] = 12
            }
        }
    }
}