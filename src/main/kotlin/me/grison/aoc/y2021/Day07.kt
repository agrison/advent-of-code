package me.grison.aoc.y2021

import me.grison.aoc.Day
import me.grison.aoc.allInts
import kotlin.math.abs

class Day07 : Day(7, 2021) {
    override fun title() = "The Treachery of Whales"

    override fun partOne() = solve { it }

    override fun partTwo() = solve { it * (it + 1) / 2 }

    private fun solve(fuelCost: (difference: Int) -> Int): Int {
        return inputString.allInts().let { crabs ->
            crabs.minOf { horizontal -> crabs.sumBy { fuelCost(abs(it - horizontal)) } }
        }
    }
}
