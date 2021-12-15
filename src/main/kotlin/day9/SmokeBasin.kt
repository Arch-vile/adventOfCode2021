package day9

import utils.Cursor
import utils.Entry
import utils.Matrix
import utils.read

fun main() {
    solve().let { println(it) }
}

val surroundingCoords = listOf(
    Cursor(0, -1),
    Cursor(-1, 0),
    Cursor(1, 0),
    Cursor(0, 1)
)

fun solve(): List<Int> {
    val input = read("./src/main/resources/day9Input.txt")
        .map { it.map { it.toString().toInt() } }

    val floor = Matrix(input)
    val lowestPoints = floor.all()
        .filter { current ->
            val surrounding = floor.getRelativeAt(
                current.cursor,
                surroundingCoords
            )

            val lowerNeighbours = surrounding
                .filter { neighbor -> neighbor.value > current.value }

            lowerNeighbours.count() == surrounding.count()
        }

    val basins = lowestPoints.map { basin(floor, it) }
    val largestBasins = basins.sortedBy { it.size }.reversed().take(3)

    return listOf(lowestPoints.map { it.value + 1 }.sum(),
        largestBasins.map { it.size }.reduce { acc, next -> acc * next }
    )
}

fun basin(floor: Matrix<Int>, start: Entry<Int>): Set<Entry<Int>> {
    var expanded = setOf(start)

    do {
        var oldSize = expanded.size
        expanded = expand(floor, expanded)
    } while (expanded.size != oldSize)

    return expanded;
}

fun expandSingle(floor: Matrix<Int>, startFrom: Entry<Int>): List<Entry<Int>> {
    return floor.getRelativeAt(startFrom.cursor, surroundingCoords)
        .filter { it.value > startFrom.value && it.value != 9 }
}

fun expand(floor: Matrix<Int>, current: Set<Entry<Int>>): Set<Entry<Int>> {
    return current.map { expandSingle(floor, it) }.flatten().toSet().plus(current)
}
