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
//        Answer({ Day03() }, 862, 1577),
//        Answer({ Day04() }, 173787, 548),
//        Answer({ Day05() }, "c6697b55", "8c35d1ab"),
//        Answer({ Day06() }, "mlncjgdg", "bipjaytb"),
//        Answer({ Day07() }, 118, 260),
//        Answer({ Day08() }, 1333, 2046),
//        Answer({ Day09() }, 251, 898),
//        Answer({ Day10() }, 360154, 5103798),
//        Answer({ Day11() }, "vzbxxyzz", "vzcaabcc"),
//        Answer({ Day12() }, 119433, 68466),
//        Answer({ Day13() }, 664, 640),
//        Answer({ Day14() }, 2696, 1084),
//        Answer({ Day15() }, 222870, 117936),
//        Answer({ Day16() }, 40, 241),
//        Answer({ Day17() }, 654, 57),
//        Answer({ Day18() }, 1939, 19999535),
//        Answer({ Day19() }, 535, 212),
//        Answer({ Day20() }, 32259706, 113L),
//        Answer({ Day21() }, 78, 148),
//        Answer({ Day22() }, 924, 213),
//        Answer({ Day23() }, 170L, 247L),
//        Answer({ Day24() }, 10439961859L, 72050269L),
//        Answer({ Day25() }, 9132360, "Merry Christmas!"),
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
