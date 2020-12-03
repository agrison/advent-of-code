package days

class Day03 : Day(3) {
    override fun title() = "Toboggan Trajectory"

    override fun partOne() = slope(3, 1)

    override fun partTwo() = listOf(11, 31, 51, 71, 12)
            .map { slope(it / 10, it % 10) }
            .multiply()

    private fun slope(rows: Int, cols: Int): Long {
        return inputList.mapIndexed { y, row ->
            val x = rows * y
            (x.divisible(cols) && row.at(x / cols, '#')).toInt()
        }.fold(0L, Long::plus)
    }

    // classic
    // private fun slope(lines: List<String>, r: Int, c: Int): Long {
    //     var (y, x, rows, columns) = arrayOf(0, 0, lines.size, lines[0].length)
    //     var trees = 0L
    //     while (y < rows) {
    //         x += r
    //         y += c
    //         if (y >= rows)
    //             break
    //         if (lines[y][x % columns] == '#')
    //             trees++
    //     }
    //     return trees
    // }
}


