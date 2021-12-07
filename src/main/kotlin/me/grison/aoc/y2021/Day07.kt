package me.grison.aoc.y2021

import me.grison.aoc.Day
import me.grison.aoc.allInts
import kotlin.math.abs

class Day07 : Day(7, 2021) {
    override fun title() = "The Treachery of Whales"

    override fun partOne() = solve { it }
    // this is way more performant in part 1 to search for the median value directly (2ms vs 34ms on my input).
    /*
    override fun partOne() =
        inputString.allInts().let { crabs ->
            crabs.median().let { median -> crabs.sumOf { abs(it - median) } }
        }
    */

    override fun partTwo() = solve { it * (it + 1) / 2 }

    private fun solve(movingCost: (Int) -> Int): Int {
        return inputString.allInts().let { crabs ->
            crabs.minOf { u -> crabs.sumBy { x -> movingCost(abs(x - u)) } }
        }
    }
}
