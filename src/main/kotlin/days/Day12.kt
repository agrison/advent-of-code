package days

import days.Day12.Direction.E

class Day12 : Day(12) {
    override fun title() = "Rain Risk"

    enum class Direction(val dx: Int, val dy: Int) { N(0, 1), E(1, 0), S(0, -1), W(-1, 0) }

    override fun partOne(): Int {
        var (pos, direction) = p(p(0, 0), E)

        inputList.forEach { l ->
            val (cmd, amount) = p(l.at(0), l.substring(1).toInt())
            when (cmd) {
                'F' -> pos += p(direction.dx * amount, direction.dy * amount)
                'N' -> pos += p(0, amount)
                'E' -> pos += p(amount, 0)
                'S' -> pos += p(0, -amount)
                'W' -> pos += p(-amount, 0)
                'L' -> direction = Direction.values()[(direction.ordinal + 1 * (amount / 90)) % 4]
                'R' -> direction = Direction.values()[(direction.ordinal + 3 * (amount / 90)) % 4]
            }
        }

        return pos.manhattan()
    }

    override fun partTwo(): Int {
        var (pos, waypoint) = p(p(0, 0), p(10, 1))

        inputList.forEach { l ->
            val (cmd, amount) = p(l.at(0), l.substring(1).toInt())
            when (cmd) {
                'F' -> pos += p(amount * waypoint.first, amount * waypoint.second)
                'N' -> waypoint += p(0, amount)
                'E' -> waypoint += p(amount, 0)
                'S' -> waypoint += p(0, -amount)
                'W' -> waypoint += p(-amount, 0)
                'L' -> (0 until amount / 90).forEach { waypoint = p(-waypoint.second, waypoint.first) }
                'R' -> (0 until amount / 90).forEach { waypoint = p(waypoint.second, -waypoint.first) }
            }
        }

        return pos.manhattan()
    }
}
