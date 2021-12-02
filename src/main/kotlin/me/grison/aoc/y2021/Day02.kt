package me.grison.aoc.y2021

import me.grison.aoc.*

class Day02 : Day(2, 2021) {
    override fun title() = "Dive!"

    override fun partOne(): Int {
        var pos = p(0, 0)
        inputList.forEach {
            val units = it.allInts().first()
            when {
                "forward" in it -> pos += p(units, 0)
                "down" in it -> pos += p(0, units)
                "up" in it -> pos += p(0, -units)
            }
        }

        return pos.product()
    }

    override fun partTwo(): Int {
        var pos = p(0, 0)
        var aim = 0
        inputList.forEach {
            val units = it.allInts().first()
            when {
                "forward" in it -> pos += p(units, aim * units)
                "down" in it -> aim += units
                "up" in it -> aim -= units
            }
        }

        return pos.product()
    }
}

