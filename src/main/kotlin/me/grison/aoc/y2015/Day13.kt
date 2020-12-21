package me.grison.aoc.y2015

import me.grison.aoc.Day

import io.vavr.kotlin.toVavrList
import kotlin.math.max

class Day13 : Day(13, 2015) {
    override fun title() = "Knights of the Dinner Table"

    override fun partOne(): Int {
        val (people, happiness) = load()
        return maxHappiness(people, happiness)
    }

    override fun partTwo(): Int {
        val (people, happiness) = load()

        people.forEach { p ->
            happiness["me$p"] = 0
            happiness["${p}me"] = 0
        }
        people.add("me")

        return maxHappiness(people, happiness)
    }

    private fun load(): Pair<MutableSet<String>, MutableMap<String, Int>> {
        val people = mutableSetOf<String>()
        val happiness = mutableMapOf<String, Int>()
        inputList.forEach { line ->
            val (p1, _, dir, amount) = line.split(" ")
            val p2 = line.split(" ").last().replace(".", "")

            people.add(p1)
            people.add(p2)
            happiness[p1 + p2] = when (dir) {
                "lose" -> -amount.toInt()
                else -> amount.toInt()
            }
        }
        return p(people, happiness)
    }

    private fun maxHappiness(people: Set<String>, happiness: Map<String, Int>) : Int {
        var max = 0
        people.toVavrList().permutations().forEach { step ->
            var gained = 0
            for (e in step.subSequence(1).zip(step.dropRight(1))) {
                gained += happiness[e._1!! + e._2!!]!!
                gained += happiness[e._2!! + e._1!!]!!
            }
            val (p1, p2) = p(step.first(), step.last())
            gained += happiness[p1 + p2]!!
            gained += happiness[p2 + p1]!!
            max = max(max, gained)
        }
        return max
    }

}
