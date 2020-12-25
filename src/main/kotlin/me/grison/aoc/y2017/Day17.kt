package me.grison.aoc.y2017

import me.grison.aoc.*

class Day17 : Day(17, 2017) {
    override fun title() = "Spinlock "

    override fun partOne() = solve().first

    override fun partTwo() = solve().second

    private fun solve(): Pair<Int, Int> {
        var (input, buffer, pt) = Triple(inputString.toInt(), mutableListOf(0), 0)
        for (i in (1..2017)) {
            pt = (pt + input) % i + 1
            buffer.add(pt, i)
        }

        var (one, two) = p(buffer[(pt+1) % 2018], 0)
        for (i in (1..50_000_000)) {
            pt = (pt + input) % i + 1
            if (pt == 1)
                two = i
        }

        return p(one, two)
    }
}