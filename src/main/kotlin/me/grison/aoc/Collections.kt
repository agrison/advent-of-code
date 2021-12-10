package me.grison.aoc

import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream
import kotlin.collections.ArrayDeque

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

fun List<Int>.median(): Int {
    this.sorted().let { x ->
        return if (x.isEmpty()) ((x[x.size / 2] + x[x.size / 2 - 1])) / 2 else x[x.size / 2]
    }
}

fun List<Int>.mean(): Int {
    return this.sum() / this.size
}

fun <T> List<T>.permutations(): List<List<T>> {
    val p = io.vavr.collection.Vector.ofAll(this).permutations()
    val list = mutableListOf<List<T>>()
    p.forEach {
        val inner = mutableListOf<T>()
        it.forEach {
            inner.add(it)
        }
        list.add(inner)
    }
    return list
}


fun <T> List<T>.first(num: Int) = this.subList(0, num)

fun <T> Collection<T>.head(): T? = if (this.isNotEmpty()) this.first() else null
fun <T> MutableCollection<T>.reset(coll: Collection<T>): MutableCollection<T> {
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
fun <T> ArrayDeque<T>.addLast(vararg e: T): ArrayDeque<T> {
    for (ee in e) {
        this.addLast(ee)
    }
    return this
}

/** `+` operator for a Deque and a new element. */
operator fun <T> ArrayDeque<T>.plus(e: T) = addLast(e)

/** Alias for `removeFirst`. */
fun <T> ArrayDeque<T>.shift() = removeFirst()
fun <T> ArrayDeque<T>.pop() = removeLast()

/** Returns the Collection without its first element. */
fun <T> Iterable<T>.tail() = drop(1)

/** Transforms a Collection of String to a Collection of Int. */
fun Iterable<String>.ints() = map { it.toInt() }

/** Transforms a Collection of String to a Collection of Long. */
fun Iterable<String>.longs() = map { it.toLong() }

/** Alias for subList. */
operator fun <T> Iterable<T>.get(x: Int, y: Int) = this.filterIndexed { i, _ -> i in x until y }

/** Alias for subList. */
operator fun <T> Iterable<T>.get(r: IntRange) = this.filterIndexed { i, _ -> i in r }

fun <T> List<T>.middle() = this[this.size/2]

/** Take the two first elements and make a Pair of it. */
fun <T> Iterable<T>.pair() = iterator().let {
    p(it.next(), it.next())
}

fun <T> Iterable<T>.keep(predicate: (T) -> Boolean) = filter(predicate)
fun <T> Iterable<T>.reject(predicate: (T) -> Boolean) = filterNot(predicate)

operator fun <T> List<T>.component6() = this[5]
operator fun <T> List<T>.component7() = this[6]
operator fun <T> List<T>.component8() = this[7]
operator fun <T> List<T>.component9() = this[8]
operator fun <T> List<T>.component10() = this[9]

fun <T> Collection<T>.join(sep: String, left: String = "", right: String = "") = joinToString(sep, left, right)

// Stack
fun <T> Stack<T>.popIf(t: T): T? = if (last() == t) pop() else null
fun <T> Stack<T>.lastIs(t: T): Boolean = last() == t


// Vavr
fun io.vavr.collection.List<Int>.sumValues(): Int = this.reduce(Integer::sum)
fun io.vavr.collection.List<Int>.mulValues(): Int = this.reduce(Math::multiplyExact)