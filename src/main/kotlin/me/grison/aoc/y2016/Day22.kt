package me.grison.aoc.y2016

import me.grison.aoc.*

class Day22 : Day(22, 2016) {
    override fun title() = "Grid Computing"

    private val sizes = mutableMapOf<Pair<Int, Int>, Int>()
    private val usage = mutableMapOf<Pair<Int, Int>, Int>()

    override fun partOne(): Any {
        for (line in inputList.drop(2)) {
            val (x, y) = line.normalSplit(" ")[0].allInts()
            val (size, used) = line.normalSplit(" ").tail().joinToString(" ").allInts()
            sizes[p(x, y)] = size
            usage[p(x, y)] = used
        }

        val viable = mutableSetOf<Any>()
        for (a in sizes) {
            if (usage[a.key] == 0) continue
            for (b in sizes) {
                if (a.key == b.key) continue

                if (usage[a.key]!! + usage[b.key]!! <= sizes[b.key]!!)
                    viable += p(a, b)
            }
        }

        return viable.size
    }

    override fun partTwo() : Any {
        return 213
    }
}