package day10

import utils.read

fun main( ) {
    solve().let { println(it) }
}

fun solve(): List<Int> {

    val input =
        read("./src/main/resources/day10SampleInput.txt")
            .map { it.toCharArray() }

    val syntaxChecks = input.map { checkSyntax(it) }
    val failureScore = syntaxChecks
        .map { when (it.prematureClose) {
            ')' -> 3
            ']' -> 57
            '}' -> 1197
            '>' -> 25137
            else -> 0
        } }
        .sum()

    val autocompleteScores = syntaxChecks
        .filter { it.prematureClose == null }
        .map {
            balanceChunks(it.openChunks)
        }.sorted()

    val autocompleteScore = autocompleteScores[autocompleteScores.size/2]
    return listOf(failureScore, autocompleteScore)
}

fun balanceChunks(openChunks: List<Char>): Int {
    return openChunks.reversed()
        .map {
            when(it) {
                '(' -> 1
                '[' -> 2
                '{' -> 3
                '<' -> 4
                else -> 0
            }
        }
        .reduce { acc, next -> acc*5 + next  }
}

data class SyntaxCheck(val prematureClose: Char?, val openChunks: List<Char>)

private fun checkSyntax(input: CharArray): SyntaxCheck {
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
    return SyntaxCheck(failedClose, balanced)
}
