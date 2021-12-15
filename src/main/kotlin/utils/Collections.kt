package utils


/**
 * Returns all the possible combinations (order does not matter) formed from given values.
 * Each combination will have at least one element.
 *
 * combinations([1,2,3]) =>  [[1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
 *
 * Duplicates are allowed if you feed in duplicates:
 * combinations([1,2,2]) => [[1], [2], [1, 2], [2, 2], [1, 2, 2]]
 */
fun <T> combinations(collection: List<T>): Set<List<T>> {
    val subsets = mutableSetOf<List<T>>()
    val n: Int = collection.size
    for (i in 0 until (1 shl n)) {
        val subset = mutableListOf<T>()
        for (j in 0 until n) {
            if (i and (1 shl j) > 0) {
                subset.add(collection[j])
            }
        }
        subsets.add(subset)
    }
    return subsets.filter { it.isNotEmpty() }.toSet()
}


fun <T> permutations(set: Set<T>): Set<List<T>> {
    if (set.isEmpty()) return emptySet()

    fun <T> allPermutations(list: List<T>): Set<List<T>> {
        if (list.isEmpty()) return setOf(emptyList())

        val result: MutableSet<List<T>> = mutableSetOf()
        for (i in list.indices) {
            allPermutations(list - list[i]).forEach { item ->
                result.add(item + list[i])
            }
        }
        return result
    }

    return allPermutations(set.toList())
}


// This seems to flip horizontally and then rotate CW 🤷‍♂️
// btw the nested list means that the first nested list is the first row of the matrix
fun <T> transpose(matrix: List<List<T>>): List<List<T>> {
    return IntRange(0, matrix[0].size - 1)
        .map { column ->
            matrix.map {
                it[column]
            }
        }
}

fun <T> intersect(data: List<List<T>>): List<T> {
    return data.reduce { acc, list -> acc.intersect(list).toList() }
}


fun <K, V> merge(maps: List<Map<K, V>>, merger: (V, V) -> V): Map<K, V> {
    return maps.reduce{ acc,next -> merge(acc,next,merger)}
}

fun <K, V> merge(map1: Map<K, V>, map2: Map<K, V>, merger: (V, V) -> V): Map<K, V> {
    val mutable = map1.toMutableMap()
    map2.forEach {
        if (map1.containsKey(it.key)) {
            mutable[it.key] = merger(it.value, map1[it.key]!!)
        }
        else
            mutable[it.key] = it.value
    }
    return mutable.toMap()
}
