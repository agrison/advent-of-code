package me.grison.aoc

import scientifik.kmath.operations.Complex
import util.InputReader
import java.lang.Integer.parseInt
import java.lang.Long.parseLong
import java.lang.System.lineSeparator
import java.math.BigInteger
import java.nio.charset.Charset
import java.security.MessageDigest
import java.util.*
import kotlin.math.abs
import kotlin.math.absoluteValue
import java.util.stream.Collectors

import java.util.stream.IntStream
import kotlin.collections.ArrayDeque


typealias Instr = String
typealias Program = List<Instr>

abstract class Day(val dayNumber: Int, val year: Int = 2020) {

    // Input feeders
    protected val inputList: List<String> by lazy { InputReader.inputAsList(dayNumber, year) }
    protected val inputGroups: List<String> by lazy { InputReader.inputAsGroups(dayNumber, year) }
    protected val program: List<String> by lazy { InputReader.inputAsList(dayNumber, year) }
    protected val inputSet: Set<String> by lazy { InputReader.inputAsSet(dayNumber, year) }
    protected val inputString: String by lazy { InputReader.inputAsString(dayNumber, year) }
    protected val inputAsVavrStrings: io.vavr.collection.List<String> by lazy {
        InputReader.inputAsVavrStrings(
            dayNumber,
            year
        )
    }
    protected val inputAsVavrInts: io.vavr.collection.List<Int> by lazy { InputReader.inputAsVavrInts(dayNumber, year) }
    protected val inputInts: List<Int> by lazy { InputReader.inputAsString(dayNumber, year).map { it.toInt() } }

    abstract fun partOne(): Any?

    abstract fun partTwo(): Any?

    abstract fun title(): String

    // Instructions
    abstract class Execution(val output: Int)
    data class InfiniteLoop(val acc: Int) : Execution(acc)
    data class Success(val acc: Int) : Execution(acc)

    fun Instr.op(): String = split(" ")[0]
    fun Instr.arg(): Int = parseInt(split(" ")[1])
    fun Instr.switchOp(from: String, to: String) = replace(from, to)
    fun Instr.swap(from: String, to: String) = when (op()) {
        from -> switchOp(from, to)
        to -> switchOp(to, from)
        else -> this
    }

    fun Program.swap(i: Int, from: String, to: String): Program {
        val l = this.toMutableList()
        l[i] = l[i].swap(from, to)
        return l
    }

    fun Program.isEnd(i: Int) = size == i


    // constants
    val alphabet = CharArray(26) { (it + 97).toChar() }.joinToString("")

    // colors for debugging graphical puzzles
    val ANSI_RESET = "\u001B[0m"
    val ANSI_BLACK = "\u001B[30m"
    val ANSI_RED = "\u001B[31m"
    val ANSI_GREEN = "\u001B[32m"
    val ANSI_YELLOW = "\u001B[33m"
    val ANSI_BLUE = "\u001B[34m"
    val ANSI_PURPLE = "\u001B[35m"
    val ANSI_CYAN = "\u001B[36m"
    val ANSI_WHITE = "\u001B[37m"
    fun blue(s: String) = "${ANSI_BLUE}${s}$ANSI_RESET"
    fun red(s: String) = "${ANSI_RED}${s}$ANSI_RESET"
    fun cyan(s: String) = "${ANSI_CYAN}${s}$ANSI_RESET"
    fun green(s: String) = "${ANSI_GREEN}${s}$ANSI_RESET"
    fun greenBg(s: String) = "\u001B[42m${ANSI_WHITE}${s}${ANSI_RESET}"
    fun redBg(s: String) = "\u001B[41m${ANSI_YELLOW}${s}${ANSI_RESET}"

    // specific to Day 03
    fun String.debug03(enabled: Boolean, at: Int): Boolean {
        if (enabled)
            println(
                this.substring(0, at % this.length) + (if (this.at(at, '#')) red("#") else green("•"))
                        + (if ((at + 1) % this.length == 0) "" else this.substring((at + 1) % this.length))
            )
        return true
    }



    fun md5(data: String) =
        MessageDigest.getInstance("MD5").let { m ->
            m.update(data.toByteArray(Charset.forName("UTF-8")))
            m.digest().toHex()
        }

    fun ByteArray.toHex() = String.format(
        "%0" + (this.size shl 1) + "x",
        BigInteger(1, this)
    )
}