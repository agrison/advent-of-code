package me.grison.aoc.y2021

import me.grison.aoc.*

class Day08 : Day(8, 2021) {
    override fun title() = "Seven Segment Search"

    private val knownSegments = mapOf(2 to 1, 3 to 7, 4 to 4, 7 to 8)

    override fun partOne() =
        inputList.map { it.after(" | ").words() }
            .fold(0) { acc, it ->
                acc + it.map { it.length }.count { it in knownSegments.keys }
            }

    // faster solution
    override fun partTwo(): Int {
        return inputList.map { it.normalSplit(" | ").map { it.words() } }
            .fold(0) { accu, (left, right) ->
                val segments = left.associate { it.length to it.set() }
                accu + right.fold("") { code, segment ->
                    segment.set().let { set ->
                        code + digit(segment.length, set.intersect(segments[4]!!).size, set.intersect(segments[2]!!).size)
                    }
                }.int()
            }
    }

    private fun digit(length: Int, second: Int, third: Int) =
        if (length in knownSegments)
            knownSegments[length]!!
        else if (length == 5)
            if (second == 2) 2 else if (third == 1) 5 else 3
        else
            if (second == 4) 9 else if (third == 1) 6 else 0

    /* initial solution, but 50 times slower
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
        */
}