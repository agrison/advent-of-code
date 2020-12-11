package days

import days.SeatStrategy.Adjacent
import days.SeatStrategy.FirstSeen

class Day11 : Day(11) {
    override fun title() = "Seating System"

    override fun partOne(): Int = evolve(Adjacent)

    override fun partTwo(): Int = evolve(FirstSeen)

    private fun evolve(strategy: SeatStrategy): Int {
        var seats = loadSeats()
        while (true) {
            val copy = seats.evolve(strategy)
            if (copy == seats)
                break
            seats = copy
        }
        return seats.occupied()
    }

    private fun Seats.adjacent(pos: Position): Int {
        return listOf(Pair(-1, -1), Pair(-1, 0), Pair(-1, 1), Pair(0, -1), Pair(0, 1), Pair(1, -1), Pair(1, 0), Pair(1, 1))
                .map { it + pos }
                .count { getOrDefault(it, ".") == "#" }
    }

    private fun Seats.firstSeen(pos: Position): Int {
        val adj = mutableSetOf<Position>()
        /*N*/ (pos.first - 1 downTo 0).firstOrNull { seat(it, pos.second) }?.let { adj.add(Pair(it, pos.second)) }
        /*S*/ (pos.first + 1..inputList.size).firstOrNull { seat(it, pos.second) }?.let { adj.add(Pair(it, pos.second)) }
        /*E*/ (pos.second + 1..inputList[0].length).firstOrNull { seat(pos.first, it) }?.let { adj.add(Pair(pos.first, it)) }
        /*W*/ (pos.second - 1 downTo 0).firstOrNull { seat(pos.first, it) }?.let { adj.add(Pair(pos.first, it)) }
        /*NE*/(1..5).firstOrNull { seat(pos + Pair(-it, it)) }?.let { adj.add(pos + Pair(-it, it)) }
        /*NW*/(1..5).firstOrNull { seat(pos + Pair(-it, -it)) }?.let { adj.add(pos + Pair(-it, -it)) }
        /*SE*/(1..5).firstOrNull { seat(pos + Pair(it, it)) }?.let { adj.add(pos + Pair(it, it)) }
        /*SW*/(1..5).firstOrNull { seat(pos + Pair(it, -it)) }?.let { adj.add(pos + Pair(it, -it)) }

        return adj.filter { occupied(it) }.count()
    }

    private fun Seats.evolve(strategy: SeatStrategy): Seats {
        val copy = toMutableMap()
        entries.forEach { (seat, state) ->
            if (state == "L" && ((if (strategy == Adjacent) adjacent(seat) else firstSeen(seat)) == 0)) {
                copy.replace(seat, "#")
            } else if (state == "#" && (if (strategy == Adjacent) adjacent(seat) >= 4 else firstSeen(seat) >= 5)) {
                copy.replace(seat, "L")
            }
        }
        return copy
    }

    private fun loadSeats(): Seats {
        var (i, seats) = Pair(0, mutableMapOf<Position, String>())
        inputList.forEach { row ->
            var j = 0
            row.toCharArray().forEach { seat ->
                seats[Pair(i, j)] = seat.toString()
                j++
            }
            i++
        }
        return seats.toSortedMap { p1, p2 ->
            if (p1.first == p2.first) p1.second.compareTo(p2.second)
            else p1.first.compareTo(p2.first)
        }
    }
}

typealias Position = Pair<Int, Int>
typealias Seats = MutableMap<Position, String>

enum class SeatStrategy { Adjacent, FirstSeen }

fun Seats.occupied() = entries.count { it.value == "#" }
fun Seats.occupied(p: Pair<Int, Int>) = get(p) == "#"
fun Seats.seat(i: Int, j: Int) = seat(Pair(i, j))
fun Seats.seat(p: Pair<Int, Int>) = get(p) != "."
