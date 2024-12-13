package year2024

import java.io.BufferedReader
import java.io.File

fun main() {

    val bufferedReader: BufferedReader = File("input13.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readLines() }
    val input = inputString.filter { it.isNotEmpty() }.toMutableList()
//    println(input)
//    val input = sampleInput13

    val parsedInput = arrayListOf<Machine>()
    val mins = arrayListOf<Int>()

    for (i in 0..(input.size -1) /3) {
        val ax = input[i*3].split("X")[1].split(",")[0]
        val ay = input[i*3].split("Y")[1]
        val bx = input[i*3+1].split("X")[1].split(",")[0]
        val by = input[i*3+1].split("Y")[1]
        val px = input[i*3+2].split("X=")[1].split(",")[0]
        val py = input[i*3+2].split("Y=")[1]
        val machine = Machine(
            Pair(ax.toInt(),ay.toInt()),
            Pair(bx.toInt(),by.toInt()),
            Pair(px.toInt(),py.toInt()),
        )
        parsedInput.add(machine)
    }

parsedInput.forEach {
    println(it)
 }
//    println()

    parsedInput.forEach {
        val ax = it.buttonA.first
        val bx = it.buttonB.first
        val px = it.prize.first

        val resX = arrayListOf<Pair<Int,Int>>()

        for (a in px/ax downTo 0) {
            for (b in px/bx downTo  0) {
//                if (a ==80                 && b == 40                    ) {
//                    println("$a $b  - ${ax*a + bx*b} - $px")
//                }
                if (ax*a + bx*b == px) {
                    resX.add(Pair(a,b))
                }
            }
        }
        resX.takeIf { it.isNotEmpty() }?.minBy {
            it.first*aCost + it.second*bCost
         }?.let {
             println("min X - $it")
          } ?: { println("no min x")}

        val ay = it.buttonA.second
        val by = it.buttonB.second
        val py = it.prize.second

        val resY = arrayListOf<Pair<Int,Int>>()

        for (a in py/ay downTo 0) {
            for (b in py/by downTo  0) {
//                if (a ==80                 && b == 40                    ) {
//                    println("$a $b  - ${ay*a + by*b} - $py")
//                }
                if (ay*a + by*b == py) {
                    resY.add(Pair(a,b))
                }
            }
        }
            resY.takeIf { it.isNotEmpty() }?.minBy {
                it.first*aCost + it.second*bCost
             }?.let {
                 println("min Y - $it")
              } ?: { println("no min y")}


        resY.takeIf { it.isNotEmpty() && resX.isNotEmpty()}?.filter { y ->
            resX.any { it == y }
        }?.takeIf { it.isNotEmpty() }?.minBy {
            it.first*aCost + it.second*bCost
         }?.let {
             mins.add(it.first*aCost + it.second*bCost)
              println("min XY - $it - cost - ${it.first*aCost + it.second*bCost}")
        } ?: run { println("no min xx") }
     }

     println("----------------------")
     println("res 1 = ${mins.sum()}")

}

val aCost = 3
val bCost = 1

data class Machine(
    val buttonA : Pair<Int, Int>,
    val buttonB : Pair<Int, Int>,
    val prize : Pair<Int, Int>,
)

val sampleInput13 = arrayListOf (
    "Button A: X+94, Y+34",
    "Button B: X+22, Y+67",
    "Prize: X=8400, Y=5400",

    "Button A: X+26, Y+66",
    "Button B: X+67, Y+21",
    "Prize: X=12748, Y=12176",

    "Button A: X+17, Y+86",
    "Button B: X+84, Y+37",
    "Prize: X=7870, Y=6450",

    "Button A: X+69, Y+23",
    "Button B: X+27, Y+71",
    "Prize: X=18641, Y=10279",
)