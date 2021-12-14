package day14

import utils.read

data class Rule(val first: Char, val second: Char, val insert: Char)

fun main() {
    solve()
        .let { println(it) }
}

fun solve(): List<Int> {

    val input = read("./src/main/resources/day14Input.txt")

   var template = input.first().toCharArray().toList()

   val rules = input.drop(2).map {
       val array  = it.toCharArray()
       Rule(array[0],array[1],array[6])
   }

    (1..10).forEach {
        template = template.windowed(2, 1)
            .map { polymer ->
                val applyingRule = rules.find {
                    it.first == polymer[0] && it.second == polymer[1]
                }
                if (applyingRule != null) {
                    listOf(applyingRule.first, applyingRule.insert)
                }
                else listOf(polymer[0])
            }.flatten().plus(template.last())
    }

   val mapped = template.groupBy { it }
    val sortedByOccurance = mapped.values.sortedBy { it.size }
    return listOf(sortedByOccurance.last().size-sortedByOccurance.first().size);
}
