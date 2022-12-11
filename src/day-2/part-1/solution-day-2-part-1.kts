import java.io.File

fun convertToPair(string: String): Pair<Char, Char> {
    val split = string.split(" ")
    return Pair(split.get(0).first(), split.get(1).first())
}

fun letterValue(char: Char): Int {
    return mapOf('A' to 1, 'B' to 2, 'C' to 3, 'X' to 1, 'Y' to 2, 'Z' to 3).getOrDefault(char, 0)
}

fun compare(pair: Pair<Char, Char>): Int {
    return (letterValue(pair.second) - letterValue(pair.first)).mod(3)
}

var result = File("../input.txt")
    .readLines()
    .map { convertToPair(it) }
    .map {
        when (compare(it)) {
            0 -> letterValue(it.second) + 3 //draw
            1 -> letterValue(it.second) + 6 //win
            else -> letterValue(it.second) //draw
        }
    }.sum()

println(result)

assert(result == 12679)
