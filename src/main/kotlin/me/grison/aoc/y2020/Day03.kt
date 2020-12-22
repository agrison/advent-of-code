package me.grison.aoc.y2020

import me.grison.aoc.*

class Day03 : Day(3, 2020) {
    override fun title() = "Toboggan Trajectory"

    override fun partOne() = slope(3, 1)

    override fun partTwo() = listOf(11, 31, 51, 71, 12)
            .map { slope(it / 10, it % 10) }
            .product()

    private fun slope(cols: Int, rows: Int): Long =
            inputList.mapIndexed { y, row ->
                val (x, c) = Pair(cols * y, cols * y / rows)
                (row.debug03(false, c) && x.divisible(rows) && row.at(c, '#')).toInt()
            }.fold(0L, Long::plus)
}