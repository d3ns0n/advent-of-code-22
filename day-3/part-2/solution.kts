import java.io.File

fun groupElves(rucksacks: List<String>): List<Triple<String, String, String>> {
    val elfGroups = mutableListOf<Triple<String, String, String>>()
    for (i in 0..rucksacks.size - 1 step 3) {
        elfGroups.add(Triple(rucksacks[i], rucksacks[i + 1], rucksacks[i + 2]))
    }
    return elfGroups
}

fun findDuplicates(elfGroup: Triple<String, String, String>): Char =
    elfGroup.first.toCharArray()
        .first { elfGroup.second.contains(it) && elfGroup.third.contains(it) }

fun getPriority(character: Char): Int {
    if (character.code in 65..90) {
        return character.code - 38
    }
    return character.code - 96
}

var result = groupElves(File("../input.txt").readLines())
    .map { findDuplicates(it) }
    .map { getPriority(it) }
    .sum()

println(result)

assert(result == 2552)
