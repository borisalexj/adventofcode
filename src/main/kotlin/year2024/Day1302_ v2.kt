package year2024

import java.io.BufferedReader
import java.io.File
import kotlin.math.max

fun main() {

    val bufferedReader: BufferedReader = File("input13.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readLines() }
//    val input = inputString.filter { it.isNotEmpty() }.toMutableList()
    val input = sampleInput13
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

    parsedInput.forEachIndexed { index, machine ->
        val ax = machine.buttonA.first
        val bx = machine.buttonB.first
        val px = machine.prize.first
        val ay = machine.buttonA.second
        val by = machine.buttonB.second
        val py = machine.prize.second

        var res : Pair<Long,Long>? = null
        val ratioAx = px/ax
        val ratioBx = px/bx

        val ratioAy = py/ay
        val ratioBy = py/by

        for (c in 0.. max(max(ratioAx, ratioBx), max(ratioAy, ratioBy))) {
            val x2 = c
            val x1 = (px - (bx*x2))/ax
//            val y2 = c
//            val y1 = (py - (by*y2))/ay

            loopCounter = loopCounter +1
            if (ax*x1 + bx*x2 == px && ay*x1 + by*x2 == py) {
                if (res != null && x1*aCost +x2* bCost < res.first* aCost + res.second*bCost) {
                    res = Pair(x1,x2)
                } else
                    if (res == null) {
                        res = Pair(x1,x2)
                    }

            }
        }

        res?.let {
            println("$index - $machine - $it")
            mins.add(it.first*aCost + it.second*bCost)
        }

    }

    println("----------------------")
    println("res 1 = ${mins.sum()}") // 35255
    println("loopCounter = ${loopCounter}") // 248 140 // 172 244

}
