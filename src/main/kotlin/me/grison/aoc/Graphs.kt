package me.grison.aoc

import util.CYAN
import java.lang.Math.pow
import java.lang.Math.sqrt
import java.util.*
import java.util.function.Predicate
import kotlin.collections.ArrayDeque
import kotlin.collections.set

typealias Graph<T> = MutableMap<T, MutableList<T>>

fun <T> List<Pair<T, T>>.toGraph() = graph(this)
fun <T> graph(edges: List<Pair<T, T>>): Graph<T> {
    val graph = mutableMapOf<T, MutableList<T>>()
    for ((a, b) in edges) {
        if (a !in graph) graph[a] = mutableListOf()
        if (b !in graph) graph[b] = mutableListOf()
        graph[a]!!.add(b)
        graph[b]!!.add(a)
    }
    return graph
}

fun <T> List<Pair<T, T>>.toDirectedGraph() = directedGraph(this)
fun <T> directedGraph(edges: List<Pair<T, T>>): Graph<T> {
    val graph = mutableMapOf<T, MutableList<T>>()
    for ((a, b) in edges) {
        if (a !in graph) graph[a] = mutableListOf()
        graph[a]!!.add(b)
    }
    return graph
}

/* find the shortest path between two nodes a and b, returns -1 if no connection found */
fun <T> Graph<T>.shortestPath(a: T, b: T): Pair<Int, List<T>> {
    val visited = mutableSetOf(a)
    val queue = ArrayDeque<Triple<T, Int, MutableList<T>>>()
    queue.addLast(Triple(a, 0, mutableListOf(a)))

    while (queue.size > 0) {
        val (node, distance, path) = queue.shift()
        if (b == node) return p(distance, path)

        for (neighbor in this[node]!!) {
            if (neighbor !in visited) {
                visited.add(neighbor)
                val newPath = path.toMutableList() + neighbor
                val t = Triple(neighbor, distance + 1, newPath)
                queue.addLast(t)
            }
        }
    }

    return p(-1, listOf())
}

fun <T> Graph<T>.hasPath(source: T, destination: T): Boolean {
    bfsIterator(source).let {
        while (it.hasNext()) {
            if (it.next() == destination)
                return true
        }
        return false
    }
}

fun <T> Graph<T>.depthFirst(root: T, visitor: (T) -> Unit) {
    val stack = Stack<T>()
    stack.push(root)

    val visited = mutableSetOf<T>()

    while (!stack.isEmpty()) {
        val current = stack.pop()
        if (current !in visited) {
            visited.add(current)
            visitor(current)

            for (neighbor in this[current]!!) {
                if (neighbor !in visited) {
                    stack.push(neighbor)
                }
            }
        }
    }
}

fun <T> Graph<T>.dfsIterator(source: T): Iterator<T> {
    val stack = Stack<T>()
    stack.push(source)

    val graph = this
    val visited = mutableSetOf<T>()
    return object : Iterator<T> {

        override fun hasNext(): Boolean {
            if (stack.isEmpty()) return false
            while (stack.isNotEmpty() && stack.last() in visited) {
                stack.pop()
            }

            return stack.isNotEmpty()
        }

        override fun next(): T {
            val current = stack.pop()
            visited.add(current)

            for (neighbor in graph[current]!!) {
                if (neighbor !in visited) {
                    stack.push(neighbor)
                }
            }

            return current
        }
    }
}

fun <T> Graph<T>.breadthFirst(source: T, visitor: (T) -> Unit) {
    val queue = ArrayDeque<T>()
    queue.add(source)

    val visited = mutableSetOf<T>()

    while (!queue.isEmpty()) {
        val current = queue.shift()

        if (current !in visited) {
            visited.add(current)
            visitor(current)

            for (neighbor in this[current]!!) {
                if (neighbor !in visited) {
                    queue.add(neighbor)
                }
            }
        }
    }
}

fun <T> Graph<T>.bfsIterator(source: T): Iterator<T> {
    val queue = ArrayDeque<T>()
    queue.add(source)

    val graph = this
    val visited = mutableSetOf<T>()
    return object : Iterator<T> {

        override fun hasNext(): Boolean {
            if (queue.isEmpty()) return false
            while (queue.isNotEmpty() && queue.first() in visited) {
                queue.shift()
            }

            return queue.isNotEmpty()
        }

        override fun next(): T {
            val current = queue.shift()
            visited.add(current)

            for (neighbor in graph[current]!!) {
                if (neighbor !in visited) {
                    queue.add(neighbor)
                }
            }

            return current
        }
    }
}

fun <T> Graph<T>.connectedComponentsCount(): Int {
    fun explore(graph: Graph<T>, current: T, visited: MutableSet<T>): Boolean {
        if (current in visited) return false
        visited.add(current)

        for (neighbor in graph[current]!!) {
            explore(graph, neighbor, visited)
        }

        return true
    }

    val visited = mutableSetOf<T>()
    var count = 0
    for (node in this.keys) {
        if (explore(this, node, visited))
            count += 1
    }

    return count
}

fun <T> Graph<T>.largestComponent(): Int {
    return sizedComponent(0) { a, b -> kotlin.math.max(a, b) }
}

fun <T> Graph<T>.smallestComponent(): Int {
    return sizedComponent(Int.MAX_VALUE) { a, b -> kotlin.math.min(a, b) }
}

private fun <T> Graph<T>.sizedComponent(default: Int, choser: (Int, Int) -> Int): Int {
    fun explore(graph: Graph<T>, current: T, visited: MutableSet<T>): Int {
        if (current in visited) return 0
        visited.add(current)
        var size = 1
        for (neighbor in graph[current]!!) {
            size += explore(graph, neighbor, visited)
        }

        return size
    }

    val visited = mutableSetOf<T>()
    var sizeval = default
    for (node in this.keys) {
        val size = explore(this, node, visited)
        sizeval = choser(size, sizeval)
//        if (size > longest) longest = size
    }

    return sizeval
}

fun <T> Grid<T>.islandCount(symbol: T, dimensions: Pair<Int, Int>): Int {
    fun explore(grid: Grid<T>, pos: Position, visited: MutableSet<Position>): Boolean {
        if (!pos.within(0, 0, dimensions.first, dimensions.second)) return false
        if (grid.getValue(pos) != symbol) return false
        if (pos in visited) return false
        visited.add(pos)
        explore(grid, pos.above(), visited) // above
        explore(grid, pos.below(), visited) // below
        explore(grid, pos.left(), visited) // left
        explore(grid, pos.right(), visited) // right
        return true
    }

    val visited = mutableSetOf<Position>()
    var count = 0
    gridPositions(dimensions).forEach { pos ->
        if (explore(this, pos, visited))
            count++
    }
    return count
}

fun <T> Grid<T>.minimumIsland(symbol: T, dimensions: Pair<Int, Int>) =
    minMaxIsland(symbol, dimensions).first

fun <T> Grid<T>.maximumIsland(symbol: T, dimensions: Pair<Int, Int>) =
    minMaxIsland(symbol, dimensions).second

private fun <T> Grid<T>.minMaxIsland(symbol: T, dimensions: Pair<Int, Int>): Pair<Int, Int> {
    fun explore(grid: Grid<T>, pos: Position, visited: MutableSet<Position>): Int {
        if (!pos.within(0, 0, dimensions.first, dimensions.second)) return 0
        if (grid.getValue(pos) != symbol) return 0
        if (pos in visited) return 0
        visited.add(pos)
        return 1 + explore(grid, pos.above(), visited) + explore(grid, pos.below(), visited) +
                explore(grid, pos.left(), visited) + explore(grid, pos.right(), visited)
    }

    val visited = mutableSetOf<Position>()
    var minSize = Int.MAX_VALUE
    var maxSize = 0
    gridPositions(dimensions).forEach { pos ->
        explore(this, pos, visited).let { size ->
            if (size in 1 until minSize)
                minSize = size
            if (size > maxSize)
                maxSize = size
        }
    }

    return p(minSize, maxSize)
}

fun <T> Grid<T>.hasPath(source: Position, destination: Position, traversable: Predicate<T>): Boolean {
    bfsIterator(source, traversable).let {
        while (it.hasNext())
            if (it.next().first == destination)
                return true
        return false
    }
}

fun <T> Grid<T>.shortestPath(a: Position, b: Position, traversable: Predicate<T>): Pair<Int, List<Position>> {
    val visited = mutableSetOf(a)
    val queue = ArrayDeque<Triple<Position, Int, MutableList<Position>>>()
    queue.addLast(Triple(a, 0, mutableListOf(a)))
    val yMax = keys.maxByOrNull { it.first }!!.first
    val xMax = keys.maxByOrNull { it.second }!!.second

    while (queue.size > 0) {
        val (node, distance, path) = queue.shift()
        if (b == node) return p(distance, path)

        node.directions().forEach { neighbor ->
            if (neighbor.within(0, 0, yMax, xMax) && neighbor !in visited
                && traversable.test(this[neighbor]!!)
            ) {
                visited.add(neighbor)
                val newPath = path.toMutableList() + neighbor
                val t = Triple(neighbor, distance + 1, newPath)
                queue.addLast(t)
            }
        }
    }

    return p(-1, listOf())
}

fun <T> Grid<T>.breadthFirst(
    source: Position,
    traversable: Predicate<T>,
    visitor: (position: Position, value: T) -> Boolean
) {
    val queue = ArrayDeque<Position>()
    queue.add(source)
    val visited = mutableSetOf<Position>()
    val yMax = keys.maxByOrNull { it.first }!!.first
    val xMax = keys.maxByOrNull { it.second }!!.second

    while (!queue.isEmpty()) {
        val current = queue.shift()

        if (current !in visited) {
            visited.add(current)
            //this.print(visited)
            if (visitor(current, this[current]!!)) {
                return
            }

            current.directions().forEach { neighbor ->
                if (neighbor.within(0, 0, yMax, xMax) && neighbor !in visited
                    && traversable.test(this[neighbor]!!)
                ) {
                    queue.add(neighbor)
                }
            }
        }
    }
}

fun <T> Grid<T>.bfsIterator(source: Position, traversable: Predicate<T>): Iterator<Pair<Position, T>> {
    val queue = ArrayDeque<Position>()
    queue.add(source)
    val visited = mutableSetOf<Position>()
    val yMax = keys.maxByOrNull { it.first }!!.first
    val xMax = keys.maxByOrNull { it.second }!!.second

    val graph = this
    return object : Iterator<Pair<Position, T>> {

        override fun hasNext(): Boolean {
            if (queue.isEmpty()) return false
            while (queue.isNotEmpty() && queue.first() in visited) {
                queue.shift()
            }

            return queue.isNotEmpty()
        }

        override fun next(): Pair<Position, T> {
            val current = queue.shift()
            visited.add(current)

            current.directions().forEach { neighbor ->
                if (neighbor.within(0, 0, yMax, xMax) && neighbor !in visited
                    && traversable.test(graph[neighbor]!!)
                ) {
                    queue.add(neighbor)
                }
            }

            return p(current, graph[current]!!)
        }
    }
}

fun <T> Grid<T>.print(visited: Set<Position> = setOf(), path: List<Position> = listOf()) {
    val yMax = keys.maxByOrNull { it.first }!!.first
    val xMax = keys.maxByOrNull { it.second }!!.second

    for (i in 0..yMax) {
        for (j in 0..xMax) {
            if (p(i, j) in path) {
                print("${CYAN}${this[p(i,j)]}\u001B[0m")
            }
            else if (p(i, j) in visited) {
                print("\u001B[35m${this[p(i,j)]}\u001B[0m")
            } else {
                print(this[p(i, j)])
            }
        }
        println()
    }
    println()
}

fun <T> Grid<T>.string(visited: Set<Position> = setOf()): String {
    val yMax = keys.maxByOrNull { it.first }!!.first
    val xMax = keys.maxByOrNull { it.second }!!.second

    val buffer = StringBuffer()
    for (i in 0..yMax) {
        for (j in 0..xMax) {
            if (p(i, j) in visited) {
                buffer.append("\u001B[35mx\u001B[0m")
            } else {
                buffer.append(this[p(i, j)])
            }
        }
        buffer.append("\n")
    }
    return buffer.toString()
}

data class Path(
    val grid: Grid<*>,
    val position: Position, val distance: Int, val path: List<Position>,
    val destination: Position, val type: DistanceType = DistanceType.MANHATTAN
) : Comparable<Path> {
    override fun compareTo(other: Path): Int {
        return (distance + type.distance(grid, position, destination))
            .compareTo(other.distance + type.distance(grid, other.position, destination))
    }
}

enum class DistanceType {
    MANHATTAN {
        override fun distance(grid: Grid<*>, a: Position, b: Position): Double {
            return a.manhattan(b).toDouble()
        }
    },
    EUCLIDEAN {
        override fun distance(grid: Grid<*>, a: Position, b: Position): Double {
            return sqrt(pow((a.first - b.first).toDouble(), 2.0) + pow((a.second - b.second).toDouble(), 2.0))
        }
    },
    LOWEST_VALUE_NEIGHBOR {
        override fun distance(grid: Grid<*>, a: Position, b: Position): Double {
            val g = grid as Grid<Int>
            return g[b]!!.toDouble()
        }
    };

    abstract fun distance(grid: Grid<*>, a: Position, b: Position): Double
}

fun <T> Grid<T>.aStar(
    a: Position,
    b: Position,
    type: DistanceType = DistanceType.MANHATTAN,
    traversable: Predicate<T>
): Pair<Int, List<Position>> {
    val visited = mutableSetOf(a)
    val queue = PriorityQueue<Path>()
    queue.add(Path(this, a, 0, listOf(a), b))
    val yMax = keys.maxByOrNull { it.first }!!.first
    val xMax = keys.maxByOrNull { it.second }!!.second

    while (queue.size > 0) {
        this.print(visited)
        val (_, node, distance, path, _) = queue.poll()
        if (b == node) return p(distance, path)

        node.directions().forEach { neighbor ->
            if (neighbor.within(0, 0, yMax, xMax) && neighbor !in visited
                && traversable.test(this[neighbor]!!)
            ) {
                visited.add(neighbor)
                val newPath = path.toMutableList() + neighbor
                queue.add(Path(this, neighbor, distance + 1, newPath, b, type))
            }
        }
    }

    return p(-1, listOf())
}

fun <T> Grid<T>.dimensions() =
    keys.maxByOrNull { it.first }!!.first.let { yMax ->
    keys.maxByOrNull { it.second }!!.second.let { xMax ->
        p(yMax, xMax)
    }
}

fun <T> Grid<T>.allPositions(dimensions: Pair<Int, Int> = dimensions()) =
    keys.maxByOrNull { it.first }!!.first.let { yMax ->
        keys.maxByOrNull { it.second }!!.second.let { xMax ->
            gridPositions(yMax + 1, xMax + 1)
        }
    }


fun Grid<Int>.shortestPath(
    start: Position,
    end: Position,
    type: DistanceType = DistanceType.EUCLIDEAN,
    traversable: (original: Pair<Position, Int>, neighbor: Pair<Position, Int>) -> Boolean = { _,_ -> true }
): Pair<Int, List<Position>> {
    val visited = mutableSetOf(start)
    val queue = PriorityQueue<Path>()
    queue.add(Path(this, start, 0, listOf(start), end))
    val yMax = keys.maxByOrNull { it.first }!!.first
    val xMax = keys.maxByOrNull { it.second }!!.second

    while (queue.size > 0) {
        val (_, node, value, path, _) = queue.poll()
        if (end == node) {
//            this.print(visited, path)
            return p(value, path)
        }

        listOf(node + p(-1,0), node + p(1,0), node + p(0, -1), node + p(0,1)).forEach { neighbor ->
            if (neighbor.within(0, 0, yMax, xMax) && neighbor !in visited
                && traversable.invoke(p(node, this[node]!!), p(neighbor, this[neighbor]!!))
            ) {
                visited.add(neighbor)
                val newPath = path.toMutableList() + neighbor
                queue.add(Path(this, neighbor, this[neighbor]!! + value, newPath, end, type))
            }
        }
    }

    return p(-1, listOf())
}