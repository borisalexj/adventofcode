package year2024

import kotlin.math.abs

fun main() {
    var parsed = sampleInput14.map {
        val pv = it.split(" ")
        Robot(
            px = pv[0].split(",")[0].split("=")[1].toInt(),
            py = pv[0].split(",")[1].toInt(),
            vx = pv[1].split(",")[0].split("=")[1].toInt(),
            vy = pv[1].split(",")[1].toInt(),
        )
    }
    parsed.forEach { println(it) }

    val board = arrayListOf<MutableList<String>>()
    val boardHeight = parsed.maxOf { it.py }
    val boardWidth = parsed.maxOf { it.px }

    val robot = parsed[10]
    parsed= arrayListOf(robot)

    fun drawBoard () {
        board.clear()
        for (y in 0..boardHeight) {
            board.add(".".repeat(boardWidth).split("").toMutableList())
            for (x in 0..boardWidth) {
                parsed.filter { it.px ==x && it.py ==y  }.size.let {
                    if (it ==0) {
                        board[y][x] = "."
                } else {
                        board[y][x] = it.toString()
                    }
                }

            }
        }
        board.forEachIndexed { index, strings ->  println("$index - $strings") }
    }
    drawBoard()

    println("--------------")

    for (s in 1..seconds) {
        println("-- sec - $s --")
        parsed.forEach {
            it.px = it.px+it.vx
            if (it.px <0) {
                it.px = boardWidth - abs(it.px) +1
            }else if (it.px > boardWidth) {
                it.px = it.px - boardWidth -1
            }
            it.py = it.py+it.vy
            if (it.py <0) {
                it.py = boardHeight - abs(it.py) +1
            }else if (it.py > boardHeight) {
                it.py = it.py - boardHeight -1
            }
            println(it)
        }
        drawBoard()
    }

}

val seconds = 5
data class Robot(
    var px: Int,
    var py: Int,
    var vx: Int,
    var vy: Int,
)

val sampleInput14 = arrayListOf(
    "p=0,4 v=3,-3",
    "p=6,3 v=-1,-3",
    "p=10,3 v=-1,2",
    "p=2,0 v=2,-1",
    "p=0,0 v=1,3",
    "p=3,0 v=-2,-2",
    "p=7,6 v=-1,-3",
    "p=3,0 v=-1,-2",
    "p=9,3 v=2,3",
    "p=7,3 v=-1,2",
    "p=2,4 v=2,-3",
    "p=9,5 v=-3,-3",
)