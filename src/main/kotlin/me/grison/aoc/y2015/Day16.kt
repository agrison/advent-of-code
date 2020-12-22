package me.grison.aoc.y2015

import me.grison.aoc.Day

// not parsing the input
class Day16 : Day(16, 2015) {
    override fun title() = "Aunt Sue"

    private val known = mutableMapOf("children" to 3, "cats" to 7, "samoyeds" to 2,
            "pomeranians" to 3, "akitas" to 0, "vizslas" to 0,
            "goldfish" to 5, "trees" to 3, "cars" to 2, "perfumes" to 1)

    //val special = mapOf("cats",)

    override fun partOne() = findAunt { a, b -> isRealAunt1(a, b) }

    override fun partTwo() = findAunt { a, b -> isRealAunt2(a, b) }

    private fun findAunt(isRealAunt: (things: MutableList<String>, values: MutableList<String>) -> Boolean): Int {
        inputList.forEach { line ->
            val x = "^Sue (\\d+): (\\w+): (\\d+), (\\w+): (\\d+), (\\w+): (\\d+)$".regex().find(line)
            val num = x!!.groupValues[1].toInt()
            val things = mutableListOf(x!!.groupValues[2], x!!.groupValues[4], x!!.groupValues[6])
            val values = mutableListOf(x!!.groupValues[3], x!!.groupValues[5], x!!.groupValues[7])
            if (isRealAunt(things, values))
                return num
        }
        return 0
    }

    private fun isRealAunt1(things: MutableList<String>, values: MutableList<String>): Boolean {
        for ((thing, value) in things.zip(values))
            if (known[thing] != value.toInt())
                return false

        return true
    }

    private fun isRealAunt2(things: MutableList<String>, values: MutableList<String>): Boolean {
        for (special in listOf("cats", "trees", "pomeranians", "goldfish")) {
            if (special in things) {
                val numThings = values.removeAt(things.indexOf(special)).toInt()
                things.remove(special)
                when (special) {
                    "cats" -> if (numThings <= known[special]!!) return false
                    "trees" -> if (numThings <= known[special]!!) return false
                    "pomeranians" -> if (numThings >= known[special]!!) return false
                    "goldfish" -> if (numThings >= known[special]!!) return false
                }
            }
        }

        return isRealAunt1(things, values)
    }
}