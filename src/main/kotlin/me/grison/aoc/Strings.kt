package me.grison.aoc

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

/** Returns the string after `str`. */
fun String.after(str: String) = split(str)[1]

/** Returns `eq` if this == `eq`, `or` otherwise. */
fun String.or(eq: String, or: String?) = if (this == eq) this else or

/** Returns the string where line separators are replaced by `sep` (default empty string). */
fun String.oneLine(sep: String = "") = replace(System.lineSeparator(), sep)

fun String.words() = normalSplit(" ")
fun String.set() = stringList().toSet()

fun String.`is`(s: String) = this == s
fun String.upper() = toUpperCase()
fun String.lower() = toLowerCase()

fun String.isLower() = all { it.isLowerCase() }
fun String.isUpper() = all { it.isUpperCase() }

fun String.command() = normalSplit(" ").last().split("=").pair()