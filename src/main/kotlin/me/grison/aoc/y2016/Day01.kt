package me.grison.aoc.y2016

import me.grison.aoc.*
import scientifik.kmath.operations.Complex

class Day01 : Day(1, 2016) {
    override fun title() = "No Time for a Taxicab"

    private val rotations = mapOf('L' to 1.j, 'R' to (-1).j)

    override fun partOne(): Any {
        var (direction, location) = p(1.j, 0.j)

        inputString.normalSplit(", ").forEach { instr ->
            val (rot, dist) = p(instr[0], instr.substring(1).toInt())
            direction *= rotations[rot]!!

            repeat(dist) { location += direction }
        }

        return location.manhattan()
    }

    override fun partTwo(): Int {
        var (direction, location) = p(1.j, 0.j)
        val visited = mutableSetOf<Complex>()
        var twice = false

        inputString.normalSplit(", ").forEach { instr ->
            val (rot, dist) = p(instr[0], instr.substring(1).toInt())
            direction *= rotations[rot]!!

            repeat(dist) {
                location += direction
                if (!twice && location in visited) {
                    twice = true
                    return location.manhattan()
                } else {
                    visited.add(location)
                }
            }
        }

        return -1
    }
}
