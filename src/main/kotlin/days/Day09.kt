package days

import arrow.core.extensions.set.foldable.exists

class Day09 : Day(9) {
    override fun title() = "Encoding Error"

    private val series = inputList.map { it.toLong() }

    override fun partOne() =
            series[(25..series.size).first { i ->
                !sumOfTwoInPreamble(series[i], series.slice(i - 25..i).toSet())
            }]

    override fun partTwo(): Long {
        val invalidNumber = partOne()
        val scan = series.scan(0L) { a, b -> a + b }
        scan.forEachIndexed { first, value ->
            (first until scan.size).find { last -> scan[last] - value == invalidNumber }
                    ?.let { last ->
                        return (series.slice(first..last).min() ?: 0L) + (series.slice(first..last).max() ?: 0L)
                    }
        }
        return -1L
    }

    private fun sumOfTwoInPreamble(n: Long, preamble: Set<Long>): Boolean {
        return preamble.exists { p -> preamble.contains(n - p) && n - p != p }
    }
}
