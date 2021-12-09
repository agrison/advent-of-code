package me.grison.aoc.y2021

import me.grison.aoc.*
import kotlin.Int.Companion.MAX_VALUE

class Day09 : Day(9, 2021) {
    override fun title() = ""

    private var height = 0
    private var width = 0
    private var grid = mutableMapOf<Position, Int>().withDefault { MAX_VALUE }
    private fun loadGrid() {
        inputList.forEach { line ->
            width = 0
            line.normalSplit("").ints().forEach { n ->
                grid[p(width++, height)] = n
            }
            height++
        }
    }

    override fun partOne(): Int {
        loadGrid()

        var sum = 0
        for (y in 0.until(height)) {
            for (x in 0.until(width)) {
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
        loadGrid()
        val basins = mutableListOf<Position>()
        for (y in 0.until(height)) {
            for (x in 0.until(width)) {
                val pos = p(x, y)
                if (grid.getValue(pos) != 9) {
                    basins.add(findBasin(pos, grid))
                }
            }
        }

        return basins.fold(hashBag<Position>()) { hash, b -> hash.increase(b) }
            .values.sortedDescending().take(3).product()
    }

    private fun findBasin(pos: Position, grid: Map<Position, Int>): Position {
        val bottom = pos.directions()
            .filter { it.within(0, 0, 100, 100) }
            .lastOrNull { grid.getValue(it) < grid.getValue(pos) }
        return if (bottom == null) pos else findBasin(bottom, grid)
    }
}