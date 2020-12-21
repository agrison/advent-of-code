package me.grison.aoc.y2015

import me.grison.aoc.Day

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser

class Day12 : Day(12, 2015) {
    override fun title() = "JSAbacusFramework.io"

    override fun partOne(): Int {
        val json = Parser().parse(StringBuilder(inputString)) as JsonObject
        return sumItems(json)
    }

    override fun partTwo(): Int {
        val json = Parser().parse(StringBuilder(inputString)) as JsonObject
        return sumItems(json, ignoreRed = true)
    }

    private fun sumItems(item: Any?, ignoreRed: Boolean = false): Int {
        if (item is Map<*, *>) {
            if (ignoreRed && "red" in item.values)
                return 0
            else {
                return item.values.map { sumItems(it, ignoreRed) }.sum()
            }
        } else if (item is List<*>) {
            return item.map { sumItems(it, ignoreRed) }.sum()
        } else if (item is Int) {
            return item.toInt()
        } else
            return 0
    }
}
