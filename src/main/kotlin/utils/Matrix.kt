package utils

data class Entry<T>(val x: Int, val y: Int, val value: T)

class Matrix<T>(input: List<List<T>>) {

    private val data: MutableList<MutableList<Entry<T>>> = mutableListOf()

    init {
        val columns = input[0].size
        for(y in input.indices) {
            val row = mutableListOf<Entry<T>>()
            data.add(row)
            for(x in 0 until columns) {
               if(input[y].size != columns)
                   throw Error("all rows must have equal amount of columns")
               row.add(Entry(x,y,input[y][x]))
            }
        }
    }

    constructor(width: Long, height: Long, init: (Int,Int) -> T) : this(initialize(width, height, init))

    fun find(value: T): Entry<T>? {
        for (y in 0 until data.size) {
            for (x in 0 until data.first().size) {
                if (data[y][x].value == value) return data[y][x]
            }
        }
        return null;
    }

    fun replace(current: Entry<T>, value: T) {
        data[current.y][current.x] = current.copy(value=value)
    }

    fun replace(x: Long, y: Long, newValue: (Entry<T>) -> T) {
        TODO("Not yet implemented")
    }

    // Rows for which the matcher matches for each element in row
    fun findRowsByValues(matcher: (Entry<T>) -> Boolean): List<List<Entry<T>>> {
        return data
            .filter { row -> row.count(matcher) == row.size }
    }

    fun findAll(matcher: (Entry<T>) -> Boolean): List<Entry<T>> {
        val found = mutableListOf<Entry<T>>()
        for (y in 0 until data.size) {
            for (x in 0 until data.first().size) {
                if (matcher(data[y][x])) found.add(data[y][x])
            }
        }
        return found.toList()
    }

    // Columns for which the matcher matches for each element in column
    fun findColsByValues(matcher: (Entry<T>) -> Boolean): List<List<Entry<T>>> {
        return rotateCW().findRowsByValues(matcher)
    }

    fun rotateCW(): Matrix<T> {
        val foo = IntRange(0, data[0].size - 1)
            .map { x ->
                ((data.size - 1) downTo 0).map { y ->
                    data[y][x].value
                }
            }
        return Matrix(foo)
    }

    override fun toString(): String {
        return data.joinToString("\n") { row -> row.map { it.value }.toString() };
    }


    companion object {
        private fun <T> initialize(width: Long, height: Long, init: (x: Int, y: Int) -> T): List<List<T>> {
            TODO("Not yet implemented")
        }
    }


}
