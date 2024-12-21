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

    parsed.forEach { println(it.joinToString ()) }
//    parsed.map {
//        it.joinToString()
//    }.let { println(it) }
    println("$startY, $startX")

    val result = arrayListOf<Path>()

    moveRecursively16v2(parsed, startY, startX, dirY = 0, dirX = 1, score = 0, result = result)
//    moveRecursively16v2(parsed, startY, startX, dirY = -1, dirX = 0, score = 0+ scoreBonus, result = result)
    val min = result.map { it.score }.min()
    println(min)
    println(result.filter { it.score == min }.size)
    result.filter { it.score == min }.forEach {
        println("-${it.score}----------------")
        drawPathOnBoard(parsed, it.moves)
    }
}

fun drawPathOnBoard(parsed: List<List<String>>, path : ArrayList<Move>) {
    println("---------------")
    parsed.forEachIndexed { indexY, strings ->
        strings.forEachIndexed { indexX, s ->
            if (path.any { it.x == indexX && it.y == indexY }) {
                print("0")
            } else {
                print(s)
            }
        }
        println()
    }
}

var step = 0
fun moveRecursively16v2(parsed: List<List<String>>, curY: Int, curX: Int, dirY: Int, dirX: Int, score: Int, path : ArrayList<Move> = arrayListOf(), result: ArrayList<Path>) {
    step = step +1
//    if (step == 10) throw IllegalStateException()
//    drawPathOnBoard(parsed, path)
//    println(path)
    if (parsed[curY][curX] == "E") {
         // success
//        println(result)
        result.add(Path(
            moves = path,
            score = score

        ))
        return
    }
    if (parsed[curY][curX] == "#") {
//        result.add(Path(path,score))
        return
    } //fuck  }

    if (path.any {it.x == curX && it.y == curY && it.dY == dirY && it.dX == dirX }) { return }

    val m = Move(curY, curX, dirY, dirX)
//    val newPath : ArrayList<Move> = arrayListOf()
//    newPath.addAll(path)
//    newPath.add(m)

    path.add( m)
    val newPath1 : ArrayList<Move> = arrayListOf()
    val newPath2 : ArrayList<Move> = arrayListOf()
    newPath1.addAll(path)
    newPath2.addAll(path)


    moveRecursively16v2(parsed, curY + dirY, curX + dirX, dirY, dirX, score + 1, path, result = result)
    if (dirY != 0 ) {
        moveRecursively16v2(parsed, curY + dirY, curX + dirX, 0, -1, score + 1 + scoreBonus, newPath1, result = result)
        moveRecursively16v2(parsed, curY + dirY, curX + dirX, 0, +1, score + 1 + scoreBonus, newPath2, result = result)
    } else if (dirX != 0 ) {
        moveRecursively16v2(parsed, curY + dirY, curX + dirX, -1, 0, score + 1+ scoreBonus, newPath1, result = result)
        moveRecursively16v2(parsed, curY + dirY, curX + dirX, +1, 0, score + 1 +scoreBonus, newPath2, result = result)

    } else {
        throw IllegalStateException()
    }
}
val scoreBonus = 1000
data class Move(
    val y: Int,
    val x: Int,
    val dY:Int,
    val dX:Int
)

data class Path(
    val moves : ArrayList<Move>,
    val score: Int
)

val sampleInput16 = arrayListOf( // 7036
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
"#.#.#.#.#.#.#.#.#",
"#.#.#.#...#...#.#",
"#.#.#.#.###.#.#.#",
"#...#.#.#.....#.#",
"#.#.#.#.#.#####.#",
"#.#...#.#.#.....#",
"#.#.#####.#.###.#",
"#.#.#.......#...#",
"#.#.###.#####.###",
"#.#.#...#.....#.#",
"#.#.#.#####.###.#",
"#.#.#.........#.#",
"#.#.#.#########.#",
"#S#.............#",
"#################",
)
