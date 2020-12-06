package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day06Test {
    private val day = Day06()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(11))
    }

    @Test
    fun testPartTwo() {
        assertThat(day.partTwo(), `is`(6))
    }
}
