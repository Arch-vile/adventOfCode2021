package utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CollectionsKtTest {

    @Test
    fun combinations() {
        assertEquals( setOf(
            listOf(1),
            listOf(2),
            listOf(3),
            listOf(1,2),
            listOf(1,3),
            listOf(2,3),
            listOf(1,2,3),
        ), combinations(listOf(1,2,3)),)
    }

    @Test
    fun combinations_with_duplicates() {
        assertEquals( setOf(
            listOf(1),
            listOf(2),
            listOf(1,2),
            listOf(2,2),
            listOf(1,2,2),
        ), combinations(listOf(1,2,2)),)
    }

}
