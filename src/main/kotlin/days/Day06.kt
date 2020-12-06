package days

import java.lang.System.lineSeparator

class Day06 : Day(6) {
    override fun title() = "Custom Customs"

    override fun partOne() = inputString.split(lineSeparator() * 2)
            .map { it.replace(lineSeparator(), "").charSet().size }
            .sum()

    override fun partTwo() = inputString.split(lineSeparator() * 2)
            .map { it.split("\n").fold(alphabet.charSet()) { all, s -> all.intersect(s.charSet()) }.size }
            .sum()
}