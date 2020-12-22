package me.grison.aoc.y2020

import me.grison.aoc.*

class Day13 : Day(13, 2020) {
    override fun title() = "Shuttle Search"

    private val schedule = inputList[1].split(",").map { if (it == "x") -1 else it.toInt() }

    override fun partOne(): Int {
        var (start, now) = p(inputList[0].toInt(), inputList[0].toInt())
        while (true) {
            for (time in schedule) {
                if (time >= 0 && now % time == 0) {
                    return time * (now - start)
                }
            }
            now++
        }
    }

    override fun partTwo(): Long {
        val (n, a) = p(mutableListOf<Long>(), mutableListOf<Long>())
        for ((i, time) in schedule.withIndex()) {
            if (time > 0) {
                n.add(time.toLong())
                a.add((time - i).toLong())
            }
        }
        return chineseRemainder(n.toLongArray(), a.toLongArray())
    }

    // wouldn't have found this myself, thank you internet
    // https://rosettacode.org/wiki/Chinese_remainder_theorem#Kotlin
    // adapted from Int to Long
    private fun chineseRemainder(n: LongArray, a: LongArray): Long {
        val prod: Long = n.fold(1L) { acc, i -> acc * i }
        var sum: Long = 0
        for (i in n.indices) {
            val p = prod / n[i]
            sum += a[i] * multInv(p, n[i]) * p
        }
        return sum % prod
    }

    /* returns x where (a * x) % b == 1 */
    private fun multInv(a: Long, b: Long): Long {
        if (b == 1L) return 1L
        var (aa, bb) = p(a, b)
        var (x0, x1) = p(0L, 1L)
        while (aa > 1) {
            var (q, t) = p(aa / bb, bb)
            bb = aa % bb
            aa = t
            t = x0
            x0 = x1 - q * x0
            x1 = t
        }
        if (x1 < 0) x1 += b
        return x1
    }
}