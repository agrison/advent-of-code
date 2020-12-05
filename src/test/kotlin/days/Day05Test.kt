package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test
import kotlin.runCatching

class Day05Test {
    private val day = Day05()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(820))
    }

    @Test
    fun testPartTwo() {
        assertThat(runCatching { day.partTwo() }.isFailure, `is`(true))
    }
}
