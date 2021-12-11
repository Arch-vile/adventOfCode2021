package day8

import utils.permutations
import utils.read
import utils.sort

data class SegmentData(val input: List<String>, val output: List<String>)


fun main() {
    solve()
        .let { println(it) }
}

const val allSegments = "abcdefg"
const val zero = "abcefg"
const val one = "cf"
const val two = "acdeg"
const val three = "acdfg"
const val four = "bcdf"
const val five = "abdfg"
const val six = "abdefg"
const val seven = "acf"
const val eight = "abcdefg"
const val nine = "abcdfg"
val allNumbers = listOf(zero, one, two, three, four, five, six, seven, eight, nine)

fun solve(): List<Int> {
   return listOf(solvePart1(), solvePart2())
}

fun solvePart2(): Int {
    var readings =
        read("./src/main/resources/day8Input.txt")
            .map { it.split(" | ") }
            .map {
                SegmentData(
                    it[0].split(" ").map { sort(it) },
                    it[1].split(" ").map { sort(it) })
            }

    val decodeKeyCandidates = permutations(allSegments.toSet())
    val decodedOutputs = readings.map { decodeOutput(decodeKeyCandidates, it) }
    val asNumbers =
        decodedOutputs.map { it.map { allNumbers.indexOf(it) }.joinToString("").toInt() }

    return asNumbers.sum()
}

fun decodeOutput(decoders: Set<List<Char>>, reading: SegmentData): List<String> {
    val correctDecoder = decoders.first { decoder ->
        val decodedInput = reading.input.map { messedNumber -> decode(messedNumber, decoder) }
        decodedInput.containsAll(allNumbers)
    }

    return reading.output.map { decode(it, correctDecoder) }
}

private fun decode(
    messedNumber: String,
    decoder: List<Char>
) = sort(
    messedNumber
        .replace(decoder[0], 'A')
        .replace(decoder[1], 'B')
        .replace(decoder[2], 'C')
        .replace(decoder[3], 'D')
        .replace(decoder[4], 'E')
        .replace(decoder[5], 'F')
        .replace(decoder[6], 'G').toLowerCase()
)

fun solvePart1(): Int {
    var segmentData =
        read("./src/main/resources/day8Input.txt")
            .map { it.split(" | ") }
            .map { SegmentData(it[0].split(" "), it[1].split(" ")) }

    return segmentData
        .map { it.output }
        .flatten()
        .filter { it.length == 7 || it.length == 4 || it.length == 3 || it.length == 2 }
        .count();


}

