package me.grison.aoc.y2020

import me.grison.aoc.Day
import me.grison.aoc.normalSplit
import kotlin.collections.set

class Day23 : Day(23, 2020) {
    override fun title() = "Crab Cups"

    override fun partOne(): Any {
        val cups = loadCups()

        var cup: Cup = cups[0]
        for (i in 1..100) {
            val move = cup.next()
            cup.next = cup.forward(4)

            val destination = cup.destination(move, 1, 9)

            var it = cup.next()
            while (it.value != destination)
                it = it.next()

            move.forward(2).next = it.next
            it.next = move
            cup = cup.next()
        }

        return cup.moveUntil(1).valuesUntil(1)
    }

    override fun partTwo(): Any {
        val cups = loadCups()
        val max = 1_000_000

        // add cups from 10 to 1,000,000
        var cup: Cup = cups[8]
        for (i in 10..max) {
            cup.next = Cup(i, null)
            cup = cup.next!!
        }
        cup.next = cups[0]

        // also save them in a Map
        cup = cups[0]
        val cache = mutableMapOf(cup.value to cup)
        var it = cup.next()
        while (it != cup) {
            cache[it.value] = it
            it = it.next()
        }

        // make the moves
        for (i in 1..10 * max) {
            val move = cup.next()
            cup.next = cup.forward(4)

            val destination = cache[cup.destination(move, 1, max)]!!
            move.forward(2).next = destination.next()
            destination.next = move
            cup = cup.next()
        }

        val one = cache[1]!!
        return one.nextValue().toLong() * one.forward(2).value.toLong()
    }

    private fun loadCups(): List<Cup> {
        val cups = inputString.normalSplit("").map { Cup(it.toInt()) }
        return cups.mapIndexed { i, c -> c.next = cups[(i + 1) % cups.size]; c }
    }
}

data class Cup(val value: Int, var next: Cup? = null) {
    fun next() = next!!

    fun nextValue() = next().value

    fun forward(n: Int): Cup {
        var cup = copy()
        repeat(n) { cup = cup.next() }
        return cup
    }

    private fun picked() = listOf(value, nextValue(), forward(2).value)

    fun destination(move: Cup, min: Int, max: Int): Int {
        var destination = if (value == min) max else value - 1
        while (destination in move.picked()) {
            if (--destination < min)
                destination = max
        }
        return destination
    }

    fun moveUntil(v: Int): Cup {
        var cup = copy()
        while (cup.value != v)
            cup = cup.next()
        return cup
    }

    fun valuesUntil(v: Int): String {
        var (s, cup) = Pair("", copy())
        while (cup.nextValue() != v) {
            s += cup.nextValue()
            cup = cup.next()
        }
        return s
    }
}