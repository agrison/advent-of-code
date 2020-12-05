package days

class Day05 : Day(5) {
    override fun title() = "Binary Boarding"

    override fun partOne() = seats().maxOrNull() ?: 0

    override fun partTwo() = seats().let { s ->
        generateSequence(0, Int::inc).first { !s.contains(it) && s.contains(it - 1, it + 1) }
    }

    private fun seats() = inputList.map {
        it.replacingRegex(mapOf("[FL]" to "0", "[BR]" to "1")).binary()
    }
}