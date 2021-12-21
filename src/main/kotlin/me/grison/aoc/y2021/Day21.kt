package me.grison.aoc.y2021

import me.grison.aoc.*

class Day21 : Day(21, 2021) {
    override fun title() = "Dirac Dice"

    private val initialPositions = inputList.map { it.allLongs().last() }.toMutableList()

    override fun partOne(): Long {
        val positions = initialPositions.copy()
        val scores = mutableListOf(0L, 0L)
        var turn = 0

        var (state, rolls) = p(0L, 0L)
        fun rollDice(): Long {
            rolls += 1
            state += 1 % 101
            return state
        }

        while (true) {
            val rollSum = (1..3).sumOf { rollDice() }
            positions[turn] = (positions[turn] + rollSum - 1) % 10 + 1
            scores[turn] += positions[turn]
            if (scores[turn] >= 1000) return scores[turn.nextTurn()] * rolls
            turn = turn.nextTurn()
        }
    }

    override fun partTwo(): Long {
        val quantumStates = longHashBag<QuantumState>()
        quantumStates[QuantumState.zero(initialPositions)] = 1
        val universes = mutableListOf(0L, 0L)

        while (quantumStates.isNotEmpty()) {
            val state = quantumStates.keys.minBy { it.totalScore }!!
            val count = quantumStates.remove(state)!!
            val (positions, scores, turn) = state
            for (roll in combinations3(1, 3).map { it.sum() }) {
                val newPositions = positions.update(turn, (positions[turn] + roll - 1) % 10 + 1)
                val newScores = scores.update(turn, scores[turn] + newPositions[turn])
                if (newScores[turn] >= 21)
                    universes[turn] += count
                else
                    quantumStates.increase(QuantumState(newPositions, newScores, turn.nextTurn()), count)
            }
        }
        return universes.max()!!
    }

    data class QuantumState(val positions: List<Long>, val scores: List<Long>, val turn: Int) {
        val totalScore: Long get() = scores.sum()

        companion object {
            fun zero(positions: List<Long>) = QuantumState(positions, listOf(0, 0), 0)
        }
    }

    private fun Int.nextTurn() = (this + 1) % 2
}
