package me.grison.aoc.y2020

import me.grison.aoc.Day

class Day02 : Day(2, 2020) {
    override fun title() = "Password Philosophy"

    override fun partOne() = countValid { l, r, letter, pwd ->
        (l..r).contains(pwd.occurrences(letter))
    }

    override fun partTwo() = countValid { l, r, letter, pwd ->
        pwd.at(l - 1).eq(letter) != pwd.at(r - 1).eq(letter)
    }

    private fun countValid(policy: (a: Int, b: Int, letter: Char, pwd: String) -> Boolean): Int {
        val regex = Regex("(\\d+)-(\\d+) ([a-z]): ([a-z]+)")
        return inputList.count {
            val (l, r, letter, pwd) = regex.find(it)!!.destructured
            policy(l.toInt(), r.toInt(), letter[0], pwd)
        }
    }
}