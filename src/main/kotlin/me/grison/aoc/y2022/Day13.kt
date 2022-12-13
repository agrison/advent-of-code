package me.grison.aoc.y2022

import arrow.syntax.function.memoize
import com.beust.klaxon.JsonArray
import com.beust.klaxon.Parser
import me.grison.aoc.Day
import me.grison.aoc.lines
import me.grison.aoc.p
import me.grison.aoc.product
import kotlin.math.min

class Day13 : Day(13, 2022) {
    override fun title() = "Distress Signal"

    override fun partOne() = solve().first

    override fun partTwo() = solve().second

    private val solve = {
        var total = 0
        val json = Parser.default()
        val packets = mutableListOf<Any>()
        inputGroups.forEachIndexed { i, pair ->
            val (pair1, pair2) = pair.lines().map { json.parse(StringBuilder(it)) as JsonArray<*> }
            packets.addAll(listOf(pair1, pair2))
            if (compare(pair1, pair2) <= 0)
                total += i.inc()
        }

        val dividerPackets = listOf(JsonArray(JsonArray(2)), JsonArray(JsonArray(6)))
        packets.addAll(dividerPackets)

        val total2 = packets.sortedWith(this::compare).withIndex()
            .filter { it.value in dividerPackets }
            .map { it.index.inc() }
            .product()

        p(total, total2)
    }.memoize()

    private fun compare(packet1: Any, packet2: Any): Int {
        return when {
            packet1 is Int && packet2 is Int -> packet1.compareTo(packet2)
            packet1 is JsonArray<*> && packet2 is Int -> compare(packet1, JsonArray(packet2))
            packet1 is Int && packet2 is JsonArray<*> -> compare(JsonArray(packet1), packet2)
            packet1 is JsonArray<*> && packet2 is JsonArray<*> -> {
                for (i in 0 until (min(packet1.size, packet2.size))) {
                    val c = compare(packet1[i]!!, packet2[i]!!)
                    if (c != 0)
                        return c
                }
                return packet1.size.compareTo(packet2.size)
            }
            else -> 0
        }
    }
}