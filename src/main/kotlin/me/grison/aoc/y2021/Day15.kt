package me.grison.aoc.y2021

import me.grison.aoc.*
import me.grison.aoc.DistanceType.LOWEST_VALUE_NEIGHBOR

class Day15 : Day(15, 2021) {
    override fun title() = "Chiton"

    override fun partOne(): Int {
        val grid = inputList.intGrid(0)
        return grid.shortestPath(p(0, 0), p(99, 99), LOWEST_VALUE_NEIGHBOR)
            .second.reject { it == p(0, 0) }.sumOf { grid[it]!! }
    }

    override fun partTwo(): Int {
        val grid = inputList.intGrid(0)
        expandGrid(grid, 100, 5)

        return grid.shortestPath(p(0, 0), p(499, 499), LOWEST_VALUE_NEIGHBOR)
            .second.reject { it == p(0, 0) }.sumOf { grid[it]!! }
    }

    private fun expandGrid(grid: Grid<Int>, dimension: Int, amount: Int) {
        for (y in 0 until dimension * amount) {
            for (x in 0 until dimension * amount) {
                var value = grid[p(y % dimension, x % dimension)]!!
                for (i in 0 until y / dimension + x / dimension) {
                    value += if (value == 9) -8 else 1
                }
                grid[p(y, x)] = value
            }
        }
    }
}