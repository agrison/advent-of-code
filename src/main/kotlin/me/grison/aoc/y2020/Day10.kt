package me.grison.aoc.y2020

import me.grison.aoc.Day

class Day10 : Day(10, 2020) {
    override fun title() = "Adapter Array"

    private val adapters = (inputList.longs() + 0L).sorted()

    override fun partOne() = adapters.zip(adapters.tail())
            .fold(Pair(0, 1)) { a, b ->
                Pair(a.first + if (b.differenceIs(1)) 1 else 0, a.second + if (b.differenceIs(3)) 1 else 0)
            }.product()

    override fun partTwo(): Long {
        val ways = mutableMapOf(0L to 1L)
        adapters.tail().sorted().forEach { j ->
            ways[j] = listOf(1L, 2L, 3L).filter { it <= j }
                    .sumOf { ways.getOrDefault(j - it, 0L) }
        }
        return ways.getOrDefault(ways.keys.max() ?: 0L, 0L)
    }
}
