package me.grison.aoc.y2016

import me.grison.aoc.Day
import me.grison.aoc.isDigits
import me.grison.aoc.words

class Day12 : Day(12, 2016) {
    override fun title() = "Leonardo's Monorail"

    private val instructions = inputList.map { "$it ." }.map { it.words() }

    override fun partOne() = emulate().getValue("a")

    override fun partTwo() = emulate(c = 1).getValue("a")

    private fun getValue(registers: Map<String, Int>, x: String) =
        if (x.isDigits()) {
            x.toInt()
        } else {
            registers.getValue(x)
        }

    private fun emulate(a: Int = 0, b: Int = 0, c: Int = 0, d: Int = 0): Map<String, Int> {
        var pc = 0
        val registers = mutableMapOf("a" to a, "b" to b, "c" to c, "d" to d)

        loop@ while (pc < instructions.size) {
            val (cmd, x, y) = instructions[pc]

            when (cmd) {
                "cpy" -> registers[y] = getValue(registers, x)
                "inc" -> registers[x] = registers.getValue(x) + 1
                "dec" -> registers[x] = registers.getValue(x) - 1
                "jnz" -> if (getValue(registers, x) != 0) {
                    pc += y.toInt()
                    continue@loop
                }
            }

            pc += 1
        }

        return registers
    }
}