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
    fun List<Int>.multiply()= this.reduce { a, b -> a * b }
    fun List<Long>.multiply()= this.reduce { a, b -> a * b }
    fun Int.divisible(other: Int) = this % other == 0
    fun Long.divisible(other: Long) = this % other == 0L
}
