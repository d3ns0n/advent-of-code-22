import java.io.File

val SOP_MARKER_LENGTH = 4

fun Char.isUniqueIn(string: CharSequence): Boolean {
    return string.count { c -> c.equals(this) } == 1
}

fun findMarker(string: String): Int {
    for (startIndex in 0..string.length - SOP_MARKER_LENGTH) {
        val endIndex = startIndex + SOP_MARKER_LENGTH
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
        assert(it == 1848)
    }



