package me.grison.aoc.y2015

import me.grison.aoc.Day
import me.grison.aoc.matches
import me.grison.aoc.normalSplit
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.set

class Day07 : Day(7, 2015) {
    override fun title() = "Some Assembly Required"

    override fun partOne() =
        calculate(loadCommands(), mutableMapOf(), "a")

    override fun partTwo() =
        calculate(loadCommands(), mutableMapOf("b" to partOne()), "a")

    private fun loadCommands() = mutableMapOf<String, List<String>>().let {
        inputList.forEach { command ->
            val (ops, res) = command.normalSplit(" -> ")
            it[res.trim()] = ops.trim().normalSplit(" ")
        }
        it
    }

    private fun calculate(
        calc: MutableMap<String, List<String>>,
        results: MutableMap<String, Int>,
        name: String
    ): Int {
        if (name.matches("\\d+"))
            return name.toInt()

        val compute: (String) -> Int = (::calculate).partial(calc, results)

        if (name !in results) {
            val ops = calc[name]!!
            val res = if (ops.size == 1) {
                compute(ops[0])
            } else {
                when (ops[ops.size - 2]) {
                    "AND" -> compute(ops[0]) and compute(ops[2])
                    "OR" -> compute(ops[0]) or compute(ops[2])
                    "NOT" -> compute(ops[1]).inv() and 0xffff
                    "RSHIFT" -> compute(ops[0]) shr compute(ops[2])
                    "LSHIFT" -> compute(ops[0]) shl compute(ops[2])
                    else -> 0
                }
            }
            results[name] = res
        }
        return results[name]!!
    }
}

fun <A, B, C, D> Function3<A, B, C, D>.partial(a: A, b: B): (C) -> D {
    return { c -> invoke(a, b, c) }
}