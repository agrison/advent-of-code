package me.grison.aoc.y2021

import me.grison.aoc.*

class Day20 : Day(20, 2021) {
    override fun title() = "Trench Map"

    private val algorithm = inputList.first().map { (it == '#').toInt() }
    private val originalImage = inputList.drop(2).map { row -> row.map { (it == '#').toInt() } }
    private val pixels2Id = (0 until 512).associate { p(it.toBinary(9).list().ints(), it) }

    override fun partOne() = solve(2)

    override fun partTwo() = solve(50)

    private fun solve(times: Int): Int {
        var (image, currentBorder) = p(originalImage, 0)
        repeat(times) {
            image = enhance(image, currentBorder)
            currentBorder = algorithm.at(-currentBorder)
        }
        return image.sumOf { it.count { col -> col == 1 } }
    }

    private fun enhance(image: Image, currentBorder: Int): Image {
        val (rows, columns) = p(image.size, image.first().size)
        fun pixel(y: Int, x: Int): Int {
            return (if (x in 0 until columns && y in 0 until rows) image[y][x] else currentBorder)
        }

        return (-1..rows).fold(mutableListOf()) { acc, row ->
            (-1..columns).map { column ->
                p(row, column).neighbors(self = true).map { pixel(it.first, it.second) }.let {
                    algorithm[pixels2Id.getValue(it)]
                }
            }.let { acc.append(it) }
        }
    }
}

private typealias Image = List<List<Int>>