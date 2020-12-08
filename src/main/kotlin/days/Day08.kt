package days

class Day08 : Day(8) {
    override fun title() = "Handheld Halting"

    // returns an InfiniteLoop(value)
    override fun partOne() = program.run().output

    override fun partTwo() = program.indices
            .map { program.swap(it, "jmp", "nop").run() }
            .first { it is Success }.output

    fun Program.run(): Execution {
        var (pos, acc, visited) = Triple(0, 0, mutableSetOf<Int>())
        while (true) {
            if (pos in visited) return InfiniteLoop(acc)
            else if (isEnd(pos)) return Success(acc)

            visited.add(pos)
            val instr = this[pos]
            pos += when (instr.op()) {
                "acc" -> { acc += instr.arg(); 1 }
                "jmp" -> instr.arg()
                "nop" -> 1
                else -> 0
            }
        }
    }
}
