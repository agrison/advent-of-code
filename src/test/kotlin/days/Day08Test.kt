package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day08Test {
    private val day = Day08()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(1262))
    }

    @Test
    fun testPartTwo() {
        assertThat(day.partTwo(), `is`(1643))
    }
}
