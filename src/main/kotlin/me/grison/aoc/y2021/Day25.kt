package me.grison.aoc.y2021

import me.grison.aoc.*

class Day25 : Day(25, 2021) {
    override fun title() = "Sea Cucumber"

    override fun partOne(): Int {
        var map = inputList.map { it.stringList().copy() }.copy()

        var steps = 0
        while (true) {
            steps++
            val previousMap = map.copy()
            map = move(move(map, p(0, 1), ">"), p(1, 0), "v")
            if (map == previousMap)
                return steps
        }
    }

    private fun move(map: Map, step: Position, seaCucumber: String): Map {
        val newMap = map.copy()
        val (rows, columns) = map.dimensions()
        for ((row, column) in map.iterate(rows, columns)) {
            if (map[row][column] == seaCucumber) {
                val nextRow = (row + step.first) % rows
                val nextColumn = (column + step.second) % columns
                if (map[nextRow][nextColumn] == ".") {
                    newMap[row][column] = "."
                    newMap[nextRow][nextColumn] = seaCucumber
                }
            }
        }
        return newMap
    }

    override fun partTwo() = SANTA
}

private typealias Map = MutableList<MutableList<String>>
fun Map.copy() = map { it.copy() }.copy()