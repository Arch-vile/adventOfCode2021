package day1

import utils.read

fun main(args: Array<String>) {
    val count = read("./src/main/resources/day1Input.txt")
        .asSequence()
        .map { it.toInt() }
        .windowed(2,1)
        .map { it[1] - it[0] }
        .filter { it > 0 }
        .count()

    println(count);
}


