package day13

import utils.Matrix
import utils.Point
import utils.read

fun main() {
    solve().let { println(it) }
}

fun solve(): Pair<Int, String> {
    val lines = read("./src/main/resources/day13Input.txt")
    val points = lines.takeWhile { it.isNotEmpty() }
        .map { it.split(",") }
        .map { Point(it[0].toLong(), it[1].toLong()) }

 val folds = lines.dropWhile { it.isNotEmpty() }
     .drop(1)
     .map {
         if (it.contains("y=")) {
             Pair(0L, it.split("=")[1].toLong())
         } else
             Pair(it.split("=")[1].toLong(),0L)
     }

    val sheetWidth = points.maxOf { it.x } + 1L
    val sheetHeight = points.maxOf { it.y } + 1L

    val sheet = Matrix(sheetWidth!!,sheetHeight!!){
        x,y -> if(points.contains(Point(x.toLong(),y.toLong()))) "#" else " "
    }

    val foldedOnce = fold(folds[0], sheet)
    val folded = folds.fold(sheet) {
        acc, next -> fold(next,acc)
    }

    val notBlanks = foldedOnce.all().map { it.value }
        .filter { it.isNotBlank() }
        .count()

    return Pair(notBlanks, folded.toString())

}

fun fold(pair: Pair<Long, Long>, sheet: Matrix<String>): Matrix<String> {
    return if(pair.first != 0L) foldX(pair.first,sheet) else foldY(pair.second, sheet)
}

fun foldX(foldAt: Long, sheet: Matrix<String>): Matrix<String> {
    return foldY(foldAt, sheet.rotateCW()).rotateCW().rotateCW().rotateCW()
}

fun foldY(foldAt: Long, sheet: Matrix<String>): Matrix<String> {

    val upperPart = sheet.tile(Point(0,0),
        Point(sheet.width().toLong()-1,foldAt-1))

    val lowerPart = sheet.tile(Point(0,foldAt+1),
        Point(sheet.width().toLong()-1,sheet.height().toLong()-1))

    val lowerFlipped = lowerPart.flipHorizontal()

    if(lowerPart.height() != upperPart.height() ||
        lowerPart.width() != upperPart.width()) {
        Error("Different size, please fix")
    }
    upperPart.combine(lowerFlipped) {
            u, l -> if (u.value.isNotBlank() || l.value.isNotBlank()) "#" else " "
    }

    return upperPart
}
