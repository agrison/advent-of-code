package me.grison.aoc.y2017

import me.grison.aoc.*

class Day15 : Day(15, 2017) {
    override fun title() = "Dueling Generators"

    override fun partOne() = solve().first

    override fun partTwo() = solve().second

    private fun solve(): Pair<Long, Long> {
        fun nextA(a: Long) = (a * 16_807) % Int.MAX_VALUE
        fun nextB(a: Long) = (a * 48_271) % Int.MAX_VALUE
        fun bitEqual(a: Long, b: Long) = ((a % 65_536) == (b % 65_536)).toInt()
        var (a, b) = inputString.allLongs()
        var (one, two) = Pair(0L, 2L)
        repeat(40_000_000) {
            a = nextA(a).also { b = nextB(b) }
            one += bitEqual(a, b)
        }

        repeat(5_000_000) {
            do a = nextA(a) while (!a.divisible(4))
            do b = nextB(b) while (!b.divisible(8))
            two += bitEqual(a, b)
        }

        return p(one, two)
    }
}