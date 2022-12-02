import java.io.File

fun convert(string: String): Pair<Char, Char> {
    val split = string.split(" ")
    return Pair(split.get(0).first(), split.get(1).first())
}

fun letterValue(char: Char): Int {
    return mapOf('A' to 1, 'B' to 2, 'C' to 3, 'X' to 1, 'Y' to 2, 'Z' to 3).getOrDefault(char, 0)
}

fun compare(a: Char, b: Char): Int {
    return (letterValue(b) - letterValue(a)).mod(3)
}

var sum = File("../input.txt")
    .readLines()
    .map {
        val pair = convert(it);
        when (compare(pair.first, pair.second)) {
            0 -> letterValue(pair.second) + 3
            1 -> letterValue(pair.second) + 6
            else -> letterValue(pair.second)
        }
    }
    .sum()

assert(sum == 12679)

println(sum)
