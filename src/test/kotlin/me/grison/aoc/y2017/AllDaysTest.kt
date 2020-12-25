package me.grison.aoc.y2017

import me.grison.aoc.Day
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

data class Answer(val inst: () -> Day, val part1: Any, val part2: Any)

class AllDaysTest {
    @TestFactory
    fun answers() = listOf(
        Answer({ Day01() }, 1029, 1220),
        Answer({ Day02() }, 41919, 303),
        Answer({ Day03() }, 480, 349975),
        Answer({ Day04() }, 383, 265),
        Answer({ Day05() }, 388611, 27763113),
        Answer({ Day06() }, 4074, 2793),
//        Answer({ Day07() }, 118, 260),
//        Answer({ Day08() }, 4902, 7037),
        Answer({ Day09() }, 13154, 6369),
//        Answer({ Day10() }, 360154, 5103798),
        Answer({ Day11() }, 812, 1603),
        Answer({ Day12() }, 152, 186),
        Answer({ Day13() }, 1580, 3943252),
//        Answer({ Day14() }, 2696, 1084),
        Answer({ Day15() }, 569, 298L),
//        Answer({ Day16() }, 40, 241),
        Answer({ Day17() }, 996, 1898341),
//        Answer({ Day18() }, 1939, 19999535),
//        Answer({ Day19() }, 535, 212),
//        Answer({ Day20() }, 32259706, 113L),
//        Answer({ Day21() }, 78, 148),
//        Answer({ Day22() }, 924, 213),
//        Answer({ Day23() }, 170L, 247L),
//        Answer({ Day24() }, 10439961859L, 72050269L),
        Answer({ Day25() }, 4230, "Merry Christmas!"),
    ).map {
        val day = it.inst.invoke()
        DynamicTest.dynamicTest("Day ${day.year}/${day.dayNumber} - Part 1 - expecting ${it.part1}") {
            assertThat(day.partOne(), `is`(it.part1))
        }
        DynamicTest.dynamicTest("Day ${day.year}/${day.dayNumber} - Part 2 - expecting ${it.part2}") {
            assertThat(
                if (day.dayNumber < 25) day.partTwo()
                else "Merry Christmas!", `is`(it.part2)
            )
        }
    }
}
