package me.grison.aoc.y2021

import me.grison.aoc.*
import kotlin.math.abs
import kotlin.math.max

class Day17 : Day(17, 2021) {
    override fun title() = "Trick Shot"

    private val solved = solve(inputString.allInts())

    override fun partOne() = solved.first

    override fun partTwo() = solved.second

    private fun solve(bounds: List<Int>): Pair<Int, Int> {
        val (tx1, tx2, ty1, ty2) = bounds
        var probeMaxY = Int.MIN_VALUE
        var velocityCount = 0
        for (ivx in 0..tx2) {
            velocityx@ for (ivy in ty1..abs(ty1)) {
                var currentMaxY = 0
                var probe = p(0, 0)
                var (vx, vy) = p(ivx, ivy)
                for (i in 1..abs(ty1 * 2)) {
                    probe += p(vx, vy)
                    vx -= vx.sign()
                    vy -= 1
                    currentMaxY = max(currentMaxY, probe.second)
                    if (probe.within(tx1, ty1, tx2, ty2)) {
                        ++velocityCount
                        probeMaxY = max(probeMaxY, currentMaxY)
                        continue@velocityx
                    }
                }
            }
        }

        return p(probeMaxY, velocityCount)
    }
}