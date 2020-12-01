@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package util

import java.io.File
import io.vavr.kotlin.*;

object InputReader {

    fun inputAsString(day: Int): String {
        return fromResources(day).readText()
    }

    fun inputAsList(day: Int): List<String> {
        return fromResources(day).readLines()
    }

    fun inputAsVavrStrings(day: Int): io.vavr.collection.List<String> {
        return fromResources(day).readLines().toVavrList()
    }

    fun inputAsVavrInts(day: Int): io.vavr.collection.List<Int> {
        return fromResources(day).readLines().toVavrList().map { it.toInt() }
    }

    fun inputAsInts(day: Int, linesSeparated: Boolean = false): List<Int> {
        return (if (linesSeparated) inputAsList(day)
        else inputAsString(day).split("[^\\d]+|\n"))
                .filter { it.isNotBlank() }
                .map { it.toInt() }
    }

    private fun fromResources(day: Int): File {
        return File(javaClass.classLoader.getResource(
                day.toString().padStart(2, '0') + ".txt").toURI())
    }
}
