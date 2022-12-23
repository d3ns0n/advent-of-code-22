package `day-11`.`part-2`

import kotlin.properties.Delegates


class Monkey {

    private lateinit var items: MutableList<Long>
    private lateinit var operation: (Long) -> Long
    private lateinit var test: (Boolean) -> Monkey
    var divisor by Delegates.notNull<Long>()

    var inspections = 0

    fun analyse(
        items: MutableList<Long>,
        operation: (Long) -> Long,
        divisor: Long,
        test: (Boolean) -> Monkey
    ) {
        this.items = items
        this.operation = operation
        this.divisor = divisor
        this.test = test
    }

    fun inspectItems(commonMultiple: Long) {
        for (i in 1..items.size) {
            val stressLevel = items.first()
            val newStressLevel = operation(stressLevel).mod(commonMultiple)
            val targetMonkey = test(newStressLevel.mod(divisor) == 0.toLong())
            items.removeFirst()
            targetMonkey.items.add(newStressLevel)
            inspections += 1
        }
    }
}