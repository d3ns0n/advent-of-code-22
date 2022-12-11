import java.io.File

val treeGrid = mutableListOf<String>()

File("../input.txt").readLines()
    .forEach { treeGrid.add(it) }

val treeGridWidth = treeGrid.first().length
val treeGridHeight = treeGrid.size

fun isAtEdge(x: Int, y: Int) = x == 0 || x == treeGridWidth - 1 || y == 0 || y == treeGridHeight - 1

fun isVisibleFromLeft(x: Int, y: Int): Boolean {
    val height = treeGrid[y][x].code
    return treeGrid[y].substring(0, x).all { it.code < height }
}

fun isVisibleFromRight(x: Int, y: Int): Boolean {
    val height = treeGrid[y][x].code
    return treeGrid[y].substring(x + 1, treeGrid[y].length).all { it.code < height }
}

fun isVisibleFromTop(x: Int, y: Int): Boolean {
    val height = treeGrid[y][x].code
    return (0 until y).all { treeGrid[it][x].code < height }
}

fun isVisibleFromBottom(x: Int, y: Int): Boolean {
    val height = treeGrid[y][x].code
    return (y + 1 until treeGridHeight).all { treeGrid[it][x].code < height }
}

fun findVisibleTrees(treeGrid: List<String>): Int {
    var visibleTrees = 0
    for (y in treeGrid.indices) {
        for (x in 0 until treeGrid[y].length) {
            if (
                isAtEdge(x, y) ||
                isVisibleFromLeft(x, y) ||
                isVisibleFromRight(x, y) ||
                isVisibleFromTop(x, y) ||
                isVisibleFromBottom(x, y)
            ) {
                visibleTrees++
            }
        }
    }
    return visibleTrees
}

val visibleTrees = findVisibleTrees(treeGrid)
println(visibleTrees)
assert(visibleTrees == 1736)
