package me.grison.aoc.y2017

import me.grison.aoc.Day
import me.grison.aoc.allInts
import me.grison.aoc.p

class Day06 : Day(6, 2017) {
    override fun title() = "Memory Reallocation"

    override fun partOne() = redistribution(inputString.allInts().toMutableList()).first()

    override fun partTwo() = redistribution(inputString.allInts().toMutableList()).let { l ->
        l[1] - l[2]
    }

    private fun redistribution(thing: MutableList<Int>): List<Int> {
        var (configs, c) = p(mutableSetOf<List<Int>>(), 0)
        while (thing !in configs) {
            configs.add(thing.toList())
            var (idx, num) = thing.withIndex().maxByOrNull { it.value }!!
            thing[idx] = 0

            while (num-- > 0) {
                idx = (idx + 1) % thing.size
                thing[idx]++
            }
            c++
        }

        return listOf(c, configs.size, configs.indexOf(thing))
    }
}