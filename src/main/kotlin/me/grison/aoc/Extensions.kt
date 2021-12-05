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
import space.kscience.kmath.complex.r
import java.lang.Integer.max
import java.lang.Integer.min
import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream
import kotlin.collections.ArrayDeque
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.ceil

// core types
// Strings

/** Returns the occurrences of `c`. */
fun String.occurrences(c: Char) = count { it == c }
/** Returns safely `this[pos]`. */
fun String.at(pos: Int) = this[pos % length]
/** Returns whether `this.at(pos) == c`. */
fun String.at(pos: Int, c: Char) = at(pos) == c
/** Returns whether this string contains all the given `strs`. */
fun String.containsAll(vararg strs: String) = strs.map { contains(it) }.fold(true) { a, b -> a && b }
fun String.field(s: String): String =
    runCatching { substring(indexOf(s) + s.length + 1).split(" ")[0] }.getOrDefault("")

fun String.intField(s: String, remove: String = "@"): Int = replace(remove, "").field(s).int()
fun String.int() = runCatching { Integer.parseInt(replace("""[^\d]""", "")) }.getOrDefault(0)
/** Returns a regex from this string. */
fun String.regex() = toRegex()
/** Returns the string without the last character. */
fun String.matches(s: String) = matches(s.regex())
/** Returns whether this string is in the given `strs`. */
fun String.`in`(vararg strs: String) = strs.contains(this)
/** Returns the Int representation of this string. */
fun String.toInt(radix: Int) = Integer.parseInt(this, radix)
/** Returns the binary representation of this string. */
fun String.binary() = Integer.parseInt(this, 2)
/** Returns the Long representation of this string. */
fun String.binaryLong() = java.lang.Long.parseLong(this, 2)
/** Returns the set of characters in this string. */
fun String.charSet() = split("").toSet() - ""
/** Returns a `List<String>` representing the lines in this string. */
fun String.lines() = split(System.lineSeparator())
/** Returns a char array. */
fun String.split() = toCharArray()
/** Returns times operator for string (repeating). */
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

/** Returns the string without the last character. */
fun String.butLast() = substring(0, length - 1)
/** Returns the string between `left` and `right`. */
fun String.between(left: String, right: String) = split(left)[1].split(right).first()
/** Returns the string after the last `s`. */
fun String.afterLast(s: String) = split(s).last()
/** Returns all int found in this string. */
fun String.allInts(): List<Int> = "(\\d+)".regex().findAll(this).map { it.value.toInt() }.toList()
/** Returns all long found in this string. */
fun String.allLongs(): List<Long> = "(\\d+)".regex().findAll(this).map { it.value.toLong() }.toList()
/** Returns like toCharArray() but with strings. */
fun String.stringList(): List<String> = map { it.toString() }
/** Returns the string without any spaces. */
fun String.noSpaces() = replace("\\s+".regex(), "")
/** Returns like Java `split()` because it's a better `split()`. */
fun String.normalSplit(delim: String) = split(delim).filter { it != "" }
/** Returns the string without all occurrences of `str`. */
fun String.except(str: String) = replace(str, "")
/** Returns the string without all occurrences of `c`. */
fun String.except(c: Char) = replace(c + "", "")
/** Returns the string before `str`. */
fun String.before(str: String) = split(str)[0]
/** Returns the string after `str`. */
fun String.after(str: String) = split(str)[1]
/** Returns `eq` if this == `eq`, `or` otherwise. */
fun String.or(eq: String, or: String?) = if (this == eq) this else or
/** Returns the string where line separators are replaced by `sep` (default empty string). */
fun String.oneLine(sep: String = "") = replace(System.lineSeparator(), sep)

fun String.`is`(s: String) = this == s
fun String.upper() = toUpperCase()
fun String.lower() = toLowerCase()

// Integers & Long
/** Returns whether this is divisible by `other`. */
fun Int.divisible(other: Int) = this % other == 0
/** Returns whether this is in the given range. */
fun Int.`in`(i: IntRange) = (i.first..(i.last + 1)).contains(this)
/** Returns whether this is divisible by `other`. */
fun Long.divisible(other: Long) = this % other == 0L

fun Int.odd() = this % 2 == 1
fun Int.even() = this % 2 == 0

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

// Characters
/** `+` operator for characters. */
operator fun Char.plus(c: Char) = toString() + c
/** Returns whether this equals `c`. */
fun Char.eq(c: Char) = this == c

//Ranges
/** Returns whether this range includes all the given `ints`. */
fun IntRange.includes(vararg ints: Int) = ints.all(this::contains)

// Collections
/** Returns the product of all elements in this Iterable. */
fun Iterable<Int>.multiply() = reduce { a, b -> a * b }
/** Returns the product of all elements in this Iterable. */
fun Iterable<Int>.product() = reduce { a, b -> a * b }
/** Returns the product of all elements in this Iterable. */
fun Iterable<Long>.multiply() = reduce { a, b -> a * b }
/** Returns the product of all elements in this Iterable. */
fun Iterable<Long>.product() = reduce { a, b -> a * b }
/** Returns whether this Collection contains all the given elements. */
fun <T> Collection<T>.contains(vararg e: T) = containsAll(e.toList())


fun <T> List<T>.first(num: Int) = this.subList(0, num)

fun <T> Collection<T>.head() : T? = if (this.isNotEmpty()) this.first() else null
fun <T> MutableCollection<T>.reset(coll: Collection<T>) : MutableCollection<T> {
    this.clear()
    this.addAll(coll)
    return this
}

/** `+` operator for sets. */
operator fun <T> MutableSet<T>.plus(e: T): MutableSet<T> {
    this.add(e)
    return this
}
/** `+` operator for lists. */
operator fun <T> MutableList<T>.plus(e: T): MutableList<T> {
    this.add(e)
    return this
}

/** Returns the cumulative sum of this Iterable. */
fun Iterable<Long>.cumSum() = this.scan(0L) { a, b -> a + b }
/** Returns the Iterable without the given element. */
fun <T> Iterable<T>.except(filter: T) = filter { it != filter }
/** Make a string of this Iterable, separator is an empty string. */
fun <T> Iterable<T>.join() = joinToString("")
/** Returns the Iterable without the last element. */
fun <T> List<T>.butLast() = dropLast(1)
/** Returns the transposed list of list. */
fun <T> List<List<T>>.transpose(): List<List<T>> {
    val N = this.stream().mapToInt { l: List<T> -> l.size }.max().orElse(-1)
    val iterList = this.stream().map { it.iterator() }.collect(Collectors.toList())
    return IntStream.range(0, N)
        .mapToObj { _ ->
            iterList.stream()
                .filter { it.hasNext() }
                .map { m: Iterator<T> -> m.next() }
                .collect(Collectors.toList())
        }
        .collect(Collectors.toList())
}
/** Make an ArrayDeque representing this Collection. */
fun <T> Collection<T>.deque() = ArrayDeque(this)
/** Returns the Iterable without the last element. */
fun <T> ArrayDeque<T>.addLast(vararg e: T) : ArrayDeque<T> {
    for (ee in e) { this.addLast(ee) }
    return this
}
/** `+` operator for a Deque and a new element. */
operator fun <T> ArrayDeque<T>.plus(e: T) = addLast(e)
/** Alias for `removeFirst`. */
fun <T> ArrayDeque<T>.shift() = removeFirst()
fun <T> ArrayDeque<T>.pop() = removeLast()

/** Returns the Collection without its first element. */
fun <T> Collection<T>.tail() = drop(1)
/** Transforms a Collection of String to a Collection of Int. */
fun Collection<String>.ints() = map { it.toInt() }
/** Transforms a Collection of String to a Collection of Long. */
fun Collection<String>.longs() = map { it.toLong() }
/** Alias for subList. */
operator fun <T> Collection<T>.get(x: Int, y: Int) = this.filterIndexed { i, _ -> i in x until y }
/** Alias for subList. */
operator fun <T> Collection<T>.get(r: IntRange) = this.filterIndexed { i, _ -> i in r }
/** Take the two first elements and make a Pair of it. */
fun <T> Iterable<T>.pair() = iterator().let {
    p(it.next(), it.next())
}
operator fun <T> List<T>.component6() = this[5]
operator fun <T> List<T>.component7() = this[6]
operator fun <T> List<T>.component8() = this[7]
operator fun <T> List<T>.component9() = this[8]
operator fun <T> List<T>.component10() = this[9]

fun <T> Collection<T>.join(sep: String, left: String ="", right: String = "") = joinToString(sep, left, right)

// Sequence
fun <T : Any> cycle(vararg xs: T): Sequence<T> {
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
fun Pair<Int, Int>.plus(a: Int, b: Int): Pair<Int, Int> = Pair(first + a, second + b)
/** `-` operator for Pairs. */
operator fun Pair<Int, Int>.minus(p: Pair<Int, Int>): Pair<Int, Int> = Pair(first - p.first, second - p.second)
/** `*` operator for Pairs. */
operator fun Pair<Int, Int>.times(p: Pair<Int, Int>): Pair<Int, Int> = Pair(first * p.first, second * p.second)

fun Pair<Int, Int>.up(i: Int) = this.plus(p(0, -i))
fun Pair<Int, Int>.down(i: Int) = this.plus(p(0, i))
fun Pair<Int, Int>.forward(i: Int) = this.plus(p(i, 0))
fun Pair<Int, Int>.forward(i: Int, aim: Int) = this.plus(p(i, aim))
fun Pair<Int, Int>.backward(i: Int) = this.plus(p(-i, 0))
// return an increasing range
fun Pair<Int, Int>.range() = min(first, second)..max(first, second)
// compute the slope of a pair representing two X or two Y (derivative)
fun Pair<Int, Int>.slope() = when {
    first == second -> 0
    first > second -> -1
    else -> 1
}

// works with MutableMap.withDefault()
fun <T> MutableMap<T, Int>.increase(key: T, amount: Int = 1): MutableMap<T, Int> {
    this[key] = this.getValue(key) + amount
    return this
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

fun Pair<Double, Double>.distance() = ceil(abs(first) + kotlin.math.max(0.0, abs(second)-abs(first)/2))

// Stack
fun <T> Stack<T>.popIf(t: T): T? = if (last() == t) pop() else null
fun <T> Stack<T>.lastIs(t: T): Boolean = last() == t


// ArrowKt
fun arrow.core.Tuple2<Int, Int>.sum(): Int = a + b
fun arrow.core.Tuple2<Int, Int>.mul(): Int = a * b
fun arrow.core.Tuple3<Int, Int, Int>.sum(): Int = a + b + c
fun arrow.core.Tuple3<Int, Int, Int>.mul(): Int = a * b * c

// Vavr
fun io.vavr.collection.List<Int>.sumValues(): Int = this.reduce(Integer::sum)
fun io.vavr.collection.List<Int>.mulValues(): Int = this.reduce(Math::multiplyExact)

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