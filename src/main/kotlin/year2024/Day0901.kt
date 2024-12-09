package year2024

import java.io.BufferedReader
import java.io.File

fun main() {
    val bufferedReader: BufferedReader = File("input09.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readText() }
    val parsed = inputString.split("").filter { it.isNotEmpty() }.toMutableList()
//    val parsed = sampleInput09.split("").filter { it.isNotEmpty() }.toMutableList()
//    println(parsed)

    val output = arrayListOf<String>()

    parsed.mapIndexed { index, s ->
        if (index % 2 == 0) { // парне
            output.addAll(
                (1..s.toInt()).map { (index / 2).toString() }
            )
//                .repeat().split("").filter { it.isNotEmpty() })
        } else { //  непарне
            output
                .addAll(
                    ".".toString()
                        .repeat(s.toInt())
                        .split("")
                        .filter { it.isNotEmpty() }
                )
        }
    }

//    println(output)

    fun findFirstPoint(inp: ArrayList<String>): Int {
        return inp.indexOf(".")
    }

    fun findLastDigit(inp: ArrayList<String>): Int {
        return inp.indexOfLast { it != "." }
    }

    while (true) {
        val left = findFirstPoint(output)
        val right = findLastDigit(output)

//        println("$left - $right")

        if (left == right || left > right) break

        val leftValue = output[left]
        val rightValue = output[right]

        output[left] = rightValue
        output[right] = leftValue
    }

//    println(output)

    val result = output.filter { it != "." }.mapIndexed { index, s ->
        index.toBigInteger() * s.toBigInteger()
    }

    println(result.sumOf { it }) // 6225730762521

//    println(0.toString().repeat(3))
//
//    println(0 % 2)
//    println(1 % 2)
//    println(2 % 2)
//    println(3 % 2)
//    println(4 % 2)
}

val sampleInput09 = "2333133121414131402"

val samplestInput09 = "12345"