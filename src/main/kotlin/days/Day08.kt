package days

class Day08 : Day(8) {
    override fun title() = "Handheld Halting"

    override fun partOne() = when (val exec = program.run()) {
        is InfiniteLoop -> exec.output
        else -> -1
    }

    override fun partTwo(): Int =
            program.indices.map { program.exchange(it, "jmp", "nop").run() }
                    .first { it is Success }.output

    fun Program.run(): Execution {
        var (pos, acc, visited) = Triple<Int, Int, MutableSet<Int>>(0, 0, mutableSetOf())
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
