package me.grison.aoc.y2015

import me.grison.aoc.*

class Day23 : Day(23, 2015) {
    override fun title() = "Opening the Turing Lock"

    override fun partOne() =
        exec(mutableMapOf("a" to 0L, "b" to 0L))

    override fun partTwo() =
        exec(mutableMapOf("a" to 1L, "b" to 0L))

    private fun exec(regs: MutableMap<String, Long>): Long {
        var pc = 0
        while (pc < inputList.size) {
            val line = inputList[pc]
            val (token, arg, dst) = "$line _".normalSplit(" ")

            pc += when (token) {
                "hlf" -> {
                    regs[arg] = regs[arg]!! / 2L; 1
                }
                "tpl" -> {
                    regs[arg] = regs[arg]!! * 3L; 1
                }
                "inc" -> {
                    regs[arg] = regs[arg]!! + 1L; 1
                }
                "jmp" -> arg.toInt()
                "jie" -> if (regs[arg.butLast()]!! % 2L == 0L) dst.toInt()
                else 1
                "jio" -> if (regs[arg.butLast()]!! == 1L) dst.toInt()
                else 1
                else -> 0
            }
        }
        return regs["b"]!!
    }
}