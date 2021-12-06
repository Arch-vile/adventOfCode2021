package day3

import utils.Binary
import utils.read

fun main() {
    solve().forEach { println(it) }
}


fun solve(): List<Long> {
    val data = read("./src/main/resources/day3Input.txt")
        .map { Binary.from(it) }

    var mostCommonBits = mostCommonBits(data)
    val gamma = mostCommonBits.asLong()
    val epsilon = mostCommonBits.invert().asLong()

    val oxygen = solve(data, 0, true)
    val co2 = solve(data, 0,false)

    return listOf(
        gamma * epsilon,
        oxygen.asLong()*co2.asLong())
}

fun solve(data: List<Binary>, i: Int, mostCommon: Boolean): Binary {

    if (data.size == 1) {
        return data.first()
    }
    else {
        val commonBits = mostCommonBits(data)
        val bitsToLook =
            if (mostCommon) commonBits else commonBits.invert()
        val filtered = data.filter {
            it.bit(i) == bitsToLook.bit(i)
        }
        return solve(filtered, i + 1, mostCommon);
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




