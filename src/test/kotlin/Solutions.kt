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
        assertEquals(day2.solve(), 1580000);
        assertEquals(day2.solve_part2(), 1251263225)
    }

    @Test
    fun day3(){
        assertEquals(day3.solve(),
            listOf(3242606L,4856080L))
    }
    @Test
    fun day4() {
        assertEquals(solve(), listOf(87456,15561))
    }
}
