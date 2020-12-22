package me.grison.aoc.y2015

import io.vavr.kotlin.toVavrList
import me.grison.aoc.Day

class Day09 : Day(9, 2015) {
    override fun title() = "All in a Single Night"

    override fun partOne() = findRoutes().first

    override fun partTwo() = findRoutes().second

    private fun findRoutes(): Pair<Int, Int> {
        val path = mutableMapOf<String, Int>()
        val cities = mutableSetOf<String>()
        inputList.forEach { line ->
            val (city1, _, city2, _, distance) = line.split(" ")
            path[city1 + city2] = distance.toInt()
            path[city2 + city1] = distance.toInt()
            cities.add(city1)
            cities.add(city2)
        }

        var (shortest, longest) = p(10000, 0)
        cities.toVavrList().permutations().forEach { route ->
            var routeLength = 0
            for (e in route.subSequence(1).zip(route.dropRight(1))) {
                routeLength += path[e._1!! + e._2!!]!!
            }
            if (routeLength < shortest)
                shortest = routeLength
            if (routeLength > longest)
                longest = routeLength
        }

        return p(shortest, longest)
    }
}