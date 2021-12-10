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
        assertEquals(
            2,
            listOf(
                p('w', 'x'),
                p('x', 'y'),
                p('z', 'y'),
                p('z', 'v'),
                p('w', 'v'),
            ).toGraph().shortestPath('w', 'z')
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

    //@Test
    //fun `should islandCount`() {
    //    assertEquals(
    //        3,
    //        """WLWWW
    //           WLWWW
    //           WWWLW
    //           LWWLL
    //           LLWWW""".trimMargin().lines().stringGrid("W").islandCount("L", p(5, 5))
    //    )
    //}

}