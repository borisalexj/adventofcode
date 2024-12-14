package year2024

import java.io.BufferedReader
import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToLong

// 10 000 000 008 400
fun main() {
    // https://ua.onlinemschool.com/math/library/analytic_geometry/lines_intersection/
    val bufferedReader: BufferedReader = File("input13.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readLines() }
    val input = inputString.filter { it.isNotEmpty() }.toMutableList()
//    val input = sampleInput13
//    println(input)

    val parsedInput = arrayListOf<Machine>()
    val mins = arrayListOf<Long>()

    for (i in 0..(input.size -1) /3) {
        val ax = input[i*3].split("X")[1].split(",")[0]
        val ay = input[i*3].split("Y")[1]
        val bx = input[i*3+1].split("X")[1].split(",")[0]
        val by = input[i*3+1].split("Y")[1]
        val px = input[i*3+2].split("X=")[1].split(",")[0]
        val py = input[i*3+2].split("Y=")[1]
        val machine = Machine(
            Pair(ax.toLong(),ay.toLong()),
            Pair(bx.toLong(),by.toLong()),
//            Pair(px.toLong(),py.toLong()),
            Pair(px.toLong() + 10000000000000,py.toLong() + 10000000000000),
        )
        parsedInput.add(machine)
    }

    var loopCounter = 0

    parsedInput.forEach {
        println(it)
    }
//    println()
    fun calculateGCD(a: Long, b: Long): Long {
        var num1 = a
        var num2 = b
        while (num2 != 0L) {
            val temp = num2
            num2 = num1 % num2
            num1 = temp
        }
        return num1
    }

    parsedInput.forEachIndexed { index, machine ->
        val ax = machine.buttonA.first
        val bx = machine.buttonB.first
        val px = machine.prize.first
        val ay = machine.buttonA.second
        val by = machine.buttonB.second
        val py = machine.prize.second

        var res: Pair<Long, Long>? = null

        val x1 = ((px.toDouble()/bx.toDouble()-py.toDouble()/by.toDouble())/(ax.toDouble()/bx.toDouble()-ay.toDouble()/by.toDouble()))
        val x2 = ((px.toDouble()-ax.toDouble()*x1.toDouble())/bx.toDouble())
//        println("$x1 - $x2")
        if (abs(x1 - x1.roundToLong()) < 0.001 && abs(x2 - x2.roundToLong()) < 0.001) {
            res = Pair(x1.roundToLong(),x2.roundToLong())
        }

        res?.let {
            println("$index - $machine - $it")
            mins.add(it.first*aCost + it.second*bCost)
        }

    }

    println("----------------------")
    println("res 2 = ${mins.sum()}") // p1 35255 // p2 87582154060429
    println("loopCounter = ${loopCounter}")
    // sample p1  2 116 // 1079
    // real p1 172 244 // 88 010
    //sample p2
    // real p2

}
