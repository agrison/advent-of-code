package me.grison.aoc.y2022

import me.grison.aoc.*

class Day04 : Day(4, 2022) {
    override fun title() = "Camp Cleanup"

    override fun partOne() = solve { a, b -> a in b || b in a }

    override fun partTwo() = solve { a, b -> a.overlap(b) }

    private fun solve(predicate: (a: IntRange, b: IntRange) -> Boolean) =
        inputList.count { predicate(it.before(",").intRange(), it.after(",").intRange()) }
}
