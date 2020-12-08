package days

class Day08 : Day(8) {
    override fun title() = "Handheld Halting"

    override fun partOne() = when (val exec = program.run()) {
        is InfiniteLoop -> exec.output
        else -> -1
    }

    override fun partTwo(): Int =
            (0 until program.size - 1).map { program.exchange(it, "jmp", "nop").run() }
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

typealias Instr = String
typealias Program = List<Instr>

abstract class Execution(val output: Int)
data class InfiniteLoop(val acc: Int) : Execution(acc)
data class Success(val acc: Int) : Execution(acc)