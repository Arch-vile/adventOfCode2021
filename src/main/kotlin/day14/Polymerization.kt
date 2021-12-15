package day14

import utils.merge
import utils.read

data class Rule(val first: Char, val second: Char, val insert: Char)

fun main() {
    solve().let { println(it) }
}

fun solve() = listOf(day14.solve(10), day14.solve(40))

fun solve(rounds: Int): Long {
    val input = read("./src/main/resources/day14Input.txt")
    var template = input.first().toCharArray().toList()
    val rules = input.drop(2).map {
        val array = it.toCharArray()
        Rule(array[0], array[1], array[6])
    }

    val countsInTemplate = template.groupBy { it }
        .mapValues { it.value.size.toLong() }

    val partlyPolymerCounts = template.windowed(2, 1)
        .map {
            countPolymers(rounds, Pair(it[0], it[1]), rules)
        }.plus(countsInTemplate)

    val result = merge(partlyPolymerCounts) { a, b -> a+b}
    val sorted = result.entries.sortedBy { it.value }
    val smallest = sorted.first().value
    val largest = sorted.last().value
    return largest-smallest
}

typealias CacheKey = Pair<Int, Pair<Char,Char>>
var cache: MutableMap<CacheKey, Map<Char,Long>> = mutableMapOf()

private fun cacheKey(
    roundsRemaining: Int,
    template: Pair<Char, Char>
) = Pair(roundsRemaining, template)

private fun countPolymers(
    roundsRemaining: Int,
    template: Pair<Char, Char>,
    rules: List<Rule>
): Map<Char, Long> {
    if (roundsRemaining == 0) {
        return mapOf()
    }

    val cacheKey = cacheKey(roundsRemaining,template)
    if(cache.containsKey(cacheKey)) {
       return cache[cacheKey]!!
    }

    val expanded = applyRule(template, rules)
    if (expanded.size > 1) {
        val inserted = expanded[0].second
        val counts = mutableMapOf<Char, Long>()
        counts[inserted] = 1
        val leftCounts = countPolymers(roundsRemaining - 1, expanded[0], rules)
        val rightCounts = countPolymers(roundsRemaining - 1, expanded[1], rules)
        val result = merge(listOf(counts,leftCounts,rightCounts)) { a, b -> a+b}
        cache[cacheKey] =result
        return result
    } else
        return mapOf()
}

fun applyRule(polymer: Pair<Char, Char>, rules: List<Rule>): List<Pair<Char, Char>> {
    val applyingRule = rules.find {
        it.first == polymer.first && it.second == polymer.second
    }
    return if (applyingRule != null) {
        listOf(
            Pair(polymer.first, applyingRule.insert),
            Pair(applyingRule.insert, polymer.second)
        )
    }
    else {
        listOf(polymer)
    }
}
