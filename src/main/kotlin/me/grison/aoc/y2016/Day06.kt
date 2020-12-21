package me.grison.aoc.y2016

import me.grison.aoc.Day

class Day06 : Day(6, 2016) {
    override fun title() = "Signals and Noise"

    private val columns = inputList.map { it.stringList() }.transpose()

    override fun partOne() = solutions().first

    override fun partTwo() = solutions().second

    private fun solutions() : Pair<String, String> {
        var first = ""
        var second = ""
        for (column in columns) {
            val count = column.groupingBy { it }.eachCount()
            first += count.maxBy { it.value }!!.key
            second += count.minBy { it.value }!!.key
        }

        return p(first, second)
    }
}
