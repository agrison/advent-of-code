package me.grison.aoc.y2015

import me.grison.aoc.*

class Day25 : Day(25, 2015) {
    override fun title() = "Let It Snow"

    private val inputs = inputString.allLongs()

    override fun partOne() : Long {
        var code = 20151125L
        var (x, y) = p(1L, 1L)
        val (inputY, inputX) = inputs
        while (true) {
            when (y) {
                1L -> {y = x + 1; x = 1}
                else -> {y--; x++}
            }
            code = (code * 252533) % 33554393

            if (x == inputX && y == inputY)
                return code
        }
    }

    override fun partTwo() = SANTA
}