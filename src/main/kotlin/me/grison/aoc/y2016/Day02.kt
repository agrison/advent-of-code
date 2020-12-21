package me.grison.aoc.y2016

import me.grison.aoc.Day
import scientifik.kmath.operations.Complex
import kotlin.math.abs

class Day02 : Day(2, 2016) {
    override fun title() = "Bathroom Security"

    private val directions = mapOf('R' to 1.r, 'L' to (-1).r, 'D' to 1.j, 'U' to (-1).j)
    private val kp1 = listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9)
    )

    private val kp2 = listOf(
        listOf(0, 0, 1, 0, 0),
        listOf(0, 2, 3, 4, 0),
        listOf(5, 6, 7, 8, 9),
        listOf(0, 'A', 'B', 'C', 0),
        listOf(0, 0, 'D', 0, 0),
    )

    override fun partOne() = bathroomCode()

    override fun partTwo() = bathroomCode(true)

    private fun bathroomCode(part2: Boolean = false): String {
        fun inside(pos: Complex) = when (part2) {
            false -> abs(pos.re) <= 1 && abs(pos.im) <= 1
            else -> pos.manhattan() <= 2
        }

        fun getKey(pos: Complex, kp: KeyPad): String {
            return kp[pos.im.toInt()][pos.re.toInt()].toString()
        }

        var kp: KeyPad = kp1
        var offset = Complex(1, 1)
        var pos = 0.r
        if (part2) {
            kp = kp2
            offset = Complex(2, 2)
            pos = (-2).r
        }

        val positions = mutableListOf<Complex>()
        inputList.forEach { line ->
            line.forEach { direction ->
                val new = pos + directions[direction]!!
                pos = if (inside(new)) new else pos
            }
            positions.add(pos)
        }

        return positions.map { getKey(it + offset, kp) }.join()
    }
}

typealias KeyPad = List<List<Any>>