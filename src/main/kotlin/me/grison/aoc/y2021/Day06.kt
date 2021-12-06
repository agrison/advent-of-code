package me.grison.aoc.y2021

import me.grison.aoc.Day
import me.grison.aoc.allInts
import me.grison.aoc.increase
import kotlin.collections.set

class Day06 : Day(6, 2021) {
    override fun title() = "Lanternfish"

    override fun partOne() = solveSmart(80)

    override fun partTwo() = solveSmart(256)

    private fun solve(numDays: Int): Long {
        val days = mutableMapOf<Int, Long>()
        inputString.allInts().forEach { fish -> days.increase(fish) }

        0.until(numDays).forEach { _ ->
            (days[0] ?: 0).let { first ->
                0.until(8).forEach { days[it] = days[it + 1] ?: 0 }
                days.increase(6, first)
                days[8] = first
            }
        }

        return days.values.sum()
    }

    private fun solveSmart(numDays: Int) =
        inputString.allInts().fold(Array<Long>(9) { 0 }) { days, i -> days.increase(i) }
            .let { 0.until(numDays).fold(it) { days, d -> days.increase((d + 7) % 9, days[d % 9]) }.sum() }
}
