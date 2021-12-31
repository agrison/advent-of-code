package me.grison.aoc.y2020

import me.grison.aoc.*
import java.lang.System.lineSeparator

class Day06 : Day(6, 2020) {
    override fun title() = "Custom Customs"

    override fun partOne() = inputString.split(lineSeparator() * 2)
            .sumOf { it.oneLine().charSet().size }

    override fun partTwo() = inputString.split(lineSeparator() * 2)
            .sumOf { it.lines().fold(alphabet.charSet()) { all, s -> all.intersect(s.charSet()) }.size }
}