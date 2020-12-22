package me.grison.aoc.y2015

import arrow.core.extensions.list.foldable.exists
import me.grison.aoc.Day


class Day11 : Day(11, 2015) {
    override fun title() = "Corporate Policy"

    override fun partOne(): String {
        var password = inputString
        while (!isValid(password)) {
            password = nextString(password)
        }
        return password
    }

    override fun partTwo(): String {
        var password = nextString(partOne())
        while (!isValid(password)) {
            password = nextString(password)
        }
        return password
    }

    private fun isValid(s: String) : Boolean {
        if ("i" in s || "o" in s || "l" in s)
            return false
        if (doubles.count { d -> s.contains(d) } < 2)
            return false
        if (!triplets.exists { t -> s.contains(t) })
            return false
        return true
    }

    private fun nextChar(c: Char) = if (c == 'z') 'a' else c + 1
    private fun nextString(s: String) : String {
        val nextChar = nextChar(s.last())
        return if (nextChar == 'a') nextString(s.substring(0, s.length - 1)) + 'a' else s.substring(0, s.length - 1) + nextChar
    }

    val doubles = alphabet.map { c -> c + c }
    val nextLetter = (alphabet.zipWithNext { a, b -> a + b } + listOf("za"))
    val triplets = nextLetter.zip((alphabet.substring(2, alphabet.length)).toList()).map { a -> a.first + a.second }
}