package me.grison.aoc.y2017

import me.grison.aoc.Day
import me.grison.aoc.allInts
import me.grison.aoc.pair

class Day13 : Day(13, 2017) {
    override fun title() = "Packet Scanners"

    override fun partOne(): Any {
        val heights = inputList.map { it.allInts().pair() }.toMap()

        return heights.filter { scan(it.value, it.key) == 0 }
            .keys
            .sumOf { it * heights[it]!! }
    }

    override fun partTwo(): Any {
        val heights = inputList.map { it.allInts().pair() }.toMap()

        var wait = 0
        while (true) {
            if (heights.filter { scan(it.value, wait + it.key) == 0 }.isEmpty()) {
                return wait
            }
            wait++
        }
    }

    private fun scan(height: Int, time: Int): Int {
        val off = time % ((height - 1) * 2)
        return if (off > height) 2 * (height - 1) - off else off
    }
}