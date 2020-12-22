package me.grison.aoc.y2015

import me.grison.aoc.Day

class Day14 : Day(14, 2015) {
    override fun title() = "Reindeer Olympics"

    override fun partOne() = load().map { r -> r.distance(2503)}.max()

    override fun partTwo(): Int? {
        val barn = load()
        (1..2503).forEach { time ->
            val distances = barn.map { it.distance(time) }
            val max = distances.max() ?: 0
            barn.forEachIndexed { i, reindeer ->
                if (distances[i] == max)
                    reindeer.incPoints()
            }
        }
        return barn.map{it.points}.max()
    }

    private fun load() : List<Reindeer> =
            inputList.map { s ->
                val p = s.split(" ")
                Reindeer(p[0], p[3].toInt(), p[6].toInt(), p[13].toInt())
            }
}

data class Reindeer(val name: String, val speed: Int, val flight: Int, val rest: Int, var points : Int = 0) {
    fun distance(time: Int) : Int {
        val cycle = flight + rest
        val (cycles, remain) = Pair(time / cycle, time % cycle)
        var dist = cycles * speed * flight
        dist += speed * (if (remain > flight) flight else remain)
        return dist
    }

    fun incPoints() = ++points
}