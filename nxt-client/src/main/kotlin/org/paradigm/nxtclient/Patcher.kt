package org.paradigm.nxtclient

import org.tinylog.kotlin.Logger
import java.io.File
import java.nio.file.Files

object Patcher {

    private const val oldRsa =
        "a297f6692a7a1d8b2786f93cf85ef1d85f2a702a6f04b4503c079d0c3970d7a7bda84292dd1c8249b1cd8d0eb0fe10e16ad2d42a7fbbb321f4f5603afec3f13a03d534b8e2233ba479c346208306d2d816ef9f8f1ee69896d2cd1f3dfcb7d8e5987ed6e9412f962811bfbfb59b689ce506438d4f3c8dfb5c95c1670ad4d2e767\u0000"
    private val newRsa = Patcher::class.java.getResource("/modulus.txt")!!.readText() + "\u0000"

    private const val oldJavConfig = "http://oldschool.runescape.com/jav_config.ws?m=0\u0000"
    private const val newJavConfig = "http://127.0.0.1/jav_config.ws\u0000"

    private const val oldTitle = "Old School RuneScape\u0000"
    private const val newTitle = "Paradigm\u0000"

    private val baseDir = File(System.getProperty("user.home")).resolve(".paradigm/")

    @JvmStatic
    fun main(args: Array<String>) {
        Logger.info("Patching Old School RuneScape NXT client.")

        if (baseDir.exists()) baseDir.deleteRecursively()
        baseDir.mkdirs()

        val output = baseDir.resolve("osclient.exe")
        val data = Patcher::class.java.getResourceAsStream("/osclient.orig.exe")!!.readAllBytes()

        patchRsa(data)
        patchJavConfig(data)
        patchTitle(data)

        Logger.info("Exporting patched Old School RuneScape NXT client to file: ${output.path}.")
        Files.write(output.toPath(), data)

        Logger.info("Patcher has completed successfully.")
    }

    private fun patchRsa(data: ByteArray) {
        Logger.info("Patching RSA modulus...")

        if (!data.replaceFirst(oldRsa.toByteArray(Charsets.US_ASCII), newRsa.toByteArray(Charsets.US_ASCII))) {
            throw IllegalStateException("Failed to patch RSA bytes.")
        }
    }

    private fun patchJavConfig(data: ByteArray) {
        Logger.info("Patching JAV_CONFIG url...")

        if (!data.replaceFirst(
                oldJavConfig.toByteArray(Charsets.US_ASCII),
                newJavConfig.toByteArray(Charsets.US_ASCII)
            )
        ) {
            throw IllegalStateException("Failed to patch JAV_CONFIG url bytes.")
        }
    }

    private fun patchTitle(data: ByteArray) {
        Logger.info("Patching window title...")

        if (!data.replaceFirst(oldTitle.toByteArray(Charsets.US_ASCII), newTitle.toByteArray(Charsets.US_ASCII))) {
            throw IllegalStateException("Failed to patch window title bytes.")
        }
    }

    fun ByteArray.replaceFirst(needle: ByteArray, replacement: ByteArray): Boolean {
        val index = indexOf(needle)
        if (index == -1) {
            return false
        }

        for (x in index until index + replacement.size) {
            this[x] = replacement[x - index]
        }

        return true
    }

    private fun ByteArray.indexOf(needle: ByteArray): Int {
        outer@ for (i in 0 until this.size - needle.size + 1) {
            for (j in needle.indices) {
                if (this[i + j] != needle[j]) {
                    continue@outer
                }
            }
            return i
        }
        return -1
    }
}