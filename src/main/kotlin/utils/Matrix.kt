package utils

data class Entry<T>(val x: Int, val y: Int, val value: T)

class Matrix<T>(input: List<List<T>>) {

    private val data: MutableList<MutableList<T>> = mutableListOf()

    init {
        input.forEach {
            data.add(it.toMutableList())
        }
    }

    constructor(width: Long, height: Long, init: (Int,Int) -> T) : this(initialize(width, height, init))

    fun find(value: T): Entry<T>? {
        for (y in 0 until data.size) {
            for (x in 0 until data.first().size) {
                if (data[y][x] == value) return Entry(x, y, data[y][x])
            }
        }
        return null;
    }

    fun replace(current: Entry<T>, value: T) {
        data[current.y][current.x] = value
    }

    fun replace(x: Long, y: Long, newValue: (Entry<T>) -> T) {
        TODO("Not yet implemented")
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
        val foo = IntRange(0, data[0].size - 1)
            .map { x ->
                ((data.size - 1) downTo 0).map { y ->
                    data[y][x]
                }
            }
        return Matrix(foo)
    }

    override fun toString(): String {
        return data.joinToString("\n") { it.toString() };
    }


    companion object {
        private fun <T> initialize(width: Long, height: Long, init: (x: Int, y: Int) -> T): List<List<T>> {
            TODO("Not yet implemented")
        }
    }


}
