package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day16Test {
    private val day = Day16()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(19087))
    }

    @Test
    fun testPartTwo() {
        assertThat(day.partTwo(), `is`(1382443095281))
    }
}
