package me.grison.aoc.y2022

import me.grison.aoc.Day
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

data class Answer(val inst: () -> Day, val part1: Any, val part2: Any)

class AllDaysTest {
    @TestFactory
    fun answers() = listOf(
        Answer({ Day01() }, 71502, 208191),
        Answer({ Day02() }, 9651, 10560),
        Answer({ Day03() }, 7553, 2758),
        Answer({ Day04() }, 450, 837),
        Answer({ Day05() }, "MQTPGLLDN", "LVZPSTTCZ"),
        Answer({ Day06() }, 1080, 3645),
        Answer({ Day07() }, 1582412, 3696336),
        Answer({ Day08() }, 1763, 671160),
        Answer({ Day09() }, 6026, 2273),
        Answer({ Day10() }, 12980, "BRJLFULP"),
        Answer({ Day11() }, 76728, 21553910156),
        Answer({ Day12() }, 391, 386),
        Answer({ Day13() }, 5292, 23868),
        Answer({ Day14() }, 843, 27625),
        Answer({ Day15() }, 5688618, 12625383204261),
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
