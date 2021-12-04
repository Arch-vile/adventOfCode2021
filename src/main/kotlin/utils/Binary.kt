package utils

import java.lang.Integer.max
import java.lang.Integer.min
import java.util.BitSet

/**
 * All operations work with least significant bit first order
 */
class Binary private constructor(private val bits: BitSet, private val bitCount: Int) {
    fun asLong(): Long {
        if (bits.toLongArray().size > 1) {
            throw Error("Bits have expanded over single long value")
        }

        return if (bits.toLongArray().isEmpty()) 0
        else
            bits.toLongArray()[0]
    }

    fun bits(): List<Int> =
        (bitCount-1 downTo 0).map { bit(it) }

    /**
     * Index 0 has the least significant bit
     */
    fun bit(index: Int): Int = if (bits.get(index)) 1 else 0

    fun asString(): String {
        return (bitCount-1 downTo 0)
            .map { if (bit(it) == 1) '1' else '0' }
            .joinToString("")
    }

    /**
     * New binary with inverted bits.
     */
    fun invert(): Binary {
        val copy = (bits.clone() as BitSet)
        copy.flip(0, bitCount)
        return Binary(copy, bitCount)
    }

    companion object {
        fun from(from: Long, bitCount: Int): Binary {
            if (from < 0) throw Error("Negative numbers not allowed")
            return Binary(BitSet.valueOf(longArrayOf(from)),bitCount)
        }

        /**
         * String must have least significant bit first
         */
        fun from(from: String): Binary {
            val bits = BitSet()
            from.forEachIndexed { index, bit ->
                // Bitset has least significant bits first instead
                val i = from.length - index - 1
                when (bit) {
                    '1' -> bits.set(i)
                    '0' -> bits.clear(i)
                    else -> throw Error("$bit at index $index is not 0 or 1")
                }
            }
            return Binary(bits,from.length)
        }

        /**
         * Least significant bit first
         */
        fun from(from: List<Int>): Binary {
            val bits = BitSet()
            from.forEachIndexed { index, bit ->
                when (bit) {
                    1 -> bits.set(index)
                    0 -> bits.clear(index)
                    else -> throw Error("$bit at index $index is not 0 or 1")
                }
            }
            return Binary(bits, from.size)
        }
    }

}


