package me.grison.aoc.y2020

import me.grison.aoc.Day
import me.grison.aoc.y2015.Day25
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

data class Answer(val inst: () -> Day, val part1: Any, val part2: Any)

class AllDaysTest {
    @TestFactory
    fun answers() = listOf(
        Answer({ Day01() }, 1003971, 84035952),
        Answer({ Day02() }, 645, 737),
        Answer({ Day03() }, 156, 3521829480),
        Answer({ Day04() }, 242, 186),
        Answer({ Day05() }, 974, 646),
        Answer({ Day06() }, 6630, 3437),
        Answer({ Day07() }, 287, 48160),
        Answer({ Day08() }, 1262, 1643),
        Answer({ Day09() }, 41682220, 5388976L),
        Answer({ Day10() }, 2414, 21156911906816),
        Answer({ Day11() }, 2283, 2054),
        Answer({ Day12() }, 1007, 41212),
        Answer({ Day13() }, 5257, 538703333547789),
        Answer({ Day14() }, 13865835758282, 4195339838136),
        Answer({ Day15() }, 319, 2424),
        Answer({ Day16() }, 19087, 1382443095281),
        Answer({ Day17() }, 362, 1980),
        Answer({ Day18() }, 7293529867931, 60807587180737),
        Answer({ Day19() }, 279, 384),
        Answer({ Day20() }, 64802175715999, 2146),
        Answer({ Day21() }, 2798, "gbt,rpj,vdxb,dtb,bqmhk,vqzbq,zqjm,nhjrzzj"),
        Answer({ Day22() }, 32598, 35836),
        Answer({ Day23() }, 53248976, 418819514477L),
        Answer({ Day24() }, 266, 3627),
        Answer({ Day25() }, 7936032, "Merry Christmas!"),
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
