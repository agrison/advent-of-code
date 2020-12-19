package me.grison.aoc.y2020

import arrow.core.extensions.list.foldable.forAll
import me.grison.aoc.Day
import java.lang.System.lineSeparator

class Day04 : Day(4, 2020) {
    override fun title() = "Passport Processing"

    override fun partOne() = validPassports(listOf { it ->
        it.containsAll("byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:")
    })

    override fun partTwo() = validPassports(listOf(
            { p -> p.intField("byr").`in`(1920..2002) },
            { p -> p.intField("iyr").`in`(2010..2020) },
            { p -> p.intField("eyr").`in`(2020..2030) },
            { p ->
                when {
                    p.field("hgt").contains("cm") -> p.intField("hgt", "cm").`in`(150..193)
                    p.field("hgt").contains("in") -> p.intField("hgt", "in").`in`(59..76)
                    else -> false
                }
            },
            { p -> p.field("hcl").matches("#[0-9a-f]{6}") },
            { p -> p.field("ecl").`in`("amb", "blu", "brn", "gry", "grn", "hzl", "oth") },
            { p -> p.field("pid").matches("\\d{9}") }))

    private fun validPassports(criteria: List<((s: String) -> Boolean)>): Int =
            inputString.split(lineSeparator() * 2)
                    .map { it.replace(lineSeparator(), " ") }
                    .count { p -> criteria.forAll { it(p) } }
}