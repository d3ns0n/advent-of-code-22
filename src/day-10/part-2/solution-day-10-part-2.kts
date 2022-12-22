package `day-10`.`part-2`

import `day-10`.AddX
import `day-10`.Instruction
import `day-10`.Noop
import java.io.File

fun readInstructions(filename: String): MutableList<Instruction> {
    val instructions: MutableList<Instruction> = mutableListOf()
    File(filename).readLines().forEach {
        when {
            it.startsWith("noop") -> instructions.add(Noop())
            it.startsWith("addx") -> instructions.add(AddX(it.split(" ", limit = 2)[1].toInt()))
        }
    }
    return instructions
}

fun processInstructions(instructions: MutableList<Instruction>): MutableList<String> {
    val values: MutableList<Int> = mutableListOf(1)
    val pixels: MutableList<String> = mutableListOf()
    var cycle = 1

    while (instructions.isNotEmpty()) {
        val instruction = this.instructions.first()
        val currentValue = instruction.execute(values.last())
        if (currentValue in cycle.mod(40) - 1..cycle.mod(40) + 1) {
            pixels.add("#")
        } else {
            pixels.add(".")
        }
        values.add(currentValue)
        if (instruction.cyclesLeft == 0) this.instructions.removeFirst()
        cycle += 1
    }

    return pixels
}

fun printPixels(pixels: MutableList<String>) {
    for (i in 0..5) {
        val rowOffset = 40 * i
        println(pixels.slice((0 + rowOffset)..39 + rowOffset))
    }
}

val instructions = readInstructions("../input.txt")
var pixels = processInstructions(instructions)
printPixels(pixels)
