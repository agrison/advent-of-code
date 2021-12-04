package me.grison.aoc.y2021

import me.grison.aoc.*

typealias Board = List<List<Int>>

class Day04 : Day(4, 2021) {
    override fun title() = "Giant Squid"

    override fun partOne(): Int {
        val suite = inputList.first().allInts()
        val boards = loadBoards()

        1.until(suite.size).forEach { round ->
            val numbers = suite.first(round)

            boards.firstOrNull { it.wins(numbers) }
                ?.let { board ->
                    return board.sumOfAllUnmarkedNumbers(numbers) * numbers.last()
                }
        }

        return 0
    }

    override fun partTwo(): Int {
        val suite = inputList.first().allInts()
        val boards = loadBoards()

        val winners = mutableListOf<Int>()
        1.until(suite.size).forEach { round ->
            val numbers = suite.first(round)

            boards.withIndex().forEach { (boardIndex, board) ->
                if (boardIndex !in winners && board.wins(numbers)) {
                    winners.add(boardIndex)

                    if (winners.size == 100)
                        return board.sumOfAllUnmarkedNumbers(numbers) * numbers.last()
                }
            }
        }

        return 0
    }

    fun loadBoards(): List<Board> {
        return inputGroups.tail().map { it.lines().map { x -> x.allInts() } }
    }

    fun Board.wins(numbers: List<Int>): Boolean {
        for (i in 0..4) {
            var (horizontal, vertical) = p(true, true)
            for (j in 0..4) {
                horizontal = horizontal && numbers.contains(this[i][j])
                vertical = vertical && numbers.contains(this[j][i])
            }
            if (horizontal || vertical) return true
        }
        return false
    }

    fun Board.sumOfAllUnmarkedNumbers(numbers: List<Int>): Int {
        var sum = 0
        for (i in 0..4) {
            for (j in 0..4) {
                if (!numbers.contains(this[i][j])) {
                    sum += this[i][j]
                }
            }
        }
        return sum
    }
}
