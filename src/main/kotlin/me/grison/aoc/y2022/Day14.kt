package me.grison.aoc.y2022

import arrow.syntax.function.memoize
import me.grison.aoc.*

class Day14 : Day(14, 2022) {
    override fun title() = "Regolith Reservoir"

    override fun partOne() = solve().first

    override fun partTwo() = solve().second

    private val solve = {
        val filled = parse()

        val floor = filled.maxY() + 2
        fun canMove(sand: Position) =
            sand.y() != floor && sand !in filled

        var (part1, part2, tick) = listOf(-1, 0, 0)
        while (true) {
            var sand = p(500, 0)
            while (true) {
                if (sand.y() + 1 >= floor && part1 < 0)
                    part1 = tick

                sand += when {
                    canMove(sand + p(0, 1)) -> p(0, 1)
                    canMove(sand + p(-1, 1)) -> p(-1, 1)
                    canMove(sand + p(1, 1)) -> p(1, 1)
                    else -> break
                }
            }

            if (sand == p(500, 0)) {
                part2 = tick + 1
                break
            }
            filled.add(sand)
            tick += 1
        }

        p(part1, part2)
    }.memoize()

    private fun parse() = mutableSetOf<Position>().let { filled ->
        inputList.forEach { line ->
            var last = p(-1, -1)
            line.normalSplit(" -> ")
                .map { it.allInts().pair() }
                .forEach { pos ->
                    if (last == p(-1, -1)) {
                        filled.add(pos)
                    } else {
                        val diff = pos - last
                        filled.addAll((0..diff.abs().max()).map { p(it, it) * diff.sign() + last })
                    }
                    last = pos
                }
        }
        filled
    }
}