import `day-7`.Directory
import `day-7`.File
import `day-7`.FileSystemItem

val root = Directory("/")

var currentDirectory = root

fun addFileSystemItem(string: String) {
    if (string.startsWith("dir")) {
        currentDirectory.content.add(Directory(string.substring(4), parent = currentDirectory))
    } else {
        val split = string.split(" ")
        currentDirectory.content.add(File(split[1], split[0].toInt()))
    }
}

fun processCommand(string: String) {
    val command = string.removePrefix("$").trim()
    if (command.startsWith("cd")) {
        val target = command.removePrefix("cd").trim()
        currentDirectory = when (target) {
            "/" -> root
            ".." -> currentDirectory.parent
            else -> currentDirectory.content.first { it is Directory && it.name == target } as Directory
        }
    }
}

fun process(string: String) {
    if (string.startsWith("$")) {
        processCommand(string)
    } else {
        addFileSystemItem(string)
    }
}

fun findDirectoriesWithSizeFrom(
    itemsToProcess: List<FileSystemItem>,
    foundItems: MutableList<FileSystemItem>,
    minSize: Int
): List<FileSystemItem> {
    val directories = itemsToProcess.filterIsInstance<Directory>()
    if (directories.isEmpty()) {
        return foundItems
    }
    val firstDirectory = directories.first()
    val remainingDirectories =
        mutableListOf<FileSystemItem>(*directories.drop(1).toTypedArray()).apply {
            this.addAll(firstDirectory.content)
        }
    if (firstDirectory.size >= minSize) {
        foundItems.add(firstDirectory)
    }
    return findDirectoriesWithSizeFrom(remainingDirectories, foundItems, minSize)
}

java.io.File("../input.txt").readLines()
    .forEach { process(it) }

val totalDiskSpace = 70000000
val desiredFreeDiskSpace = 30000000
val freeDiskSpace = totalDiskSpace - root.size
val neededDiskSpace = desiredFreeDiskSpace - freeDiskSpace

val wantedDirectorySize =
    findDirectoriesWithSizeFrom(root.content, mutableListOf(), neededDiskSpace)
        .sortedBy { it.size }
        .first()
        .size

println(wantedDirectorySize)

assert(wantedDirectorySize == 5649896)
