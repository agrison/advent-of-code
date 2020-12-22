package me.grison.aoc.y2020

import me.grison.aoc.Day
import java.lang.System.lineSeparator

class Day19 : Day(19, 2020) {
    override fun title() = "Monster Messages"
    private val solutionKey = "0"

    override fun partOne() = findSolutions(loadRules()).let { solutions ->
        loadMessages().count { solutions[solutionKey]!!.regex().matches(it) }
    }

    override fun partTwo(): Any {
        val rules = loadRules()
        val resolved = findSolutions(rules)
        // replacement rules as per part 2 spec
        rules["8"] = "42 | 42 8"
        rules["11"] = "42 31 | 42 11 31"

        val solutionsSoFar: MutableSet<String> = mutableSetOf()
        // iterate until we cannot find a new solution (infinite loop)
        while (true) {
            var foundSolution = false
            iterateRules(rules, resolved)

            loadMessages().forEach { message ->
                if (message !in solutionsSoFar && resolved[solutionKey]!!.regex().matches(message)) {
                    solutionsSoFar.add(message)
                    foundSolution = true
                }
            }

            if (!foundSolution)
                return solutionsSoFar.size
        }
    }

    private fun loadRules(): MutableMap<String, String> = mutableMapOf<String, String>().let { rules ->
        rules().forEach { line ->
            rules[line.before(":")] = line.after(": ")
        }
        rules
    }

    // iterate on rules until the rule "0" can be fully resolved
    private fun findSolutions(rules: Map<String, String>) = mutableMapOf<String, String>().let { solutions ->
        while (solutionKey !in solutions.keys) {
            iterateRules(rules, solutions)
        }
        solutions
    }

    // build a map of regular expressions to match messages with later on
    private fun iterateRules(rules: Map<String, String>, solutions: MutableMap<String, String>) {
        rules.forEach { (number, rule) ->
            when {
                // this is an initial rule
                '"' in rule -> solutions[number] = rule.except('"')
                else -> {
                    val ruleParts = rule.normalSplit(" ")
                    val solved = ruleParts.except("|").all { it in solutions.keys }
                    if (solved) {
                        solutions[number] = "(" + ruleParts.map { it.or("|", solutions[it]) }.join() + ")"
                    }
                }
            }
        }
    }

    private fun rules() = inputGroups[0].lines()
    private fun loadMessages() = inputGroups[1].lines()
}