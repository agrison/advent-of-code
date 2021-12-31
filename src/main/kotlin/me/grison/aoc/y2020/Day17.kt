package me.grison.aoc.y2020

import me.grison.aoc.*

class Day17 : Day(17, 2020) {
    override fun title() = "Conway Cubes"

    override fun partOne() = (0..5).fold(loadCubes()) { g, _ -> g.evolve() }.count()

    override fun partTwo() = (0..5).fold(loadCubes(true)) { g, _ -> g.evolve() }.count()

    private fun loadCubes(hyper: Boolean = false) = mutableSetOf<Position>().let {
        inputList.forEachIndexed { x, line ->
            line.forEachIndexed { y, state ->
                if (state == '#') it.add(Position(x, y, 0))
            }
        }
        Cubes(it, hyper)
    }
}

data class Position(val x: Int, val y: Int, val z: Int, val w: Int = 0)

data class Cubes(val cubes: Set<Position>, val hyper: Boolean = false) {
    fun count() = cubes.size

    fun evolve(): Cubes {
        val nextCubes = mutableSetOf<Position>()
        val (xRange, yRange, zRange, wRange) = allRanges()
        for (x in xRange)
            for (y in yRange)
                for (z in zRange)
                    for (w in wRange) {
                        val n = countLiveNeighbours(x, y, z, w)
                        val p = Position(x, y, z, w)
                        if (n == 3) nextCubes.add(p)
                        else if (p in cubes && (n == 2 || n == 3)) nextCubes.add(p)
                    }

        return Cubes(nextCubes, hyper)
    }

    private fun countLiveNeighbours(x: Int, y: Int, z: Int, w: Int): Int {
        var total = 0
        for (xx in -1..1)
            for (yy in -1..1)
                for (zz in -1..1)
                    for (ww in (if (hyper) -1..1 else 0..0)) // iterate also on w when hyper
                        if (!(xx == 0 && yy == 0 && zz == 0 && ww == 0)) // avoid self!!
                            if (Position(xx + x, yy + y, zz + z, w + ww) in cubes)
                                total += 1
        return total
    }

    private fun allRanges() = mutableListOf<IntRange>().let { ranges ->
        ranges.add(cubes.minOf { it.x } - 1..cubes.maxOf { it.x } + 1)
        ranges.add(cubes.minOf { it.y } - 1..cubes.maxOf { it.y } + 1)
        ranges.add(cubes.minOf { it.z } - 1..cubes.maxOf { it.z } + 1)
        // part2: if hyper then same as x,y,z, otherwise a range of 1
        ranges.add(if (hyper) cubes.minOf { it.w } - 1..cubes.maxOf { it.w } + 1
                   else       0..0)
        ranges
    }
}