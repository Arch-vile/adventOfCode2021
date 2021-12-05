package day4

import utils.Matrix
import utils.read

fun main() {
    val numbers = read("./src/main/resources/day4Input.txt")
        .take(1)
        .flatMap { it.split(",") }
        .map { it.toInt() }

    val sheets = read("./src/main/resources/day4Input.txt")
        .drop(1)
        .windowed(6,6)
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

    val bingos = numbers.asSequence()
        .flatMap { number ->
            sheets.filter { sheet ->
                val found = sheet.find(Pair(number, false))
                if (found != null) {
                    sheet.replace(found, Pair(number, true))
                    val completedRows = sheet.findRowsByValues { value -> value.second }
                    val completedColumns = sheet.findColsByValues { value -> value.second }
                    completedRows.isNotEmpty() || completedColumns.isNotEmpty()
                }
                else false
            }.map { sheet -> Pair(number,sheet) }
        }

    val winner = bingos.first()
    val sumOfMissedNmbr = winner.second.findAll{ value -> !value.second }.map { it.value.first }.sum()
    println(sumOfMissedNmbr * winner.first)

}
