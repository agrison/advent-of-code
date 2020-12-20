package me.grison.aoc.y2020

import arrow.syntax.collections.tail
import me.grison.aoc.Day

class Day20 : Day(20, 2020) {
    override fun title() = "Jurassic Jigsaw"

    override fun partOne(): Long {
        val tiles = tiles()

        val edgeCount = mutableMapOf<String, Int>().withDefault { 0 }
        tiles.forEach { (_, tile) ->
            tile.minEdges().forEach { edge -> edgeCount[edge] = edgeCount.getValue(edge) + 1 }
        }

        return tiles.filter { (_, tile) -> tile.minEdges().count { edgeCount.getValue(it) == 1 } == 2 }
            .map { (num, _) -> num.toLong() }.multiply()
    }

    override fun partTwo(): Any {

        return 0
    }

    private fun tiles() =
        inputGroups.map { Tile(it.lines().first().allInts().first(), it.lines().tail()) }
            .map { Pair(it.num, it) }.toMap()

    data class Tile(val num: Int, val pixels: List<String>) {
        private fun left() = pixels.map { it[0] }.reversed().joinToString("")
        private fun top() = pixels.first()
        private fun bottom() = pixels.last().reversed()
        private fun right() = pixels.map { it.last() }.joinToString("")
        fun edges(): List<String> = listOf(left(), top(), right(), bottom())
        fun minEdges(): List<String> = edges().map { if (it > it.reversed()) it.reversed() else it }
        fun flip() = pixels.joinToString("\n") { it.reversed() }
    }
}