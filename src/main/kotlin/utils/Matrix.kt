package utils

data class Entry<T>(val x: Int, val y: Int, val value: T)

class Matrix<T>(input: List<List<T>>) {

    private val data: MutableList<MutableList<T>> = mutableListOf()

    init {
        input.forEach {
            data.add(it.toMutableList())
        }
    }

    fun find(value: T): Entry<T>? {
        for (y in 0 until data.size) {
            for (x in 0 until data.first().size) {
                if (data[y][x] == value) return Entry(x, y, data[y][x])
            }
        }
        return null;
    }

    fun replace(found: Entry<T>, value: T) {
        data[found.y][found.x] = value
    }

    // Rows for which the matcher matches for each element in row
    fun findRowsByValues(matcher: (T) -> Boolean): List<List<T>> {
        return data
            .filter { row -> row.count(matcher) == row.size }
    }

    fun findAll(matcher: (T) -> Boolean): List<Entry<T>> {
        val found = mutableListOf<Entry<T>>()
        for (y in 0 until data.size) {
            for (x in 0 until data.first().size) {
                if (matcher(data[y][x])) found.add(Entry(x, y, data[y][x]))
            }
        }
        return found.toList()
    }

    // Columns for which the matcher matches for each element in column
    fun findColsByValues(matcher: (T) -> Boolean): List<List<T>> {
       return rotateCW().findRowsByValues(matcher)
    }

    fun rotateCW(): Matrix<T> {
        val foo = IntRange(0,data[0].size-1)
            .map {x ->
                ((data.size-1) downTo 0).map { y ->
                    data[y][x]
                }
            }
        return Matrix(foo)
    }

    override fun toString(): String {
        return data.joinToString("\n") { it.toString() };
    }


}
