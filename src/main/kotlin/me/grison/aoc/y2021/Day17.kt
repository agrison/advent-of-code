package me.grison.aoc.y2021

import me.grison.aoc.*
import kotlin.Int.Companion.MIN_VALUE
import kotlin.math.abs
import kotlin.math.max

class Day17 : Day(17, 2021) {
    override fun title() = "Trick Shot"

    private val solved = solve(inputString.allInts())

    override fun partOne() = solved.first

    override fun partTwo() = solved.second

    private fun solve(bounds: List<Int>): Pair<Int, Int> {
        val (tx1, tx2, ty1, ty2) = bounds
        var (velocityCount, probeMaxY) = p(0, MIN_VALUE)
        (0..tx2).forEach { ivx ->
            (ty1..abs(ty1)).forEach { ivy ->
                probeOnTarget(ivx, ivy, abs(ty1 * 2)) { it.within(tx1, ty1, tx2, ty2) }?.let { maxY ->
                    velocityCount++
                    probeMaxY = max(probeMaxY, maxY)
                }
            }
        }

        return p(probeMaxY, velocityCount)
    }

    private fun probeOnTarget(ivx: Int, ivy: Int, steps: Int, inTarget: (probe: Position) -> Boolean): Int? {
        var currentMaxY = 0
        var probe = p(0, 0)
        var velocity = p(ivx, ivy)
        repeat(steps) {
            probe += velocity
            velocity -= p(velocity.first.sign(), 1)
            currentMaxY = max(currentMaxY, probe.second)
            if (inTarget.invoke(probe))
                return currentMaxY
        }

        return null
    }
}