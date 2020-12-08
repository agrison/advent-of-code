package days

typealias Instr = String
typealias Instructions = List<Instr>

abstract class Execution
data class InfiniteLoop(val output: Int) : Execution()
data class Success(val output: Int) : Execution()

class Day08 : Day(8) {
    override fun title() = "Handheld Halting"

    override fun partOne() = when (val exec = runCode(inputList)) {
        is InfiniteLoop -> exec.output
        else -> -1
    }

    override fun partTwo(): Int {
        for (i in (0..inputList.size)) {
            val program = inputList.toMutableList()
            program[i] = program[i].exchange("jmp", "nop")
            when (val exec = runCode(program)) {
                is Success -> return exec.output
            }
        }
        return -1
    }

    private fun runCode(program: Instructions): Execution {
        var (pos, acc, visited) = Triple<Int, Int, MutableSet<Int>>(0, 0, mutableSetOf())
        while (true) {
            if (pos in visited) return InfiniteLoop(acc)
            else if (pos.atEnd(program)) return Success(acc)

            visited.add(pos)
            val instr = program[pos]
            pos += when (instr.op()) {
                "acc" -> { acc += instr.arg(); 1 }
                "jmp" -> instr.arg()
                "nop" -> 1
                else -> 0
            }
        }
    }
}