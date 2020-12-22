package me.grison.aoc.y2015

import io.vavr.kotlin.toVavrList
import me.grison.aoc.Day
import me.grison.aoc.longs

class Day24 : Day(24, 2015) {
    override fun title() = "It Hangs in the Balance"

    private val weights = inputList.longs()

    override fun partOne() = solve(3)

    override fun partTwo() = solve(4)

    private fun solve(groups: Long): Long {
        val groupSize = weights.sum() / groups

        weights.indices.forEach { i ->
            val quantums = weights.toVavrList().combinations(i)
                .filter { c -> c.sum() == groupSize }
                .map { it.product() }
            if (!quantums.isEmpty) {
                return quantums.min().get().toLong()
            }
        }

        return 0
    }
}