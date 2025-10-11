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
//    result.forEach { println(it.joinToString (".")) }


    moveRecursively16v2d(parsed, startY, startX, dirY = 0, dirX = 1, score = 0, result = result)


    println("-------------------")
    result[endY][endX].let {
        println(it) // correct 147628
    }

}

//fun drawPathOnBoard(parsed: List<List<String>>, path : ArrayList<Move>) {
//    println("---------------")
//    parsed.forEachIndexed { indexY, strings ->
//        strings.forEachIndexed { indexX, s ->
//            if (path.any { it.x == indexX && it.y == indexY }) {
//                print("0")
//            } else {
//                print(s)
//            }
//        }
//        println()
//    }
//}

//var step = 0
fun moveRecursively16v2d(parsed: List<List<String>>, curY: Int, curX: Int, dirY: Int, dirX: Int, score : Int, result: ArrayList<ArrayList<Int>>) {
    val current = parsed[curY][curX]
//    println("$curY - $curX - $dirY - $dirX - ${parsed[curY][curX]} - $score")
    if (parsed[curY][curX] == "#") return
    if (parsed[curY][curX] == "E") {
        println("score - $score")
//        throw IllegalStateException("finished")
    }
    if (result[curY][curX] > score) {
        result[curY][curX] = score
        moveRecursively16v2d(parsed, curY + dirY, curX + dirX, dirY, dirX, score + 1, result = result)
        if (dirY != 0 ) {
            moveRecursively16v2d(parsed, curY, curX -1, 0, -1, score + 1 + scoreBonus, result = result)
            moveRecursively16v2d(parsed, curY, curX + 1, 0, +1, score + 1 + scoreBonus,  result = result)
        } else if (dirX != 0 ) {
            moveRecursively16v2d(parsed, curY -1, curX, -1, 0, score + 1+ scoreBonus, result = result)
            moveRecursively16v2d(parsed, curY +1, curX, +1, 0, score + 1 +scoreBonus, result = result)
        } else {
            throw IllegalStateException()
        }
    } else {
        return
    }
}
//val scoreBonus = 1000
//data class Move(
//    val y: Int,
//    val x: Int,
//    val dY:Int,
//    val dX:Int
//)
//
//data class Path(
//    val moves : ArrayList<Move>,
//    val score: Int
//)
//
//val sampleInput16 = arrayListOf( // 7036
//    "###############",
//"#.......#....E#",
//"#.#.###.#.###.#",
//"#.....#.#...#.#",
//"#.###.#####.#.#",
//"#.#.#.......#.#",
//"#.#.#####.###.#",
//"#...........#.#",
//"###.#.#####.#.#",
//"#...#.....#.#.#",
//"#.#.#.###.#.#.#",
//"#.....#...#.#.#",
//"#.###.#.#.#.#.#",
//"#S..#.....#...#",
//"###############",
//)
//
//val sampleInput162 = arrayListOf(
//    "#################",
//"#...#...#...#..E#",
//"#.#.#.#.#.#.#.#.#",
//"#.#.#.#...#...#.#",
//"#.#.#.#.###.#.#.#",
//"#...#.#.#.....#.#",
//"#.#.#.#.#.#####.#",
//"#.#...#.#.#.....#",
//"#.#.#####.#.###.#",
//"#.#.#.......#...#",
//"#.#.###.#####.###",
//"#.#.#...#.....#.#",
//"#.#.#.#####.###.#",
//"#.#.#.........#.#",
//"#.#.#.#########.#",
//"#S#.............#",
//"#################",
//)
