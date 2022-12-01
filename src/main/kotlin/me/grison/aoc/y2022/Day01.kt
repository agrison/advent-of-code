package me.grison.aoc.y2022

import me.grison.aoc.Day
import me.grison.aoc.allInts

class Day01 : Day(1, 2022) {
    override fun title() = "Calorie Counting"

    override fun partOne() = solve(1)

    override fun partTwo() = solve(3)

    private fun solve(top: Int) =
        inputGroups.map { it.allInts().sum() }
            .sortedDescending()
            .take(top)
            .sum()
}