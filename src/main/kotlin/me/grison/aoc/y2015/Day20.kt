package me.grison.aoc.y2015

import me.grison.aoc.Day

class Day20 : Day(20, 2015) {
    override fun title() = "Infinite Elves and Infinite Houses"

    private val houses = IntArray(36000000 / 10)

    override fun partOne() : Int {
        var houseNumber = houses.size
        (1 until houses.size).forEach { i ->
            for (j in i until houses.size step i) {
                houses[j] += i
                if (houses[j] > houses.size && j < houseNumber)
                    houseNumber = j
            }
        }
        return houseNumber
    }

    override fun partTwo() : Int {
        var houseNumber = houses.size
        houses.fill(11)
        (1 until houses.size).forEach { i ->
            var visits = 0
            for (j in i until houses.size step i) {
                houses[j] += i * 11
                if (houses[j] >= houses.size * 10 && j < houseNumber)
                    houseNumber = j

                if (++visits == 50) break
            }
        }
        return houseNumber
    }
}