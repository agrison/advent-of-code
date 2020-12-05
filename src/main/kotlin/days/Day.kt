package days

import util.InputReader
import java.lang.Integer.parseInt

abstract class Day(dayNumber: Int) {

    // Input feeders
    protected val inputList: List<String> by lazy { InputReader.inputAsList(dayNumber) }
    protected val inputString: String by lazy { InputReader.inputAsString(dayNumber) }
    protected val inputInts: List<Int> by lazy { InputReader.inputAsInts(dayNumber) }
    protected val inputAsVavrStrings: io.vavr.collection.List<String> by lazy { InputReader.inputAsVavrStrings(dayNumber) }
    protected val inputAsVavrInts: io.vavr.collection.List<Int> by lazy { InputReader.inputAsVavrInts(dayNumber) }

    abstract fun partOne(): Any

    abstract fun partTwo(): Any

    abstract fun title(): String

    // Extensions
    // ArrowKt
    fun arrow.core.Tuple2<Int, Int>.sum(): Int = this.a + this.b
    fun arrow.core.Tuple2<Int, Int>.mul(): Int = this.a * this.b
    fun arrow.core.Tuple3<Int, Int, Int>.sum(): Int = this.a + this.b + this.c
    fun arrow.core.Tuple3<Int, Int, Int>.mul(): Int = this.a * this.b * this.c

    // Vavr
    fun io.vavr.collection.List<Int>.sumValues(): Int = this.reduce(Integer::sum)
    fun io.vavr.collection.List<Int>.mulValues(): Int = this.reduce(Math::multiplyExact)

    // core types
    fun String.occurrences(c: Char) = this.count { it == c }
    operator fun Char.plus(c: Char) = this.toString() + c
    fun Char.eq(c: Char) = this == c
    fun IntRange.includes(vararg ints: Int) = ints.all(this::contains)
    fun String.at(pos: Int) = this[pos % this.length]
    fun String.at(pos: Int, c: Char) = this.at(pos) == c
    fun Boolean.toInt() = if (this) 1 else 0
    fun List<Int>.multiply() = this.reduce { a, b -> a * b }
    fun List<Long>.multiply() = this.reduce { a, b -> a * b }
    fun Int.divisible(other: Int) = this % other == 0
    fun Long.divisible(other: Long) = this % other == 0L
    fun String.containsAll(vararg strs: String) = strs.map { this.contains(it) }.fold(true) { a, b -> a && b }
    fun String.field(s: String): String = runCatching { this.substring(this.indexOf(s) + s.length + 1).split(" ")[0] }.getOrDefault("")
    fun String.intField(s: String, remove: String = "@"): Int = this.replace(remove, "").field(s).int()
    fun String.int() = runCatching { Integer.parseInt(this.replace("""[^\d]""", "")) }.getOrDefault(0)
    fun Int.`in`(i: IntRange) = (i.first..(i.last + 1)).contains(this)
    fun String.regex() = this.toRegex()
    fun String.matches(s: String) = this.matches(s.regex())
    fun String.`in`(vararg strs: String) = strs.contains(this)
    operator fun String.times(i: Int) = this.repeat(i)
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
    fun String.toInt(radix: Int) = parseInt(this, radix)
    fun String.binary() = parseInt(this, 2)
    fun <T> Collection<T>.contains(vararg e: T) = this.containsAll(e.toList())

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
