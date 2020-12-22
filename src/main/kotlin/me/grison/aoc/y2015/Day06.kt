package me.grison.aoc.y2015

import me.grison.aoc.*

class Day06 : Day(6, 2015) {
    override fun title() = "Probably a Fire Hazard"

    override fun partOne() = evolve({ 1 }, { 0 }, { if (it == 0) 1 else 0 })
            .count { it == 1 }

    override fun partTwo() = evolve({ it + 1 }, { if (it == 0) 0 else it - 1 }, { it + 2 })
            .sum()

    private fun evolve(on: (_: Int) -> Int, off: (_: Int) -> Int, toggle: (_: Int) -> Int): IntArray {
        val lights = IntArray(1000 * 1000)
        inputList.forEach {
            val cmd = parse(it)
            for (x in (cmd.x1..cmd.x2)) {
                for (y in (cmd.y1..cmd.y2)) {
                    val index = 1000 * x + y
                    lights[index] = when (cmd.type) {
                        "turn on" -> on(lights[index])
                        "turn off" -> off(lights[index])
                        "toggle" -> toggle(lights[index])
                        else -> lights[index]
                    }
                }
            }
        }
        return lights
    }

    private fun parse(s: String): Command {
        val m = "([^\\d]+) (\\d+),(\\d+) through (\\d+),(\\d+)".regex().find(s)!!
        val (type, x1, y1, x2, y2) = m.destructured
        return Command(type.trim(), x1.int(), y1.int(), x2.int(), y2.int())
    }

    data class Command(val type: String, val x1: Int, val y1: Int, val x2: Int, val y2: Int)
}