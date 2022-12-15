package me.grison.aoc.y2022

import me.grison.aoc.*
import kotlin.math.abs
import kotlin.math.max

class Day15 : Day(15, 2022) {
    override fun title() = "Beacon Exclusion Zone"

    override fun partOne(): Int {
        val data = data()

        val beacons = data.map { it.second }.toSet()
        val freeZone = mutableSetOf<LPosition>()
        val sensorRow = 2_000_000L

        data.forEach { (sensor, beacon) ->
            val distance = sensor.manhattan(beacon)

            for (dx in listOf(-1, 1)) {
                var dist = abs(sensor.second - sensorRow)
                var current = sensor.first
                while (dist++ <= distance) {
                    freeZone.add(p(current, sensorRow))
                    current += dx
                }
            }
        }

        return (freeZone - beacons).size
    }

    override fun partTwo(): Long {
        val data = data()

        for (i in 3_000_000..4_000_000) {
            val bounds = mutableListOf<LPosition>()
            for ((sensor, beacon) in data) {
                val distance = sensor.manhattan(beacon)
                val diff = distance - abs(i - sensor.first)
                if (diff >= 0)
                    bounds.add(p(sensor.second - diff, sensor.second + diff))
            }
            bounds.sortBy { it.first }

            var last = 0L
            for ((bx, by) in bounds) {
                if (bx > last)
                    return i * 4000000L + (bx-1)
                last = max(last, by);
            }
        }

        return -1
    }

    private fun data() = inputList.map {
        val (x1, y1, x2, y2) = it.allLongs()
        p(p(x1, y1), p(x2, y2))
    }
}