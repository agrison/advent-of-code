package me.grison.aoc.y2015

import me.grison.aoc.*

class Day04 : Day(4, 2015) {
    override fun title() = "The Ideal Stocking Stuffer"

    override fun partOne(): Int =
            generateSequence(0) { it + 1 }
                    .first { md5("$inputString$it").startsWith("0" * 5) }

    override fun partTwo(): Int =
            generateSequence(0) { it + 1 }
                    .first { md5("$inputString$it").startsWith("0" * 6) }
}