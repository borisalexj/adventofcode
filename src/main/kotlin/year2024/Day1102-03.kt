package year2024

fun main() {

    /*
    0 -> 1
    even -> 10 and 0
    other -> number * 2024
     */

    val parsed = realInput11.split(" ").map { it.toLong() }.map { it to 1L }.toMap()
    println(parsed)

    val blinks = 75

    var res = mutableMapOf<Long, Long>()
    res.putAll(parsed)

//    val sums = arrayListOf<Long>()
//    var newRes = mutableMapOf<Long, Long>()
//    newRes.putAll(parsed)


    for (i in 1..blinks) {
        val keys = res.keys
//        newRes.putAll(res)
        var newRes = mutableMapOf<Long, Long>()
        res.forEach { stone, amount ->
//            println("$stone - $res | $newRes")
            if (stone == 0L) {
                val newStone = 1L
                newRes[newStone] = newRes.getOrDefault(newStone, 0) + amount
//                val oldStone = 0L
//                newRes[oldStone] = (newRes.getOrDefault(oldStone, 1) ) - 1
            } else if (stone.toString().length % 2 == 0) { // парне
                val stoneStr = stone.toString()
                val leftStone = stoneStr.subSequence(0, stoneStr.length/2).toString().toLong()
                val rightStone = stoneStr.subSequence(stoneStr.length/2, stoneStr.length).toString().toLong()
                newRes[leftStone] = newRes.getOrDefault(leftStone, 0) + amount
//                newRes[rightStone] = amount + 1
                newRes[rightStone] = newRes.getOrDefault(rightStone, 0) + amount
//                newRes[stone] = newRes.getOrDefault(leftStone, 0) - 1
            } else {
                val stone2024 = stone*2024L
                newRes[stone2024] = newRes.getOrDefault(stone2024, 0) + amount
//                newRes[stone] = (newRes.getOrDefault(stone, 1) ) - 1
            }
//            println("$stone - ${res.filter { it.value != 0L }} | ${newRes.filter { it.value != 0L }}")
//            println("--------------")
        }
        res.clear()
        res.putAll(newRes.filter { it.value != 0L })
//            newRes = mutableMapOf<Long, Long>()

        println("blinks - $i | stones - ${newRes.size}")
        }
//    }
    println(res)
    println("answer 2 - ${res.values.sum()}")
//    println(res.sum())
}

//val sampleInput11 = "0 1 10 99 999"
//val sampleInput112 = "125 17"
//val realInput11 = "0 27 5409930 828979 4471 3 68524 170" //  val blinks = 25 // answer  2 - 232454623677743