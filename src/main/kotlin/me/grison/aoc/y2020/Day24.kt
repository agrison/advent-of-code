package me.grison.aoc.y2020

import me.grison.aoc.*

class Day24 : Day(24, 2020) {
    override fun title() = "Lobby Layout"

    override fun partOne() = loadBlacks().values.count { it }

    override fun partTwo() = loadBlacks().let { blacks ->
        var floor = blacks.toList().filter { it.second }.map { it.first }.toSet()
        repeat(100) { floor = nextDay(floor) }
        floor.size
    }

    private fun loadBlacks() = mutableMapOf<Pair<Int, Int>, Boolean>().let { blacks ->
        inputString.upper().lines().forEach { line ->
            var p = zero2d
            hexagonCoordinates.keys.join("|").regex().findAll(line).forEach { dir ->
                p += hexagonCoordinates[dir.value]!!
            }
            blacks.merge(p, true) { prev, new -> prev xor new }
        }
        blacks
    }

    private fun nextDay(floor: Set<Pair<Int, Int>>) = mutableMapOf<Pair<Int, Int>, Int>().let { count ->
        floor.forEach { f ->
            hexagonCoordinates.values.forEach { p ->
                count.merge(f + p, 1) { prev, new -> prev + new }
            }
        }

        count.filter { e -> (e.key in floor && e.value == 1) || e.value == 2 }.map { it.key }.toSet()
    }
}