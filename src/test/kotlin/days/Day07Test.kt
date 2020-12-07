package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day07Test {
    private val day = Day07()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(287))
    }

    @Test
    fun testPartTwo() {
        assertThat(day.partTwo(), `is`(48160))
    }
}
