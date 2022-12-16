package me.grison.aoc.y2022

import me.grison.aoc.*
import kotlin.math.max
import kotlin.math.min

class Day16 : Day(16, 2022) {
    override fun title() = "Proboscidea Volcanium"

    override fun partOne(): Int {
        val valves = parseValves()
        val distances = findAllDistances(valves)
        return solve(valves, distances, 30, elephant = false)
    }

    override fun partTwo(): Int {
        val valves = parseValves()
        val distances = findAllDistances(valves)
        return solve(valves, distances, 26, elephant = true)
    }

    private fun parseValves(): Valves {
        val valves = mutableMapOf<String, Valve>()
        inputList.forEach {
            val flow = it.allInts().first()
            val name = it.after("Valve ").before(" has")
            val destinations = (if (it.contains("valves ")) it.after("valves ").normalSplit(", ")
            else it.after("valve ").normalSplit(", ")).reversed()

            valves[name] = Valve(name, flow, destinations)
        }

        return valves
    }

    private fun findAllDistances(valves: Valves): Distances {
        val distances = mutableMapOf<Pair<String, String>, Int>()
        val combinations = valves.values.pairCombinations()
        combinations.forEach { (v1, v2) ->
            distances[p(v1.name, v2.name)] = if (v1.name == v2.name) 0 else 100
        }

        repeat(valves.size) {
            combinations.forEach { (v1, v2) ->
                v1.destinations.forEach {
                    distances[p(it, v2.name)] = min(distances[p(it, v2.name)]!!, distances[p(v1.name, v2.name)]!! + 1)
                }
            }
        }

        return distances
    }

    private fun solve(valves: Valves, distances: Distances, totalMinutes: Int, elephant: Boolean = false): Int {
        val nonNulls = valves.values.filter { it.flow > 0 }.map { it.name }
        val alternatives = mutableMapOf<Set<String>, Int>()

        fun findMostPressure(valveName: String, minutes: Int, pressure: Int = 0, seen: MutableSet<String> = mutableSetOf(), elephant: Boolean = false): Int {
            var (currentPressure, minutesRemaining) = p(pressure, minutes)
            // done
            if (minutes <= 1) {
                if (!elephant) return pressure
                else {
                    val currentSeen = seen.toSet()
                    if (seen in alternatives) return pressure + alternatives[currentSeen]!!
                    else {
                        alternatives[currentSeen] = findMostPressure("AA", totalMinutes, 0, seen, false) // restart
                        return pressure + alternatives[currentSeen]!!
                    }
                }
            }

            val currentValve = valves[valveName]!!

            // open valve
            if (currentValve.flow > 0) {
                minutesRemaining -= 1
                currentPressure += minutesRemaining * currentValve.flow
            }

            var totalPressure = currentPressure
            nonNulls.forEach { nextValveName ->
                if (nextValveName !in seen) {
                    seen.add(nextValveName)
                    // maximize pressure
                    val minutesArrived = minutesRemaining - distances[p(valveName, nextValveName)]!!
                    totalPressure = max(
                        findMostPressure(nextValveName, minutesArrived, currentPressure, seen, elephant),
                        totalPressure
                    )
                    seen.remove(nextValveName)
                }
            }

            return totalPressure
        }

        return findMostPressure("AA", totalMinutes, elephant = elephant)
    }
}

class Valve(val name: String, val flow: Int, val destinations: List<String>)
typealias Valves = Map<String, Valve>
typealias Distances = Map<Pair<String, String>, Int>