package me.grison.aoc.y2016

import me.grison.aoc.*

class Day20 : Day(20, 2016) {
    override fun title() = "Firewall Rules"

    val ips = inputList.map { it.allLongs(includeNegative = false) }.map { p(it[0], it[1]) }
        .sortedWith(compareBy({it.first}, {it.second}))

    override fun partOne() = lowest().first

    override fun partTwo() = lowest().second

    private fun lowest(): Pair<Long, Long> {
        var (nr_available, lowest_available, the_lowest) = Triple(0L, 0L, 0L)

        for ((low, high) in ips) {
            if (low > lowest_available) {
                nr_available += low - lowest_available
                if (the_lowest == 0L) {
                    the_lowest = lowest_available
                }
            }
            lowest_available = if(lowest_available > high + 1L) lowest_available else high + 1L
        }
        return p(the_lowest, nr_available)
    }
}