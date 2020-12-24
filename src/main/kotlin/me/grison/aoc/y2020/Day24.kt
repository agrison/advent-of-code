package me.grison.aoc.y2020

import me.grison.aoc.Day
import me.grison.aoc.plus
import me.grison.aoc.regex

class Day24 : Day(24, 2020) {
    override fun title() = "Lobby Layout"

    override fun partOne() = loadBlacks().values.count { it }

    override fun partTwo() = loadBlacks().let { blacks ->
        var grid = blacks.toList().filter { it.second }.map { it.first }.toSet()
        repeat(100) { grid = next(grid) }
        grid.size
    }

    private fun loadBlacks() = mutableMapOf<Pair<Int, Int>, Boolean>().let {
        inputList.forEach { line ->
            var p = zero2d
            "e|se|sw|w|nw|ne".regex().findAll(line).forEach { dir ->
                p += HexagonCoordinates.valueOf(dir.value.toUpperCase()).pos
            }
            it.merge(p, true) { prev, new -> prev xor new }
        }
        it
    }

    private fun next(floor: Set<Pair<Int, Int>>) = mutableMapOf<Pair<Int, Int>, Int>().let { count ->
        floor.forEach { f ->
            HexagonCoordinates.values().map { it.pos }.forEach { p ->
                count.merge(f + p, 1) { prev, new -> prev + new }
            }
        }

        count.filter { e -> (e.key in floor && e.value == 1) || e.value == 2 }.map { it.key }.toSet()
    }
}