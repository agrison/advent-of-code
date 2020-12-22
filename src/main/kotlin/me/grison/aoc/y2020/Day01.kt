package me.grison.aoc.y2020

import me.grison.aoc.Day

class Day01 : Day(1, 2020) {
    override fun title() = "Report Repair"

    override fun partOne() = solve(2)

    override fun partTwo() = solve(3)

    private fun solve(size: Int): Int =
            inputAsVavrInts.combinations(size)
                    .filter { it.sumValues() == 2020 }
                    .map { it.mulValues() }
                    .head()
}

// -- also part 1 can be 7 times faster using a Set --
//    override fun partOne(): Int {
//        val input = inputList.map{it.toInt()}.toSet()
//        return input.mapNotNull { v -> if (input.contains(2020 - v)) v * (2020 - v) else null }
//                .first()
//    }