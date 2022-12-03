package me.grison.aoc.y2019

import me.grison.aoc.Day
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

data class Answer(val inst: () -> Day, val part1: Any, val part2: Any)

class AllDaysTest {
    @TestFactory
    fun answers() = listOf(
        Answer({ Day01() }, 3262991, 4891620),
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
