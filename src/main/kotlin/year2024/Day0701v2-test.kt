package year2024

import java.util.ArrayList

fun main() {

        val numbers = realInput07ros.map { it.split(":")[0].toLong() }
    val values = realInput07.map { it.split(":")[1] }.map { it.split(" ").filter { it.isNotEmpty() }.map { it.toLong() } }

    println(numbers.size)
    println(numbers.toSet().size)
}

