package me.grison.aoc.y2015

import me.grison.aoc.Day
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

data class Answer(val inst: () -> Day, val part1: Any, val part2: Any)

class AllDaysTest {
    @TestFactory
    fun answers() = listOf(
        Answer({ Day01() }, 138, 1771),
        Answer({ Day02() }, 1606483, 3842356),
        Answer({ Day03() }, 2572, 2631),
        Answer({ Day04() }, 254575, 1038736),
        Answer({ Day05() }, 258, 53),
        Answer({ Day06() }, 569999, 17836115),
        Answer({ Day07() }, 0, 0),
        Answer({ Day08() }, 1333, 2046),
        Answer({ Day09() }, 251, 898),
        Answer({ Day10() }, 360154, 5103798),
        Answer({ Day11() }, "vzbxxyzz", "vzcaabcc"),
        Answer({ Day12() }, 119433, 68466),
        Answer({ Day13() }, 664, 640),
        Answer({ Day14() }, 2696, 1084),
        Answer({ Day15() }, 222870, 117936),
        Answer({ Day16() }, 40, 241),
        Answer({ Day17() }, 654, 57),
        Answer({ Day18() }, 768, 781),
        Answer({ Day19() }, 535, 212),
        Answer({ Day20() }, 831600, 884520),
    ).map {
        val day = it.inst.invoke()
        DynamicTest.dynamicTest("Day ${day.year}/${day.dayNumber} - Part 1 - expecting ${it.part1}") {
            assertThat(day.partOne(), `is`(it.part1))
        }
        DynamicTest.dynamicTest("Day ${day.year}/${day.dayNumber} - Part 2 - expecting ${it.part2}") {
            assertThat(day.partTwo(), `is`(it.part2))
        }
    }
}
