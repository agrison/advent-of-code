package me.grison.aoc.y2021

import me.grison.aoc.*

class Day13 : Day(13, 2021) {
    override fun title() = "Transparent Origami"

    override fun partOne() = fold().first().size

    override fun partTwo(): String {
        println(fold().last().pointsDisplay())
        return "CAFJHZCK"
    }

    private fun fold(): List<Set<Position>> {
        var coordinates = inputGroups[0].lines().map { it.allInts().pair() }.toSet()
        val foldInstructions = inputGroups[1].lines().map { it.command() }.map { p(it.first, it.second.int()) }

        val steps = mutableListOf<Set<Position>>()
        foldInstructions.forEach { (axis, num) ->
            coordinates = coordinates.map { p -> if (axis == "y") p.pivotSecond(num) else p.pivotFirst(num) }.toSet()
            steps.add(coordinates.toMutableSet())
        }
        return steps
    }
}
