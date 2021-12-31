package me.grison.aoc.y2020

import arrow.core.extensions.set.foldable.exists
import me.grison.aoc.*

class Day09 : Day(9, 2020) {
    override fun title() = "Encoding Error"

    private val series = inputList.map { it.toLong() }

    override fun partOne() =
            series[(25..series.size).first { i ->
                !sumOfTwoInPreamble(series[i], series.slice(i - 25..i).toSet())
            }]

    override fun partTwo(): Long {
        val (invalidNumber, scan) = Pair(partOne(), series.cumSum())
        val result = mutableListOf<Long>()
        scan.forEachIndexed { first, value ->
            (first until scan.size).find { last -> scan[last] - value == invalidNumber }
                    ?.let { last ->
                        result.add(
                            (series.slice(first..last).minOrNull() ?: 0L) + (series.slice(first..last).maxOrNull() ?: 0L))
                    }
        }
        return result.first()
    }

    private fun sumOfTwoInPreamble(n: Long, preamble: Set<Long>) =
            preamble.exists { p -> preamble.contains(n - p) && n - p != p }
}