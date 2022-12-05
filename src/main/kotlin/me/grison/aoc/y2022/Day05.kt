package me.grison.aoc.y2022

import me.grison.aoc.*

class Day05 : Day(5, 2022) {
    override fun title() = "Supply Stacks"

    override fun partOne() = solve { it.reversed() }

    override fun partTwo() = solve { it }

    fun solve(mixer: (Iterable<String>) -> Iterable<String>): String {
        val (initial, moves) = inputGroups
        val stacks = initial.lines().butLast()
            .map { it.replace("    ", "[.]").remove("[^\\w.]+".regex()).list() }.transpose()
            .map { it.rejectAll(".") }.mutable()

        moves.lines().forEach {
            val (amount, from, to) = it.allInts()
            stacks[to - 1] = mixer(stacks[from - 1].take(amount)) + stacks[to - 1]
            stacks[from - 1] = stacks[from - 1].drop(amount)
        }

        return stacks.map { it[0] }.join()
    }
}
