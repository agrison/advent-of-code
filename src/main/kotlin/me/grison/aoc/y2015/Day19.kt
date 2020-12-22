package me.grison.aoc.y2015

import me.grison.aoc.Day

class Day19 : Day(19, 2015) {
    override fun title() = "Medicine for Rudolph"

    override fun partOne(): Int {
        val base = inputString.split("\n").last()
        val molecules = mutableSetOf<String>()
        inputList.dropLast(2).forEach { line ->
            val (match, _, replace) = line.split(" ")
            molecules.addAll(allReplacements(base, match, replace))
        }
        return molecules.size
    }

    override fun partTwo(): Int {
        val replacements = inputList.dropLast(2)
                .map { p(it.split(" ").last(), it.split(" ").first()) }
                .sortedBy { it.first.length }
                .reversed()

        var (total, base) = p(0, inputString.split("\n").last())
        while (base != "e") {
            for ((start, end) in replacements) {
                if (start in base) {
                    base = base.replaceFirst(start, end)
                    total += 1
                    break
                }
            }
        }

        return total
    }

    private fun allReplacements(base: String, match: String, replace: String): Sequence<String> {
        val indices = match.toRegex().findAll(base).map { it.range.first }
        return indices.map { base.substring(0, it) + replace + base.substring(it + match.length) }
    }
}