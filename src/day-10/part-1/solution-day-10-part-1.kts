package `day-10`.`part-1`

import `day-10`.AddX
import `day-10`.Instruction
import `day-10`.Noop
import java.io.File

val instructions: MutableList<Instruction> = mutableListOf()
val values: MutableList<Int> = mutableListOf(1);
var cycle = 1

fun readInstructions(filename: String) {
    File(filename).readLines()
        .forEach {
            when {
                it.startsWith("noop") -> instructions.add(Noop())
                it.startsWith("addx") -> instructions.add(AddX(it.split(" ", limit = 2)[1].toInt()))
            }
        }
}

fun processInstructions() {
    while (instructions.isNotEmpty()) {
        val instruction = instructions.first()
        values.add(instruction.execute(values.last()))
        if (instruction.cyclesLeft == 0) instructions.removeFirst()
        cycle += 1
    }
}

readInstructions("../input.txt")
processInstructions()

val result = listOf(20, 60, 100, 140, 180, 220).sumOf { it * values[it - 1] }

println(result)

assert(result == 15260)