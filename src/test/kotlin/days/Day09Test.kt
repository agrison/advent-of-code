package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day09Test {
    private val day = Day09()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(41682220L))
    }

    @Test
    fun testPartTwo() {
        assertThat(day.partTwo(), `is`(5388976))
    }
}
