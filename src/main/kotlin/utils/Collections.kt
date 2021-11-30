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


fun <T> rotateCW(matrix: List<List<T>>): List<List<T>> {
  return IntRange(0,matrix[0].size-1)
    .map {x ->
      ((matrix.size-1) downTo 0).map { y ->
        matrix[y][x]
      }
    }
}



// This seems to flip horizontally and then rotate CW 🤷‍♂️
// btw the nested list means that the first nested list is the first row of the matrix
fun <T>  transpose(matrix: List<List<T>>): List<List<T>> {
  return IntRange(0,matrix[0].size-1)
    .map { column ->
      matrix.map {
        it[column]
      }
    }
}

fun <T> intersect(data: List<List<T>>): List<T> {
return data.reduce { acc, list -> acc.intersect(list).toList() }
}

