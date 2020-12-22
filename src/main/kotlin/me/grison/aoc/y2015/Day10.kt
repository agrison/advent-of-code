package me.grison.aoc.y2015

import me.grison.aoc.Day


class Day10 : Day(10, 2015) {
    override fun title() = "Elves Look, Elves Say"

    override fun partOne() = lookAndSay(40)

    override fun partTwo() = lookAndSay(50)

    private fun lookAndSay(times: Int): Int {
        var (s, repetitions) = p(inputString, """(\d)\1*""".regex())
        (0 until times).forEach { _ ->
            s = repetitions.findAll(s).fold(StringBuilder()) { acc, c ->
                acc.append("${c.value.length}${c.value[0]}")}.toString()
        }
        return s.length
    }
}
