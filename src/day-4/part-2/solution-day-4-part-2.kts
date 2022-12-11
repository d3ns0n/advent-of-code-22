import java.io.File

fun splitAssignemntGroups(input: String): Pair<String, String> {
    val split = input.split(",")
    return Pair(split[0], split[1])
}

fun splitAssignemntGroup(assignmentGroup: String): Pair<Int, Int> {
    val split = assignmentGroup.split("-")
    return Pair(split[0].toInt(), split[1].toInt())
}

fun isPartlyContained(assignmentGroup: Pair<String, String>): Boolean {
    val firstAssignment = splitAssignemntGroup(assignmentGroup.first)
    val secondAssignment = splitAssignemntGroup(assignmentGroup.second)
    return firstAssignment.first in secondAssignment.first..secondAssignment.second ||
            firstAssignment.second in secondAssignment.first..secondAssignment.second ||
            secondAssignment.first in firstAssignment.first..firstAssignment.second ||
            secondAssignment.second in firstAssignment.first..firstAssignment.second
}

var result = File("../input.txt").readLines()
    .map { splitAssignemntGroups(it) }
    .map { isPartlyContained(it) }
    .count { it }

println(result)

assert(result == 900)
