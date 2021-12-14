package me.grison.aoc.y2021

import me.grison.aoc.*

class Day14 : Day(14, 2021) {
    override fun title() = "Extended Polymerization"

    private val insertionRules = hashMapOf(*inputGroups[1].lines().map { it.normalSplit(" -> ").pair() }.toTypedArray())

    override fun partOne(): Int {
        var template = inputGroups[0]
        for (i in 1..10) {
            template = template.stringList().windowed(2).joinToString("") { (left, right) ->
                left + insertionRules[left + right]!!
            } + template.last() // don't forget last char
        }

        return template.frequency().values.range()
    }

    override fun partTwo(): Long {
        val template = inputGroups[0]
        val currentPairs = longHashBag<String>()

        template.stringList().windowed(2).forEach { (a, b) -> currentPairs.increase(a + b) }

        for (i in 1..40) {
            val previousStep = currentPairs.toMutableMap()
            currentPairs.clear()
            previousStep.forEach { (k, v) ->
                val insertion = insertionRules[k]!!
                currentPairs
                    .increase(k.first() + insertion, v)
                    .increase(insertion + k.last(), v)
            }
        }

        return currentPairs.frequency { it.first() }
            .increase(template.last()) // don't forget last char
            .values.range()
    }
}