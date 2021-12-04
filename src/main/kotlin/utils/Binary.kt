package utils

import java.util.BitSet

/**
 * All operations work with least significant bit first order
 */
class Binary private constructor(private val bits: BitSet) {
    fun asLong(): Long {
        if (bits.toLongArray().size > 1) {
            throw Error("Bits have expanded over single long value")
        }

        return if (bits.toLongArray().isEmpty()) 0
        else
            bits.toLongArray()[0]
    }

    /**
     * Least significant bit first. No padding zeros at end.
     */
    fun bits(): List<Int> =
        (0 until bits.length()).map { bit(it) }

    fun bit(index: Int): Int = if (bits.get(index)) 1 else 0

    fun asString(): String = asString(0);

    /**
     * With padded zeros for most significant bits until given length reached
     */
    fun asString(padEnd: Int): String {
        return (0 until maxOf(bits.length(), padEnd))
            .map { if (bit(it) == 1) '1' else '0' }
            .joinToString("")
    }

    /**
     * New binary with inverted bits.
     */
    fun invert(): Binary {
        val copy = (bits.clone() as BitSet)
        copy.flip(0, copy.length())
        return Binary(copy)
    }

    companion object {
        fun from(from: Long): Binary {
            if (from < 0) throw Error("Negative numbers not allowed")
            return Binary(BitSet.valueOf(longArrayOf(from)))
        }

        /**
         * String must have least significant bit first
         */
        fun from(from: String): Binary {
            val bits = BitSet()
            from.forEachIndexed { index, bit ->
                when (bit) {
                    '1' -> bits.set(index)
                    '0' -> bits.clear(index)
                    else -> throw Error("$bit at index $index is not 0 or 1")
                }
            }
            return Binary(bits)
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
            return Binary(bits)
        }
    }

}


