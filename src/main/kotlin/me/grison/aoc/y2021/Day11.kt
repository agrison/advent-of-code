package me.grison.aoc.y2021

import me.grison.aoc.*
import kotlin.text.lines

class Day11 : Day(11, 2021) {
    override fun title() = "Dumbo Octopus"

    private var flashCount = 0
    private var grid = inputString.lines().intGrid(Int.MIN_VALUE)

    override fun partOne(): Int {
        for (step in 1..100)
            evolve()
        return flashCount
    }

    override fun partTwo(): Int {
        var step = 1
        while (evolve())
            step++
        return 100 + step
    }

    private fun evolve(): Boolean {
        grid.allPositions().let { positions ->
            positions.forEach { grid.increase(it, 1) }
            positions.forEach { if (grid[it]!! == 10) flash(grid, it) }
            positions.forEach { if (grid[it]!! == -1) grid[it] = 0 }
        }
        return !grid.values.all { it == 0 }
    }

    private fun flash(grid: Grid<Int>, pos: Position) {
        flashCount++
        grid[pos] = -1
        pos.neighbors().forEach {
            if (it in grid.keys && grid[it]!! != -1) {
                grid[it] = grid[it]!! + 1
                if (grid[it]!! >= 10)
                    flash(grid, it)
            }
        }
    }
}