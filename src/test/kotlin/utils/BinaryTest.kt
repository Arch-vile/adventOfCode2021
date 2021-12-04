package utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class BinaryTest {

    @Test
    fun from_long() {
        assertEquals(123L, Binary.from(123L).asLong())
        assertEquals(0L, Binary.from(0L).asLong())
        assertThrows(Error::class.java) {
            Binary.from(-1L)
        }
    }

    @Test
    fun from_binary_string() {
        assertEquals(
            38234,
            Binary.from("1001010101011010".reversed()).asLong()
        )
    }

    @Test
    fun asBits() {
        assertEquals(
            listOf(1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0).reversed(),
            Binary.from(38234).bits()
        )
    }

    @Test
    fun asString() {
        assertEquals("0111011", Binary.from(110).asString())
        assertEquals("01110110", Binary.from(110).asString(8))
        assertEquals("0111011", Binary.from(110).asString(1))
    }

    @Test
    fun bit() {
        val binary = Binary.from("010101")
        assertEquals(0, binary.bit(0))
        assertEquals(1, binary.bit(1))
        assertEquals(0, binary.bit(2))

        // Indexes continue till infinity
        assertEquals(0, binary.bit(33))
    }

    @Test
    fun invert() {
        assertEquals(
            Binary.from("111000").bits(),
            Binary.from("000111").invert().bits()
        )

        // Invert less
        assertEquals(
            Binary.from("111").bits(),
            Binary.from("000111").invert().bits()
        )
    }

}
