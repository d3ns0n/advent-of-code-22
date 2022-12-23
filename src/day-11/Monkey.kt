package `day-11`

import kotlin.properties.Delegates


class Monkey {

    private lateinit var items: MutableList<Long>
    private lateinit var operation: (Long) -> Long
    var divisor by Delegates.notNull<Long>()
    private lateinit var test: (Boolean) -> Monkey

    var inspections: Long = 0

    fun analyse(
        items: MutableList<Long>,
        operation: (Long) -> Long,
        divisor: Long,
        test: (Boolean) -> Monkey,
    ) {
        this.items = items
        this.operation = operation
        this.divisor = divisor
        this.test = test
    }

    fun inspectItems(reduceStress: (Long) -> Long) {
        for (i in 1..items.size) {
            items[0] = operation(items[0]).let(reduceStress)
            val targetMonkey = test(items[0].mod(divisor) == 0.toLong())
            targetMonkey.items.add(items.removeAt(0))
            inspections += 1
        }
    }
}