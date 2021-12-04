package day3

import utils.Binary
import utils.read

fun main() {
    val data = read("./src/main/resources/day3SampleInput.txt")
        .map { Binary.from(it) }

    var mostCommonBits = mostCommonBits(data)
    val gamma = mostCommonBits.asLong()
    val epsilon = mostCommonBits.invert().asLong()
    println(gamma * epsilon)
}

fun solve(data: List<List<Long>>): List<Long> {

    if (data.size == 1) {
        return data.first()
    }
    else {
//        val commonBits = mostCommonBits(data)

        return data[0];
//        val newData =
//            filter data(1) == mostCommonBit

    }
}


private fun mostCommonBits(data: List<Binary>): Binary {
    // All rows have same amount of bits
    val mostCommon = (0 until data[0].bits().size)
        // Gives us the vertical bit rows
        .map { index -> data.map { it.bit(index) } }
        // Lets change 0 to -1
        .map { it.map { bit -> if (bit == 0) -1 else 1 } }
        // And finally if we take sum, we know did we have more 1's than 0's
        .map { if (it.sum() < 0) 0 else 1 }

    return Binary.from(mostCommon)
}




