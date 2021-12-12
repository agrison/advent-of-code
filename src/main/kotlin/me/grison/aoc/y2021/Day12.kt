package me.grison.aoc.y2021

import me.grison.aoc.*
import org.apache.commons.io.FileUtils
import java.io.File
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Predicate


class Day12 : Day(12, 2021) {
    override fun title() = "Passage Pathing"

    override fun partOne(): Int {
        return inputList.map { it.normalSplit("-").pair() }
            .toGraph()
            .countAllPaths("start", "end", trackVisit = { it.toLowerCase() == it })
    }

    override fun partTwo(): Int {
        return inputList.map { it.normalSplit("-").pair() }
            .toGraph()
            .countAllPaths("start", "end",
                trackVisit = { it.toLowerCase() == it },
                ignore = { it == "start" },
                twice = false)
    }

    val A = AtomicInteger()

    private fun <T> Graph<T>.countAllPaths(
        source: T, destination: T,
        trackVisit: Predicate<T> = Predicate { true },
        ignore: Predicate<T> = Predicate { false },
        twice: Boolean? = null
    ): Int {
        fun explore(graph: Graph<T>, current: T, visited: MutableSet<T>, twice: Boolean? = null,
                   // stack: MutableList<Pair<T,T>> = mutableListOf()
        ): Int {
            if (current == destination) {
//                FileUtils.write(
//                    File("/tmp/img" + A.incrementAndGet() + ".dot"),
//                    generateDot(A.get(), current, stack)
//                )
                return 1
            }
            var paths = 0
            for (neighbor in graph[current]!!.reject { ignore.test(it) }) {
//                val s=stack.toMutableList()
//                s.add(p(current, neighbor))
                if (trackVisit.test(neighbor) && neighbor != destination) {
                    if (neighbor in visited) {
                        if (twice != null && !twice)
                            paths += explore(graph, neighbor, visited, true)//, s)
                    } else
                        paths += explore(graph, neighbor, visited.toMutableSet() + neighbor, twice)//, s)
                } else
                    paths += explore(graph, neighbor, visited, twice)//, s)
            }

            return paths
        }

        return explore(this, source, mutableSetOf(source), twice)
    }

    // -- graphviz generation

    fun <T> generateDot(i:Int, current: T, stack: List<Pair<T, T>>):String {
        val s = StringBuffer("digraph G { bgcolor=\"#10101a\"\n    label=\"Path: "+i+"\"\n" +
                "    fontcolor=\"white\"")
        val Z = inputList.map { it.normalSplit("-").pair() }
        Z
            .forEach { (a, b) ->
                s.append(a).append("->").append(b)
                if (stack.contains(p(a,b))) s.append(" [color=\"#FFFF66\"]") else s.append(" [color=white]")
                s.append(" \n")
                s.append(b).append("->").append(a)
                if (stack.contains(p(b,a))) s.append(" [color=\"#94B3E3\"]") else s.append(" [color=white]")
                s.append(" \n")
            }

        fun inStack(x:String): Boolean {
            return x == "start" || stack.any { it.second == x  }
        }

        Z
            .forEach { (a, b) ->
                if (a == "start")
                    s.append("$a [color=\"#a47a4d\",fontcolor=\"#a47a4d\"]\n")
                else if (b == "start")
                    s.append("$a [color=\"#a47a4d\",fontcolor=\"#a47a4d\"]\n")
                else {
                    if (a == current) //
                        s.append("$a [color=\"yellow\",fontcolor=\"yellow\"]\n")
                    else if (inStack(a))
                        s.append("$a [color=\"#009900\",fontcolor=\"#009900\"]\n")
                    else
                        s.append("$a [color=\"white\",fontcolor=\"white\"]\n")

                    if (b == current) // #009900
                        s.append("$b [color=\"yellow\",fontcolor=\"yellow\"]\n")
                    else if (inStack(b))
                        s.append("$b [color=\"#009900\",fontcolor=\"#009900\"]\n")
                    else
                        s.append("$b [color=\"white\",fontcolor=\"white\"]\n")
                }
            }
        s.append("\nstart [shape=Mdiamond];\n" +
                "  end [shape=Msquare];\n}")
        return s.toString()
    }
}
