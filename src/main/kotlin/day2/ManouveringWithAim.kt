package day2

import utils.read

data class Manouver(val aim: Int, val depth: Int, val position: Int)

fun main() {
    println(solve_part2())
}

fun solve_part2(): Int {

    val data = read("./src/main/resources/day2Input.txt")
        .asSequence()
        .map { it.split(" ") }
        .map { Pair(it[0], it[1].toInt()) }

    val result = data.fold(Manouver(0, 0, 0)) { acc, next ->
        if (next.first == "forward") {
            acc.copy(
                position = acc.position + next.second,
                depth = acc.depth + acc.aim * next.second
            )
        }
        else if (next.first == "up") {
            acc.copy(aim = acc.aim - next.second)
        }
        else {
            acc.copy(aim = acc.aim + next.second)
        }
    }

    return(result.depth * result.position)
}

