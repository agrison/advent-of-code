package me.grison.aoc.y2021

import me.grison.aoc.*

/**
 * Solved it manually exactly like Day 23, with some more luck involved.
 * Solution is based on the brilliant explanation you'll find here:
 * https://github.com/dphilipson/advent-of-code-2021/blob/master/src/days/day24.rs
 */
class Day24 : Day(24, 2021) {
    override fun title() = "Arithmetic Logic Unit"

    override fun partOne() = solve(9999999 downTo 1111111)

    // should start at 1111111, but speed it up starting near the correct answer
    override fun partTwo() = solve(2222222..9999999)

    private fun solve(range: IntProgression): Long {
        val cycleSize = inputList.tail().withIndex().first { it.value.startsWith("inp") }.index + 1
        val parts = inputList.chunked(cycleSize).map { Values(it) }

        for (input in range) {
            var (z, digitPosition, submarineNumber) = Triple(0, 0, "")
            for (part in parts) {
                val digit: Int
                if (part.div == 1) {
                    digit = input.padLeft(7, '0')[digitPosition].int()
                    digitPosition++
                } else
                    digit = z % 26 + part.check
                z = part.computeZ(digit, z)
                submarineNumber += digit
            }

            if (validNumber(z, submarineNumber))
                return submarineNumber.toLong()
        }

        return 0
    }

    private fun validNumber(z: Int, number: String) =
        z == 0 && number.length == 14 && !number.contains('0')

    data class Values(val div: Int, val check: Int, val offset: Int) {
        constructor(instructions: Instructions) :
                this(
                    instructions.first { it.startsWith("div") }.firstInt(),  // first DIV
                    instructions.second { it.startsWith("add") }.firstInt(), // second ADD
                    instructions.filter { it.startsWith("add") }.butLast().last().firstInt() // before last ADD
                )

        fun computeZ(w: Int, z: Int): Int {
            val x = if ((z % 26) + check != w) 1 else 0
            return (z / div) * (25 * x + 1) + ((w + offset) * x)
        }
    }
}

private typealias Instructions = List<String>
