package me.grison.aoc.y2017

import me.grison.aoc.Day
import me.grison.aoc.p
import me.grison.aoc.stringList

class Day09 : Day(9, 2017) {
    override fun title() = "Stream Processing"

    // 13154
    override fun partOne() = solve().first

    // 6369
    override fun partTwo() = solve().second

    private fun solve(): Pair<Int, Int> {
        var (opened, garbage, ignore) = Triple(0, false, false)
        var (one, two) = p(0, 0)
        inputString.stringList().forEach { c ->
            if (ignore) ignore = false
            else if (c == "!") ignore = true
            else if (garbage)
                if (c == ">") garbage = false
                else two++
            else if (c == "<") garbage = true
            else if (c == "{") opened += 1
            else if (c == "}") one += opened--
        }

        return p(one, two)
    }
}