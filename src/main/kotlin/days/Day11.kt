package days

import kotlin.reflect.KFunction2

class Day11 : Day(11) {
    override fun title() = "Seating System"

    override fun partOne(): Int = evolve(4, this::adjacent)

    override fun partTwo(): Int = evolve(5, this::firstSeen)

    private fun evolve(tolerance: Int, strategy: KFunction2<AllSeats, SeatPosition, Int>): Int {
        var seats = loadSeats()
        while (true)
            when (val copy = evolve(seats, tolerance, strategy)) {
                seats -> return seats.occupied()
                else -> seats = copy
            }
    }

    private fun adjacent(seats: AllSeats, pos: SeatPosition): Int {
        return listOf(p(-1, -1), p(-1, 0), p(-1, 1), p(0, -1), p(0, 1), p(1, -1), p(1, 0), p(1, 1))
                .count { seats[it + pos] == "#" }
    }

    private fun firstSeen(s: AllSeats, pos: SeatPosition): Int {
        val adj = mutableSetOf<SeatPosition>()
        /*N*/  (pos.first - 1 downTo 0).firstOrNull { s.isSeat(it, pos.second) }?.let { adj.add(p(it, pos.second)) }
        /*S*/  (pos.first + 1..inputList.size).firstOrNull { s.isSeat(it, pos.second) }?.let { adj.add(p(it, pos.second)) }
        /*E*/  (pos.second + 1..inputList[0].length).firstOrNull { s.isSeat(pos.first, it) }?.let { adj.add(p(pos.first, it)) }
        /*W*/  (pos.second - 1 downTo 0).firstOrNull { s.isSeat(pos.first, it) }?.let { adj.add(p(pos.first, it)) }
        /*NE*/ (1..5).firstOrNull { s.isSeat(pos + p(-it, it)) }?.let { adj.add(pos + p(-it, it)) }
        /*NW*/ (1..5).firstOrNull { s.isSeat(pos + p(-it, -it)) }?.let { adj.add(pos + p(-it, -it)) }
        /*SE*/ (1..5).firstOrNull { s.isSeat(pos + p(it, it)) }?.let { adj.add(pos + p(it, it)) }
        /*SW*/ (1..5).firstOrNull { s.isSeat(pos + p(it, -it)) }?.let { adj.add(pos + p(it, -it)) }

        return adj.filter { s.occupied(it) }.count()
    }

    private fun evolve(s: AllSeats, tolerance: Int, strategy: KFunction2<AllSeats, SeatPosition, Int>): AllSeats {
        return s.toMutableMap().let { copy ->
            s.entries.forEach { (seat, state) ->
                if (state == "L" && strategy(s, seat) == 0)
                    copy.replace(seat, "#")
                else if (state == "#" && strategy(s, seat) >= tolerance)
                    copy.replace(seat, "L")
            }
            copy
        }
    }

    private fun loadSeats(): AllSeats {
        var (i, seats) = p(0, mutableMapOf<SeatPosition, String>())
        inputList.forEach { row ->
            var j = 0
            row.toCharArray().forEach { seat ->
                seats[p(i, j)] = seat.toString()
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

typealias SeatPosition = Pair<Int, Int>
typealias AllSeats = MutableMap<SeatPosition, String>

fun AllSeats.occupied() = entries.count { it.value == "#" }
fun AllSeats.occupied(p: Pair<Int, Int>) = get(p) == "#"
fun AllSeats.isSeat(i: Int, j: Int) = isSeat(Pair(i, j))
fun AllSeats.isSeat(p: Pair<Int, Int>) = get(p) != "."
