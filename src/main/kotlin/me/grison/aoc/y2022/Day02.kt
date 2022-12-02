package me.grison.aoc.y2022

import me.grison.aoc.Day
import me.grison.aoc.butLast

class Day02 : Day(2, 2022) {
    override fun title() = "Rock Paper Scissors"

    override fun partOne() = solve()

    override fun partTwo() = solve { round -> round.butLast() + roundEnding[round] }

    private fun solve(inputMixer: (String) -> String = { it }) =
        inputList
            .map { inputMixer(it) }
            .sumOf { outcomes.getValue(it) + shapeScores.getValue(it.last()) }

    // X: 🗿; Y: 📜; Z: ✂️
    private val shapeScores = mapOf('X' to 1, 'Y' to 2, 'Z' to 3).withDefault { 0 }

    private val outcomes =
        mapOf(
            "A X" to 3 /* 🗿 */, "B Y" to 3 /* 📜 */, "C Z" to 3 /* ✂️️ */,         // draw
            "A Y" to 6 /* 🗿 📜 */, "B Z" to 6 /* 📜 ✂️ */, "C X" to 6 /* ✂️ 🗿 */ // win
        ).withDefault { 0 }

    private val roundEnding = mapOf(
        "A X" to 'Z' /* 🗿⤑✂️ */, "B X" to 'X' /* 📜⤑✂️ */, "C X" to 'Y' /* ✂️⤑📜 */, // lose
        "A Y" to 'X' /* 🗿⤑🗿 */, "B Y" to 'Y' /* 📜⤑📜 */, "C Y" to 'Z' /* ✂️⤑✂️ */,  // draw
        "A Z" to 'Y' /* 🗿⤑📜 */, "B Z" to 'Z' /* 📜⤑✂️ */, "C Z" to 'X' /* ✂️⤑🗿 */   // win/
    )
}