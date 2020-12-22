package me.grison.aoc.y2020

import arrow.core.extensions.list.foldable.exists
import me.grison.aoc.*
import java.lang.System.lineSeparator

class Day16 : Day(16, 2020) {
    override fun title() = "Ticket Translation"

    override fun partOne(): Int {
        val validRanges = flattenedValidRanges()
        return nearbyTickets().map { it.allInts() }.flatten()
                .filter { !validRanges.exists { v -> v.contains(it) } }.sum()
    }

    override fun partTwo(): Long {
        val flattenedValidRanges = flattenedValidRanges()
        val validRanges = validRanges()
        val nearby = nearbyTickets()

        // find all invalid lines
        val invalidTickets = nearby.mapIndexed { i, ticket ->
            if (ticket.allInts().exists { it !in flattenedValidRanges.flatten() }) i else null
        }.filterNotNull()

        // find all lines
        val validTickets = mutableMapOf<Int, Set<Int>>().withDefault { mutableSetOf() }
        nearby.mapIndexed { i, ticket -> p(i, ticket) }
                .filter { it.first !in invalidTickets } // keep valid
                .forEach {
                    // for each int in this nearby ticket
                    it.second.allInts().forEachIndexed { i, int ->
                        validRanges.forEachIndexed { j, v ->
                            // if none of the 2 ranges matches
                            if (!v[0].contains(int) && !v[1].contains(int)) {
                                validTickets[i] = validTickets.getValue(i) + j
                            }
                        }
                    }
                }

        // keep track of all the scanning errors
        val scanningErrors = mutableMapOf<Int, Int>()
        // these are the spec we need to find each int is what
        val rangesToFind = validRanges.indices.toMutableList()

        // while there are some valid tickets to find which int is what
        while (validTickets.isNotEmpty()) {
            // strategy is to get the largest int
            val largestInvalidTicket = validTickets.entries.maxByOrNull { it.value.size }!!
            // and try to find the first range from the spec where the largest int is valid
            val validInt = rangesToFind.first { it !in largestInvalidTicket.value }
            // we don't need to find that class of range anymore
            rangesToFind.remove(validInt)
            // nor do we need to find something for this ticket
            validTickets.remove(largestInvalidTicket.key)
            scanningErrors[largestInvalidTicket.key] = validInt
        }

        return myTickets()
                // "departure" are specified in the first 6 lines in my input
                .filterIndexed { index, _ -> scanningErrors[index] in 0..5 }
                .multiply()
    }

    private fun flattenedValidRanges(): List<IntRange> {
        return validRanges().flatten()
    }

    private fun validRanges(): MutableList<List<IntRange>> {
        val (spec, _, _) = inputString.split(lineSeparator() * 2)

        return mutableListOf<List<IntRange>>().let {
            spec.split(lineSeparator()).forEach { line ->
                val ints = line.allInts()
                it.add(listOf(IntRange(ints[0], ints[1]), IntRange(ints[2], ints[3])))
            }
            it
        }
    }

    private fun nearbyTickets(): List<String> {
        val (_, _, _nearby) = inputGroups
        return _nearby.lines().tail()
    }

    private fun myTickets(): List<Long> {
        val (_, my, _) = inputGroups
        return my.lines().last().allLongs()
    }
}