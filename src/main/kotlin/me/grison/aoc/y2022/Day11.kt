package me.grison.aoc.y2022

import me.grison.aoc.*
import java.math.BigInteger.ONE

class Monkey(
    var id: Int,
    var items: MutableList<Long>,
    var operation: (Long) -> Long,
    var divisible: Long,
    var destinations: Pair<Int, Int>
)

class Day11 : Day(11, 2022) {
    override fun title() = "Monkey in the Middle"

    override fun partOne() = solve(20, parseMonkeys()) { level -> level / 3L }

    override fun partTwo() = parseMonkeys().let { monkeys ->
        val lcm = lcm(monkeys)
        solve(10_000, monkeys) { level -> level % lcm }
    }

    private fun parseMonkeys(): List<Monkey> {
        val monkeys = mutableListOf<Monkey>()

        inputGroups.forEach { monkey ->
            val (id, items, operation, divisible, ok, nok) = monkey.lines()
            val after = operation.after(": ").words()
            val (op, operand) = p(after[3], after[4])
            monkeys.add(
                Monkey(
                    id.firstInt(),
                    items.allLongs().toMutableList(),
                    { i ->
                        when (operand.isDigits()) {
                            false -> if (op == "+") i + i else i * i
                            true -> if (op == "+") i + operand.toLong() else i * operand.toLong()
                        }
                    },
                    divisible.allLongs().first(),
                    p(ok.firstInt(), nok.firstInt())
                )
            )
        }

        return monkeys
    }

    private fun lcm(monkeys: List<Monkey>) =
        monkeys.map { it.divisible.toBigInteger() }.fold(ONE) { acc, i -> (acc * i) / acc.gcd(i) }.toLong()

    private fun solve(rounds: Int, monkeys: List<Monkey>, newLevel: (Long) -> Long): Long {
        val monkeyBusiness = mutableList(monkeys.size, 0L)
        repeat(rounds) {
            monkeys.forEach { monkey ->
                monkey.items.forEach { item ->
                    monkeyBusiness[monkey.id] += 1L
                    val level = newLevel(monkey.operation(item))
                    val destination =
                        if (level % monkey.divisible == 0L) monkey.destinations.first
                        else monkey.destinations.second
                    monkeys[destination].items.add(level)
                }
                monkey.items.clear()
            }
        }

        return monkeyBusiness.sorted().takeLast(2).product()
    }
}
