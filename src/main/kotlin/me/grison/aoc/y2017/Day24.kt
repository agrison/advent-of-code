package me.grison.aoc.y2017

import me.grison.aoc.Day
import me.grison.aoc.allInts
import me.grison.aoc.pair
import me.grison.aoc.sum

class Day24 : Day(24, 2017) {
    override fun title() = "Electromagnetic Moat"

    val elements = inputList.map { it.allInts().pair() }

    // 1656
    override fun partOne() = buildBridge(0, listOf(), elements, compareBy(Bridge::value)).value()

    // 1642
    override fun partTwo() =
        buildBridge(0, listOf(), elements, compareBy(Bridge::size) then compareBy(Bridge::value)).value()

    private fun load() = inputList.map { it.allInts().pair() }
}

typealias Bridge = List<Pair<Int, Int>>

fun Bridge.value() = this.sumBy { it.sum() }
fun buildBridge(x: Int, bridge: Bridge, remaining: Bridge, comparator: Comparator<Bridge>): Bridge {
    return remaining.filter { it.first == x || it.second == x }
        .map { buildBridge(if (it.first == x) it.second else it.first, bridge + it, remaining - it, comparator) }
        .maxWithOrNull(comparator) ?: bridge
}

