package me.grison.aoc.y2017

import me.grison.aoc.Day
import me.grison.aoc.manhattan
import me.grison.aoc.p

class Day03 : Day(3, 2017) {
    override fun title() = "Spiral Memory"

    val coords = listOf(
        p(1, 0), p(1, -1), p(0, -1), p(-1, -1),
        p(-1, 0), p(-1, 1), p(0, 1), p(1, 1)
    )

    val goal = inputString.toInt()

    override fun partOne(): Int {
        var (x, y) = p(0, 0)
        var (dx, dy) = p(0, -1)
        var step = 0

        while (true) {
            step += 1
            if (goal == step)
                return p(x, y).manhattan()
            if (x == y || (x < 0 && x == -y) || (x > 0 && x == 1 - y)) {
                dx = -dy.also { dy = dx }
            }
            x += dx.also { y += dy }
        }
    }

    override fun partTwo() : Int {
        var (x, y) = p(0, 0)
        var (dx, dy) = p(0, -1)
        val grid = mutableMapOf<Pair<Int, Int>, Int>()

        while (true) {
            var total = 0
            for ((ox, oy) in coords)
                if (p(x + ox, y + oy) in grid)
                    total += grid[p(x + ox, y + oy)]!!

            if (total > goal)
                return total

            grid[p(x, y)] = if (x == 0 && y == 0) 1 else total
            if (x == y || (x < 0 && x == -y) || (x > 0 && x == 1 - y)) {
                dx = -dy.also { dy = dx }
            }
            x += dx.also { y += dy }
        }
    }
}