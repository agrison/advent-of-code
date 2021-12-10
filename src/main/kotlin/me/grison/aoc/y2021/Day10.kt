package me.grison.aoc.y2021

import me.grison.aoc.Day
import me.grison.aoc.middle
import me.grison.aoc.p
import java.util.*

class Day10 : Day(10, 2021) {
    override fun title() = "Syntax Scoring"

    private val matching = mapOf(')' to '(', ']' to '[', '>' to '<', '}' to '{')

    override fun partOne() = solve(mapOf(')' to 3L, ']' to 57L, '}' to 1197L, '>' to 25137L)).first

    override fun partTwo() = solve(mapOf('(' to 1L, '[' to 2L, '{' to 3L, '<' to 4L)).second

    private fun solve(scores: Map<Char, Long>): Pair<Long, Long> {
        var (firstScore, allScores) = p(0L, mutableListOf<Long>())
        inputList.forEach { line ->
            var (stack, corrupted) = p(Stack<Char>(), false)
            for (c in line) {
                if (c in "(<[{") stack.push(c)
                else if (stack.pop() != matching[c]) {
                    corrupted = true
                    firstScore += scores[c] ?: 0L
                    break
                }
            }

            if (!corrupted) {
                stack.reversed().fold(0L) { acc, c -> 5L * acc + (scores[c] ?: 0L) }
                    .let { score -> allScores.add(score) }
            }
        }

        return p(firstScore, allScores.sorted().middle())
    }
}