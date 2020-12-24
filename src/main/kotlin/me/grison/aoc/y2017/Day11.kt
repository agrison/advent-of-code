package me.grison.aoc.y2017

import me.grison.aoc.*

class Day11 : Day(11, 2017) {
    override fun title() = "Hex Ed"

    override fun partOne() = solve().first

    override fun partTwo() = solve().second

    private fun solve(): Pair<Int, Int> {
        val dirs = mapOf(
            "n" to pd(0.0, -1.0),
            "ne" to pd(1.0, -0.5),
            "se" to pd(1.0, 0.5),
            "s" to pd(0.0, 1.0),
            "sw" to pd(-1.0, 0.5),
            "nw" to pd(-1.0, -0.5)
        )
        var pp = pd(0.0, 0.0)
        var (one, two) = pd(0.0, 0.0)

        inputString.normalSplit(",").forEach { dir ->
            pp = pd(pp.first + dirs[dir]!!.first, pp.second + dirs[dir]!!.second)
            two = kotlin.math.max(two, pp.distance())
        }
        one = pp.distance()

        return p(one.toInt(), two.toInt())
    }
}