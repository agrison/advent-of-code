package me.grison.aoc.y2020

import me.grison.aoc.*

// see previous commit, I was using a Map, but it was taking 4.5 sec for part 2
// using an IntArray makes it < 1s on my machine.
class Day15 : Day(15, 2020) {
    override fun title() = "Rambunctious Recitation"

    override fun partOne() = memoryGame(2020)

    override fun partTwo() = memoryGame(30000000)

    private fun memoryGame(idx: Int): Any {
        val alreadySpoken = IntArray(idx + 1)
        var (last, turn, lastSpoken) = Triple(0, 1, 0)
        cycle(13, 16, 0, 12, 15, 1).forEachIndexed { i, n ->
            // first consume the input, then use the last spoken ones
            val num = if (i < 6) n else lastSpoken
            last = num // <-
            lastSpoken = if (alreadySpoken[num] != 0) turn - alreadySpoken[num] else 0
            alreadySpoken[num] = turn++
            if (turn == idx+1)
                return last
        }
        return last
    }
}