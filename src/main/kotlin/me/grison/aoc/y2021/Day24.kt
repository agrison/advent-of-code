package me.grison.aoc.y2021

import me.grison.aoc.*

class Day24 : Day(24, 2021) {
    override fun title() = "Arithmetic Logic Unit"

    override fun partOne() = solve(9999999 downTo 1111111)

    override fun partTwo() = solve(1111111..9999999)

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
        constructor(instructions: List<String>) :
                this(
                    instructions.first { it.startsWith("div") }.allInts().first(),  // first DIV
                    instructions.filter { it.startsWith("add") }[1].allInts().first(), // second ADD
                    instructions.filter { it.startsWith("add") }.butLast().last().allInts().first() // before last ADD
                )

        fun computeZ(w: Int, z: Int): Int {
            val x = if ((z % 26) + check != w) 1 else 0
            return (z / div) * (25 * x + 1) + ((w + offset) * x)
        }
    }
}
