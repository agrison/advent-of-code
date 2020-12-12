package days

class Day12 : Day(12) {
    override fun title() = "Rain Risk"
    // ugly
    val degToDir = mapOf(0 to "east", -360 to "east",
            90 to "north", -270 to "north",
            180 to "west", -180 to "west",
            270 to "south", -90 to "south")
    val dirToDeg = degToDir.entries.associate { (k, v) -> v to k }

    fun forward(direction: String, pa: Pair<Int, Int>, amount: Int): Pair<Int, Int> {
        return when (direction) {
            "east" -> pa + p(amount, 0)
            "west" -> pa + p(-amount, 0)
            "north" -> pa + p(0, amount)
            "south" -> pa + p(0, -amount)
            else -> pa
        }
    }

    override fun partOne(): Int {
        var (pos, direction) = p(p(0, 0), "east")

        inputList.forEach { l ->
            val (cmd, amount) = p(l.at(0), l.substring(1).toInt())
            when (cmd) {
                'F' -> pos = forward(direction, pos, amount)
                'E' -> pos += p(amount, 0)
                'W' -> pos += p(-amount, 0)
                'N' -> pos += p(0, amount)
                'S' -> pos += p(0, -amount)
                'L' -> direction = degToDir[(dirToDeg[direction]!! + amount) % 360]!!
                'R' -> direction = degToDir[(dirToDeg[direction]!! - amount) % 360]!!
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
                'E' -> waypoint += p(amount, 0)
                'W' -> waypoint += p(-amount, 0)
                'N' -> waypoint += p(0, amount)
                'S' -> waypoint += p(0, -amount)
                'L' -> waypoint = when (amount / 90) {
                    1 -> p(-waypoint.second, waypoint.first)
                    2 -> p(-waypoint.first, -waypoint.second)
                    3 -> p(waypoint.second, -waypoint.first)
                    else -> waypoint
                }
                'R' -> waypoint = when (amount / 90) {
                    1 -> p(waypoint.second, -waypoint.first)
                    2 -> p(-waypoint.first, -waypoint.second)
                    3 -> p(-waypoint.second, waypoint.first)
                    else -> waypoint
                }
            }
        }

        return pos.manhattan()
    }
}
