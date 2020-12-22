package me.grison.aoc.y2015

import me.grison.aoc.*

class Day01 : Day(1, 2015) {
    override fun title() = "Not Quite Lisp"

    override fun partOne() =
            inputString.count { it == '(' } - inputString.count { it == ')' }

    override fun partTwo(): Int {
        var floor = 0
        return inputString.split().map {
            if (it == '(') ++floor else --floor
        }.indexOf(-1) + 1
    }
}