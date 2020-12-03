package days

class Day03 : Day(3) {
    override fun title() = "Toboggan Trajectory"

    override fun partOne() = slope(3, 1)

    override fun partTwo() = listOf(11, 31, 51, 71, 12)
            .map { slope(it / 10, it % 10) }
            .multiply()

    private fun slope(rows: Int, cols: Int): Long {
        return inputList.mapIndexed { y, row ->
            val (x, c) = Pair(rows * y, rows * y / cols)
            (row.debug03(false, c) &&                              // turn on debugging if needed
                    x.divisible(cols) && row.at(c, '#')).toInt()
        }.fold(0L, Long::plus)
    }
}


