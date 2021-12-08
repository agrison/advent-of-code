package me.grison.aoc.y2021

import me.grison.aoc.*

class Day08 : Day(8, 2021) {
    override fun title() = "Seven Segment Search"

    override fun partOne() =
        inputList.map { it.after(" | ").words() }
            .fold(0) { acc, it ->
                acc + it.map { it.length }.count { it in listOf(2, 3, 4, 7) }
            }

    override fun partTwo(): Int {
        return inputList.map { it.normalSplit(" | ") }
            .fold(0) { accu, (left, right) ->
                val permutation = findPermutation(left.words())
                accu + right.words()
                    .map { segment -> findDigitCode(segment, permutation) }
                    .fold("") { number, code -> number + "${digitCodes.indexOf(code)}" }
                    .int()
            }
    }

    private val digitCodes =
        listOf("abcefg", "cf", "acdeg", "acdfg", "bcdf", "abdfg", "abdefg", "acf", "abcdefg", "abcdfg").map { it.set() }
    private val letters = "abcdefg".list()
    private val permutations = letters.permutations()

    private fun findDigitCode(segment: String, permutation: List<String>) =
        segment.stringList()
            .map { permutation.indexOf(it) }
            .filter { it.pos() }
            .map { letters[it] }
            .toSet()

    private fun findPermutation(displays: List<String>) =
        permutations.first { permutation ->
            displays.all { segment -> digitCodes.contains(findDigitCode(segment, permutation)) }
        }
}