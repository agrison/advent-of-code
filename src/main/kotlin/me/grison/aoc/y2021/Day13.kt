package me.grison.aoc.y2021

import me.grison.aoc.*

class Day13 : Day(13, 2021) {
    override fun title() = "Transparent Origami"

    override fun partOne() = fold().first().size

    override fun partTwo() = fold().last().pointsDisplay().ocrExtract()

    private fun fold(): List<Set<Pair<Long, Long>>> {
        var coordinates = inputGroups[0].lines().map { it.allLongs().pair() }.toSet()
        val foldInstructions = inputGroups[1].lines().map { it.longCommand() }

        val steps = mutableListOf<Set<Pair<Long, Long>>>()
        foldInstructions.forEach { (axis, num) ->
            coordinates = coordinates.map { if (axis == "y") it.pivotSecond(num) else it.pivotFirst(num) }.toSet()
                    steps.add(coordinates)
        }

        return steps
    }

    fun Pair<Long, Long>.string() = "'$first,$second'"
}
