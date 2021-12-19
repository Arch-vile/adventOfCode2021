package day15

import utils.Cursor
import utils.Matrix
import utils.graphs.Node
import utils.graphs.shortestPath
import utils.read


fun main() {
    solve().let { println(it) }
}

fun solve(): List<Long> {
    val data = read("./src/main/resources/day15Input.txt")
        .map { it.windowed(1, 1).map { it.toInt() } }
    val dataMatrix = Matrix(data)

    return listOf(
        solve1(dataMatrix),
//        solve2(dataMatrix)
    )
}

private fun toNodeMatrix(dataMatrix: Matrix<Int>): Matrix<Node<Pair<Cursor, Int>>> {
    val matrix = dataMatrix.map { Node(Pair(it.cursor, it.value)) }
    matrix.all().map { current ->
        val neighbourghs = matrix.getRelativeAt(
            current.cursor,
            listOf(Cursor(0, -1), Cursor(-1, 0), Cursor(1, 0), Cursor(0, 1))
        )

        neighbourghs.forEach { neighbour ->
            current.value.link(neighbour.value.value.second.toLong(), neighbour.value)
        }
    }
    return matrix
}


fun solve1(dataMatrix: Matrix<Int>): Long {
    val matrix = toNodeMatrix(dataMatrix)
    return shortestPath(
        matrix.get(0, 0).value,
        matrix.get(matrix.width() - 1, matrix.height() - 1).value
    )!!
}

fun solve2(inputMatrix: Matrix<Int>): Long {
    val dataMatrix =
        Matrix(inputMatrix.width().toLong() * 5, inputMatrix.height().toLong() * 5) { x, y -> 0 }
    var startFrom = inputMatrix
    for (y in 0..4) {
        var next = startFrom
        for (x in 0..4) {
            dataMatrix.combine(
                next,
                Cursor(x * inputMatrix.width(), y * inputMatrix.height())
            ) { existing, new -> new.value }
            next = duplicate(next)
        }
        startFrom = duplicate(startFrom)
    }

    val matrix = toNodeMatrix(dataMatrix)
    return shortestPath(
        matrix.get(0, 0).value,
        matrix.get(matrix.width() - 1, matrix.height() - 1).value
    )!!
}

private fun duplicate(matrix: Matrix<Int>) = matrix.map {
    val newValue = it.value + 1
    if (newValue > 9) 1 else newValue
}
