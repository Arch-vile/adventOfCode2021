package utils

import java.lang.Long.max
import java.lang.Long.min

data class Point(val x: Long, val y: Long)
data class Line(val start: Point, val end: Point)

// Only for horizontal and vertical lines for now
fun pointsInLine(line: Line): List<Point> {
    return (min(line.start.y, line.end.y)..max(line.start.y,line.end.y))
        .flatMap { y ->
            (min(line.start.x, line.end.x)..max(line.start.x,line.end.x))
                .map { x ->
                    Point(x,y)
                }
        }
}
