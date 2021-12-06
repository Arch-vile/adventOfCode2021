import day4.solve
import org.junit.jupiter.api.Test
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
}
