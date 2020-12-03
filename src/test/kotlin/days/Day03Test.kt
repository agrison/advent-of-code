package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day03Test {
    private val day = Day03()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(7L))
    }

    @Test
    fun testPartTwo() {
        assertThat(day.partTwo(), `is`(336L))
    }
}
