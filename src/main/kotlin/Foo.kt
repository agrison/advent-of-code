//import java.io.StringBufferInputStream
//import java.lang.Integer.parseInt
//import java.util.*
//import kotlin.math.abs
//
//// Vestigium (7pts)
//fun main1(args: Array<String>) {
//    val reader = Scanner(System.`in`)
//    val cases = reader.nextLine().toInt()
//    (1..cases).forEach { i ->
//        val size = reader.nextLine().toInt()
//        val matrix = mutableListOf<List<Int>>()
//        for (ln in (1..size)) {
//            matrix.add(reader.nextLine().split(" ").map { it.toInt() })
//        }
//
//        val k = (0 until size).map { matrix[it][it] }.sum()
//        val r = matrix.count { it.toSet().size != it.size }
//        val c = (0 until size).map { c -> matrix.map { it[c] } }.count { it.toSet().size != it.size }
//
//        println("Case #$i: $k $r $c")
//    }
//}
//
//// Nesting Depth (7pts)
//fun main2(args: Array<String>) {
//    val reader = Scanner(System.`in`)
//    val cases = reader.nextLine().toInt()
//    (1..cases).forEach { i ->
//        val s = reader.nextLine().map { parseInt("$it") }
//        var (sprime, count) = Pair("", 0)
//
//        s.indices.forEach { j ->
//            val diff = abs(s[j] - count)
//            count += when {
//                count < s[j] -> {
//                    sprime += "(".repeat(diff) + s[j]; diff
//                }
//                count > s[j] -> {
//                    sprime += ")".repeat(diff) + s[j]; -diff
//                }
//                else -> {
//                    sprime += s[j]; 0
//                }
//            }
//        }
//        sprime += ")".repeat(count)
//
//        println("Case #$i: $sprime")
//    }
//}
//
//// Nesting Depth (7pts)
//fun main(args: Array<String>) {
//    val reader = Scanner(StringBufferInputStream("""4
//3
//360 480
//420 540
//600 660
//3
//0 1440
//1 3
//2 4
//5
//99 150
//1 100
//100 301
//2 5
//150 250
//2
//0 720
//720 1440"""))
//    val cases = reader.nextLine().toInt()
//    for (i in (1..cases)) {
//        val kids = reader.nextLine().toInt()
//        val intervals = mutableListOf<List<Int>>()
//        for (j in (1..kids)) {
//            intervals.add(reader.nextLine().split(" ").map { it.toInt() })
//        }
//
//        val res = " ".repeat(kids).toCharArray()
//        val duplicates = intervals.map { it }.toMutableList()
//        intervals.sortBy { it[0] }
//
//        var currentInterval = duplicates.indexOf(intervals.first())
//        duplicates[currentInterval] = emptyList()
//        res[currentInterval] = 'C'
//
//        var (z, y) = Pair(0, 0)
//        var impossible = false
//
//        fun schedule(a: Int, kid: Char): Int {
//            currentInterval = duplicates.indexOf(intervals[a])
//            duplicates[currentInterval] = emptyList()
//            res[currentInterval] = kid
//            return a
//        }
//
//        for (k in (1 until intervals.size)) {
//            if (intervals[k][0] >= intervals[z][1]) {
//                z = schedule(k, 'C')
//            } else if (y == 0 || intervals[k][0] >= intervals[y][1]) {
//                y = schedule(k, 'J')
//            } else {
//                impossible = true
//                break
//            }
//        }
//
//        println("Case #$i: ${if (impossible) "IMPOSSIBLE" else res.joinToString("")}")
//    }
//}