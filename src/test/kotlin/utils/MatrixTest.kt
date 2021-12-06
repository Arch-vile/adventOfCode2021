package utils

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

internal class MatrixTest {

    @Test
    fun tile() {
        val source = Matrix(listOf(
            listOf(0,0,1,0),
            listOf(0,1,1,0)
        ))

        assertEquals(
           Matrix(listOf(
               listOf(0,1),
               listOf(1,1)
           )),
            source.tile(Point(1,0), Point(2,1))
        )
    }

    @Test
    fun equals() {
        assertEquals(
            Matrix(listOf(listOf(1,2), listOf(3,4), listOf(5,6))),
            Matrix(listOf(listOf(1,2), listOf(3,4), listOf(5,6))),
        )

        assertNotEquals(
            Matrix(listOf(listOf(1,2), listOf(3,4), listOf(5,6))),
            Matrix(listOf(listOf(1,2), listOf(1,4), listOf(5,6))),
        )

    }

}
