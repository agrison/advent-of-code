package me.grison.aoc.y2021

import me.grison.aoc.*
import kotlin.Int.Companion.MAX_VALUE

class Day09 : Day(9, 2021) {
    override fun title() = "Smoke Basin"

    private val grid = loadGrid()

    override fun partOne(): Int {
        return gridPositions(height, width)
            .map { pos -> p(pos, grid.getValue(pos)) }
            .filter { (pos, value) -> pos.directions().all { value < grid.getValue(it) } }
            .sumBy { (_, value) -> value + 1 }
    }

    override fun partTwo(): Int {
        return gridPositions(height, width)
            .map { pos -> p(pos, grid.getValue(pos)) }
            .filter { (_, value) -> value < 9 }
            .map { (pos, _) -> findBasin(pos, grid) }
            .fold(hashBag<Position>()) { hash, b -> hash.increase(b) }
            .values.sortedDescending().take(3).product()
    }

    private fun loadGrid(): Map<Position, Int> =
        inputList.let {
            width = it[0].length
            height = it.size
            return it.intGrid(MAX_VALUE)
        }

    private fun findBasin(pos: Position, grid: Map<Position, Int>): Position =
        pos.directions().lastOrNull { grid.getValue(it) < grid.getValue(pos) }
            ?.let { findBasin(it, grid) } ?: pos

    private var width: Int = 0
    private var height: Int = 0
}