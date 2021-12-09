package me.grison.aoc.y2021

import me.grison.aoc.*
import kotlin.Int.Companion.MAX_VALUE

class Day09 : Day(9, 2021) {
    override fun title() = "Smoke Basin"

    private var dimensions = p(0, 0)
    private val grid = loadGrid()

    override fun partOne() =
        gridPositions(dimensions)
            .map { pos -> p(pos, grid.getValue(pos)) }
            .filter { (pos, value) -> pos.directions().all { value < grid.getValue(it) } }
            .sumBy { (_, value) -> value + 1 }

    override fun partTwo() =
        gridPositions(dimensions)
            .map { pos -> p(pos, grid.getValue(pos)) }
            .filter { (_, value) -> value < 9 }
            .map { (pos, _) -> basin(pos) }
            .fold(hashBag<Position>()) { hash, b -> hash.increase(b) }
            .values.sortedDescending().take(3).product()

    private fun loadGrid(): Map<Position, Int> =
        inputList.let {
            dimensions = p(it[0].length, it.size)
            it.intGrid(MAX_VALUE)
        }

    private val basin = { pos: Position -> findBasin(pos) }.memoize()
    private fun findBasin(pos: Position): Position =
        pos.directions().firstOrNull { grid.getValue(it) < grid.getValue(pos) }
            ?.let { basin(it) } ?: pos
}


