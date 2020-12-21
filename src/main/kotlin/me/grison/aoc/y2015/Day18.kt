package me.grison.aoc.y2015

import me.grison.aoc.Day

class Day18 : Day(18, 2015) {
    override fun title() = "Like a GIF For Your Yard"

    private val width = 100
    private val height = 100

    override fun partOne(): Int {
        val input = inputString.replace("\n".regex(), "").toCharArray()
        Grid(width, height, input).let { grid ->
            repeat(100) { grid.tick() }
            return grid.onCells()
        }
    }

    override fun partTwo(): Int {
        inputString.replace("\n".regex(), "").toCharArray().let {
            it[0] = '#'
            it[width - 1] = '#'
            it[width * height - width] = '#'
            it[width * height - 1] = '#'
            Grid(width, height, it).let { grid ->
                repeat(100) { grid.tick(part2 = true) }
                return grid.onCells()
            }
        }
    }

    data class Grid(var width: Int, var height: Int, var cells: CharArray) {
        private fun getCell(x: Int, y: Int) = cells[width * x + y]

        private fun isOn(x: Int, y: Int) = getCell(x, y) == '#'

        fun onCells() = cells.fold(0) { total, cell -> if (cell == '#') total + 1 else total }

        private fun isInGrid(x: Int, y: Int) = x in 0 until width && y in 0 until height

        private fun getNeighboursCount(x: Int, y: Int): Int {
            var count = if (isOn(x, y)) -1 else 0

            for (yy in (0..2))
                for (xx in (0..2))
                    if (isInGrid(x + xx - 1, y + yy - 1) && isOn(x + xx - 1, y + yy - 1))
                        count++

            return count
        }

        fun tick(part2: Boolean = false) {
            val cells = CharArray(width * height)
            cells.fill('.')

            if (part2) {
                cells[0] = '#'
                cells[width - 1] = '#'
                cells[width * height - 1] = '#'
                cells[width * height - width] = '#'
            }

            for (y in 0 until height) {
                for (x in 0 until width) {
                    val onLightsCount = getNeighboursCount(x, y)

                    if (isOn(x, y)) {
                        if (onLightsCount in 2..3)
                            cells[width * x + y] = '#'
                    } else if (onLightsCount == 3)
                        cells[width * x + y] = '#'
                }
            }

            this.cells = cells
        }
    }

}

