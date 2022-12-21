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

var knotLog: Array<MutableList<Coordinate>> = Array(10) { mutableListOf(Coordinate()) }

fun moveHead(direction: String, distance: Int) {
    for (i in 1..distance) {
        when (direction) {
            "R" -> knotLog[0].add(knotLog[0].last().moveRight())
            "L" -> knotLog[0].add(knotLog[0].last().moveLeft())
            "U" -> knotLog[0].add(knotLog[0].last().moveUp())
            "D" -> knotLog[0].add(knotLog[0].last().moveDown())
        }
        moveKnotsIfNecessary()
    }
}

fun moveKnotsIfNecessary() {
    for (i in 1..9) {
        val knot = knotLog[i].last()
        val previousKnot = knotLog[i - 1].last()
        if (!knot.isTouching(previousKnot)) {
            var tempKnot = knot
            if (previousKnot.x != knot.x) {
                tempKnot =
                    if (previousKnot.x - knot.x > 0) tempKnot.moveRight() else tempKnot.moveLeft()
            }
            if (previousKnot.y != knot.y) {
                tempKnot =
                    if (previousKnot.y - knot.y > 0) tempKnot.moveUp() else tempKnot.moveDown()
            }
            knotLog[i].add(tempKnot)
        } else {
            knotLog[i].add(knot)
        }
    }

}

fun process(line: String) {
    val (direction, distance) = line.split(" ", limit = 2)
    moveHead(direction, distance.toInt())
}

File("../input.txt").readLines()
    .forEach { process(it) }

val tailLogDistinctSize = knotLog[9].distinct().size

println(tailLogDistinctSize)

assert(tailLogDistinctSize == 2541)
