package me.grison.aoc.y2015

import me.grison.aoc.Day

// not parsing the input
class Day15 : Day(15, 2015) {
    override fun title() = "Science for Hungry People"

    private val attributes = mutableMapOf("Sugar" to Attributes(3, 0, 0, -3, 2),
            "Sprinkles" to Attributes(-3, 3, 0, 0, 9),
            "Candy" to Attributes(-1, 0, 4, 0, 1),
            "Chocolate" to Attributes(0, 0, -2, 2, 8))

    override fun partOne() = allCookies().map { it.first }.maxOrNull()

    override fun partTwo() = allCookies().filter { it.second == 500 }.maxByOrNull { it.first }?.first

    fun allCookies(): List<Pair<Int, Int>> {
        val allCookies = mutableListOf<Pair<Int, Int>>()
        (0..99).forEach { sugar ->
            (0..99).forEach { sprinkles ->
                (0..99).forEach { candy ->
                    val chocolate = 100 - sugar - sprinkles - candy
                    allCookies.add(makeCookie(
                            mapOf("Sugar" to sugar,
                                    "Sprinkles" to sprinkles,
                                    "Candy" to candy,
                                    "Chocolate" to chocolate)))
                }
            }
        }
        return allCookies
    }

    fun makeCookie(teaspoons: Map<String, Int>): Pair<Int, Int> {
        val m = mutableMapOf<String, Int>()
        attributes.keys.forEach { ingredient ->
            val attr = attributes[ingredient]!!
            val ts = teaspoons[ingredient]!!

            m["capacity"] = (m["capacity"] ?: 0) + attr.capacity * ts
            m["durability"] = (m["durability"] ?: 0) + attr.durability * ts
            m["flavor"] = (m["flavor"] ?: 0) + attr.flavor * ts
            m["texture"] = (m["texture"] ?: 0) + attr.texture * ts
            m["calories"] = (m["calories"] ?: 0) + attr.calories * ts
        }

        if (m.values.count { e -> e <= 0 } > 0) return Pair(0, m["calories"]!!)
        return Pair(m.filter { it.key != "calories" }.values.fold(1) { a, b -> a * b }, m["calories"]!!)
    }
}

data class Attributes(val capacity: Int, val durability: Int,
                      val flavor: Int, val texture: Int, val calories: Int)