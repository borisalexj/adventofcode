package year2024

import java.io.BufferedReader
import java.io.File

fun main() {
    val bufferedReader: BufferedReader = File("input18.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readLines() }
    val input = inputString.map {
                it.split(",")  }.map {
                    Pair(it[1].toInt(), it[0].toInt())
                 }
        .subList(0,1024)


//    val input = sampleInput18.map {
//        it.split(",")  }.map {
//            Pair(it[1].toInt(), it[0].toInt())
//         }.subList(0,12)


    println(input)

    fun draw() {
        val maxX = 71// input.map { it.first}.max()
        val maxY = 71 //input.map { it.second}.max()
        for (y in 0 .. maxY-1) {
        for (x in 0 .. maxX-1) {
            if (input.any { it.second == x && it.first == y }  ) {
                print("#")
            } else {
                print(".")
            }
        }
            println()
    }
    }
    fun draw2(result: java.util.ArrayList<Pair<Int, Int>>) {
        println("--------------------")
        val maxX = 71 //input.map { it.first}.max()
        val maxY = 71 //input.map { it.second}.max()
        for (y in 0 .. maxY-1) {
        for (x in 0 .. maxX-1) {
            if (input.any { it.second == x && it.first == y }  ) {
                print("#")
            } else if (result.any { it.second == x && it.first == y }  ) {
                print("O")
            } else {
                print(".")
            }
        }
            println()
    }
    }

    draw()
    val startX = 0
    val startY = 0
    val result : ArrayList<ArrayList<Pair<Int, Int>>> = arrayListOf()
    moveRecur(startY, startX, result = result, input = input )
    println(result)
    val min = result.map { it.size }.min()
    println(min)
    result.forEach {
//        draw2(it)

     }
     result.filter { it.size == min }.forEach { draw2(it) }
     println("result 1 - ${min-1}")
}

fun moveRecur(curY:Int, curX: Int, path : ArrayList<Pair<Int, Int>> = arrayListOf(), result :ArrayList<ArrayList<Pair<Int, Int>>> = arrayListOf(), input: List<Pair<Int, Int>>) {
    if (curY<0 || curX<0 || curY >70 || curX>70) return
    if (input.filter {it.first == curY && it.second == curX}.isNotEmpty()) {
//        result.add(path)
        return
    }
    if (path.filter{it.first == curY && it.second == curX}.isNotEmpty()) {
//        result.add(path)
        return
    }

    path.add(Pair(curY, curX))
    if (curY == 70 && curX == 70) {
        result.add(path)
        return
    }
    val path1 : ArrayList<Pair<Int, Int>> = arrayListOf()
    val path2 : ArrayList<Pair<Int, Int>> = arrayListOf()
    val path3 : ArrayList<Pair<Int, Int>> = arrayListOf()
    val path4 : ArrayList<Pair<Int, Int>> = arrayListOf()
    path1.addAll(path)
    path2.addAll(path)
    path3.addAll(path)
    path4.addAll(path)
    moveRecur(curY-1, curX, path1, result, input)
    moveRecur(curY+1, curX, path2, result, input)
    moveRecur(curY, curX-1, path3, result, input)
    moveRecur(curY, curX+1, path4, result, input)
}

val sampleInput18 = arrayListOf(
    "5,4",
    "4,2",
    "4,5",
    "3,0",
    "2,1",
    "6,3",
    "2,4",
    "1,5",
    "0,6",
    "3,3",
    "2,6",
    "5,1",
    "1,2",
    "5,5",
    "2,5",
    "6,5",
    "1,4",
    "0,4",
    "6,4",
    "1,1",
    "6,1",
    "1,0",
    "0,5",
    "1,6",
    "2,0",
)