package me.grison.aoc.y2022

import me.grison.aoc.Day
import me.grison.aoc.inTwo

class Day03 : Day(3, 2022) {
    override fun title() = "Rucksack Reorganization"

    override fun partOne() =
        inputList.sumOf { rucksack ->
            val common = commonItem(rucksack.inTwo().map { it.toSet() })
            itemPriority(common)
        }

    override fun partTwo() =
        inputList
            .map { it.toSet() }
            .windowed(size = 3, step = 3)
            .sumOf { itemPriority(commonItem(it)) }

    private fun commonItem(compartments: List<Set<Char>>) =
        compartments.reduce { a, b -> a.intersect(b) }.first()

    private fun itemPriority(item: Char): Int =
        if (item.isLowerCase()) {
            1 + (item.code - 'a'.code)
        } else {
            27 + (item.code - 'A'.code)
        }
}