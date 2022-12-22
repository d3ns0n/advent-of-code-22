package `day-11`.`part-1`


class Monkey {

    private lateinit var items: MutableList<Int>
    private lateinit var operation: (Int) -> Int
    private lateinit var test: (Int) -> Monkey

    private val reduceStress: (Int) -> Int = { x -> x.floorDiv(3) }
    var inspections = 0

    fun analyse(
        items: MutableList<Int>, operation: (Int) -> Int, test: (Int) -> Monkey
    ) {
        this.items = items
        this.operation = operation
        this.test = test
    }

    fun inspectItems() {
        for (i in 1..items.size) {
            items[0] = operation(items[0]).let(reduceStress)
            val targetMonkey = test(items[0])
            targetMonkey.items.add(items.removeFirst())
            inspections += 1
        }
    }
}