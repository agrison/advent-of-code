package me.grison.aoc

import java.math.BigInteger
import java.util.*

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

fun String.allLines() = split('\n')

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
fun String.allInts(includeNegative: Boolean = true): List<Int> =
    (if (includeNegative) "(-?\\d+)" else "(\\d+)").regex().findAll(this).map { it.value.toInt() }.toList()

fun String.allPositiveInts(): List<Int> = this.allInts(false)
/** Returns all long found in this string. */
fun String.allLongs(includeNegative: Boolean = true): List<Long> =
    (if (includeNegative) "(-?\\d+)" else "(\\d+)").regex().findAll(this).map { it.value.toLong() }.toList()

fun String.allBigIntegers(includeNegative: Boolean = true): List<BigInteger> =
    (if (includeNegative) "(-?\\d+)" else "(\\d+)").regex().findAll(this).map { it.value.toBigInteger() }.toList()

fun String.firstInt(includeNegative: Boolean = true) = allInts(includeNegative).first()
fun String.lastInt(includeNegative: Boolean = true) = allInts(includeNegative).last()

/** Returns like toCharArray() but with strings. */
fun String.stringList(): List<String> = map { it.toString() }

/** Returns like toCharArray() but with strings. */
fun String.list(): List<String> = map { it.toString() }

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

fun String.intRange() = this.normalSplit("-").let { IntRange(it[0].toInt(), it[1].toInt()) }
fun String.longRange() = this.normalSplit("-").let { LongRange(it[0].toLong(), it[1].toLong()) }

/** Returns the string after `str`. */
fun String.after(str: String) = split(str)[1]

/** Returns `eq` if this == `eq`, `or` otherwise. */
fun String.or(eq: String, or: String?) = if (this == eq) this else or

/** Returns the string where line separators are replaced by `sep` (default empty string). */
fun String.oneLine(sep: String = "") = replace(System.lineSeparator(), sep)

fun String.words() = normalSplit(" ")
fun String.set() = stringList().toSet()

fun String.`is`(s: String) = this == s
fun String.upper() = uppercase(Locale.getDefault())
fun String.lower() = lowercase(Locale.getDefault())

fun String.isLower() = all { it.isLowerCase() }
fun String.isUpper() = all { it.isUpperCase() }

fun String.intCommand() = command().let { p(it.first, it.second.int()) }
fun String.longCommand() = command().let { p(it.first, it.second.toLong()) }
fun String.command() = normalSplit(" ").last().split("=").pair()

fun String.frequency() =  hashBag<Char>().let { hash ->
    this.toCharArray().forEach { c -> hash.increase(c) }
    hash
}

fun String.subs(x: Int, y: Int) = substring(x, y)

operator fun String.get(x: Int, y: Int) = substring(x, y)

fun String.isAscii() = all { it.code <= 255 }

fun String.isDigits() = all { it.code in 48..57 }

fun String.split(at: Int) = listOf(this.take(at), this.drop(at))

fun String.inTwo() = this.split(this.length / 2)

fun String.remove(r: Regex) = this.replace(r, "")

operator fun String.get(r: IntRange) = this.substring(r)

fun foo() {
    "".replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}