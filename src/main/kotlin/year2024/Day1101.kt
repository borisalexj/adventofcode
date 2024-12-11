package year2024

fun main() {

    /*
    0 -> 1
    even -> 10 and 0
    other -> number * 2024
     */

    val parsed = realInput11.split(" ").map { it.toLong() }
    println(parsed)

    val blinks = 25

    var res = arrayListOf<Long>()
    res.addAll(parsed)

    val sums = arrayListOf<Long>()

    for (i in 1..blinks) {
        val newRes = arrayListOf<Long>()
        for (stone in res) {
            if (stone == 0L) {
                newRes.add(1L)
            } else if (stone.toString().length % 2 == 0) { // парне
                val stoneStr = stone.toString()
                val leftStone = stoneStr.subSequence(0, stoneStr.length/2).toString()
                val rightStone = stoneStr.subSequence(stoneStr.length/2, stoneStr.length).toString()
                newRes.add(leftStone.toLong())
                newRes.add(rightStone.toLong())
            } else {
                newRes.add(stone*2024)
            }

        }
        println("blinks - $i | stones - ${newRes.size}")
        res = newRes
    }
    println(res)
    println("answer 1 - ${res.size}")
    println(res.sum())
}

val sampleInput11 = "0 1 10 99 999"
val sampleInput112 = "125 17"
val realInput11 = "0 27 5409930 828979 4471 3 68524 170" //  val blinks = 25 // answer - 194482