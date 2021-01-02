package me.grison.aoc.y2016

import me.grison.aoc.Day
import me.grison.aoc.p

class Day09 : Day(9, 2016) {
    override fun title() = "Explosives in Cyberspace"

    override fun partOne() = decompress(inputString, false)

    override fun partTwo() = decompress(inputString, true)

    private fun decompress(file: String, two: Boolean): Long {
        var (ans, i, chars) = Triple(0L, 0, file.toCharArray())
        while (i < chars.size) {
            if (chars[i] == ' ') {
                i++
                continue
            } else if (chars[i] == '(') {
                val end = file.indexOf(')', i)
                val marker = file.substring(i + 1, end).split("x")
                val (next, times) = p(marker[0].toInt(), marker[1].toLong())
                val repeat = file.substring(end + 1, end + 1 + next)
                ans += times * if (two) decompress(repeat, two) else repeat.length.toLong()
                i = end + next
            } else {
                ans++
            }
            i++
        }
        return ans
    }
}