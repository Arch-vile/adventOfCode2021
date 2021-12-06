package day6

import utils.read

fun main() {
    solve()
        .let { println(it) }
}


fun solve(): List<Int> {

//    var fish = listOf(3, 4, 3, 1, 2).map { it.toInt() }
    var fish =  read("./src/main/resources/day6Input.txt")
        .flatMap { it.split(",").map { it.toInt() } }
    val days = 80

    (1..days)
        .forEach { day ->
            fish = fish.flatMap {
                val newFish = mutableListOf<Int>()
                if(it == 0) {
                    newFish.add(8)
                }
                var newValue = if (it == 0) { 6 } else { it - 1 }
                newFish.add(newValue)
                newFish
            }
        }
   return listOf(fish.count())
}
