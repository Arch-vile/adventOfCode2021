package day5

import utils.Line
import utils.Point
import utils.Matrix
import utils.pointsInLine
import utils.read


fun main() {
    solve()
        .let { println(it) }
}

fun solve(): List<Int> {
    val lines = read("./src/main/resources/day5Input.txt")
        .map { it.split(" ") }
        .map { line ->
            val firsCoord = line[0].split(",") // ['5','6']
            val secondCoord = line[2].split(",")
            Line(
                Point(firsCoord[0].toLong(), firsCoord[1].toLong()),
                Point(secondCoord[0].toLong(), secondCoord[1].toLong()),
                )
        }

    val allPointsInLines = lines
        .filter { it.start.x == it.end.x || it.start.y == it.end.y }
        .flatMap { pointsInLine(it) }
    val seaFloor = Matrix(1000,1000){ x,y -> 0 }
    allPointsInLines.forEach { point ->
        seaFloor.replace(point.x.toInt(), point.y.toInt()) { entry -> entry.value + 1 }
    }

    return listOf(seaFloor.findAll { it.value >= 2 }.count())

}

