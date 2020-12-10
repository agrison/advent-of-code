package days

import arrow.syntax.collections.tail

class Day10 : Day(10) {
    override fun title() = "Adapter Array"

    val adapters = (inputList.map { it.toInt() } + 0).sorted()

    override fun partOne() = adapters.zip(adapters.tail())
            .fold(Pair(0, 1)) { a, b ->
                Pair(a.first + if (b.differenceIs(1)) 1 else 0, a.second + if (b.differenceIs(3)) 1 else 0)
            }.multiply()

    override fun partTwo(): Long {
        val ways = mutableMapOf(0 to 1L)
        adapters.tail().sorted().forEach { j ->
            ways[j] = listOf(1, 2, 3).filter { it <= j }
                    .sumOf { ways.getOrDefault(j - it, 0L) }
        }
        return ways.getOrDefault(ways.keys.max() ?: 0L, 0L)
    }
}
