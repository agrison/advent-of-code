package me.grison.aoc.y2022

import me.grison.aoc.*

class Day17 : Day(17, 2022) {
    override fun title() = "Pyroclastic Flow"

    override fun partOne() = solve(2022)

    override fun partTwo() = solve(1_000_000_000_000L)

    // all start at x = 2
    private val ROCKS = listOf(
        listOf(p(2L, 0), p(3L, 0), p(4L, 0), p(5L, 0)),
        listOf(p(3L, 2), p(2L, 1), p(3L, 1), p(4L, 1), p(3L, 0)),
        listOf(p(4L, 2), p(4L, 1), p(2L, 0), p(3L, 0), p(4L, 0)),
        listOf(p(2L, 0), p(2L, 1), p(2L, 2), p(2L, 3)),
        listOf(p(2L, 1), p(3L, 1), p(2L, 0), p(3L, 0))
    )

    private fun rock(t: Int, y: Long) = ROCKS[t].map { it.second(y + it.second) } as Rock

    private fun move(jet: Char, rock: Rock, allRocks: Set<LPosition>): Rock {
        if (jet == '>') {
            val next = rock.right()
            if (next.intersect(allRocks).isEmpty())
                return next // move
        } else {
            val next = rock.left()
            if (next.intersect(allRocks).isEmpty())
                return next // move
        }
        return rock
    }

    fun solve(rocks: Long): Long {
        val allRocks = mutableSetOf<LPosition>()
        for (x in 0L..6L) allRocks.add(p(x, 0L)) // floor

        val knownStates = mutableMapOf<Triple<Int, Long, Rock>, LPosition>()

        var (top, tick, linesSkipped) = listOf(0L, 0L, 0L)
        var jet = 0
        while (tick <= rocks) {
            var rock = rock((tick % 5L).toInt(), top + 4)
            move@ while (true) {
                rock = move(inputString[jet++], rock, allRocks).down()
                jet %= inputString.length
                if (rock.intersect(allRocks).isNotEmpty()) {
                    rock = rock.up()
                    allRocks.addAll(rock)
                    top = allRocks.map { it.second }.max()

                    // save current 100 top rows pattern
                    val currentTop = allRocks.filter { top - it.second <= 100 }.map { it.second(top - it.second) }
                    val currentState = Triple(jet, tick % 5, currentTop)
                    if (currentState in knownStates) {
                        val (lastTick, lastTop) = knownStates[currentState]!!
                        val span = (rocks - tick) / (tick - lastTick)
                        linesSkipped += span * (top - lastTop)
                        tick += span * (tick - lastTick)
                    }
                    knownStates[currentState] = p(tick, top)
                    break@move
                }
            }
            tick++
        }

        return top + linesSkipped
    }
}

typealias Rock = List<LPosition>

fun Rock.left() = if (any { it.first == 0L }) this else map { it.first(it.first - 1) }
fun Rock.right() = if (any { it.first == 6L }) this else map { it.first(it.first + 1) }
fun Rock.down() = map { it.second(it.second - 1) }
fun Rock.up() = map { it.second(it.second + 1) }