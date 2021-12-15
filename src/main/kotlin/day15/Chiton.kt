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
    val matrix = Matrix(data).map { Node(Pair(it.cursor, it.value)) }

    matrix.all().map { current ->
        val neighbourghs = matrix.getRelativeAt(
            current.cursor,
            listOf(Cursor(0, -1), Cursor(-1, 0), Cursor(1, 0), Cursor(0, 1))
        )

        neighbourghs.forEach { neighbour ->
            current.value.link(neighbour.value.value.second.toLong(),  neighbour.value)
        }
    }

    return listOf(solve1(matrix))
}

fun solve1(matrix: Matrix<Node<Pair<Cursor, Int>>>): Long {
    return shortestPath(matrix.get(0,0).value, matrix.get(matrix.width()-1, matrix.height()-1).value)!!
}
