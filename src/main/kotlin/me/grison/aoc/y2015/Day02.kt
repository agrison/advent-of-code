package me.grison.aoc.y2015

import me.grison.aoc.Day

import kotlin.math.min

class Day02 : Day(2, 2015) {
    override fun title() = "I Was Told There Would Be No Math"

    override fun partOne() =
            inputList
                    .map { it.split("x").map { x -> x.toInt() } }
                    .fold(0) { total, (l, w, h) ->
                        total + (2 * l * w) + (2 * w * h) + (2 * h * l) + min(l * w, min(w * h, h * l))
                    }

    override fun partTwo() =
            inputList
                    .map { it.split("x").map { x -> x.toInt() } }
                    .map { it.sorted() }
                    .fold(0) { total, (l, w, h) ->
                        total + (l + l + w + w) + (l * w * h)
                    }
}