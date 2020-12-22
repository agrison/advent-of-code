package me.grison.aoc.y2015

import arrow.core.extensions.list.foldable.exists
import me.grison.aoc.Day

class Day05 : Day(5, 2015) {
    override fun title() = "Doesn't He Have Intern-Elves For This?"

    override fun partOne() = inputList.count {
        threeVowels(it) && containsDoubleLetter(it)
                && !containsRestricted(it)
    }

    override fun partTwo() = inputList.count { containsPair(it) && containsRepeatLetter(it) }

    // part 1
    private fun threeVowels(s: String) = s.count { "aeiou".contains(it) } >= 3
    private fun containsDoubleLetter(s: String) =
            alphabet.map { c -> c + c }.exists { s.indexOf(it) != -1 }

    private fun containsRestricted(s: String) =
            listOf("ab", "cd", "pq", "xy").exists { s.indexOf(it) != -1 }

    // part 2
    private fun containsPair(s: String) = s.contains("([a-z][a-z]).*\\1".regex())
    private fun containsRepeatLetter(s: String) = s.contains("([a-z])[a-z]\\1".regex())
}