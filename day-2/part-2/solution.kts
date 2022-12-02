import java.io.File

fun convert(string: String): Pair<Char, Char> {
    val split = string.split(" ")
    return Pair(split.get(0).first(), split.get(1).first())
}

fun letterValue(char: Char): Int {
    return mapOf('A' to 1, 'B' to 2, 'C' to 3, 'X' to 1, 'Y' to 2, 'Z' to 3).getOrDefault(char, 0)
}

fun calculate(number: Int): Int {
    val mod = number.mod(3)
    return if (mod == 0) 3 else mod
}

var sum = File("../input.txt")
    .readLines()
    .map { convert(it) }
    .map {
        when (it.second) {
            'X' -> calculate(letterValue(it.first) + 2)
            'Y' -> letterValue(it.first) + 3
            else -> calculate(letterValue(it.first) + 1) + 6
        }
    }
    .sum()

assert(sum == 14470)

println(sum)
