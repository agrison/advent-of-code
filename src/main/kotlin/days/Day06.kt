package days

class Day06 : Day(6) {
    override fun title() = "Custom Customs"

    override fun partOne() = inputString.split(System.lineSeparator() * 2)
            .map { it.replace(System.lineSeparator(), "").charSet().size }
            .sum()

    override fun partTwo() = inputString.split(System.lineSeparator() * 2)
            .map { it.split("\n").fold(alphabet.charSet()) { all, s -> all.intersect(s.charSet()) }.size }
            .sum()
}