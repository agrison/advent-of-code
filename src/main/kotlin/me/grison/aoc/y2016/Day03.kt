package me.grison.aoc.y2016

import me.grison.aoc.Day

class Day03 : Day(3, 2016) {
    override fun title() = "Squares With Three Sides"

    override fun partOne() = triangles(inputList.map { it.allInts() })

    override fun partTwo() = inputList.map { it.allInts() }.let { h ->
        val vertical = listOf(h.map { it[0] } + h.map { it[1] } + h.map { it[2] })
        triangles(vertical, true)
    }

    private fun validTriangle(sides: List<Int>): Boolean {
        val (a, b, c) = sides.sorted()
        return a + b > c
    }

    private fun triangles(candidates: List<List<Int>>, part2: Boolean = false): Int {
        return when (part2) {
            false -> candidates.count { validTriangle(it) }
            else -> {
                val flattened = candidates.flatten()
                (0 until (flattened.size - 2) step 3)
                    .map { flattened.subList(it, it + 3) }.count { validTriangle(it) }
            }
        }
    }
}
