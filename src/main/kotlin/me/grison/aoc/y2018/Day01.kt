package me.grison.aoc.y2018

import me.grison.aoc.Day
import me.grison.aoc.ints

class Day01 : Day(1, 2018) {
    override fun title() = "Chronal Calibration"

    override fun partOne() = inputList.ints().sum()

    override fun partTwo(): Int {
        val seen = hashSetOf(0)
        var sum = 0
        while (true) {
            inputList.ints().forEach {
                sum += it
                if (seen.contains(sum)) return sum
                else seen.add(sum)
            }
        }
    }
}