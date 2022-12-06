package me.grison.aoc.y2022

import me.grison.aoc.Day
import me.grison.aoc.get

class Day06 : Day(6, 2022) {
    override fun title() = "Tuning Trouble"

    override fun partOne() = solve(4)

    override fun partTwo() = solve(14)

    fun solve(start: Int) =
        (start..inputLength).first {
            inputString[it - start, it].toSet().size == start
        }
}
