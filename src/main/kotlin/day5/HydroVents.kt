package day5

import day4.solve
import utils.Point
import utils.Matrix
import utils.read

data class Line(val start: Point, val end: Point)

fun main() {
    solve()
        .forEach { println(it) }
}

fun solve(): Int {
    val lines = read("./src/main/resources/day5SampleInput.txt")
        .map { it.split(" ") }
        .map { line ->
            val firsCoord = line[0].split(",") // ['5','6']
            val secondCoord = line[2].split(",")
            Line(
                Point(firsCoord[0].toLong(), firsCoord[1].toLong()),
                Point(secondCoord[0].toLong(), secondCoord[1].toLong()),
                )
        }

    val allPointsInLines = lines.flatMap { pointsInLine(it) }
    val seaFloor = Matrix(10,10){ x,y -> 0 }
    allPointsInLines.forEach { point ->
        seaFloor.replace(point.x, point.y) { entry -> entry.value + 1 }
    }

    return 1;
}

fun pointsInLine(line: Line): List<Point> {
   TODO()
}
