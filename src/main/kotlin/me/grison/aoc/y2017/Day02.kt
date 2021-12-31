package me.grison.aoc.y2017

import io.vavr.kotlin.toVavrList
import me.grison.aoc.Day
import me.grison.aoc.allInts

class Day02 : Day(2, 2017) {
    override fun title() = "Corruption Checksum"

    override fun partOne() = inputList.map { it.allInts().sorted() }
        .sumOf { it.last() - it.first() }

    override fun partTwo() = inputList.map { it.allInts().sorted() }
        .flatMap { row -> row.toVavrList().combinations(2) }
        .filter { it[1] % it[0] == 0 }
        .sumOf { it[1] / it[0] }
}