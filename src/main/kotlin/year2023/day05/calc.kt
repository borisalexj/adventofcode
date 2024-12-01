package year2023.day05

fun main(args: Array<String>) {
    var total = 0L
    val seedsList = realSeeds
    for (i in 0..((seedsList.size-1) /2) ) {
        val _seed = seedsList[i * 2].toLong()
        val seedRange = seedsList[i * 2 + 1]
        total = total + seedRange
    }
    println(total)
}