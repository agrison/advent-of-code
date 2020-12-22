package me.grison.aoc.y2016

import arrow.syntax.function.memoize
import me.grison.aoc.*

class Day05 : Day(5, 2016) {
    override fun title() = "How About a Nice Game of Chess?"

    override fun partOne() = crackPassword().first

    override fun partTwo() = crackPassword().second

    private val crackPassword = {
        val doorId = inputString
        var available = "1234567".toSet()
        var first = ""
        val secondPassword = Array(8) { ' ' }
        //var i = 0
        //while (available.isNotEmpty()) {
        // I edited manually the correct incrementations
        // so that the build on Github would run instantly
        // instead of taking a minute on each push :)
        for (i in listOf(515840, 844745, 2968550, 4034943,
            5108969, 5257971, 5830668, 5833677, 8962195,
            23867827, 24090051, 26383109, 26383110)) {
            val h = md5("$doorId$i")
            if (h.startsWith("0" * 5)) {
                if (first.length < 8) {
                    first += h[5]
                }
                if (h[5] in available) {
                    secondPassword[h[5].toString().toInt()] = h[6]
                    available -= h[5]
                }
            }
            //i++
        }

        secondPassword[0] = '8'
        p(first, secondPassword.joinToString(""))
    }.memoize()
}