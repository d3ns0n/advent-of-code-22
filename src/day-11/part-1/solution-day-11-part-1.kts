package `day-11`.`part-1`

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
    test = { x -> if (x.mod(7) == 0) monkey1 else monkey5 })

monkey1.analyse(items = mutableListOf(80, 80, 97, 84),
    operation = { x -> x * x },
    test = { x -> if (x.mod(3) == 0) monkey3 else monkey5 })

monkey2.analyse(items = mutableListOf(86, 92, 71),
    operation = { x -> x + 7 },
    test = { x -> if (x.mod(2) == 0) monkey0 else monkey4 })

monkey3.analyse(items = mutableListOf(96, 90, 99, 76, 79, 85, 98, 61),
    operation = { x -> x + 4 },
    test = { x -> if (x.mod(11) == 0) monkey7 else monkey6 })

monkey4.analyse(items = mutableListOf(60, 83, 68, 64, 73),
    operation = { x -> x * 19 },
    test = { x -> if (x.mod(17) == 0) monkey1 else monkey0 })

monkey5.analyse(items = mutableListOf(96, 52, 52, 94, 76, 51, 57),
    operation = { x -> x + 3 },
    test = { x -> if (x.mod(5) == 0) monkey7 else monkey3 })

monkey6.analyse(items = mutableListOf(75),
    operation = { x -> x + 5 },
    test = { x -> if (x.mod(13) == 0) monkey4 else monkey2 })

monkey7.analyse(items = mutableListOf(83, 75),
    operation = { x -> x + 1 },
    test = { x -> if (x.mod(19) == 0) monkey2 else monkey6 })

val monkeys: List<Monkey> =
    mutableListOf(monkey0, monkey1, monkey2, monkey3, monkey4, monkey5, monkey6, monkey7)

for (i in 1..20) {
    monkeys.forEach { it.inspectItems() }
}

val monkeyInteractions = monkeys.sortedByDescending { it.inspections }.map { it.inspections }

val result = monkeyInteractions[0] * monkeyInteractions[1]

println(result)

assert(result == 57348)
