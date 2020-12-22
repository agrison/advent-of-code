package me.grison.aoc.y2015

import me.grison.aoc.Day

class Day08 : Day(8, 2015) {
    override fun title() = "Matchsticks"

    override fun partOne(): Int = inputList.fold(0) { acc, line ->
        val mem = line.replace("\\\\", "?").replace("\\\"", "?").replace("""\\x..""".regex(), "?")
        acc + line.length - mem.substring(1, mem.length - 1).length
    }

    override fun partTwo() = inputList.fold(0) { acc, line ->
        val mem = '"' + line.replace("\\", "\\\\").replace("\"", "\\\"") + '"'
        acc + mem.length - line.length
    }
}