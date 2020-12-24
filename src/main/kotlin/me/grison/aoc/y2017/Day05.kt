package me.grison.aoc.y2017

import me.grison.aoc.Day
import me.grison.aoc.ints

class Day05 : Day(5, 2017) {
    override fun title() = "A Maze of Twisty Trampolines, All Alike"

    override fun partOne() = steps { 1 }

    override fun partTwo() = steps { if (it >= 3) -1 else 1 }

    fun steps(inc: (Int) -> Int): Int {
        val ints = inputList.ints().toMutableList()
        var (index, step, size) = Triple(0, 0, ints.size)
        while (true) {
            val to = index + ints[index]
            ints[index] += inc(ints[index])
            index = to

            step++

            if (index >= size || index < 0)
                return step
        }
    }
}