package me.grison.aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GraphsTests {
    @Test
    fun `should build graph`() {
        assertEquals(
            "{w=[x, v], x=[w, y], y=[x, z], z=[y, v], v=[z, w]}",
            listOf(
                p('w', 'x'),
                p('x', 'y'),
                p('z', 'y'),
                p('z', 'v'),
                p('w', 'v'),
            ).toGraph().toString()
        )
    }

    @Test
    fun `should return shortest path`() {
        val graph = listOf(
            p('w', 'x'),
            p('x', 'y'),
            p('z', 'y'),
            p('z', 'v'),
            p('w', 'v'),
        ).toGraph()

        assertEquals(
            2,
            graph.shortestPath('w', 'z').first
        )
        assertEquals(
            listOf('w', 'v', 'z'),
            graph.shortestPath('w', 'z').second
        )
    }

    @Test
    fun `should depth first`() {
        val builder = StringBuilder()
        listOf(
            p('a', 'b'),
            p('a', 'c'),
            p('b', 'd'),
            p('c', 'e'),
            p('d', 'f'),
        ).toGraph().depthFirst('a') { node -> builder.append(node) }
        assertEquals("acebdf", builder.toString())

        builder.clear()
        mutableMapOf(
            'a' to mutableListOf('b', 'c'),
            'b' to mutableListOf('d'),
            'c' to mutableListOf('e'),
            'd' to mutableListOf('f'),
            'e' to mutableListOf(),
            'f' to mutableListOf()
        ).depthFirst('a') { node -> builder.append(node) }
        assertEquals("acebdf", builder.toString())

        builder.clear()
        mutableMapOf(
            'A' to mutableListOf('B', 'S'),
            'B' to mutableListOf('A'),
            'C' to mutableListOf('D', 'E', 'F', 'S'),
            'D' to mutableListOf('C'),
            'E' to mutableListOf('C', 'H'),
            'F' to mutableListOf('C', 'G'),
            'G' to mutableListOf('F', 'S'),
            'H' to mutableListOf('E', 'G'),
            'S' to mutableListOf('A', 'C', 'G')
        ).depthFirst('A') { node -> builder.append(node) }
        assertEquals("ASGFCEHDB", builder.toString())

        builder.clear()
        val it = mutableMapOf(
            'A' to mutableListOf('B', 'S'),
            'B' to mutableListOf('A'),
            'C' to mutableListOf('D', 'E', 'F', 'S'),
            'D' to mutableListOf('C'),
            'E' to mutableListOf('C', 'H'),
            'F' to mutableListOf('C', 'G'),
            'G' to mutableListOf('F', 'S'),
            'H' to mutableListOf('E', 'G'),
            'S' to mutableListOf('A', 'C', 'G')
        ).dfsIterator('A')
        while (it.hasNext())
            builder.append(it.next())
        assertEquals("ASGFCEHDB", builder.toString())
    }


    @Test
    fun `should breadth first`() {
        val builder = StringBuilder()
        listOf(
            p('a', 'b'),
            p('a', 'c'),
            p('b', 'd'),
            p('c', 'e'),
            p('d', 'f'),
        ).toGraph().breadthFirst('a') { node -> builder.append(node) }
        assertEquals("abcdef", builder.toString())

        builder.clear()
        mutableMapOf(
            'a' to mutableListOf('b', 'c'),
            'b' to mutableListOf('d'),
            'c' to mutableListOf('e'),
            'd' to mutableListOf('f'),
            'e' to mutableListOf(),
            'f' to mutableListOf()
        ).breadthFirst('a') { node -> builder.append(node) }
        assertEquals("abcdef", builder.toString())

        builder.clear()
        mutableMapOf(
            'A' to mutableListOf('B', 'S'),
            'B' to mutableListOf('A'),
            'C' to mutableListOf('D', 'E', 'F', 'S'),
            'D' to mutableListOf('C'),
            'E' to mutableListOf('C', 'H'),
            'F' to mutableListOf('C', 'G'),
            'G' to mutableListOf('F', 'S'),
            'H' to mutableListOf('E', 'G'),
            'S' to mutableListOf('A', 'C', 'G')
        ).breadthFirst('A') { node -> builder.append(node) }
        assertEquals("ABSCGDEFH", builder.toString())

        builder.clear()
        val it = mutableMapOf(
            'A' to mutableListOf('B', 'S'),
            'B' to mutableListOf('A'),
            'C' to mutableListOf('D', 'E', 'F', 'S'),
            'D' to mutableListOf('C'),
            'E' to mutableListOf('C', 'H'),
            'F' to mutableListOf('C', 'G'),
            'G' to mutableListOf('F', 'S'),
            'H' to mutableListOf('E', 'G'),
            'S' to mutableListOf('A', 'C', 'G')
        ).bfsIterator('A')
        while (it.hasNext())
            builder.append(it.next())
        assertEquals("ABSCGDEFH", builder.toString())
    }

    @Test
    fun `should hasPath`() {
        assertTrue(
            listOf(
                p('a', 'b'),
                p('a', 'c'),
                p('b', 'd'),
                p('c', 'e'),
                p('d', 'f'),
                p('g', 'h')
            ).toGraph().hasPath('a', 'e')
        )

        assertTrue(
            listOf(
                p('a', 'b'),
                p('a', 'c'),
                p('b', 'd'),
                p('c', 'e'),
                p('d', 'f'),
                p('g', 'h')
            ).toGraph().hasPath('g', 'h')
        )

        assertTrue(
            listOf(
                p('a', 'b'),
                p('a', 'c'),
                p('b', 'd'),
                p('c', 'e'),
                p('d', 'f'),
                p('g', 'h')
            ).toGraph().hasPath('d', 'e')
        )

        assertFalse(
            listOf(
                p('a', 'b'),
                p('a', 'c'),
                p('b', 'd'),
                p('c', 'e'),
                p('d', 'f'),
                p('g', 'h')
            ).toGraph().hasPath('a', 'h')
        )
    }

    @Test
    fun `should connectedComponentsCount`() {
        assertEquals(
            2,
            mutableMapOf(
                0 to mutableListOf(8, 1, 5),
                1 to mutableListOf(0),
                5 to mutableListOf(0, 8),
                8 to mutableListOf(0, 5),
                2 to mutableListOf(3, 4),
                3 to mutableListOf(2, 4),
                4 to mutableListOf(3, 2),
            ).connectedComponentsCount()
        )
    }

    @Test
    fun `should largestComponent`() {
        assertEquals(
            4,
            mutableMapOf(
                0 to mutableListOf(8, 1, 5),
                1 to mutableListOf(0),
                5 to mutableListOf(0, 8),
                8 to mutableListOf(0, 5),
                2 to mutableListOf(3, 4),
                3 to mutableListOf(2, 4),
                4 to mutableListOf(3, 2),
            ).largestComponent()
        )
    }

    @Test
    fun `should smallestComponent`() {
        assertEquals(
            0,
            mutableMapOf(
                0 to mutableListOf(8, 1, 5),
                1 to mutableListOf(0),
                5 to mutableListOf(0, 8),
                8 to mutableListOf(0, 5),
                2 to mutableListOf(3, 4),
                3 to mutableListOf(2, 4),
                4 to mutableListOf(3, 2),
            ).smallestComponent()
        )
    }

    @Test
    fun `should islandCount`() {
        assertEquals(
            3,
            "/graphs/map5.txt".load().lines().stringGrid("W").islandCount("L", p(5, 5))
        )

        assertEquals(
            13,
            "/graphs/map50x15.txt".load().lines().stringGrid(".").islandCount("#", p(15, 50))
        )
    }

    @Test
    fun `should minimumIsland`() {
        assertEquals(
            3,
            "/graphs/map6.txt".load().lines().stringGrid(".").minimumIsland("#", p(6, 6))
        )

        assertEquals(
            3,
            "/graphs/map50x15.txt".load().lines().stringGrid(".").minimumIsland("#", p(15, 50))
        )
    }

    @Test
    fun `should maximumIsland`() {
        assertEquals(
            8,
            "/graphs/map6.txt".load().lines().stringGrid(".").maximumIsland("#", p(6, 6))
        )

        assertEquals(
            16,
            "/graphs/map50x15.txt".load().lines().stringGrid(".").maximumIsland("#", p(15, 50))
        )
    }

    @Test
    fun `should Maze`() {
        assertTrue(
            "/graphs/maze.txt".load().lines().stringGrid(".").hasPath(Position(0, 0), Position(12, 48)) { it == "." })
        assertFalse(
            "/graphs/maze.txt".load().lines().stringGrid(".").hasPath(Position(0, 0), Position(0, 10)) { it == "." })

        var path = "/graphs/maze.txt".load().lines().stringGrid(".")
            .shortestPath(Position(0, 0), Position(12, 48)) { it == "." }
        assertEquals(66, path.first)
        assertEquals(path.first + 1, path.second.size)
        assertEquals(
            "(0, 0);(0, 1);(0, 2);(1, 2);(1, 3);(1, 4);(1, 5);(2, 5);(3, 5);(4, 5);(4, 6);(4, 7);(4, 8);(4, 9);(5, 9);(5, 10);(5, 11);(6, 11);(7, 11);(8, 11);(9, 11);(9, 12);(9, 13);(10, 13);(10, 14);(10, 15);(10, 16);(10, 17);(10, 18);(10, 19);(10, 20);(10, 21);(9, 21);(9, 22);(9, 23);(9, 24);(9, 25);(9, 26);(9, 27);(9, 28);(9, 29);(9, 30);(9, 31);(9, 32);(10, 32);(10, 33);(11, 33);(11, 34);(11, 35);(12, 35);(12, 36);(12, 37);(13, 37);(14, 37);(14, 38);(14, 39);(14, 40);(14, 41);(14, 42);(14, 43);(14, 44);(14, 45);(13, 45);(12, 45);(12, 46);(12, 47);(12, 48)",
            path.second.map { it.toString() }.join(";")
        )

        path = "/graphs/maze.txt".load().lines().stringGrid(".")
            .shortestPath(Position(0, 0), Position(0, 10)) { it == "." }
        assertEquals(-1, path.first)
        assertEquals(emptyList<Position>(), path.second)

        path = "/graphs/maze.txt".load().lines().stringGrid(".")
            .aStar(Position(0, 0), Position(12, 48)) { it == "." }
        assertEquals(66, path.first)
        assertEquals(path.first + 1, path.second.size)
        assertEquals(
            "(0, 0);(0, 1);(1, 1);(2, 1);(2, 2);(2, 3);(3, 3);(4, 3);(4, 4);(4, 5);(5, 5);(5, 6);(6, 6);(7, 6);(8, 6);(8, 7);(8, 8);(9, 8);(9, 9);(10, 9);(10, 10);(10, 11);(10, 12);(10, 13);(10, 14);(10, 15);(10, 16);(10, 17);(10, 18);(10, 19);(10, 20);(10, 21);(9, 21);(9, 22);(9, 23);(9, 24);(9, 25);(9, 26);(9, 27);(9, 28);(9, 29);(9, 30);(9, 31);(10, 31);(10, 32);(10, 33);(11, 33);(11, 34);(11, 35);(12, 35);(12, 36);(12, 37);(13, 37);(14, 37);(14, 38);(14, 39);(14, 40);(14, 41);(14, 42);(14, 43);(14, 44);(13, 44);(12, 44);(12, 45);(12, 46);(12, 47);(12, 48)",
            path.second.map { it.toString() }.join(";")
        )

        path = "/graphs/maze.txt".load().lines().stringGrid(".")
            .aStar(Position(0, 0), Position(12, 48), DistanceType.EUCLIDIAN) { it == "." }
        assertEquals(66, path.first)
        assertEquals(path.first + 1, path.second.size)
        assertEquals(
            "(0, 0);(0, 1);(1, 1);(2, 1);(2, 2);(2, 3);(3, 3);(4, 3);(4, 4);(4, 5);(5, 5);(5, 6);(6, 6);(7, 6);(8, 6);(8, 7);(8, 8);(9, 8);(9, 9);(10, 9);(10, 10);(10, 11);(10, 12);(10, 13);(10, 14);(10, 15);(10, 16);(10, 17);(10, 18);(10, 19);(10, 20);(10, 21);(9, 21);(9, 22);(9, 23);(9, 24);(9, 25);(9, 26);(9, 27);(9, 28);(9, 29);(9, 30);(9, 31);(10, 31);(10, 32);(10, 33);(11, 33);(11, 34);(11, 35);(12, 35);(12, 36);(12, 37);(13, 37);(14, 37);(14, 38);(14, 39);(14, 40);(14, 41);(14, 42);(14, 43);(14, 44);(13, 44);(12, 44);(12, 45);(12, 46);(12, 47);(12, 48)",
            path.second.map { it.toString() }.join(";")
        )
    }
}

fun String.load() = Day::class.java.getResource(this).readText()