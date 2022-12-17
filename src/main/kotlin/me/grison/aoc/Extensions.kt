/**
 * This file contains useful Kotlin extensions to deal with
 * various problems that we need to solve during Advent Of Code
 *
 * Including String, Ints, Longs, Iterable & Collections, Ranges, Pairs, Complex, ...
 *
 * These extensions are used in all the different years solutions.
 */
package me.grison.aoc

import space.kscience.kmath.complex.Complex
import util.CYAN
import util.RESET
import java.lang.Integer.max
import java.lang.Integer.min
import java.math.BigInteger
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.ceil

// core types
// Strings


/*
 219
 398
 ->
 {(0,0)=2, (0,1)=1, (0,2)=9
  (1,0)=3, (1,1)=9, (1,2)=8}
 */

typealias Grid<T> = MutableMap<Position, T>

fun List<String>.intGrid(default: Int, size: Int): Grid<Int> {
    val grid = HashMap<Position, Int>(size).withDefault { default }
    var height = 0
    forEach { line ->
        var width = 0
        line.normalSplit("").ints().forEach { n ->
            grid[p(height, width++)] = n
        }
        height++
    }
    return grid
}

fun List<String>.intGrid(default: Int): Grid<Int> {
    val grid = mutableMapOf<Position, Int>().withDefault { default }
    var height = 0
    forEach { line ->
        var width = 0
        line.normalSplit("").ints().forEach { n ->
            grid[p(height, width++)] = n
        }
        height++
    }
    return grid
}

fun List<String>.stringGrid(default: String): Grid<String> {
    val grid = mutableMapOf<Position, String>().withDefault { default }
    var height = 0
    forEach { line ->
        var width = 0
        line.normalSplit("").forEach { n ->
            grid[p(height, width++)] = n
        }
        height++
    }
    return grid
}

fun <T> MutableList<T>.append(t: T): MutableList<T> {
    this.add(t)
    return this
}

// Integers & Long
/** Returns whether this is divisible by `other`. */
fun Int.divisible(other: Int) = this % other == 0

/** Returns whether this is in the given range. */
fun Int.`in`(i: IntRange) = (i.first..(i.last + 1)).contains(this)

/** Returns whether this is divisible by `other`. */
fun Long.divisible(other: Long) = this % other == 0L

fun Int.odd() = this % 2 == 1
fun Int.even() = this % 2 == 0
fun Int.zero() = this == 0
fun Int.pos() = this >= 0
fun Int.neg() = this < 0

// Complex
/** Returns the manhattan distance of this Complex. */
fun Complex.manhattan() = (abs(this.re) + abs(this.im)).toInt()
val Double.j: Complex
    get() = Complex(0, this)
val Int.j: Complex
    get() = Complex(0, this)
val Double.r: Complex
    get() = Complex(this, 0)
val Int.r: Complex
    get() = Complex(this, 0)

operator fun Complex.times(c: Complex) =
    Complex(this.re * c.re - this.im * c.im, this.re * c.im + this.im * c.re)

operator fun Complex.plus(c: Complex) =
    Complex(this.re + c.re, this.im + c.im)

// Booleans
/** Returns `1` if `true`, `0` otherwise. */
fun Boolean.toInt() = if (this) 1 else 0

/** Returns `1` if `true`, `0` otherwise. */
fun Boolean.toLong() = if (this) 1L else 0L

fun Int.sign() = kotlin.math.sign(this.toDouble()).toInt()

// Characters
/** `+` operator for characters. */
operator fun Char.plus(c: Char) = toString() + c

/** Returns whether this equals `c`. */
fun Char.eq(c: Char) = this == c

//Ranges
/** Returns whether this range includes all the given `ints`. */
fun IntRange.includes(vararg ints: Int) = ints.all(this::contains)


// Sequence
fun <T : Any> cycle(vararg xs: T): Sequence<T> {
    var i = 0
    return generateSequence { xs[i++ % xs.size] }
}

fun <T : Any> cycle(xs: List<T>): Sequence<T> {
    var i = 0
    return generateSequence { xs[i++ % xs.size] }
}

// Pairs
/** Difference between an ordered Pair. */
fun Pair<Int, Int>.difference() = second - first

/** Whether the difference between an ordered Pair is `i`. */
fun Pair<Int, Int>.differenceIs(i: Int) = difference() == i

/** Difference between an ordered Pair. */
fun Pair<Long, Long>.difference() = (second - first).absoluteValue

/** Whether the difference between an ordered Pair is `i`. */
fun Pair<Long, Long>.differenceIs(i: Long) = difference() == i

/** Returns the sum of the elements in this Pair. */
fun Pair<Int, Int>.sum() = first + second

/** Returns the product of the elements in this Pair. */
fun Pair<Int, Int>.multiply() = first * second

/** Returns the product of the elements in this Pair. */
fun Pair<Int, Int>.product() = first * second

/** `+` operator for Pairs. */
operator fun Pair<Int, Int>.plus(p: Pair<Int, Int>): Pair<Int, Int> = Pair(first + p.first, second + p.second)
//operator fun Pair<Long, Long>.plus(p: Pair<Long, Long>): Pair<Long, Long> = Pair(first + p.first, second + p.second)
fun Pair<Int, Int>.plus(a: Int, b: Int): Pair<Int, Int> = Pair(first + a, second + b)

/** `-` operator for Pairs. */
operator fun Pair<Int, Int>.minus(p: Pair<Int, Int>): Pair<Int, Int> = Pair(first - p.first, second - p.second)

/** `*` operator for Pairs. */
operator fun Pair<Int, Int>.times(p: Pair<Int, Int>): Pair<Int, Int> = Pair(first * p.first, second * p.second)/** `*` operator for Pairs. */
//operator fun Pair<Long, Long>.times(p: Pair<Long, Long>): Pair<Long, Long> = Pair(first * p.first, second * p.second)

fun bools() = mutableListOf<Boolean>()
fun ints() = mutableListOf<Int>()

fun directions() = listOf(p(-1, 0), p(1, 0), p(0, -1), p(0, 1))

fun Pair<Int, Int>.above() = this.plus(p(0, -1))
fun Pair<Int, Int>.below() = this.plus(p(0, 1))
fun Pair<Int, Int>.left() = this.plus(p(-1, 0))
fun Pair<Int, Int>.right() = this.plus(p(1, 0))
fun Pair<Int, Int>.up(i: Int) = this.plus(p(0, -i))
fun Pair<Int, Int>.down(i: Int) = this.plus(p(0, i))
fun Pair<Int, Int>.forward(i: Int) = this.plus(p(i, 0))
fun Pair<Int, Int>.forward(i: Int, aim: Int) = this.plus(p(i, aim))
fun Pair<Int, Int>.backward(i: Int) = this.plus(p(-i, 0))
fun Pair<Int, Int>.within(minx: Int, miny: Int, maxx: Int, maxy: Int) = first in minx..maxx && second in miny..maxy
fun Pair<Int, Int>.directions() = listOf(above(), below(), left(), right())
fun Pair<Int, Int>.neighbors(self: Boolean = false): List<Position> {
    val neighbors = listOf(
        plus(-1, -1), plus(-1, 0), plus(-1, 1),
        plus(0, -1), this, plus(0, 1),
        plus(1, -1), plus(1, 0), plus(1, 1)
    )
    return if (self) neighbors else neighbors.reject { it == this }
}
fun Pair<Int, Int>.abs() = p(abs(first), abs(second))
fun Pair<Int, Int>.max() = kotlin.math.max(first, second)
fun Pair<Int, Int>.min() = kotlin.math.min(first, second)
fun <T, U> Pair<T, U>.first(t: T) = p(t, second)
fun <T, U> Pair<T, U>.second(u: U) = p(first, u)
fun movements() = mapOf("R" to p(0, 1), "L" to p(0, -1), "D" to p(1, 0), "U" to p(-1, 0))

// return an increasing range
fun Pair<Int, Int>.range() = min(first, second)..max(first, second)

// compute the slope of a pair representing two X or two Y (derivative)
fun Pair<Int, Int>.slope() = when {
    first == second -> 0
    first > second -> -1
    else -> 1
}

fun Pair<Int, Int>.pivotSecond(loc: Int) = if (second < loc) this else p(first, loc - (second - loc))
fun Pair<Int, Int>.pivotFirst(loc: Int) = if (first < loc) this else p(loc - (first - loc), second)

fun Pair<Long, Long>.pivotSecond(loc: Long) = if (second < loc) this else Pair(first, loc - (second - loc))
fun Pair<Long, Long>.pivotFirst(loc: Long) = if (first < loc) this else Pair(loc - (first - loc), second)

fun Triple<Int, Int, Int>.sum() = first + second + third
fun Triple<Long, Long, Long>.sum() = first + second + third

// works with MutableMap.withDefault()
fun <T> MutableMap<T, Int>.increase(key: T, amount: Int = 1): MutableMap<T, Int> {
    this[key] = this.getOrDefault(key, 0) + amount
    return this
}

fun <T> MutableMap<T, Long>.increase(key: T, amount: Long = 1L): MutableMap<T, Long> {
    this[key] = this.getOrDefault(key, 0L) + amount
    return this
}

fun <T> MutableMap<T, BigInteger>.increase(key: T, amount: BigInteger = BigInteger.ONE): MutableMap<T, BigInteger> {
    this[key] = this.getOrDefault(key, BigInteger.ZERO).add(amount)
    return this
}

fun <T> List<T>.copy() = this.toMutableList()
fun <T> List<T>.update(i: Int, value: T): List<T> {
    val copy = this.toMutableList()
    copy[i] = value
    return copy
}

fun <T> List<T>.combinations(k: Int) = io.vavr.collection.Vector.ofAll(this)
    .combinations(k).map {
        it.toJavaList() as List<T>
    }.toJavaList() as List<List<T>>

fun <T> List<T>.uniqueCombinations(k: Int) = this.combinations(k).toSet()


fun combinations2(lo: Int, hi: Int) = sequence {
    for (i in lo..hi)
        for (j in lo..hi)
            yield(Pair(i, j))
}

fun combinations3(lo: Int, hi: Int) = sequence {
    for (i in lo..hi)
        for (j in lo..hi)
            for (k in lo..hi)
                yield(Triple(i, j, k))
}

/** Returns the sum of the elements in this Pair. */
fun Pair<Long, Long>.sum() = first + second

/** Returns the product of the elements in this Pair. */
fun Pair<Long, Long>.multiply() = first * second

/** Shortcut for creating a Pair. */
fun <T, U> p(t: T, u: U): Pair<T, U> = Pair(t, u)
fun pd(t: Double, u: Double): Pair<Double, Double> = Pair(t, u)

/** Returns the manhattan distance for this Pair. */
fun Pair<Int, Int>.manhattan() = first.absoluteValue + second.absoluteValue

/** Swap a Pair. */
fun <T, U> Pair<T, U>.swap() = p(second, first)

fun Pair<Double, Double>.distance() = ceil(abs(first) + kotlin.math.max(0.0, abs(second) - abs(first) / 2))


// ArrowKt
fun arrow.core.Tuple2<Int, Int>.sum(): Int = a + b
fun arrow.core.Tuple2<Int, Int>.mul(): Int = a * b
fun arrow.core.Tuple3<Int, Int, Int>.sum(): Int = a + b + c
fun arrow.core.Tuple3<Int, Int, Int>.mul(): Int = a * b * c


class Memoize1<in T, out R>(val f: (T) -> R) : (T) -> R {
    private val values = mutableMapOf<T, R>()
    override fun invoke(x: T): R {
        return values.getOrPut(x, { f(x) })
    }
}

fun <T, R> ((T) -> R).memoize(): (T) -> R = Memoize1(this)


fun <T> List<List<T>>.swapRowCols() = flatMap { it.withIndex() }
    .groupBy({ (i, _) -> i }, { (_, v) -> v })
    .map { (_, v) -> v.reversed() }

fun List<List<List<Boolean>>>.flattenGrid() = flatMap { it.mapIndexed { i, list -> IndexedValue(i, list) } }
    .groupBy({ (i, _) -> i }, { (_, v) -> v })
    .map { (_, v) -> v.reduce { acc, list -> acc + list } }

fun intSeq() = generateSequence(1) { it + 1 }
fun intSeq(x: Int) = generateSequence(x) { it + 1 }

typealias HashBag<T> = MutableMap<T, Int>

fun <T> hashBag() = mutableMapOf<T, Int>().withDefault { 0 }
typealias LongHashBag<T> = MutableMap<T, Int>

fun <T> longHashBag() = mutableMapOf<T, Long>().withDefault { 0L }

fun <T> bigIntHashBag() = mutableMapOf<T, BigInteger>().withDefault { BigInteger.ZERO }

typealias Position = Pair<Int, Int>
typealias LPosition = Pair<Long, Long>

fun Position.manhattan(other: Position) = abs(first - other.first) + abs(second - other.second)
fun LPosition.manhattan(other: LPosition) = abs(first - other.first) + abs(second - other.second)

fun <T> makeList(size: Int, t: T): MutableList<T> {
    val list = mutableListOf<T>()
    for (i in 0.until(size)) {
        list.add(t)
    }
    return list
}

fun Iterable<Int>.sumLong(): Long = this.fold(0L, Long::plus)

fun Array<Long>.increase(i: Int, amount: Long = 1): Array<Long> {
    this[i] += amount
    return this
}

fun gridPositions(dimensions: Pair<Int, Int>) = gridPositions(dimensions.first, dimensions.second)
fun gridPositions(height: Int, width: Int) = (0.until(height)).flatMap { y -> (0.until(width)).map { x -> p(y, x) } }


fun Iterable<Position>.pointsDisplay(empty: String = " "): String {
    val (maxX, maxY) = p(maxOf { it.first }, maxOf { it.second })
    val display = arrayListOf<List<String>>()
    for (y in 0..maxY) {
        display.add((0..maxX).map { x -> if (p(x, y) in this) "$CYAN#$RESET" else empty })
    }
    return display.joinToString("\n") { it.joinToString("") }
}

//fun Iterable<Pair<Long, Long>>.pointsDisplay(empty: String = " "): String {
//    val (maxX, maxY) = p(maxOf { it.first }, maxOf { it.second })
//    val display = arrayListOf<List<String>>()
//    for (y in 0..maxY) {
//        display.add((0..maxX).map { x -> if (p(x, y) in this) "$CYAN#$RESET" else empty })
//    }
//    return display.joinToString("\n") { it.joinToString("") }
//}

fun Collection<Int>.toRange() = IntRange(this.first(), this.last())
fun Collection<Long>.toRange() = LongRange(this.first(), this.last())
fun Pair<Int, Int>.toRange() = IntRange(this.first, this.second)
fun Pair<Long, Long>.toRange() = LongRange(this.first, this.second)

operator fun IntRange.contains(range: IntRange) = this.first <= range.first && this.last >= range.last
fun IntRange.overlap(range: IntRange) = range.first in this || this.first in range

fun Collection<Int>.range() = maxOrNull()!! - minOrNull()!!
fun Collection<Long>.range() = maxOrNull()!! - minOrNull()!!
fun Collection<BigInteger>.range() = maxOrNull()!!.minus(minOrNull()!!)

fun <T> Map<T, Long>.frequency() = longHashBag<T>().let { hash ->
    this.forEach { (k, v) -> hash.increase(k, v) }
    hash
}

fun <T, U> Map<T, Long>.frequency(selector: (T) -> U) = longHashBag<U>().let { hash ->
    this.forEach { (k, v) -> hash.increase(selector(k), v) }
    hash
}

fun <T> Collection<T>.frequency() = bigIntHashBag<T>().let { hash ->
    this.forEach { c -> hash.increase(c) }
    hash
}

fun Int.toBinary(length: Int? = null): String {
    return if (length == null) this.toString(2)
    else Integer.toBinaryString((1 shl length) or this).substring(1)
}

fun Int.padLeft(length: Int, value: Char): String {
    return this.toString().padStart(length, value)
}

fun Char.int() = this.toString().toInt()

fun <T,U,V> t(t: T, u: U, v: V) = Triple(t, u, v)

fun Position.sign() = p(first.sign(), second.sign())

fun Position.x() = first
fun Position.y() = second

fun List<Int>.min() = minByOrNull { it }!!
fun Iterable<Position>.minX() = map {it.first}.min()
fun Iterable<Position>.minY() = map {it.second}.min()

fun List<Int>.max() = maxByOrNull { it }!!
fun Iterable<Position>.maxX() = map {it.first}.max()
fun Iterable<Position>.maxY() = map {it.second}.max()

fun List<Long>.max() = maxByOrNull { it }!!

fun <T> List<T>.peek(index: Int, sideEffect: (T) -> Unit) : List<T> {
    sideEffect.invoke(this[index])
    return this;
}
