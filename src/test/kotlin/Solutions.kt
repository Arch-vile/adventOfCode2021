import day4.solve
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate
import kotlin.test.assertEquals

class Solutions {

    @Test
    fun testSum() {
        val expected = 42
        assertEquals(expected, 40 + 2)
    }

    @Test
    fun day2(){
        assertEquals( 1580000,day2.solve());
        assertEquals( 1251263225, day2.solve_part2())
    }

    @Test
    fun day3(){
        assertEquals(listOf(3242606L,4856080L),day3.solve())
    }
    @Test
    fun day4() {
        assertEquals(listOf(87456,15561),solve())
    }

    @Test
    fun day5() {
        assertEquals(listOf(6283,18864),day5.solve())
    }

    @Test
    fun day6() {
        assertEquals(listOf(365131,1650309278600),day6.solve())
    }

    @Test
    fun day8() {
        assertEquals(listOf(261,987553),day8.solve())
    }

    @Test
    fun day9() {
        assertEquals(listOf(436,1317792),day9.solve())
    }

    @Test
    fun day10() {
        assertEquals(listOf(321237L,2360030859L),day10.solve())
    }

    @Test
    fun day12() {
        assertEquals(listOf(4792,133360),day12.solve())
    }
}
