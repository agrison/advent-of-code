package me.grison.aoc.y2017

import me.grison.aoc.*

class Day25 : Day(25, 2017) {
    override fun title() = "The Halting Problem"

    override fun partOne(): Any {
        val steps = inputString.allLongs().first()
        val states = mapOf("A" to 0, "B" to 1, "C" to 2, "D" to 3, "E" to 4, "F" to 5)

        val turingMachine = mutableMapOf<Pair<Int, Int>, Triple<Int, Int, Int>>()
        inputGroups.tail().forEachIndexed { i, group ->
            val instr = group.lines().tail()
            turingMachine[p(i, 0)] = Triple(
                instr[1].lastWord().toInt(),
                if (instr[2].lastWord() == "left") -1 else 1,
                states[instr[3].lastWord()]!!
            )
            turingMachine[p(i, 1)] = Triple(
                instr[5].lastWord().toInt(),
                if (instr[6].lastWord() == "left") -1 else 1,
                states[instr[7].lastWord()]!!
            )
        }

        val tape = mutableMapOf<Int, Int>()
        var (head, state) = p(0, states[inputList[0].lastWord()]!!)

        for (i in (0 until steps)) {
            val value = tape[head] ?: 0
            val (writeValue, move, nextState) = turingMachine[p(state, value)]!!

            tape[head] = writeValue
            head += move
            state = nextState
        }

        return tape.values.sum()
    }

    override fun partTwo() = SANTA

    private fun String.lastWord() = normalSplit(" ").last().butLast()
}