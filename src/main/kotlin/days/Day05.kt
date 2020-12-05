package days

class Day05 : Day(5) {
    override fun title() = "Binary Boarding"

    override fun partOne() = seats().maxOrNull() ?: 0

    override fun partTwo() = seats().let { s ->
        (0..256 * 8).first { !s.contains(it) && s.containsAll(listOf(it - 1, it + 1)) }
    }

    private fun seats() = inputList.map {
        it.replacing(mapOf('F' to '0', 'B' to '1', 'L' to '0', 'R' to '1')).binary()
    }
}