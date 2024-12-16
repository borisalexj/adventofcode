package year2024

import java.io.BufferedReader
import java.io.File


fun main() {
    val bufferedReader: BufferedReader = File("input16.txt").bufferedReader()
        val inputString = bufferedReader.use { it.readLines() }
//        val input = inputString.map {it.split("").filter { it.isNotEmpty() }}
//
    val input = sampleInput161.map {it.split("").filter { it.isNotEmpty() }}
//    val input = sampleInput162.map {it.split("").filter { it.isNotEmpty() }}
    input.forEach { println(it) }

    val startY = input.indexOfFirst { it.contains("S") }
    val startX = input.find { it.contains("S") }!!.indexOf("S")

    println("$startY - $startX")

    val result = mutableMapOf<Int,ArrayList<Pair<Pair<Int, Int>, Pair<Int, Int>>>>()
    moveREcursion(result, input, _score = 1000, posY = startY, posX = startX, yDir = -1, xDir = 0)
    moveREcursion(result, input, _score = 1000,  posY = startY, posX = startX, yDir = 1, xDir = 0)
    moveREcursion(result, input, _score = 1000, posY = startY, posX = startX,  yDir = 0, xDir = -1)
    moveREcursion(result, input, _score = 1000, posY = startY, posX = startX,  yDir = 0, xDir = -1)

println("====================")
//println(result.keys)
    result.keys.sorted().forEach {
        try {if (
         result[it]!![0].first == Pair(12,1) &&
            result[it]!![1].first == Pair(11,1) &&
            result[it]!![2].first == Pair(10,1) &&
            result[it]!![3].first == Pair(9,1) &&
            result[it]!![4].first == Pair(9,2) &&
            result[it]!![5].first == Pair(9,3)
            &&             result[it]!![6].first == Pair(8,3)
            &&             result[it]!![7].first == Pair(7,3)
            &&             result[it]!![8].first == Pair(7,4)
            &&             result[it]!![9].first == Pair(7,5)
            &&             result[it]!![10].first == Pair(7,6)
            &&             result[it]!![11].first == Pair(7,7)
            &&             result[it]!![12].first == Pair(7,8)
            &&             result[it]!![13].first == Pair(7,9)
            &&             result[it]!![14].first == Pair(7,10)
            ) {
        println("$it - ${result[it]!!.map { it.first}}")
        draw(input, result[it]!!, it)
        }
        } catch (e:Exception) {}
     }
//    result.keys.min().let {
//        draw(input, result[it]!!, it)
//        println("$it - ${result[it]}")
//     }
//results.filter {
//
//    it.value[0] == Pair(12,1) &&
//    it.value[1] == Pair(11,1) &&
//    it.value[2] == Pair(10,1) &&
//    it.value[3] == Pair(9,1) &&
//    it.value[4] == Pair(9,2)
// }.forEach {
//
//     draw(input, it.key, it.)
//  }
}

fun moveREcursion(result: MutableMap<Int, ArrayList<Pair<Pair<Int, Int>, Pair<Int, Int>>>>, input: List<List<String>>, _score: Int, posY: Int, posX: Int, yDir: Int, xDir: Int, _path : ArrayList<Pair<Pair<Int, Int>, Pair<Int, Int>>> = arrayListOf()) {
    val score = _score + 1
    val curY = posY+yDir
    val curX = posX+xDir
     1
//    draw(input, _path, score)

    if (input[curY][curX] == wall) {
        result[_path.hashCode()] = _path
        return
    }
    if (_path.contains(Pair(Pair(curY, curX),Pair(yDir, xDir)))) {
        result[_path.hashCode()] = _path
        return
    }

    val newPath : ArrayList<Pair<Pair<Int, Int>, Pair<Int, Int>>> = arrayListOf()
    newPath.addAll(_path)
    newPath.add(Pair(Pair(curY, curX), Pair(yDir, xDir)))

    if (input[curY][curX] == end) {
        result[score] = newPath
        return
    }

    moveREcursion(result, input, score, curY, curX, yDir, xDir, newPath)

    if (yDir == -1 || yDir ==1) {
        val newPath2 : ArrayList<Pair<Pair<Int, Int>, Pair<Int, Int>>> = arrayListOf()
        newPath2.addAll(newPath)
        moveREcursion(result, input, score+rotateScore, curY, curX, 0, 1, newPath2)
        val newPath3 : ArrayList<Pair<Pair<Int, Int>, Pair<Int, Int>>> = arrayListOf()
        newPath3.addAll(newPath)
        moveREcursion(result, input, score+rotateScore, curY, curX, 0, -1, newPath3)
    }
    if (xDir == -1 || xDir ==1) {
        val newPath4 : ArrayList<Pair<Pair<Int, Int>, Pair<Int, Int>>> = arrayListOf()
        newPath4.addAll(newPath)
        moveREcursion(result, input, score+rotateScore, curY, curX, -1, 0, newPath4)
        val newPath5 : ArrayList<Pair<Pair<Int, Int>, Pair<Int, Int>>> = arrayListOf()
        newPath5.addAll(newPath)
        moveREcursion(result, input, score+rotateScore, curY, curX, 1, 0, newPath5)
    }
}

fun draw(input: List<List<String>>, path: ArrayList<Pair<Pair<Int, Int>, Pair<Int, Int>>>, score:Int) {
    println("------$score---------")
    input.forEachIndexed { indexY, strings ->
        strings.mapIndexed { indexX, s ->
            if (path.filter { it.first.first == indexY && it.first.second == indexX }.isNotEmpty()) {
                "X"
            } else {
                s
            }
         }.joinToString("").let { println(it) }
     }
}

val rotateScore = 1000
val wall = "#"
val end = "E"
val sampleInput161 = arrayListOf(
    "###############",
    "#.......#....E#",
    "#.#.###.#.###.#",
    "#.....#.#...#.#",
    "#.###.#####.#.#",
    "#.#.#.......#.#",
    "#.#.#####.###.#",
    "#...........#.#",
    "###.#.#####.#.#",
    "#...#.....#.#.#",
    "#.#.#.###.#.#.#",
    "#.....#...#.#.#",
    "#.###.#.#.#.#.#",
    "#S..#.....#...#",
    "###############",
)

val sampleInput162 = arrayListOf(
    "#################",
    "#...#...#...#..E#",
    "#.#.#.#.#.#.#.#^#",
    "#.#.#.#...#...#^#",
    "#.#.#.#.###.#.#^#",
    "#>>v#.#.#.....#^#",
    "#^#v#.#.#.#####^#",
    "#^#v..#.#.#>>>>^#",
    "#^#v#####.#^###.#",
    "#^#v#..>>>>^#...#",
    "#^#v###^#####.###",
    "#^#v#>>^#.....#.#",
    "#^#v#^#####.###.#",
    "#^#v#^........#.#",
    "#^#v#^#########.#",
    "#S#>>^..........#",
    "#################",
)