package me.grison.aoc.y2022

import arrow.syntax.function.memoize
import me.grison.aoc.*
import java.lang.Integer.max

class Day08 : Day(8, 2022) {
    override fun title() = "Treetop Tree House"

    override fun partOne() = solve().first

    override fun partTwo() = solve().second

    private val solve = {
        val grid = inputList.intGrid(-1)

        var (visibleTrees, scenicScore) = p(0, 0)
        grid.allPositions().forEach { pos ->
            val (visible, seen) = p(bools(), ints())

            for (dir in directions()) {
                val trees = grid.allInDirection(pos, dir)
                visible.append(trees.all { grid.at(it) < grid.at(pos) })
                seen.add(trees.countUntil { grid.at(it) >= grid.at(pos) })
            }

            scenicScore = max(scenicScore, seen.product())
            visibleTrees += visible.any { it }.toInt()
        }

        p(visibleTrees, scenicScore)
    }.memoize()
}
