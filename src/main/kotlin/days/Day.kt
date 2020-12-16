package days

import util.InputReader
import java.lang.Integer.parseInt
import java.lang.Long.parseLong
import java.lang.System.lineSeparator
import kotlin.math.absoluteValue

typealias Instr = String
typealias Program = List<Instr>

abstract class Day(dayNumber: Int) {

    // Input feeders
    protected val inputList: List<String> by lazy { InputReader.inputAsList(dayNumber) }
    protected val program: List<String> by lazy { InputReader.inputAsList(dayNumber) }
    protected val inputSet: Set<String> by lazy { InputReader.inputAsSet(dayNumber) }
    protected val inputString: String by lazy { InputReader.inputAsString(dayNumber) }
    protected val inputAsVavrStrings: io.vavr.collection.List<String> by lazy { InputReader.inputAsVavrStrings(dayNumber) }
    protected val inputAsVavrInts: io.vavr.collection.List<Int> by lazy { InputReader.inputAsVavrInts(dayNumber) }
    protected val inputInts: List<Int> by lazy { InputReader.inputAsString(dayNumber).map {it.toInt()} }

    abstract fun partOne(): Any?

    abstract fun partTwo(): Any?

    abstract fun title(): String

    // Extensions
    // ArrowKt
    fun arrow.core.Tuple2<Int, Int>.sum(): Int = a + b
    fun arrow.core.Tuple2<Int, Int>.mul(): Int = a * b
    fun arrow.core.Tuple3<Int, Int, Int>.sum(): Int = a + b + c
    fun arrow.core.Tuple3<Int, Int, Int>.mul(): Int = a * b * c

    // Vavr
    fun io.vavr.collection.List<Int>.sumValues(): Int = this.reduce(Integer::sum)
    fun io.vavr.collection.List<Int>.mulValues(): Int = this.reduce(Math::multiplyExact)

    // core types
    // Strings
    fun String.occurrences(c: Char) = count { it == c }
    fun String.at(pos: Int) = this[pos % length]
    fun String.at(pos: Int, c: Char) = at(pos) == c
    fun String.containsAll(vararg strs: String) = strs.map { contains(it) }.fold(true) { a, b -> a && b }
    fun String.field(s: String): String = runCatching { substring(indexOf(s) + s.length + 1).split(" ")[0] }.getOrDefault("")
    fun String.intField(s: String, remove: String = "@"): Int = replace(remove, "").field(s).int()
    fun String.int() = runCatching { parseInt(replace("""[^\d]""", "")) }.getOrDefault(0)
    fun String.regex() = toRegex()
    fun String.matches(s: String) = matches(s.regex())
    fun String.`in`(vararg strs: String) = strs.contains(this)
    fun String.toInt(radix: Int) = parseInt(this, radix)
    fun String.binary() = parseInt(this, 2)
    fun String.binaryLong() = parseLong(this, 2)
    fun String.charSet() = split("").toSet() - ""
    fun String.lines() = split(lineSeparator())
    fun String.split() = toCharArray()
    operator fun String.times(i: Int) = repeat(i)
    fun String.replacing(m: Map<Char, Char>): String {
        var s = this
        m.forEach { s = s.replace(it.key, it.value) }
        return s
    }
    fun String.replacingRegex(m: Map<String, String>): String {
        var s = this
        m.forEach { s = s.replace(it.key.regex(), it.value) }
        return s
    }
    fun String.butLast() = substring(0, length - 1)
    fun String.between(left: String, right: String) = split(left)[1].split(right).first()
    fun String.afterLast(s: String) = split(s).last()
    fun String.allInts() : List<Int> = "(\\d+)".regex().findAll(this).map { it.value.toInt() }.toList()
    fun String.allLongs() : List<Long> = "(\\d+)".regex().findAll(this).map { it.value.toLong() }.toList()

    // Integers & Long
    fun Int.divisible(other: Int) = this % other == 0
    fun Int.`in`(i: IntRange) = (i.first..(i.last + 1)).contains(this)
    fun Long.divisible(other: Long) = this % other == 0L

    // Booleans
    fun Boolean.toInt() = if (this) 1 else 0

    // Characters
    operator fun Char.plus(c: Char) = toString() + c
    fun Char.eq(c: Char) = this == c

    //Ranges
    fun IntRange.includes(vararg ints: Int) = ints.all(this::contains)

    // Collections
    fun List<Int>.multiply() = reduce { a, b -> a * b }
    fun List<Long>.multiply() = reduce { a, b -> a * b }
    fun <T> Collection<T>.contains(vararg e: T) = containsAll(e.toList())
    operator fun <T> MutableSet<T>.plus(e: T): MutableSet<T> {
        this.add(e)
        return this
    }
    operator fun <T> MutableList<T>.plus(e: T): MutableList<T> {
        this.add(e)
        return this
    }
    fun List<Long>.cumSum() = scan(0L) { a, b -> a + b}

    // Sequence
    fun <T : Any> cycle(vararg xs: T): Sequence<T> {
        var i = 0
        return generateSequence { xs[i++ % xs.size] }
    }

    // Pairs
    fun Pair<Int, Int>.difference() = second - first
    fun Pair<Int, Int>.differenceIs(i: Int) = difference() == i
    fun Pair<Long, Long>.difference() = (second - first).absoluteValue
    fun Pair<Long, Long>.differenceIs(i: Long) = difference() == i
    fun Pair<Int, Int>.sum() = first + second
    fun Pair<Int, Int>.multiply() = first * second
    operator fun Pair<Int, Int>.plus(p: Pair<Int, Int>): Pair<Int, Int> = Pair(first + p.first, second + p.second)
    operator fun Pair<Int, Int>.times(p: Pair<Int, Int>): Pair<Int, Int> = Pair(first * p.first, second * p.second)
    fun Pair<Long, Long>.sum() = first + second
    fun Pair<Long, Long>.multiply() = first * second
    fun <T,U> p(t: T, u: U) :Pair<T, U> = Pair(t, u)
    fun Pair<Int, Int>.manhattan() = first.absoluteValue + second.absoluteValue

    // Instructions
    abstract class Execution(val output: Int)
    data class InfiniteLoop(val acc: Int) : Execution(acc)
    data class Success(val acc: Int) : Execution(acc)

    fun Instr.op(): String = split(" ")[0]
    fun Instr.arg(): Int = parseInt(split(" ")[1])
    fun Instr.switchOp(from: String, to: String) = replace(from, to)
    fun Instr.swap(from: String, to: String) = when(op()) {
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
            println(this.substring(0, at % this.length) + (if (this.at(at, '#')) red("#") else green("•"))
                    + (if ((at + 1) % this.length == 0) "" else this.substring((at + 1) % this.length)))
        return true
    }
}
