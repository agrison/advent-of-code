package me.grison.aoc.y2022

import me.grison.aoc.*

class Day12 : Day(12, 2022) {
    override fun title() = "Hill Climbing Algorithm"

    private fun cellValue(x: String) =
        x.replace("S", "a").replace("E", "z").first().code

    override fun partOne() = solve("S")

    override fun partTwo() = solve("a")

    private fun solve(start: String): Int {
        val grid = inputList.stringGrid("z")
        val starts = grid.allPositions().filter { grid.at(it) == start }
        val end = grid.allPositions().find { grid.at(it) == "E" }!!

        val edges = mutableListOf<Pair<Position, Position>>()
        grid.allPositions().forEach { p ->
            for (dir in p.directions()) {
                if (cellValue(grid.at(dir)) <= cellValue(grid.at(p)) + 1) {
                    edges.add(p(p, dir))
                }
            }
        }

        val graph = edges.toDirectedGraph()
        return starts.map { graph.shortestPath(it, end).first }.filter { it > -1 }.minOrNull()!!
    }
}
