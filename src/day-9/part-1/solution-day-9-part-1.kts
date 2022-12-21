import java.io.File
import kotlin.math.abs

class Coordinate(val x: Int = 0, val y: Int = 0) {
    fun moveRight() = Coordinate(x + 1, y)
    fun moveLeft() = Coordinate(x - 1, y)
    fun moveUp() = Coordinate(x, y + 1)
    fun moveDown() = Coordinate(x, y - 1)
    fun isTouching(other: Coordinate) = abs(x - other.x) <= 1 && abs(y - other.y) <= 1

    override fun equals(other: Any?): Boolean {
        return other is Coordinate && x == other.x && y == other.y
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}

var headLog: MutableList<Coordinate> = mutableListOf(Coordinate())
var tailLog: MutableList<Coordinate> = mutableListOf(Coordinate())

fun moveHead(direction: String, distance: Int) {
    for (i in 1..distance) {
        when (direction) {
            "R" -> headLog.add(headLog.last().moveRight())
            "L" -> headLog.add(headLog.last().moveLeft())
            "U" -> headLog.add(headLog.last().moveUp())
            "D" -> headLog.add(headLog.last().moveDown())
        }
        moveTailIfNecessary()
    }
}

fun moveTailIfNecessary() {
    val tail = tailLog.last()
    val head = headLog.last()
    if (!tail.isTouching(head)) {
        var tempTail = tail
        if (head.x != tail.x) {
            tempTail = if (head.x - tail.x > 0) tempTail.moveRight() else tempTail.moveLeft()
        }
        if (head.y != tail.y) {
            tempTail = if (head.y - tail.y > 0) tempTail.moveUp() else tempTail.moveDown()
        }
        tailLog.add(tempTail)
    } else {
        tailLog.add(tail)
    }
}

fun process(line: String) {
    val (direction, distance) = line.split(" ", limit = 2)
    moveHead(direction, distance.toInt())
}

File("../input.txt").readLines()
    .forEach { process(it) }

val tailLogDistinctSize = tailLog.distinct().size

println(tailLogDistinctSize)

assert(tailLogDistinctSize == 6339)
