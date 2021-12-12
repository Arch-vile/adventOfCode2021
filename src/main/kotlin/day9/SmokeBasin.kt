package day9

import utils.Matrix
import utils.read


fun main() {
    solve()
        .let { println(it) }
}

fun solve(): List<Int> {

    val input =
        read("./src/main/resources/day9Input.txt")
//        listOf(
//            "2199943210",
//            "3987894921",
//            "9856789892",
//            "8767896789",
//            "9899965678" )
                    .map { it.map { it.toString().toInt() } }

    val floor = Matrix(input)

    val lowestPoints = floor.all()
        .filter { current ->
            val surrounding = floor.getRelativeAt(
                current.x, current.y,
                listOf(
                    Pair(0, -1),
                    Pair(-1, 0),
                    Pair(1, 0),
                    Pair(0, 1)
                )
            )

            val lowerNeighbours = surrounding
                .filter { neighbor -> neighbor.value > current.value }

            lowerNeighbours.count() == surrounding.count()
        }

//    lowestPoints.forEach { println(it) }

    val riskLevelSum = lowestPoints.map { it.value+1 }.sum()

    return listOf(riskLevelSum)
}

