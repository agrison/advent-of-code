package me.grison.aoc.y2020

import me.grison.aoc.*

class Day25 : Day(25, 2020) {
    override fun title() = "Combo Breaker"

    override fun partOne(): Long {
        var (card, door, current) = inputList.longs() + 1
        val loopSize = intSeq().first {
            current = transform(current, 7)
            current == door
        }

        return (0 until loopSize).fold(1) { acc, _ ->  transform(acc, card) }
    }

    private fun transform(input: Long, subject: Long) =
        (input * subject) % 20201227

    override fun partTwo() = SANTA
}