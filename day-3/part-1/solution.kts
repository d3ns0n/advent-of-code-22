import java.io.File

fun splitCompartments(string: String): Pair<String, String> = Pair(
    string.substring(0..(string.length / 2)),
    string.substring((string.length / 2))
)

fun findDuplicates(compartments: Pair<String, String>): Char =
    compartments.first.toCharArray().first { compartments.second.contains(it) }

fun getPriority(character: Char): Int {
    if (character.code in 65..90) {
        return character.code - 38
    }
    return character.code - 96
}

var result = File("../input.txt").readLines()
    .map { splitCompartments(it) }
    .map { findDuplicates(it) }
    .map { getPriority(it) }
    .sum()

println(result)

assert(result == 8493)
