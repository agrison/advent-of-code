package me.grison.aoc.y2016

import me.grison.aoc.Day
import me.grison.aoc.allInts
import me.grison.aoc.normalSplit

class Day08 : Day(8, 2016) {
    override fun title() = "Two-Factor Authentication"

    private val width = 50
    private val height = 6

    private val screen = Array(height) { BooleanArray(width) { false } }

    override fun partOne(): Int {
        inputList.forEach { line ->
            val (cmd, arg) = line.normalSplit(" ")
            val (a, b) = line.allInts()
            when (cmd) {
                "rect" -> rect(a, b)
                "rotate" -> when (arg) {
                    "row" -> rotateRow(a, b)
                    "column" -> rotateColumn(a, b)
                }
            }
        }

        return screen.fold(0) { acc, it -> acc + it.count { it } }
    }

    override fun partTwo(): String {
        return "\n" + screen.joinToString(separator = "\n") { row ->
            row.joinToString(separator = " ") { col ->
                if (col) "X" else " "
            }
        }
    }

    fun rect(width: Int, height: Int) {
        (0 until height).forEach { row ->
            (0 until width).forEach { col ->
                screen[row][col] = true
            }
        }
    }

    fun rotateRow(row: Int, by: Int) {
        (0 until width).map { screen[row][it] }
            .forEachIndexed { i, b -> screen[row][(i + by) % width] = b }
    }

    fun rotateColumn(col: Int, by: Int) {
        (0 until height).map { screen[it][col] }
            .forEachIndexed { i, b -> screen[(i + by) % height][col] = b }
    }
}