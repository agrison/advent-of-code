package me.grison.aoc.y2021

import me.grison.aoc.*
import java.util.function.Predicate

class Day12 : Day(12, 2021) {
    override fun title() = "Passage Pathing"

    private val graph = inputList.map { it.normalSplit("-").pair() }.toGraph()

    override fun partOne() = graph.countAllPaths("start", "end", trackVisit = { it.isLower() })

    override fun partTwo() = graph.countAllPaths(
        "start", "end",
        trackVisit = { it.isLower() },
        ignore = { it == "start" },
        visitUpToTimes = 2
    )

    /**
     * Count all possible paths from source to destination.
     * By default node can be visited only once, but this can be changed with the
     * `trackVisit` predicate.
     * Some nodes can also be ignored by using the `ignore` predicate.
     */
    private fun <T> Graph<T>.countAllPaths(
        source: T, destination: T,
        trackVisit: Predicate<T> = Predicate { true },
        ignore: Predicate<T> = Predicate { false },
        visitUpToTimes: Int = 1,
    ): Int {
        fun explore(graph: Graph<T>, current: T, visited: Set<T>, times: Int): Int {
            if (current == destination) return 1
            var paths = 0
            for (neighbor in graph[current]!!.reject { ignore.test(it) }) {
                paths += when (trackVisit.test(neighbor)) {
                    true -> when (neighbor) {
                        !in visited -> explore(graph, neighbor, visited + neighbor, times)
                        else -> if (times > 1) explore(graph, neighbor, visited, times - 1) else 0
                    }
                    else -> explore(graph, neighbor, visited, times)
                }
            }
            return paths
        }

        return explore(this, source, mutableSetOf(source), visitUpToTimes)
    }
}
