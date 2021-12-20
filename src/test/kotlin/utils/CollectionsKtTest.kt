package utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertContains

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

    @Test
    fun permutations() {
        assertEquals(
            setOf(
                listOf(3, 2, 1),
                listOf(2, 3, 1),
                listOf(3, 1, 2),
                listOf(1, 3, 2),
                listOf(2, 1, 3),
                listOf(1, 2, 3)
            ),
            permutations(setOf(1, 2, 3))
        )
    }


    @Test
    fun sortedLookup_override_values() {
        val foo = SortedLookup<String,Long>()
        foo.add("foo",1)
        foo.add("foo",3)
        assertEquals(3, foo.get("foo"))
    }

    @Test
    fun sortedLookup_same_value_for_many_keys() {
        val foo = SortedLookup<String,Long>()
        foo.add("foo",1)
        foo.add("bar",1)

        assertEquals(
            setOf("foo","bar"),
            foo.sortedSequence().map { it.first }
        )
    }

}
