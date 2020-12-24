package me.grison.aoc.y2017

import me.grison.aoc.*

class Day12 : Day(12, 2017) {
    override fun title() = "Digital Plumber"

    override fun partOne() = solve().first

    override fun partTwo() = solve().second

    private fun solve(): Pair<Int, Int> {
        var (one, two) = p(0, 0)
        val connected = mutableSetOf<Any>()
        val rest = inputList.indices.toMutableList()
        val right = inputList.map { l -> l.after(" <-> ").normalSplit(", ").ints() }
        inputList.indices.forEach {
            if (it in rest) {
                val q = listOf(it).deque()
                while (q.isNotEmpty()) {
                    val dq = q.pop()
                    for (n in right[dq]) {
                        if (n !in connected) {
                            connected.add(n)
                            q.addLast(n)
                            rest.remove(n)
                        }
                    }
                }
                two += 1
            }
            if (it == 0)
                one = connected.size
        }

        return p(one, two)
    }
}