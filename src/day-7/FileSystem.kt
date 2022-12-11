package `day-7`

interface FileSystemItem {
    val name: String
    val size: Int
}

data class File(override val name: String, override val size: Int) : FileSystemItem

class Directory(
    override val name: String,
    val content: MutableList<FileSystemItem> = mutableListOf(),
    parent: Directory? = null
) : FileSystemItem {
    override val size: Int get() = content.sumOf { it.size }
    var parent: Directory = parent ?: this

    override fun toString(): String {
        return "name: $name, size: $size"
    }
}