package me.grison.aoc.y2017

import me.grison.aoc.*

class Day04 : Day(4, 2017) {
    override fun title() = "High-Entropy Passphrases"

    override fun partOne() = inputList.map { it.normalSplit(" ") }
        .count { it.size == it.toSet().size }

    override fun partTwo() = inputList.map { it.normalSplit(" ").map { s -> s.toCharArray().sorted().join() } }
        .count { it.size == it.toSet().size }
}