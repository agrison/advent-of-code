package me.grison.aoc.y2021

import me.grison.aoc.*
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.component3
import kotlin.collections.component4

class Day05 : Day(5, 2021) {
    override fun title() = "Hydrothermal Venture"

    override fun partOne() = solve { it[0] == it[2] || it[1] == it[3] }

    override fun partTwo() = solve { true }

    fun solve(predicate: (List<Int>) -> Boolean): Int {
        val points = hashBag<Pair<Int, Int>>()
        inputList.map { it.allInts() }
            .filter { predicate(it) }
            .forEach { (x1, y1, x2, y2) ->
                val (dX, dY) = p(p(x1, x2).slope(), p(y1, y2).slope())

                generateSequence(p(x1, y1)) { it + p(dX, dY) }
                    .takeWhile { (x, y) -> x != x2 + dX || y != y2 + dY  }
                    .forEach { (x, y) -> points.increase(p(x, y)) }
            }

        return points.count { it.value > 1 }
    }
}