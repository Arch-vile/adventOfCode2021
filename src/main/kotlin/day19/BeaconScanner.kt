package day19

import utils.Point
import utils.read
import utils.splitAfter


fun main() {

    val data = read("./src/main/resources/day19Input.txt")
        .splitAfter("")
        .map {
            if(it.last() == "")
                it.drop(1).dropLast(1)
            else
                it.drop(1)
        }
        .map { it.map {
            val parts = it.split(",")
            Point(parts[0].toLong(), parts[1].toLong(), parts[2].toLong())
        } }

    data
        .forEach {

        val distances = allPairs(it).map { it.first.distance(it.second) }
            .map { it.toInt() }
            .sorted()
            .take(20)
        println(distances)

    }

}

fun allPairs(it: List<Point>): List<Pair<Point, Point>> {
    val pairs = mutableListOf<Pair<Point,Point>>()
    for(i in it.indices-1) {
        for(j in (i+1 until it.size)) {
           pairs.add(Pair(it[i],it[j]))
        }
    }
    return pairs.toList()
}
