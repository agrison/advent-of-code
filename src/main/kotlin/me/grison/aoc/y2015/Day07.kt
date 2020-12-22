package me.grison.aoc.y2015

import me.grison.aoc.Day

class Day07 : Day(7, 2015) {
    override fun title() = "Some Assembly Required"

    override fun partOne() : Int {
        //println(inputList.map { parse(it) })
        return 0
    }

    override fun partTwo() = 0

    val wires = mutableMapOf<String, Command>()

    private fun compute(op: String, a: Int, b: Int): Int {
        return when(op) {
            "AND" -> a and b
            "OR" -> a or b
            "NOT" -> -a - 1
            "LSHIFT" -> a shl b
            "RSHIFT" -> a shr b
            else -> 0
        }
    }

    private fun calculateWire(name: String): Int {
        val wire = wires.get(name)!!
        if (name.matches("^\\d+")) return name.toInt();
        //if (wire.matches)
        return 0
    }

    private fun parse(s: String) : Command{
        val (left, dest) = s.split("->")
        val command = left.replace("[^A-Z]".regex(), "")
        val args = left.replace("[A-Z]".regex(), "").trim().split("\\s+".regex())
        return Command(command.trim(), args.toList(), dest.trim())
    }

    data class Command(val command: String, val args: List<String>, val destination: String)
}