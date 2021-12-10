package me.grison.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class StringsTests {
    @Test
    fun occurrences() {
        assertEquals(2, "foo".occurrences('o'))
    }

    @Test
    fun at() {
        assertEquals('f', "foo".at(0))
        assertEquals('o', "foo".at(1))
        assertEquals('f', "foo".at(3))
    }

    @Test
    fun int() {
        assertEquals(10, "10".int())
        assertEquals(0, "lol".int())
    }

    @Test
    fun `in`() {
        assertTrue("a".`in`("b", "a", "c"))
    }

    @Test
    fun binary() {
        assertEquals(5, "101".binary())
    }

    @Test
    fun charSet() {
        assertEquals(setOf("f", "o", "b", "a", "r"), "foobar".charSet())
    }

    @Test
    fun butLast() {
        assertEquals("fooba", "foobar".butLast())
    }

    @Test
    fun between() {
        assertEquals("foobar", "[foobar]".between("[", "]"))
    }

    @Test
    fun afterLast() {
        assertEquals("foobar", "foo,foo,foobar".afterLast(","))
    }

    @Test
    fun allInts() {
        assertEquals(listOf(1, 2, 33), "hello 1, how 2, are 33 you".allInts())
    }

    @Test
    fun noSpaces() {
        assertEquals("foobar", "f  oo    b    ar        ".noSpaces())
    }

    @Test
    fun except() {
        assertEquals("fbar", "foobar".except("o"))
    }

    @Test
    fun before() {
        assertEquals("foo", "foobar".before("bar"))
    }

    @Test
    fun after() {
        assertEquals("bar", "foobar".after("foo"))
    }

    @Test
    fun or() {
        assertEquals("bar", "foo".or("lol", "bar"))
        assertEquals("foo", "foo".or("foo", "bar"))
    }

    @Test
    fun words() {
        assertEquals(listOf("hello", "kotlin", "world"), "hello kotlin world".words())
    }
}