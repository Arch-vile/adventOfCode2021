package utils.graphs

import java.time.LocalDateTime
import java.util.Date
import kotlin.concurrent.timer


// Not immutable by any means
data class Node<T>(val value: T) {

    val edges: MutableSet<Edge<T>> = mutableSetOf()

    fun link(distance: Long, target: Node<T>) {
        edges.add(Edge(distance, target))
    }

    fun link(distance: Long, targets: List<Node<T>>) {
        targets.forEach { link(distance, it) }
    }

    fun biLink(distance: Long, targets: List<Node<T>>) {
        targets.forEach { biLink(distance, it) }
    }

    fun biLink(distance: Long, other: Node<T>) {
        link(distance, other)
        other.link(distance, this)
    }

    override fun toString(): String {
        return "Node(value=$value, edges=$edges)"
    }

    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}

data class Edge<T>(var distance: Long, var target: Node<T>) {
    override fun toString(): String {
        return "Edge(distance=$distance, target=Node(${target.value}))"
    }
}

fun <T> shortestPath(start: Node<T>, target: Node<T>): Long? {
    val unvisitedNodes = allNodes(start)
        .groupBy{ node -> node }
        .mapValues { entry -> entry.value[0] }
        .toMutableMap()

    val tentativeDistances =
        unvisitedNodes.mapValues { entry -> Long.MAX_VALUE }
            .toMutableMap()
    tentativeDistances[start] = 0

    var current = start
    while (true) {
        val edges = current.edges.filter { unvisitedNodes.contains(it.target) }
        edges.forEach { edge ->
            val newDistance = tentativeDistances[current]!! + edge.distance
            if (newDistance < tentativeDistances[edge.target]!!)
                tentativeDistances[edge.target] = newDistance
        }
        unvisitedNodes.remove(current)

        if (!unvisitedNodes.contains(target)) {
            return tentativeDistances[target]
        }
        else {
            // TODO: This sorting slows things down alot
            current = tentativeDistances.entries.sortedBy { it.value }
                .first { unvisitedNodes.contains(it.key) }.key
        }
    }
}

fun <T> route(start: Node<T>, target: Node<T>, tentativeDistances: MutableMap<Node<T>, Long>): List<Node<T>> {
    return backtrack(target, start, tentativeDistances)
}

fun <T> backtrack(current: Node<T>, target: Node<T>, tentativeDistances: MutableMap<Node<T>, Long>): List<Node<T>> {
    if(current == target) {
        return listOf()
    }

    val next = current.edges.minByOrNull { tentativeDistances[it.target]!! }!!.target
    return listOf(next).plus(route(next,target,tentativeDistances))
}

fun <T> allNodes(start: Node<T>): Set<Node<T>> {

    val unvisited = mutableSetOf(start)
    val visited = mutableSetOf<Node<T>>()

    while (unvisited.isNotEmpty()) {
        val current = unvisited.first()
        unvisited.remove(current)
        current.edges
            .filter { !visited.contains(it.target) }
            .forEach { unvisited.add(it.target) }
        visited.add(current)
    }

    return visited
}

