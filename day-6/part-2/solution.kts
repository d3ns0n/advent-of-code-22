import java.io.File

val SOM_MARKER_LENGTH = 14

fun Char.isUniqueIn(string: CharSequence): Boolean {
    return string.count { c -> c.equals(this) } == 1
}

fun findMarker(string: String): Int {
    for (startIndex in 0..string.length - SOM_MARKER_LENGTH) {
        val endIndex = startIndex + SOM_MARKER_LENGTH
        val possibleMarker = string.subSequence(startIndex, endIndex)
        if (possibleMarker.all { char -> char.isUniqueIn(possibleMarker) }) {
            return endIndex
        }
    }
    return -1
}

File("../input.txt").readLines()
    .map { findMarker(it) }
    .forEach {
        println(it)
        assert(it == 2308)
    }
