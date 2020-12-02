package days

import arrow.core.Tuple2
import arrow.core.Tuple3
import util.InputReader

abstract class Day(dayNumber: Int) {

    // Input feeders
    protected val inputList: List<String> by lazy { InputReader.inputAsList(dayNumber) }
    protected val inputString: String by lazy { InputReader.inputAsString(dayNumber) }
    protected val inputInts: List<Int> by lazy { InputReader.inputAsInts(dayNumber) }
    protected val inputAsVavrStrings: io.vavr.collection.List<String> by lazy { InputReader.inputAsVavrStrings(dayNumber) }
    protected val inputAsVavrInts: io.vavr.collection.List<Int> by lazy { InputReader.inputAsVavrInts(dayNumber) }

    abstract fun partOne(): Any

    abstract fun partTwo(): Any

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
    fun Boolean.toInt() = if (this) 1 else 0
    fun String.occurrences(c: Char) = this.count { it == c }
    operator fun Char.plus(c: Char) = this.toString() + c
    fun Char.eq(c: Char) = this == c
}
