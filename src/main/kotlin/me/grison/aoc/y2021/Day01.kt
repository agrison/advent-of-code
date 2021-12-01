package me.grison.aoc.y2021

import me.grison.aoc.Day
import me.grison.aoc.ints

class Day01 : Day(1, 2021) {
    override fun title() = "Sonar Sweep"

    override fun partOne() = solve(1)

    override fun partTwo() = solve(3)

    private fun solve(size: Int): Int {
        return inputList.ints()
            .windowed(size).map { it.sum() }
            .windowed(2).count { it.last() > it.first() }
    }
}