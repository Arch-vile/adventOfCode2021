package day12

import utils.read

fun main() {
    solve()
        .let { println(it) }
}

data class Cave(var accessTo: MutableList<Cave>, val name: String) {
    fun isSmall(): Boolean {
        if(isEnd() || isStart())
            return false;

        return """[a-z]*""".toRegex().matches(name)
    }

    private fun isEnd(): Boolean = name == "end"

    private fun isStart() = name == "start"

    fun addAccessTo(other: Cave) {
        // Do not add route to back to Start and no routes out of End
        if (!other.isStart() && !isEnd()) {
            accessTo.add(other)
        }
    }

    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        return if(other is Cave)
            name == other.name
        else
            false
    }

}

fun solve(): List<Int> {
    val caves = mutableMapOf<String, Cave>()
    read("./src/main/resources/day12Input.txt")
        .map { it.split("-") }
        .forEach {
            val source = caves.getOrDefault(it[0], Cave(mutableListOf(), it[0]))
            val target = caves.getOrDefault(it[1], Cave(mutableListOf(), it[1]))
            source.addAccessTo(target)
            target.addAccessTo(source)
            caves.put(source.name, source)
            caves.put(target.name, target)
        }

    return listOf(
        countRoutes(false, caves["start"]!!, caves["end"]!!),
        countRoutes(true, caves["start"]!!, caves["end"]!!))
}

fun countRoutes(allowDuplicates: Boolean, start: Cave, end: Cave): Int {
    val routes = findRoutes(allowDuplicates, listOf(),start)
    val routesReachingEnd = routes.filter { it.contains(end) }
    return routesReachingEnd.count()
}

fun findRoutes(allowDuplicates: Boolean, path: List<Cave>, cave: Cave): MutableList<MutableList<Cave>> {
    if (cave.accessTo.isEmpty()) {
        return mutableListOf(mutableListOf(cave))
    }

    val newPath = path.plus(cave)
    val childRoutes = cave.accessTo
        .filter {
           if(it.isSmall()) {
               if(newPath.contains(it))
                   allowDuplicates && !hasDoubleSmall(newPath)
               else
                   true
           } else true
        }
        .flatMap {
            findRoutes(allowDuplicates, newPath, it)
        }
    childRoutes.forEach { it.add(0, cave) }
    return childRoutes.toMutableList()
}

fun hasDoubleSmall(newPath: List<Cave>): Boolean {
    return newPath
        .filter { it.isSmall() }
        .map { it.name }
        .distinct().count() != newPath.filter { it.isSmall() }.count()
}
