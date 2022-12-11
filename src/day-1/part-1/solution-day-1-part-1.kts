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

val (elf, calories) = caloriesPerElf.maxBy { it.value }

print("Elf #$elf carries the most food with $calories calories.")
