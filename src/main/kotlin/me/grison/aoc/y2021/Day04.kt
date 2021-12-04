package me.grison.aoc.y2021

import me.grison.aoc.*

class Day04 : Day(4, 2021) {
    override fun title() = "Giant Squid"

    private val suite = inputList.first().allInts()
    private val boards = inputGroups.tail().map { it.lines().map { it.allInts() } }

    override fun partOne(): Int {
        (1..suite.size).forEach { round ->
            suite.first(round).let { numbers ->
                boards.firstOrNull { it.wins(numbers) }?.let { board ->
                    return board.sumOfAllUnmarkedNumbers(numbers) * numbers.last()
                }
            }
        }
        return 0
    }

    override fun partTwo(): Int {
        val winners = mutableListOf<Int>()
        (1..suite.size).forEach { round ->
            suite.first(round).let { numbers ->
                boards.withIndex().forEach { (boardIndex, board) ->
                    if (boardIndex !in winners && board.wins(numbers)) {
                        winners.add(boardIndex)

                        if (winners.size == boards.size)
                            return board.sumOfAllUnmarkedNumbers(numbers) * numbers.last()
                    }
                }
            }
        }
        return 0
    }
}


typealias Board = List<List<Int>>

fun Board.wins(numbers: List<Int>): Boolean {
    return indices.any { row ->
        this[row].all { numbers.contains(it) } || // horizontal
                indices.all { numbers.contains(this[it][row]) } // vertical
    }
}

fun Board.sumOfAllUnmarkedNumbers(numbers: List<Int>) =
    this.sumOf { it.filter { !numbers.contains(it) }.sum() }
