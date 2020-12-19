package days

import java.lang.System.lineSeparator

class Day19 : Day(19) {
    override fun title() = "Monster Messages"
    private val solutionKey = "0"

    override fun partOne() = findSolutions(loadRules()).let { solutions ->
        loadMessages().count { solutions[solutionKey]!!.regex().matches(it) }
    }

    override fun partTwo(): Any {
        val rules = loadRules()
        val resolved = findSolutions(rules)
        rules["8"] = "42 | 42 8"
        rules["11"] = "42 31 | 42 11 31"

        val solutionsSoFar: MutableSet<String> = mutableSetOf()
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
        inputString.split(lineSeparator() * 2)[0].split(lineSeparator()).forEach { line ->
            rules[line.before(":")] = line.after(": ")
        }
        rules
    }

    private fun findSolutions(rules: Map<String, String>) = mutableMapOf<String, String>().let { solutions ->
        while (solutionKey !in solutions.keys) {
            iterateRules(rules, solutions)
        }
        solutions
    }

    private fun iterateRules(rules: Map<String, String>, resolved: MutableMap<String, String>) {
        rules.forEach { (number, rule) ->
            when {
                '"' in rule -> resolved[number] = rule.except('"')
                else -> {
                    val ruleParts = rule.normalSplit(" ")
                    val solved = ruleParts.except("|").all { it in resolved.keys }
                    if (solved) {
                        resolved[number] = "(" + ruleParts.map { if (it == "|") "|" else resolved[it] }.joinToString("") + ")"
                    }
                }
            }
        }
    }

    private fun loadMessages() = inputString.split(lineSeparator() * 2)[1].split(lineSeparator())
}