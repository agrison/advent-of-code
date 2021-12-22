package me.grison.aoc.y2021

import me.grison.aoc.*
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.component3
import kotlin.collections.component4
import kotlin.collections.component5
import kotlin.collections.set
import kotlin.math.max
import kotlin.math.min
import kotlin.ranges.LongRange.Companion.EMPTY

class Day22 : Day(22, 2021) {
    override fun title() = "Reactor Reboot"

    override fun partOne(): Int {
        val cubes = mutableMapOf<Any, String>()

        for ((state, xrange, yrange, zrange) in cuboids())
            for (x in xrange.intersection(-50..50L))
                for (y in yrange.intersection(-50..50L))
                    for (z in zrange.intersection(-50..50L))
                        cubes[Triple(x, y, z)] = state

        return cubes.values.count { it == "on" }
    }

    override fun partTwo(): Long {
        val cuboids = cuboids(inclusive = 1)
        return cuboids.foldIndexed(0L) { i, acc, cube ->
            acc + if (cube.state == "on") countCubes(cube, cuboids.sub(i + 1)) else 0L
        }
    }

    private fun countCubes(cuboid: Cuboid, rest: List<Cuboid>): Long {
        val intersections = rest.mapNotNull { it.intersection(cuboid) }
        return intersections.foldIndexed(cuboid.volume()) { i, acc, cube ->
            acc - countCubes(cube, intersections.sub(i + 1))
        }
    }

    private fun cuboids(inclusive: Int = 0) = inputList.map {
        val (xa, xb, ya, yb, za, zc) = it.allLongs(includeNegative = true)
        Cuboid(it.before(" "), xa..xb + inclusive, ya..yb + inclusive, za..zc + inclusive)
    }
}

private fun LongRange.length() = last - start
private fun LongRange.intersection(range: LongRange) =
    if (last < range.first || first > range.last) EMPTY
    else min(max(first, range.first), range.last)..min(max(last, range.first), range.last)


data class Cuboid(val state: String = "", val xrange: LongRange, val yrange: LongRange, val zrange: LongRange) {
    fun volume() = xrange.length() * yrange.length() * zrange.length()

    fun intersection(other: Cuboid) =
        Cuboid(
            state, xrange.intersection(other.xrange), yrange.intersection(other.yrange),
            zrange.intersection(other.zrange)
        ).let {
            if (it.valid()) it else null
        }

    fun valid() = !xrange.isEmpty() && !yrange.isEmpty() && !zrange.isEmpty()
}