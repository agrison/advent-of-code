package days

import arrow.core.extensions.sequence.foldable.get

class Day15 : Day(15) {
    override fun title() = "Rambunctious Recitation"

    override fun partOne() = memoryGame().get(2020 - 1).orNull()

    override fun partTwo() = memoryGame().get(30000000 - 1).orNull()

    private fun memoryGame(): Sequence<Int> {
        return sequence {
            val alreadySpoken = mutableMapOf<Int, Int>()
            var (turn, lastSpoken) = p(1, 0)
            cycle(13, 16, 0, 12, 15, 1).forEachIndexed { i, n ->
                // first consume the input, then use the last spoken ones
                val num = if (i < 6) n else lastSpoken
                yield(num) // <-
                lastSpoken = if (num in alreadySpoken) turn - alreadySpoken[num]!! else 0
                alreadySpoken[num] = turn
                turn++
            }
        }
    }
}
