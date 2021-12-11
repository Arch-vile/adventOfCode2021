package day10

import utils.read

fun main( ) {
    solve()
}

fun solve(): List<Int> {

    val input =
        read("./src/main/resources/day10Input.txt")
//        listOf("{([(<{}[<>[]}>{[]{[(<()>")
            .map { it.toCharArray() }

    val failedClosings = input.map { checkClosings(it) }

    val failureScore = failedClosings
        .map { when (it) {
            ')' -> 3
            ']' -> 57
            '}' -> 1197
            '>' -> 25137
            else -> 0
        } }
        .sum()

    println(failureScore)

    return listOf(failureScore)
}

private fun checkClosings(input: CharArray): Char? {
    val balanced = mutableListOf<Char>()
    var failedClose: Char? = null
    for (next in input) {
        if (failedClose != null) break

        if (next == ')') {
            if (balanced.last() == '(') balanced.removeLast()
            else failedClose = next
        }
        else if (next == ']') {
            if (balanced.last() == '[') balanced.removeLast()
            else failedClose = next
        }
        else if (next == '}') {
            if (balanced.last() == '{') balanced.removeLast()
            else failedClose = next
        }
        else if (next == '>') {
            if (balanced.last() == '<') balanced.removeLast()
            else failedClose = next
        }
        else balanced.add(next)
    }
    return failedClose
}
