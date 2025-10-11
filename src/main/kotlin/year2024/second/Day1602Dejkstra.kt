package year2024.second

import year2024.result
import java.io.BufferedReader
import java.io.File

fun main() {
//    val parsed = sampleInput16.map { it.split("").filter { it.isNotEmpty() } }
//    val parsed = sampleInput162.map { it.split("").filter { it.isNotEmpty() } }

    val bufferedReader: BufferedReader = File("input16.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readLines() }
    val parsed = inputString.map {it.split("").filter { it.isNotEmpty() }}

    val startX = parsed.find { it.contains("S") }!!.indexOf("S")
    val startY = parsed.indexOfFirst { it.contains("S") }

    val endX = parsed.find { it.contains("E") }!!.indexOf("E")
    val endY = parsed.indexOfFirst { it.contains("E") }

    parsed.forEach { println(it.joinToString ("")) }
    println("$startY, $startX")

    val result = arrayListOf<ArrayList<Int>>()
    parsed.forEachIndexed { indexY, strings ->
        val tmp = arrayListOf<Int>()
        strings.forEachIndexed { indexX, s ->
            tmp.add(Int.MAX_VALUE)
        }
        result.add(tmp)
    }

    moveRecursively16v2d2(parsed, startY, startX, dirY = 0, dirX = 1, score = 0, result = result, _path = arrayListOf<Pair<Int, Int>>())


    println("-------------------")
    result[endY][endX].let {
        println(it) // correct 147628
    }
    val best = paths.minBy { it.first }.first
    paths.filter { it.first == best }.map { it.second }.flatten().toSet().size.let { println(it) } // correct 2 - 670

    val bestPoints = paths.filter { it.first == best }.map { it.second }.flatten().toSet()

//    parsed.forEachIndexed { indexY, strings ->
//        strings.forEachIndexed { indexX, s ->
//            if (bestPoints.contains(Pair(indexY, indexX))) {
//                print("O")
//            } else {
//                print(parsed[indexY][indexX])
//            }
//        }
//        println()
//    }

}

val paths = arrayListOf<Pair<Int, ArrayList<Pair<Int,Int>>>>()

fun moveRecursively16v2d2(parsed: List<List<String>>,
                          curY: Int, curX: Int, dirY: Int, dirX: Int, score : Int,
                          result: ArrayList<ArrayList<Int>>, _path: ArrayList<Pair<Int,Int>>) {
    val current = parsed[curY][curX]
//    println("$curY - $curX - $dirY - $dirX - ${parsed[curY][curX]} - $score")
    if (parsed[curY][curX] == "#") return
    val path = arrayListOf<Pair<Int,Int>>()
    path.addAll(_path)
    path.add(Pair(curY, curX))
    if (parsed[curY][curX] == "E") {
        paths.add(Pair(score, path))
//        println("score - $score")
    }
    if (result[curY][curX] >= score) {
        result[curY][curX] = score
    }

    if (score - result[curY][curX] < 1+ scoreBonus) {

    moveRecursively16v2d2(parsed, curY + dirY, curX + dirX, dirY, dirX, score + 1, result = result, _path = path)
        if (dirY != 0) {
            moveRecursively16v2d2(parsed, curY, curX - 1, 0, -1, score + 1 + scoreBonus, result = result, _path = path)
            moveRecursively16v2d2(parsed, curY, curX + 1, 0, +1, score + 1 + scoreBonus, result = result, _path = path)
        }
        if (dirX != 0) {
            moveRecursively16v2d2(parsed, curY - 1, curX, -1, 0, score + 1 + scoreBonus, result = result, _path = path)
            moveRecursively16v2d2(parsed, curY + 1, curX, +1, 0, score + 1 + scoreBonus, result = result, _path = path)
        }
        if (dirY != 0 && dirX != 0) {
            throw IllegalStateException()
        }
    }
}
