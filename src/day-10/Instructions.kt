package `day-10`

abstract class Instruction(var cyclesLeft: Int, private val op: (Int) -> Int) {
    fun execute(value: Int): Int {
        cyclesLeft -= 1
        return if (cyclesLeft == 0) op(value) else value
    }
}

class Noop : Instruction(1, { x -> x })

class AddX(value: Int) : Instruction(2, { x -> x + value })