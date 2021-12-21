package me.grison.aoc.y2021

import com.beust.klaxon.JsonArray
import com.beust.klaxon.Parser
import me.grison.aoc.*
import kotlin.math.max

@Suppress("UNCHECKED_CAST", "ControlFlowWithEmptyBody")
class Day18 : Day(18, 2021) {
    override fun title() = "Snailfish"

    private fun homework(): MutableList<Any> {
        val homework = mutableListOf<Any>()
        inputString.lines().forEach {
            val jsonArray = Parser.default().parse(StringBuilder(it)) as JsonArray<*>
            homework.add(jsonArray.toMutableList() as MutableList<Any>)
        }
        return homework
    }

    override fun partOne(): Int {
        val homework = homework()
        val init = homework.first()
        return homework.tail().fold(init) { accu, list ->
            arrayListOf(accu, list).also { reduce(it) }
        }.let { magnitude(it as MutableList<*>) }
    }

    override fun partTwo(): Int {
        return homework().pairCombinations().map { (a, b) ->
            val d1 = arrayListOf(copy(a), copy(b)).also { reduce(it) }
            val d2 = arrayListOf(copy(b), copy(a)).also { reduce(it) }
            max(magnitude(d1), magnitude(d2))
        }.maxOf { it }
    }

    enum class SnailPart { LEFT, RIGHT }

    private fun handleList(pair: Pair<Int?, Int?>, type: SnailPart, list: MutableList<Any>, part: Any): Pair<Int?, Int?> {
        val order = if (type == SnailPart.RIGHT) listOf(1, 0) else listOf(0, 1)
        val num = if (type == SnailPart.RIGHT) pair.second else pair.first

        if (num != null) {
            var original = list
            var nums = part
            while (nums !is Int) {
                original = nums as MutableList<Any>
                nums = original[order[1]]
            }

            if (original == list) {
                list[order[0]] = nums + num
            } else {
                original[order[1]] = nums + num
            }

            return if (type == SnailPart.RIGHT) p(pair.first, null) else p(null, pair.second)
        }
        return pair
    }

    private fun explode(list: MutableList<Any>, level: Int, explode: () -> Unit = {}): Pair<Int?, Int?>? {
        val (left, right) = list
        if (left is MutableList<*>)
            explode(left as MutableList<Any>, level + 1) { list[0] = 0 }?.let {
                return handleList(it, SnailPart.RIGHT, list, right)
            }

        if (right is MutableList<*>)
            explode(right as MutableList<Any>, level + 1) { list[1] = 0 }?.let {
                return handleList(it, SnailPart.LEFT, list, left)
            }

        if (left is Int && right is Int && level >= 4) {
            explode()
            return p(left, right)
        }

        return null
    }

    private fun reduce(list: MutableList<Any>) {
        while (explode(list, 0) != null || split(list));
    }

    private fun magnitude(list: MutableList<*>): Int {
        val (left, right) = list
        var magnitude = 0
        when (left) {
            is MutableList<*> -> magnitude += magnitude(left) * 3
            is Int -> magnitude += left * 3
        }

        when (right) {
            is MutableList<*> -> magnitude += magnitude(right) * 2
            is Int -> magnitude += right * 2
        }

        return magnitude
    }

    private fun split(list: MutableList<Any>): Boolean {
        val (left, right) = list
        return when {
            left is List<*> && split(left as MutableList<Any>) -> true
            left is Int && left > 9 -> {
                list[0] = arrayListOf(left / 2, (left + 1) / 2); true
            }
            right is List<*> && split(right as MutableList<Any>) -> true
            right is Int && right > 9 -> {
                list[1] = arrayListOf(right / 2, (right + 1) / 2); true
            }
            else -> false
        }
    }

    private fun copy(input: Any?): Any {
        return when (input is List<*>) {
            true -> arrayListOf(copy(input[0]), copy(input[1]))
            else -> input as Int
        }
    }
}
