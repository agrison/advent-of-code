package me.grison.aoc.y2021

import me.grison.aoc.*

class Day02 : Day(2, 2021) {
    override fun title() = "Dive!"

    override fun partOne(): Int {
        var pos = p(0, 0)
        inputList.forEach {
            val (units) = it.allInts()
            pos = when {
                "forward" in it -> pos.forward(units)
                "down" in it -> pos.down(units)
                "up" in it -> pos.up(units)
                else -> pos
            }
        }

        return pos.product()
    }

    override fun partTwo(): Int {
        var pos = p(0, 0)
        var aim = 0
        inputList.forEach {
            val (units) = it.allInts()
            when {
                "forward" in it -> pos = pos.forward(units, aim * units)
                "down" in it -> aim += units
                "up" in it -> aim -= units
            }
        }

        return pos.product()
    }
}

