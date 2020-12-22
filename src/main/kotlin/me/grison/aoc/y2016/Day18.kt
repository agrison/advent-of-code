package me.grison.aoc.y2016

import me.grison.aoc.*

class Day18 : Day(18, 2016) {
    override fun title() = "Like a Rogue"

    override fun partOne() = safes().first

    override fun partTwo() = safes().second

    private fun safes(): Pair<Int, Int> {
        var (row, part1, part2) = Triple(inputString, 0, 0)

        (0 until 400000).forEach { i ->
            if (i == 40)
                part1 = part2
            part2 += row.count { it == '.' }
            val old = ".$row.".also { row = "" }
            for ((left, right) in old.zip(old.substring(2))) {
                row += if (left != right) "^" else "."
            }
        }

        return p(part1, part2)
    }
}
