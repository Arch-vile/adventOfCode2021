package day6

import utils.read

fun main() {
    solve().let { println(it) }
}

fun solve(): List<Long> {
    var startingFishes = read("./src/main/resources/day6Input.txt")
        .flatMap { it.split(",").map { it.toInt() } }

    return listOf(countFish(startingFishes, 80), countFish(startingFishes, 256))
}

private fun countFish(startingFishes: List<Int>, totalDays: Int): Long {
    val cache = mutableMapOf<String, Long>()

    fun countDescendants(dayNow: Int, ticker: Int): Long {
        if (dayNow > totalDays) return 0
        if (cache.contains("$dayNow,$ticker")) return cache["$dayNow,$ticker"]!!

        val firstSpawnOn = dayNow + ticker
        val spawnOn = (firstSpawnOn..totalDays step 7)

        // Count myself
        val result = 1 +
                // add all my children and their descendants
                spawnOn.sumOf { day -> countDescendants(day, 9) }

        cache["$dayNow,$ticker"] = result

        return result
    }

    return startingFishes.sumOf { countDescendants( 1, it) }
}
