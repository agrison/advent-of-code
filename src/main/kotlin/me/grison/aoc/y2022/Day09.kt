package me.grison.aoc.y2022

import me.grison.aoc.*

class Day09 : Day(9, 2022) {
    override fun title() = "Rope Bridge"

    override fun partOne() = solve(2)

    override fun partTwo() = solve(10)

    private fun solve(ropeSize: Int): Int {
        val seen = mutableSetOf<Position>()
        val movements = mapOf("R" to p(0, 1), "L" to p(0, -1), "D" to p(1, 0), "U" to p(-1, 0))
        val rope = mutableList(ropeSize, p(0, 0))
        fun distance(predicate: Boolean, head: Int, tail: Int) = if (predicate) (head + tail) / 2 else head

        inputList
            .map { it.words() }
            .forEach { (dir, amount) ->
                repeat(amount.toInt()) {
                    rope[0] += movements.at(dir)
                    (1 until ropeSize).forEach { i ->
                        val (head, tail) = p(rope[i - 1], rope[i])
                        val diff = (head - tail).abs()
                        if (diff.max() == 2)
                            rope[i] = tail.first(distance(diff.first == 2, head.first, tail.first))
                                .second(distance(diff.second == 2, head.second, tail.second))
                    }
                    seen.add(rope.last())
                }
            }

        return seen.size
    }
}
