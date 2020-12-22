package me.grison.aoc.y2020

import arrow.syntax.collections.tail
import me.grison.aoc.Day
import me.grison.aoc.butLast
import me.grison.aoc.flattenGrid
import me.grison.aoc.swapRowCols
import java.util.*

class Day20 : Day(20, 2020) {
    override fun title() = "Jurassic Jigsaw"

    private fun tiles() =
        inputGroups.map { it.lines() }
            .map { t -> Tile(t.first().allLongs().first(), t.tail().map { s -> s.map { it == '#' } }) }

    override fun partOne(): Long {
        val tiles = tiles()

        val edgeCount = mutableMapOf<List<Boolean>, Int>().withDefault { 0 }
        tiles.forEach { tile ->
            tile.minEdges().forEach { edge ->
                edgeCount[edge] = edgeCount.getValue(edge) + 1
            }
        }

        return tiles.filter { tile -> tile.minEdges().count { edgeCount.getValue(it) == 1 } == 2 }
            .map { tile -> tile.id }.product()
    }

    override fun partTwo(): Int {
        val tiles = tiles()
        val globalTile = assembleCompleteTile(tiles)



        var count = searchNessie(globalTile)?.let {
            globalTile.pixels.sumOf { r -> r.count { it } } - it * 15
        } ?: 0

        var s = globalTile.mkString()
        fun String.sharps() = withIndex().filter { (_, c) -> c == '#' }.map { (i, _) -> i }

        var L = s.split("\n").map { it.stringList().toMutableList() }.toMutableList()
        fun String.red() = red(this)
        fun String.roughColor() = this
        val SSS = mutableSetOf<Pair<Int, Int>>()
        NESSIES.forEach { (y, x) ->

            L[y - 1] = L[y - 1].mapIndexed { i, v -> if (i-x in nessie[0].sharps()) nessieR[0][i-x].toString().red() else v.roughColor() }.toMutableList()
            L[y] = L[y].mapIndexed { i, v -> if (i-x in nessie[1].sharps()) nessieR[1][i-x].toString().red() else v.roughColor() }.toMutableList()
            L[y + 1] = L[y + 1].mapIndexed { i, v -> if (i-x in nessie[2].sharps()) nessieR[2][i-x].toString().red() else v.roughColor() }.toMutableList()
        }

        L = L.map { l ->
            l.map { if (it == "#") cyan(it) else if (it == ".") blue(it) else it}.toMutableList()
        }.toMutableList()

        println("\n" + L.joinToString("\n") { it.joinToString("") })


        return count
    }

    val NESSIES = mutableListOf<Pair<Int, Int>>()

    private fun countNessies(tile: Tile) =
        (1 until tile.size())
            .flatMap { y -> (0..tile.size() - nessie[0].length).map { x -> p(x, y) } }
            .count { (x, y) ->
                val z = isNessie(tile, x, y)
                if (z) NESSIES.add(p(y, x))
                z
            }

    private val nessie = listOf(
        "                  # ",
        "#    ##    ##    ###",
        " #  #  #  #  #  #   "
    )

    private val nessieR = listOf(
        """                  _ """,
        """\    __    __    /O>""",
        """ \  /  \  /  \  /   """
    )

    private fun isNessie(tile: Tile, x: Int, y: Int): Boolean {
        fun String.sharps() = withIndex().filter { (_, c) -> c == '#' }.map { (i, _) -> i }
        return nessie[0].sharps().all { tile[x + it, y - 1] } // head
                && nessie[1].sharps().all { tile[x + it, y] } // middle
                && nessie[2].sharps().all { tile[x + it, y + 1] } // bottom
    }

    // search nessie in the big tile
    private fun searchNessie(tile: Tile): Int? {
        (0 until 4).forEach { _ ->
            var count = countNessies(tile)
            if (count != 0) return count
            else tile.vFlip()

            count = countNessies(tile)
            if (count != 0) return count
            else tile.hFlip()

            count = countNessies(tile)
            if (count != 0) return count
            else tile.vFlip()

            count = countNessies(tile)
            if (count != 0) return count
            else {
                tile.hFlip()
                tile.rotateRight()
            }
        }
        return null
    }

    private fun assembleCompleteTile(tiles: List<Tile>): Tile {
        val allTiles = tiles.associateBy { it.id }
        val checkedTiles = mutableListOf<Tile>()
        val remainingTiles = tiles.toMutableList()

        val tilesToCheck = Stack<Tile>()
        tilesToCheck += tiles.first()

        while (tilesToCheck.isNotEmpty()) {
            val currentTile = tilesToCheck.pop()
            remainingTiles -= currentTile
            val currentEdges = currentTile.edges()

            remainingTiles.forEach { tile ->
                tile.edges().forEachIndexed { num, edge ->
                    val reversed = edge.reversed() in currentEdges
                    val connected = edge in currentEdges || reversed
                    val currentEdge = currentEdges.indexOf(if (reversed) edge.reversed() else edge)
                    val amount = ((currentEdge + 4) - ((num + 2) % 4)) % 4

                    if (connected && currentEdge in (0..3)) {
                        allTiles[tile.id]!!.rotateRight(amount)
                        if (!reversed) {
                            if (currentEdge == 0 || currentEdge == 2) allTiles[tile.id]!!.hFlip()
                            else allTiles[tile.id]!!.vFlip()
                        }
                        currentTile.connectTiles(currentEdge, tile.id)
                        tile.connectTiles((currentEdge + 2) % 4, currentTile.id)
                        tilesToCheck += tile
                    }
                }
            }

            checkedTiles += currentTile
        }

        val bigTile = Array(12) { arrayOfNulls<Tile>(12) }

        var left: Tile? = allTiles.values.find { it.left == null && it.top == null }

        bigTile.indices.forEach { y ->
            var right: Tile? = left!!
            bigTile[y].indices.forEach { x ->
                bigTile[y][x] = right!!
                right = allTiles[right!!.right]
            }
            left = allTiles[left!!.bottom]
        }

        return Tile(0, bigTile.map { row ->
            row.map { tile -> tile!!.noBorders() }.flattenGrid()
        }.reduce { acc, list -> acc + list })
    }

    class Tile(val id: Long, var pixels: List<List<Boolean>>) {
        var top: Long? = null
        var right: Long? = null
        var bottom: Long? = null
        var left: Long? = null

        fun size() = pixels.size

        fun noBorders() = pixels.tail().butLast().map { it.tail().butLast() }.let { pixels = it; pixels }

        fun edges() = listOf(
            pixels.first(), // top
            pixels.map { it.last() }, // right
            pixels.last().reversed(), // bottom
            pixels.map { it.first() }.reversed() // left
        )

        fun minEdges(): List<List<Boolean>> {
            return edges().map { e ->
                var x = e.joinToString("") { b -> if (b) "1" else "0" }
                x = if (x > x.reversed()) x.reversed() else x
                x.split("").filter { b -> b != "" }.map { b -> b == "1" }
            }
        }

        fun rotateRight(amount: Int = 1) {
            repeat(amount) { pixels = pixels.swapRowCols() }
        }

        fun hFlip() = pixels.map { it.reversed() }.let { pixels = it }
        fun vFlip() = pixels.reversed().let { pixels = it }
        operator fun get(x: Int, y: Int) = pixels[y][x]

        fun connectTiles(i: Int, tileID: Long) {
            when (i) {
                0 -> top = tileID
                1 -> right = tileID
                2 -> bottom = tileID
                3 -> left = tileID
            }
        }

        fun mkString() : String {
            return pixels.joinToString("\n") { it.map { b -> if (b) '#' else '.' }.joinToString("") }
        }
    }
}