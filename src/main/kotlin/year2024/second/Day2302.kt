package year2024.second

import java.io.BufferedReader
import java.io.File

fun main() {
    val bufferedReader: BufferedReader = File("input23.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readLines() }
//    println(inputString)
//    inputString.forEach { println(it) }

//    val input = sampleInput23
    val input = inputString
    val parsed = input.map {
        Pair(it.split("-")[0], it.split("-")[1])
    }
    val possible = parsed.map { arrayListOf(it.first, it.second) }.flatten().toSet().sorted()
    println(parsed)
    println(possible)

    val result1 = arrayListOf<Triple<String, String, String>>()
    val result2 = mutableListOf<ArrayList<String>>()

    for (first in possible) {
//        println(first)
        val secondList = parsed.filter { it.first ==first || it.second == first }.map {
            if (it.first == first) it.second else it.first
        }
//        println(secondList)
        for (second in secondList) {
//            println(second)
            val thirdList = parsed.filter { (it.first == second) || (it.second == second) }.map {
                if (it.first == second) it.second else it.first
            }
            val thirdFilterd = thirdList.filter {third ->
                parsed.any { it.first == first && it.second == third} || parsed.any { it.first == third && it.second == first}
            }



//            val thirdFilterd = thirdList.filter {
//                it != first && it != second
//            }
//            println(thirdList)
//            println(thirdFilterd)
//            break
            thirdFilterd.forEach {
                result1.add(Triple(first, second, it))
                result2.add(arrayListOf(first, second, it))
            }
//            break
        }
//        break
    }

    println("-----------------")
//    result1.forEach { println(it) }
//    println("-----------------")
//    result2.forEach { println(it) }
    println("******************")
    println(result2.size)
//    result2.forEach { println(it) }
    val resulted = result2.map { it.sorted() }.toSet()
//    resulted.let {
//        println(it.size)
//        println(it)
//    }

    println("****************")
    resulted.filter {
        it.any { it.startsWith("t") }
    }.let {
        println("Result1 - ${it.size}") // 926
    }



}

//val sampleInput23 = arrayListOf(
//    "kh-tc",
//    "qp-kh",
//    "de-cg",
//    "ka-co",
//    "yn-aq",
//    "qp-ub",
//    "cg-tb",
//    "vc-aq",
//    "tb-ka",
//    "wh-tc",
//    "yn-cg",
//    "kh-ub",
//    "ta-co",
//    "de-co",
//    "tc-td",
//    "tb-wq",
//    "wh-td",
//    "ta-ka",
//    "td-qp",
//    "aq-cg",
//    "wq-ub",
//    "ub-vc",
//    "de-ta",
//    "wq-aq",
//    "wq-vc",
//    "wh-yn",
//    "ka-de",
//    "kh-ta",
//    "co-tc",
//    "wh-qp",
//    "tb-vc",
//    "td-yn",
//)