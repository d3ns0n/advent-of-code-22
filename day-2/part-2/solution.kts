import java.io.File

fun convertToPair(string: String): Pair<Char, Char> {
    val split = string.split(" ")
    return Pair(split.get(0).first(), split.get(1).first())
}

fun letterValue(char: Char): Int {
    return mapOf('A' to 1, 'B' to 2, 'C' to 3, 'X' to 1, 'Y' to 2, 'Z' to 3).getOrDefault(char, 0)
}

fun calculateOwnLetterValue(number: Int): Int = number.mod(3).let { if (it == 0) 3 else it }

var result = File("../input.txt")
    .readLines()
    .map { convertToPair(it) }
    .map {
        when (it.second) {
            'X' -> calculateOwnLetterValue(letterValue(it.first) + 2)//loose
            'Y' -> letterValue(it.first) + 3//draw
            else -> calculateOwnLetterValue(letterValue(it.first) + 1) + 6//win
        }
    }.sum()

println(result)

assert(result == 14470)
