package day6

import utils.read

fun main() {
    solve()
        .let { println(it) }
}

fun solve(): List<Int> {
    var startingFishes = read("./src/main/resources/day6Input.txt")
        .flatMap { it.split(",").map { it.toInt() } }

    val value =
        startingFishes.sumOf {
            countDescendants(256, 1, it)
        }

    println(value)
    return listOf()
}

val cache = mutableMapOf<String, Long>()

// ticker only matters for the initial ones
fun countDescendants(totalDays: Int, dayNow: Int, ticker: Int): Long {

    if (dayNow > totalDays) {
        return 0;
    }

    if (cache.contains("$dayNow,$ticker")) {
        return cache["$dayNow,$ticker"]!!
    }

    val firstReroduceOn = dayNow + ticker
    val daysOnWhichIReproduce =
        listOf(firstReroduceOn).plus(
            (firstReroduceOn + 7..totalDays step 7)
        )

    // Myself
    val result = 1 +
            // all my children and their descendants
            daysOnWhichIReproduce.sumOf { dayOfReproduce ->
                countDescendants(totalDays, dayOfReproduce, 9)
            }

    cache.put("$dayNow,$ticker", result)

    return result
}

private fun reproduce(
    days: Int,
    fish: List<Int>
): List<Int> {
    var fish1 = fish
    (1..days)
        .forEach { day ->

            if (day % 10 == 0)
                println(day)

            fish1 = fish1.flatMap {
                val newFish = mutableListOf<Int>()
                if (it == 0) {
                    newFish.add(8)
                }
                var newValue = if (it == 0) {
                    6
                }
                else {
                    it - 1
                }
                newFish.add(newValue)
                newFish
            }
        }
    return fish1
}
