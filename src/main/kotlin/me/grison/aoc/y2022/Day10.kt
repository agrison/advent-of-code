package me.grison.aoc.y2022

import arrow.syntax.function.memoize
import me.grison.aoc.*
import kotlin.math.abs

class Day10 : Day(10, 2022) {
    override fun title() = "Cathode-Ray Tube"

    override fun partOne() = solve().first

    override fun partTwo() = solve().second

    private val interesting = listOf(20, 60, 100, 140, 180, 220)

    private val solve = {
        var (cycles, x, sumStrength) = t(0, 1, 0)
        val crt = mutableListOf<Position>()
        fun signalStrength() = x * cycles.inc()

        fun cycle() {
            if (cycles.inc() in interesting)
                sumStrength += signalStrength()

            if (abs(cycles % 40 - x) < 2)
                crt.add(p(cycles % 40, cycles / 40))

            cycles += 1
        }

        inputList.forEach {
            when (it) {
                "noop" -> cycle()
                else -> {
                    repeat(2) { cycle() }
                    x += it.firstInt()
                }
            }
        }

        p(sumStrength, crt.pointsDisplay().ocrExtract())
    }.memoize()
}
