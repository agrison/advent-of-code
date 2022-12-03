package me.grison.aoc.y2022

import me.grison.aoc.Day
import me.grison.aoc.inTwo
import me.grison.aoc.intersectAll
import me.grison.aoc.mapSet

class Day03 : Day(3, 2022) {
    override fun title() = "Rucksack Reorganization"

    override fun partOne() = solve { rucksack -> rucksack.map { it.inTwo().mapSet() } }

    override fun partTwo() = solve { it.mapSet().chunked(3) }

    private fun solve(inputMixer: (List<String>) -> List<List<Set<Char>>>) =
        inputMixer(inputList).sumOf { itemPriority(it) }

    private fun itemPriority(compartments: List<Set<Char>>): Int =
        compartments.intersectAll().first().let { item ->
            if (item.isLowerCase()) {
                1 + (item.code - 'a'.code)
            } else {
                27 + (item.code - 'A'.code)
            }
        }
}