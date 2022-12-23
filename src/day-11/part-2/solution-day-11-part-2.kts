package `day-11`.`part-2`

import `day-11`.Monkey

val monkey0 = Monkey()
val monkey1 = Monkey()
val monkey2 = Monkey()
val monkey3 = Monkey()
val monkey4 = Monkey()
val monkey5 = Monkey()
val monkey6 = Monkey()
val monkey7 = Monkey()

monkey0.analyse(items = mutableListOf(91, 58, 52, 69, 95, 54),
    operation = { x -> x * 13 },
    divisor = 7,
    test = { x -> if (x) monkey1 else monkey5 })

monkey1.analyse(items = mutableListOf(80, 80, 97, 84),
    operation = { x -> x * x },
    divisor = 3,
    test = { x -> if (x) monkey3 else monkey5 })

monkey2.analyse(items = mutableListOf(86, 92, 71),
    operation = { x -> x + 7 },
    divisor = 2,
    test = { x -> if (x) monkey0 else monkey4 })

monkey3.analyse(items = mutableListOf(96, 90, 99, 76, 79, 85, 98, 61),
    operation = { x -> x + 4 },
    divisor = 11,
    test = { x -> if (x) monkey7 else monkey6 })

monkey4.analyse(items = mutableListOf(60, 83, 68, 64, 73),
    operation = { x -> x * 19 },
    divisor = 17,
    test = { x -> if (x) monkey1 else monkey0 })

monkey5.analyse(items = mutableListOf(96, 52, 52, 94, 76, 51, 57),
    operation = { x -> x + 3 },
    divisor = 5,
    test = { x -> if (x) monkey7 else monkey3 })

monkey6.analyse(items = mutableListOf(75),
    operation = { x -> x + 5 },
    divisor = 13,
    test = { x -> if (x) monkey4 else monkey2 })

monkey7.analyse(items = mutableListOf(83, 75),
    operation = { x -> x + 1 },
    divisor = 19,
    test = { x -> if (x) monkey2 else monkey6 })

val monkeys: List<Monkey> =
    mutableListOf(monkey0, monkey1, monkey2, monkey3, monkey4, monkey5, monkey6, monkey7)

val commonMultiple = monkeys.map { monkey -> monkey.divisor }.fold(1.toLong()) { acc, l -> acc * l }

for (i in 1..10000) {
    monkeys.forEach { it.inspectItems { x -> x.mod(commonMultiple) } }
}

val monkeyInteractions = monkeys.sortedByDescending { it.inspections }.map { it.inspections }
val result = monkeyInteractions[0] * monkeyInteractions[1]

println(result)

assert(result == 14106266886)
