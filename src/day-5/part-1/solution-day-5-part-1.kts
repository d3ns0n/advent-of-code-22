import java.io.File

val stacks: MutableList<MutableList<Char>> = mutableListOf()
val rearrangementProcedures: MutableList<Triple<Int, Int, Int>> = mutableListOf()

fun initStacks(numberOfStacks: Int) {
    for (i in 0 until numberOfStacks) {
        stacks.add(mutableListOf())
    }
}

fun storeStacks(numberOfStacks: Int, lines: List<String>) {
    initStacks(numberOfStacks)

    for (line in lines.reversed()) {
        for (i in 0 until line.length) {
            if (line[i].equals('[')) {
                val stackNumber = i.div(4)
                val character = line[i + 1]
                stacks[stackNumber].add(character)
            }
        }
    }
}

fun storeRearrangementProcedures(lines: List<String>) {
    for (line in lines) {
        val indexFrom = line.indexOf("from")
        val indexTo = line.indexOf("to")

        rearrangementProcedures.add(
            Triple(
                line.substring(5 until indexFrom - 1).toInt(),
                line.substring(indexFrom + 5 until indexTo - 1).toInt(),
                line.substring(indexTo + 3 until line.length).toInt()
            )
        )
    }
}

fun readFromFile(file: File) {
    val fileContent = file.readLines()
    val separatorIndex = fileContent.indexOf("")

    storeStacks(
        fileContent.get(separatorIndex - 1).last().digitToInt(),
        fileContent.subList(0, separatorIndex - 1)
    )

    storeRearrangementProcedures(fileContent.subList(separatorIndex + 1, fileContent.size))
}

fun applyRearrangementProcedures() {
    for (rearrangementProcedure in rearrangementProcedures) {
        for (i in 0 until rearrangementProcedure.first) {
            val crate = stacks[rearrangementProcedure.second - 1].removeLastOrNull()
            if (crate != null) {
                stacks[rearrangementProcedure.third - 1].add(crate)
            }
        }
    }
}

fun printCratesOnTop() {
    for (stack in stacks) {
        print(stack.last())
    }
}

readFromFile(File("../input.txt"))
applyRearrangementProcedures()
printCratesOnTop()
