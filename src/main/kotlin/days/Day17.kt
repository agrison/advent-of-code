package days

class Day17 : Day(17) {
    override fun title() = "Conway Cubes"

    override fun partOne() = (0..5).fold(loadCubes()) { g, _ -> g.evolve() }.count()

    override fun partTwo() = (0..5).fold(loadCubes(true)) { g, _ -> g.evolve() }.count()

    private fun loadCubes(hyper: Boolean = false): Cubes {
        mutableSetOf<Position>().let {
            inputList.forEachIndexed { x, line ->
                line.forEachIndexed { y, state ->
                    if (state == '#') it.add(Position(x, y, 0))
                }
            }
            return Cubes(it, hyper)
        }
    }
}

data class Position(val x: Int, val y: Int, val z: Int, val w: Int = 0)

data class Cubes(var cubes: Set<Position>, var hyper: Boolean = false) {
    fun count() = cubes.size

    fun evolve(): Cubes {
        val nextCubes = mutableSetOf<Position>()
        val (xRange, yRange, zRange, wRange) = scanAmplitude()
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

    private fun scanAmplitude(): MutableList<IntRange> {
        val res = mutableListOf<IntRange>()
        res.add(IntRange(cubes.map { it.x }.min()!! - 1, cubes.map { it.x }.max()!! + 1))
        res.add(IntRange(cubes.map { it.y }.min()!! - 1, cubes.map { it.y }.max()!! + 1))
        res.add(IntRange(cubes.map { it.z }.min()!! - 1, cubes.map { it.z }.max()!! + 1))
        // part2: if hyper then same as x,y,z, otherwise a range of 1
        res.add(if (hyper) IntRange(cubes.map { it.w }.min()!! - 1, cubes.map { it.w }.max()!! + 2)
                else IntRange(0, 0))
        return res
    }
}


