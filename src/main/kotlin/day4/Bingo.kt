package day4

import utils.Matrix
import utils.read

typealias Sheet = Matrix<Pair<Int, Boolean>>

fun main() {
    solve().forEach { println(it) }
}

fun solve(): List<Any> {
    val numbers = read("./src/main/resources/day4Input.txt")
        .take(1)
        .flatMap { it.split(",") }
        .map { it.toInt() }

    val sheets = read("./src/main/resources/day4Input.txt")
        .drop(1)
        .windowed(6, 6)
        .map { rows ->
            rows.drop(1)
                .map { row ->
                    row.windowed(2, 3)
                        .map {
                            Pair(it.replace(" ", "").toInt(), false)
                        }
                }
        }
        .map { Matrix(it) }

    var nonWinningSheets = sheets.toMutableList()
    var winningSheets = mutableListOf<Pair<Int,Sheet>>();

    for (number in numbers) {
        for (sheet in nonWinningSheets) {
            val found = sheet.find(Pair(number, false))
            if (found != null) {
                sheet.replace(found, Pair(number, true))
                val completedRows = sheet.findRowsByValues { entry -> entry.value.second }
                val completedColumns = sheet.findColsByValues { entry -> entry.value.second }
                if (completedRows.isNotEmpty() || completedColumns.isNotEmpty()) {
                    winningSheets.add(Pair(number,sheet))
                }
            }
        }
        nonWinningSheets.removeAll(winningSheets.map { it.second })
    }

    return listOf(
        calculateScore(winningSheets.first()),
        calculateScore(winningSheets.last())
    )
}

private fun calculateScore(sheet: Pair<Int, Sheet>): Int {
    val sumOfMissedNmbr =
        sheet.second.findAll { entry -> !entry.value.second }.map { it.value.first }.sum()
    return sumOfMissedNmbr * sheet.first
}
