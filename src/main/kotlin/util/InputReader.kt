@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package util

import java.io.File
import io.vavr.kotlin.*;
import java.lang.System.lineSeparator

object InputReader {

    fun inputAsString(day: Int, year: Int): String {
        return fromResources(day, year).readText()
    }

    fun inputAsList(day: Int, year: Int): List<String> {
        return fromResources(day, year).readLines()
    }

    fun inputAsGroups(day: Int, year: Int): List<String> {
        return fromResources(day, year).readText().split(lineSeparator() + lineSeparator())
    }

    fun inputAsSet(day: Int, year: Int): Set<String> {
        val s = LinkedHashSet<String>()
        s.addAll(fromResources(day, year).readLines())
        return s
    }

    fun inputAsVavrStrings(day: Int, year: Int): io.vavr.collection.List<String> {
        return fromResources(day, year).readLines().toVavrList()
    }

    fun inputAsVavrInts(day: Int, year: Int): io.vavr.collection.List<Int> {
        return fromResources(day, year).readLines().toVavrList().map { it.toInt() }
    }

    fun inputAsInts(day: Int, year: Int, linesSeparated: Boolean = false): List<Int> {
        return (if (linesSeparated) inputAsList(day, year)
        else inputAsString(day, year).split("[^\\d]+|\n"))
                .filter { it.isNotBlank() }
                .map { it.toInt() }
    }

    private fun fromResources(day: Int, year: Int): File {
        return File(javaClass.classLoader.getResource("$year/" +
                day.toString().padStart(2, '0') + ".txt").toURI())
    }
}
