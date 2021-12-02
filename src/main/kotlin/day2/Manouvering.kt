package day2

import utils.read

fun main(args: Array<String>) {
    val data = read("./src/main/resources/day2Input.txt")
        .asSequence()
        .map { it.split(" ") }
        .map { Pair(it[0], it[1].toInt()) }

    val up = data.filter { it.first == "up" }
        .map { it.second  }
        .sum()

    val down = data.filter { it.first == "down" }
        .map { it.second  }
        .sum()

    val forward = data.filter { it.first == "forward" }
        .map { it.second  }
        .sum()

    println((down - up) * forward)

}

