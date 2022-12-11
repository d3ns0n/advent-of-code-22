import java.io.File

val treeGrid = mutableListOf<String>()

File("../input.txt").readLines().forEach { treeGrid.add(it) }

val treeGridWidth = treeGrid.first().length
val treeGridHeight = treeGrid.size

fun viewingDistanceLeft(x: Int, y: Int): Int {
    val height = treeGrid[y][x].code
    for ((index, value) in treeGrid[y].substring(0, x).reversed().withIndex()) {
        if (value.code >= height) {
            return index + 1
        }
    }
    return x
}

fun viewingDistanceRight(x: Int, y: Int): Int {
    val height = treeGrid[y][x].code
    for ((index, value) in treeGrid[y].substring(x + 1, treeGrid[y].length).withIndex()) {
        if (value.code >= height) {
            return index + 1
        }
    }
    return treeGridWidth - 1 - x
}

fun viewingDistanceTop(x: Int, y: Int): Int {
    val height = treeGrid[y][x].code
    for (i in (y - 1 downTo 0)) {
        if (treeGrid[i][x].code >= height) {
            return y - i
        }
    }
    return y
}

fun viewingDistanceBottom(x: Int, y: Int): Int {
    val height = treeGrid[y][x].code
    for (i in (y + 1 until treeGridHeight)) {
        if (treeGrid[i][x].code >= height) {
            return i - y
        }
    }
    return treeGridHeight - 1 - y
}

fun findHighestScenicScore(treeGrid: List<String>): Int {
    var highestScenicScore = 0
    for (y in treeGrid.indices) {
        for (x in 0 until treeGrid[y].length) {
            val scenicScore = viewingDistanceLeft(x, y) *
                    viewingDistanceTop(x, y) *
                    viewingDistanceRight(x, y) *
                    viewingDistanceBottom(x, y)
            if (scenicScore > highestScenicScore) {
                highestScenicScore = scenicScore
            }
        }
    }
    return highestScenicScore
}

val highestScenicScore = findHighestScenicScore(treeGrid)
println(highestScenicScore)
assert(highestScenicScore == 268800)
