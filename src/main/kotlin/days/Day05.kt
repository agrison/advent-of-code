package days

class Day05 : Day(5) {
    override fun title() = "Binary Boarding"

    override fun partOne() = seats().maxOrNull() ?: 0

    override fun partTwo() = seats().let { s ->
        (1..128 * 8).first { !s.contains(it) && s.contains(it - 1, it + 1) }
    }

    private fun seats() = inputSet.map {
        it.replacingRegex(mapOf("[FL]" to "0", "[BR]" to "1")).toInt(2)
    }
}