package me.grison.aoc.y2020

import me.grison.aoc.*

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

    private fun decks(): Pair<Deck, Deck> = inputGroups.map { it.lines().tail().ints().deque() }.pair()

    private fun ArrayDeque<Int>.score() = zip((size downTo 1)).sumBy { it.product() }

    private fun recursiveCombat(player1: Deck, player2: Deck): Int {
        val previousRounds = hashSetOf<Any>()
        while (player1.isNotEmpty() && player2.isNotEmpty()) {
            val round = p(player1, player2)
            if (!previousRounds.add(round))
                return 1

            val (a, b) = p(player1.shift(), player2.shift())
            val winner = if (a <= player1.size && b <= player2.size) {
                recursiveCombat(player1[0, a].deque(), player2[0, b].deque())
            } else {
                if (a > b) 1 else 2
            }

            if (winner == 1) player1.addLast(a, b)
            else player2.addLast(b, a)
        }

        return if (player1.isEmpty()) 2 else 1
    }
}

typealias Deck = ArrayDeque<Int>