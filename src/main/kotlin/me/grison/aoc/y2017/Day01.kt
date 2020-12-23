package me.grison.aoc.y2017

import me.grison.aoc.Day
import me.grison.aoc.regex

class Day01 : Day(1, 2017) {
    override fun title() = "Inverse Captcha"

    override fun partOne() = solve(1)
    override fun partTwo() = solve(inputString.length / 2)

    private fun solve(n: Int): Int {
        return """(\d)(?=.{${n - 1}}\1)""".regex().findAll(
            inputString + inputString.substring(0, n)
        ).sumBy { it.value.toInt() }
    }
}
