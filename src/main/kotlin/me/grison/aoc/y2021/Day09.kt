package me.grison.aoc.y2021

import me.grison.aoc.*
import kotlin.Int.Companion.MAX_VALUE

class Day09 : Day(9, 2021) {
    override fun title() = ""

    override fun partOne(): Int {
        val grid = loadGrid()
        var sum = 0
        for (y in 0..height) {
            for (x in 0..width) {
                val pos = p(x, y)
                val num = grid.getValue(pos)
                if (pos.directions().all { num < grid.getValue(it) }) {
                    sum += num + 1
                }
            }
        }

        return sum
    }

    override fun partTwo(): Int {
        val grid = loadGrid()
        val basins = mutableListOf<Position>()
        for (y in 0..height) {
            for (x in 0..width) {
                val pos = p(x, y)
                if (grid.getValue(pos) != 9) {
                    basins.add(findBasin(pos, grid))
                }
            }
        }

        return basins.fold(hashBag<Position>()) { hash, b -> hash.increase(b) }
            .values.sortedDescending().take(3).product()
    }

    private fun loadGrid() : Map<Position, Int> {
        inputList.let {
            width = it[0].length
            height = it.size
            return it.intGrid(MAX_VALUE)
        }
    }

    private fun findBasin(pos: Position, grid: Map<Position, Int>): Position {
        val bottom = pos.directions().lastOrNull { grid.getValue(it) < grid.getValue(pos) }
        return if (bottom == null) pos else findBasin(bottom, grid)
    }

    private var width: Int = 0
    private var height: Int = 0
}