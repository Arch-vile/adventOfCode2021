package day3

import utils.read
import utils.rotateCW

fun main() {

    val data = read("./src/main/resources/day3Input.txt")
        .map{ it.toCharArray().toList()}
        .map { it.map { it.toString().toLong() } }

    var mostCommonBits = rotateCW(data)
        .map { it.map { value -> if (value == 0L) -1 else 1 } }
        .map { it.sum() }
        .map { if (it < 0) 0 else 1 }

    val gamma = mostCommonBits.joinToString("").toLong(2)

    val epsilon = mostCommonBits
        .map { if (it == 1) 0 else 1 }
        .joinToString("")
        .toLong(2)

    println(gamma*epsilon)
}
