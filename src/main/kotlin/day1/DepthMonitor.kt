package day1

import utils.read

fun main(args: Array<String>) {
    val data = read("./src/main/resources/day1Input.txt").asSequence().map { it.toInt() }
    println("part1: ${countDepth(data)}")

    val windowedData = data.windowed(3,1).map { it.sum() }
    println("part2: ${countDepth(windowedData)}")
}

private fun countDepth(data: Sequence<Int>) = data
    .windowed(2, 1)
    .map { it[1] - it[0] }
    .filter { it > 0 }
    .count()


