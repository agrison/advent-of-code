package me.grison.aoc.y2015

import me.grison.aoc.*

class Day17 : Day(17, 2015) {
    override fun title() = "No Such Thing as Too Much"

    override fun partOne() = sumTo(inputList.map { it.toInt() }, 150)

    override fun partTwo(): Int {
        val sizes = mutableListOf<Int>()
        sumTo(inputList.map { it.toInt() }, 150, sizes = sizes)
        return sizes.count { it == sizes.minOrNull() }
    }

    private fun sumTo(containers: List<Int>, goal: Int, value: Int = 0, sizes: MutableList<Int> = mutableListOf()): Int {
        if (containers.isEmpty())
            return 0
        val (first, tail) = p(containers.first(), containers.tail())
        return when {
            first > goal -> sumTo(tail, goal, value, sizes)
            first == goal -> 1 + sumTo(tail, goal, value, sizes + (value + 1))
            else -> sumTo(tail, goal, value, sizes) + sumTo(tail, goal - first, value + 1, sizes)
        }
    }
}