import java.io.File

var elfCounter = 1
val caloriesPerElf = mutableMapOf<Int, Int>()

File("../input.txt").readLines().forEach {
    if (it.isBlank()) {
        elfCounter++
    } else {
        caloriesPerElf.put(elfCounter, caloriesPerElf.getOrDefault(elfCounter, 0) + it.toInt())
    }
}

val caloriesOfTop3Elves = caloriesPerElf.toList()
    .sortedBy { (_, value) -> -value }
    .take(3)
    .sumOf(Pair<Int, Int>::second)

print("The top 3 elves carry $caloriesOfTop3Elves calories with them.")
