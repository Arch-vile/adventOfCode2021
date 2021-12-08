package day8

import utils.read

data class SegmentData(val input: List<String>, val output: List<String>)


fun main() {
    solve()
        .let { println(it) }
}

fun solve(): Int {
    var segmentData =
        read("./src/main/resources/day8Input.txt")
            .map { it.split(" | ") }
            .map { SegmentData(it[0].split(" "), it[1].split(" ")) }

    return segmentData
        .map { it.output }
        .flatten()
        .filter { it.length == 7 ||  it.length == 4 || it.length == 3 || it.length == 2}
        .count();



}

