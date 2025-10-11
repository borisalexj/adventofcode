package year2024.second

fun main() {
    val arr = arrayListOf(
        arrayListOf("a", "b", "c"),
        arrayListOf("q", "w", "e"),
        arrayListOf("1", "2", "3"),
    )
    val parsedMap = arr.map {
        it.map { it }
    }
    parsedMap.forEach {
        println(it)
    }
}