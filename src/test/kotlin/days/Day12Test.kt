package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day12Test {
    private val day = Day12()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(1007))
    }

    @Test
    fun testPartTwo() {
        assertThat(day.partTwo(), `is`(41212))
    }
}
