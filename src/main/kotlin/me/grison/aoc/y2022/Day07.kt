package me.grison.aoc.y2022

import arrow.syntax.function.memoize
import me.grison.aoc.*

class Day07 : Day(7, 2022) {
    override fun title() = "No Space Left On Device"

    override fun partOne() = disk().values.filter { it <= 100000 }.sum()

    override fun partTwo() = (70000000 - disk().getValue("/<root>")).let { emptySpace ->
        disk().values.filter { emptySpace + it > 30000000 }.minOrNull()
    }

    private val disk = {
        val directorySizes = mutableMapOf<String, Int>().withDefault { 0 }
        val currentPath = mutableListOf<String>()
        inputList
            .filter { !it.startsWith("dir ") }
            .map { ("$it #").words() }
            .forEach { (promptOrSize, commandOrName, cdPath) ->
                when (promptOrSize) {
                    "$" -> when (commandOrName) {
                        "cd" -> when (cdPath) {
                            "/" -> currentPath.reset("<root>")
                            ".." -> currentPath.removeLast()
                            else -> currentPath.append(cdPath)
                        }
                    }
                    else -> currentPath.scan("") { acc, dir -> "$acc/$dir" }.forEach { fullPath ->
                        directorySizes.increase(fullPath, promptOrSize.toInt())
                    }
                }
            }
        directorySizes
    }.memoize()
}
