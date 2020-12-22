package me.grison.aoc.y2015

import me.grison.aoc.*

class Day03 : Day(3, 2015) {
    override fun title() = "Perfectly Spherical Houses in a Vacuum"

    val input = inputString.toList()

    override fun partOne(): Int = path(input).size

    override fun partTwo(): Int =
            (path(input.filterIndexed { i, _ -> i % 2 == 0 })
                    + path(input.filterIndexed { i, _ -> i % 2 == 1 })).size

    private fun path(input: List<Char>): Set<Pair<Int, Int>> {
        val coordinates = mutableSetOf(p(0, 0))
        input.fold(p(0, 0)) { curCoords, dir ->
            val newCoords = when (dir) {
                '^' -> p(curCoords.first, curCoords.second + 1)
                'v' -> p(curCoords.first, curCoords.second - 1)
                '>' -> p(curCoords.first + 1, curCoords.second)
                '<' -> p(curCoords.first - 1, curCoords.second)
                else -> p(0, 0)
            }

            coordinates.add(newCoords)
            newCoords
        }
        return coordinates
    }
}