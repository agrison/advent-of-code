package me.grison.aoc.y2020

import me.grison.aoc.*
import me.grison.aoc.y2020.Day12.Direction.E

class Day12 : Day(12, 2020) {
    override fun title() = "Rain Risk"

    override fun partOne(): Int {
        var (pos, dir) = p(p(0, 0), E)

        inputList.forEach { l ->
            val (cmd, amount) = p(l.slice(0..0), l.substring(1).toInt())
            when (cmd) {
                "L" -> dir = Direction.values()[(dir.ordinal + 1 * (amount / 90)) % 4]
                "R" -> dir = Direction.values()[(dir.ordinal + 3 * (amount / 90)) % 4]
                "F" -> pos = move(pos, dir, amount)
                // N, E, S, W
                else -> pos = move(pos, cmd.direction(), amount)
            }
        }

        return pos.manhattan()
    }

    override fun partTwo(): Int {
        var (pos, waypoint) = p(p(0, 0), p(10, 1))

        inputList.forEach { l ->
            val (cmd, amount) = p(l.slice(0..0), l.substring(1).toInt())
            when (cmd) {
                "L" -> repeat(amount / 90) { waypoint = p(-waypoint.second, waypoint.first) }
                "R" -> repeat(amount / 90) { waypoint = p(waypoint.second, -waypoint.first) }
                "F" -> pos += p(amount * waypoint.first, amount * waypoint.second)
                // N, E, S, W
                else -> waypoint = move(waypoint, cmd.direction(), amount)
            }
        }

        return pos.manhattan()
    }

    enum class Direction(val dx: Int, val dy: Int) { N(0, 1), E(1, 0), S(0, -1), W(-1, 0) }
    private fun String.direction() = Direction.valueOf(this)

    private fun move(pos: Pair<Int, Int>, direction: Direction, amount: Int) =
            pos + p(direction.dx * amount, direction.dy * amount)
}