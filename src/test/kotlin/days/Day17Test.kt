package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day17Test {
    private val day = Day17()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(362))
    }

    @Test
    fun testPartTwo() {
        assertThat(day.partTwo(), `is`(1980))
    }
}
