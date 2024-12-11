package year2024

fun main() {

    /*
    0 -> 1
    even -> 10 and 0
    other -> number * 2024
     */

    val parsed = realInput11.split(" ").map { it.toLong() }
    println(parsed)


    var res = arrayListOf<Long>()
    res.addAll(parsed)


    val sums = arrayListOf<Long>()

//    for (i in 1..blinks) {
        val newRes = arrayListOf<Long>()
        for (stone in res) {
            recurstion11(stone, 1)
//            if (stone == 0L) {
//                newRes.add(1L)
//            } else if (stone.toString().length % 2 == 0) { // парне
//                val stoneStr = stone.toString()
//                val leftStone = stoneStr.subSequence(0, stoneStr.length/2).toString()
//                val rightStone = stoneStr.subSequence(stoneStr.length/2, stoneStr.length).toString()
//                newRes.add(leftStone.toLong())
//                newRes.add(rightStone.toLong())
//            } else {
//                newRes.add(stone*2024)
//            }

        }
//        println("blinks - $i | stones - ${newRes.size}")
//        res = newRes
//    }
//    println(res)
//    println("answer 1 - ${res.size}")
    println("answer 2 - $result11")
//    println(res.sum())
}
    var result11 = 0L
    val blinksTotal = 75L

fun recurstion11 (stone :Long, blinks : Long)  {
    if (blinks == blinksTotal) {
        result11 = result11 +1
    }
    if (stone == 0L) {
        recurstion11(1L, blinks + 1)
    } else if (stone.toString().length % 2 == 0) { // парне
        val stoneStr = stone.toString()
        val leftStone = stoneStr.subSequence(0, stoneStr.length/2).toString()
        val rightStone = stoneStr.subSequence(stoneStr.length/2, stoneStr.length).toString()
        recurstion11(leftStone.toLong(), blinks +1)
        recurstion11(rightStone.toLong(), blinks +1)
//        newRes.add(rightStone.toLong())
    } else {
        recurstion11(stone*2024, blinks +1)
    }
}

//val sampleInput11 = "0 1 10 99 999"
//val sampleInput112 = "125 17"
//val realInput11 = "0 27 5409930 828979 4471 3 68524 170" //  val blinks = 25 // answer - 194482