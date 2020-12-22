package me.grison.aoc.y2015

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import me.grison.aoc.Day

class Day12 : Day(12, 2015) {
    override fun title() = "JSAbacusFramework.io"

    override fun partOne(): Int {
        val json = Parser.default().parse(StringBuilder(inputString)) as JsonObject
        return sumItems(json)
    }

    override fun partTwo(): Int {
        val json = Parser.default().parse(StringBuilder(inputString)) as JsonObject
        return sumItems(json, ignoreRed = true)
    }

    private fun sumItems(item: Any?, ignoreRed: Boolean = false): Int {
        return if (item is Map<*, *>) {
            if (ignoreRed && "red" in item.values) 0
            else item.values.map { sumItems(it, ignoreRed) }.sum()
        } else if (item is List<*>) item.map { sumItems(it, ignoreRed) }.sum()
        else if (item is Int) item.toInt()
        else 0
    }
}