package me.grison.aoc.y2020

import me.grison.aoc.Day

class Day22 : Day(22, 2020) {
    override fun title() = "Crab Combat"

    override fun partOne(): Any {
        val (player1, player2) = decks()
        while (player1.isNotEmpty() && player2.isNotEmpty()) {
            val (p1, p2) = p(player1.shift(), player2.shift())
            if (p1 > p2) player1.addLast(p1, p2)
            else player2.addLast(p2, p1)
        }

        return (if (player1.isEmpty()) player2 else player1).score()
    }

    override fun partTwo() = decks().let { (player1, player2) ->
        (if (recursiveCombat(player1, player2) == 1) player1 else player2).score()
    }

    private fun decks(): Pair<ArrayDeque<Int>, ArrayDeque<Int>> =
        p(inputGroups[0].lines().tail().ints().deque(), inputGroups[1].lines().tail().ints().deque())

    private fun ArrayDeque<Int>.score() = zip((size downTo 1)).sumBy { it.first * it.second }

    private fun recursiveCombat(player1: ArrayDeque<Int>, player2: ArrayDeque<Int>): Int {
        val states: MutableSet<Any> = hashSetOf()
        while (player1.size > 0 && player2.size > 0) {
            val state = p(player1, player2)
            if (!states.add(state))
                return 1

            val (a, b) = p(player1.shift(), player2.shift())
            val winner = if (a <= player1.size && b <= player2.size) {
                recursiveCombat(player1.subList(0, a).deque(), player2.subList(0, b).deque())
            } else {
                if (a > b) 1 else 2
            }

            if (winner == 1) player1.addLast(a, b)
            else player2.addLast(b, a)
        }

        return if (player1.isEmpty()) 2 else 1
    }
}