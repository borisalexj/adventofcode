package year2024

import kotlin.IllegalStateException

val sampleInput152 = arrayListOf(
    "#######",
"#...#.#",
"#.....#",
"#..OO@#",
"#..O..#",
"#.....#",
"#######",
)
val sampleDirections152 = arrayListOf("<vv<<^^<<^^")
//val sampleDirections152 = arrayListOf("<^<<v<<^^")

fun main() {
//    val input = sampleInput152.map {
//        it.split("").map {
//            if (it == "@") arrayListOf(it, ".")
//            else if (it == "O") arrayListOf("[", "]")
//            else arrayListOf(it, it)
//        }.flatten().takeIf { it.isNotEmpty() }!!.toMutableList()
//    }
//    val directions = sampleDirections152.joinToString("")
//    input.forEach { println(it.joinToString("")) }

    val input = sampleInput15big.map {
        it.split("").map {
            if (it == "@") arrayListOf(it, ".")
            else if (it == "O") arrayListOf("[", "]")
            else arrayListOf(it, it)
        }.flatten().takeIf { it.isNotEmpty() }!!.toMutableList()
    }
    val directions = sampleDirections15big.joinToString("")
    input.forEach { println(it.joinToString("")) }


    //    val input = sampleInput15.map { it.split("").toMutableList()}
//    val directions = sampleDirections15.joinToString("")

//    val input = sampleInput15big.map { it.split("").toMutableList()}
//    val input = sampleInput15big.map { it.split("").toMutableList()}

//    val input = realInput15.map { it.split("").toMutableList()}
//    val directions = realDirections15.joinToString("")

    input.forEach { println(it) }
    println("===========================")
//println()
    val startY = input.indexOfFirst { it.contains("@") }
    val startX = input.find { it.contains("@") }?.indexOf("@")!!
    var posY = startY
    var posX = startX
    directions.forEachIndexed { index, direction ->
        val moveTo = when (direction) {
            '<' -> {
                Pair(0, -1)
            }

            '>' -> {
                Pair(0, 1)
            }

            'v' -> {
                Pair(1, 0)
            }

            '^' -> {
                Pair(-1, 0)
            }

            else -> {
                throw IllegalStateException()
            }
        }
        val next = input[posY + moveTo.first][posX + moveTo.second]
        if (next == empty) {
            input[posY][posX] = empty
            input[posY + moveTo.first][posX + moveTo.second] = "@"
            input[posY][posX] = empty
            posY = posY + moveTo.first
            posX = posX + moveTo.second
        } else if (next == movableL || next == movableR) {
            val shouldMove = if (moveTo.second != 0)
                recursionPushHor(input, posY + moveTo.first, posX + moveTo.second, moveTo.first, moveTo.second)
            else if (next == movableR) {
                recursionPushVer(input, posY + moveTo.first, posX + moveTo.second, moveTo.first, moveTo.second, index)
//                recursionPushVer(input,posY + moveTo.first,posX + moveTo.second - 1,moveTo.first,moveTo.second, index)
            } else if (next == movableL) {
                recursionPushVer(input, posY + moveTo.first, posX + moveTo.second, moveTo.first, moveTo.second, index)
//                recursionPushVer(input,posY + moveTo.first,posX + moveTo.second + 1,moveTo.first,moveTo.second, index)
            } else {
                throw IllegalStateException()
            }

            if (shouldMove) {
                input[posY + moveTo.first][posX + moveTo.second] = "@"
                input[posY][posX] = empty
                posY = posY + moveTo.first
                posX = posX + moveTo.second
            }
        } else if (next == brick) {
            // do nothing
        }
        if (index < 5000000) {
            println("-$index- $direction -------------")
            input.forEach { println(it.joinToString("")) }
        }
    }

//     val input2 = arrayListOf(
//         "#######",
//         "#...O..",
//         "#......",
//     ).map { it.split("").toMutableList()}

    input.mapIndexed { indexY, strings ->
        strings.mapIndexed { indexX, letter ->
            if (letter == movableR) {
                100 * indexY + indexX - 1
            } else 0
        }
    }.flatten().sum().let {
        println("Result 1 - $it")
    }
}

val movableL = "["
val movableR = "]"

//val brick = "#"
//val empty = "."
//
//
fun recursionPushHor(input: List<MutableList<String>>, posY: Int, posX: Int, moveToY: Int, moveToX: Int): Boolean {
//    if (posY+moveToY == 1 || posX+moveToX == 1 || posY+moveToY == input.size || posX+moveToX == input[0].size) {
//        return false
//    }
    val next = input[posY + moveToY][posX + moveToX]
    if (next == brick) {
        return false
    }
    if (next == empty) {
        input[posY + moveToY][posX + moveToX] = input[posY][posX]
        input[posY][posX] = empty
        return true
    } else if (next == movableL || next == movableR) {
        if (recursionPushHor(input, posY + moveToY, posX + moveToX, moveToY, moveToX)) {
            input[posY + moveToY][posX + moveToX] = input[posY][posX]
            input[posY][posX] = empty
            return true
        }
    }
    return false
}

fun recursionPushVer(input: List<MutableList<String>>, posY: Int, posX: Int, moveToY: Int, moveToX: Int, index: Int): Boolean {
//    if (posY+moveToY == 1 || posX+moveToX == 1 || posY+moveToY == input.size || posX+moveToX == input[0].size) {
//        return false
//    }
//    if (index == 5) {
//        input.forEach { println(it.joinToString("")) }
//    }
    val next = input[posY + moveToY][posX + moveToX]
    if (next == brick) {
        return false
    }
//    if (next == empty) {
//        return true
//    }
    val displacement = if(moveToY == -1) 1 else -1
    val current = input[posY][posX]
    if (current == empty) return true
    if ((current == movableL && next == empty && input[posY + moveToY][posX + moveToX + 1] == empty)
        ||
        (current == movableR && next == empty && input[posY + moveToY][posX + moveToX - 1] == empty)
    ) {
        if (current == movableL) {
            input[posY + moveToY][posX + moveToX + 1] = movableR
            input[posY][posX + 1] = empty
        } else if (current == movableR) {
            input[posY + moveToY][posX + moveToX - 1] = movableL
            input[posY][posX - 1] = empty
        }
        input[posY + moveToY][posX + moveToX] = current
        input[posY][posX] = empty
        return true
    } else if (current == movableL) {
        if (recursionPushVer(input, posY + moveToY, posX + moveToX, moveToY, moveToX, index)
            && (recursionPushVer(input, posY + moveToY, posX + moveToX + 1, moveToY, moveToX, index) || input[posY + moveToY][posX + moveToX - 1] == empty)
        ) {
            input[posY + moveToY][posX + moveToX + 1] = movableR
            input[posY][posX + 1] = empty
            input[posY + moveToY][posX + moveToX] = current
            input[posY][posX] = empty
            return true
        }
    } else if (current == movableR) {
        if (recursionPushVer(input, posY + moveToY, posX + moveToX, moveToY, moveToX, index)
            && (recursionPushVer(input, posY + moveToY, posX + moveToX - 1, moveToY, moveToX, index) || input[posY + moveToY][posX + moveToX - 1] == empty)
        ) {
            input[posY + moveToY][posX + moveToX - 1] = movableL
            input[posY][posX - 1] = empty
            input[posY + moveToY][posX + moveToX] = current
            input[posY][posX] = empty
            return true
        }
    }
    return false
}

//
//val sampleInput15 = arrayListOf(
//"########",
//"#..O.O.#",
//"##@.O..#",
//"#...O..#",
//"#.#.O..#",
//"#...O..#",
//"#......#",
//"########",
//)
//val sampleInput15big = arrayListOf(
//    "##########",
//    "#..O..O.O#",
//    "#......O.#",
//    "#.OO..O.O#",
//    "#..O@..O.#",
//    "#O#..O...#",
//    "#O..O..O.#",
//    "#.OO.O.OO#",
//    "#....O...#",
//    "##########",
//)
//
//val sampleDirections15 = arrayListOf(
//"<^^>>>vv<v>>v<<")
//val sampleDirections15big = arrayListOf(
//    "<vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^",
//    "vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v",
//    "><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<",
//    "<<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^",
//    "^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><",
//    "^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^",
//    ">^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^",
//    "<><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>",
//    "^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>",
//    "v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^",
//    )
//
//    val realInput15 = arrayListOf(
//        "##################################################",
//        "#.....OO..O..O.O..#....OOO..O...OO.....O....O....#",
//        "#.O#...#OO.....O.O.O....#..OO..OO.##.#......O#.#.#",
//        "#.....O.............O..O....OO#...O..O.......O...#",
//        "#...O..#..........#....O..#...O.OOO....OO........#",
//        "#O.##..OO....#O..OO#..#.#...OO.O....O#..O.O.O..OO#",
//        "#......#..O..O#.OOOO.O.OO...O#.OOO...#...#....OOO#",
//        "#.#......O.OO......OO.O.O....#.....O.O.O.OO.##OO.#",
//        "#.....OO..OOO...O........O...OO.O....O.O.....O...#",
//        "#..O#...O#.....OO...OO.OO.OO..........OOO.#.#..O.#",
//        "#....O....#.O......O......O#.OO.O..#O...#......OO#",
//        "#......O.#O...OO.....O..........#......O.O.......#",
//        "#O..#O#O.OO..O.O.#...#.O...O.#OO.....O.OO#OOO..O.#",
//        "#...OO#...OO..O..#.OO.##....O.O....#OO.O.........#",
//        "#..#.........O....OOO..#O..OOO.O....O.O...O......#",
//        "#.OO......O..O#.......O.O...OO........#.....O..O.#",
//        "#OO...O..O..O.O.OOO.........OO.....O.O.......O..##",
//        "#O...OOO......O........O#...........O.....O.OO..O#",
//        "#..OO.....#..O..#.OOO...O..O#....O.OO.#...O......#",
//        "#....#.O##.O..O...OO.O.O#.O.O#.#O.#O.OO...O......#",
//        "#O....O....O..O..##O....O.....O.O..OO..OOO.OO....#",
//        "#O.......O..#O.......O..O...O....O...OO.OO.#.....#",
//        "##.OO.OO#.O#.O.OO..OOO.O...#..#...O...O..#...O...#",
//        "#.O...O..#..OO.O..O..#O.OO...OOO.OO..O....O.....##",
//        "#.O..OO..O.OO#...O...OOO@..O.#O.OO.OO.....OO..O.O#",
//        "#..O....OO..#O..O.O.O...O..OO....OO..OO.#.#.O....#",
//        "#.##...O.O...OOOO.O.O..O.O..#O#.O..O.OO#..O......#",
//        "#OOO#..#.#.O##.....OO.O.O..#O..O...O.....OO....OO#",
//        "#..O.O...#........O..O.#....OOOO.....#..#O...O..##",
//        "#...O....OO......O.OO..O#O.O...#........O.O.#..O.#",
//        "#.....OO...#OO.O.OO.O#.O.O.#..........#...O....O.#",
//        "#..OO..#O.O..OO.O.O.O...O.O..OO.OO..OO.....O...O.#",
//        "##O.....O.#.O........O...OO..O#..........#....#O##",
//        "#.#O.....O.....O.OO.OOO.O............O.O...O#.#O.#",
//        "#.O..#.O....#.........#..OO.OO.#..OO.O.O.O.....O.#",
//        "#....OO#....O.O...O...O..O#..O.....O....O..O...O.#",
//        "#O...OO.O..#..O........O..#O.O#..#O#...O...O..#.##",
//        "#............O..#..#.O...O.##OO.....O.O..#.OO#.O.#",
//        "#....O....#........#O..O.O...............O.#..#..#",
//        "#O....OO...OO...#...O.....O.#O#.............O..O##",
//        "#..OOO......OOOO#.OO...OO#.O..O........OO.O.....O#",
//        "##.OOOO#..O.O......O......O....O...OO.OO.OOO.....#",
//        "#.O.#.#.O.#......O..OOO#O#OO..O...#O.O#.O..#.O...#",
//        "#O.OO..#OO......#.#.O#..O.O....OO....#O...O......#",
//        "#.O#...O...OO.O..O#..OO...#.O.O...O#.##O#....OOOO#",
//        "#..#........O..O...OO.#.....O..O...O#.OO..O.....O#",
//        "#.#.#.O....O..O..O...O.O.O..#..O.O..#...O..O.....#",
//        "#O.O....OO..O..O..O....O..O.OO...O.O.....O.#....##",
//        "#.OO....OO.....OO.......O...O..O.OO#..OO...O.O.#O#",
//        "##################################################",
//    )
//
//    val realDirections15 = arrayListOf(
//        "v^^<<<<<^^>v^>><v^<^<v><^^v<^^<>>^^^<^^>v>>>v>v>>^^<^>>^>><^<<v^^<^v><v^><><v^^<<^>v^v^v>v<<vv<^^<vvv>>>v<>v>v<^>>v<>^><vv>^^vv<v<^^vv^v^<v<>>vvv>vv>>v<^v<v<><<^>^<vv^<^>vv>vv<><>vvvv>^v^^<v>vv<^<v>><vv><>><>>v>^v^^v><v>>v>^<vv^v>v<v^>>^><<<<><^>v><v<>>v>^^^><v>^>v<v^>>^^<vv^>^><^>v>><v^>^>><v<^>>>^^<<>^v<>>^>><v<<>^^><<><^>><<>v<^^v>v^<><^><<^v^^>^>vv^^<^^v<v^v><<v<>^vv<<^<>^v^v^<^^v<^^v^<<v>>v>vv<^<>v>v<^^><v<>>^>v^^^^><>>v>^<^>^^^v^>>>vv>^<vv>^<>^<v<v<vv>>v><>>^<v^^^v>>v<<v>^v<v^<>>v<^v<<vvv<^v>v^^><<^^<v^><^v<<>>v><v^<>>^v^^^^^><>><^^^v<^v><v<>>^vv^^<>v<<<<^vv^<<>^^<<>vv^vv<v^<^v<>><<v<v<<^v>>^>vvv>>>^vv<<<v<>v^>^>vv^>v<v<<><v^<^v<>v><<^<v<^<^^v^vvvv^>^v^>vv<v>^v><<vv^<v<<^v>>vv>v<><v^>^<^><^><><^<^<<>^v^^<<v^>^^><><<>v^><>vv<<v^<<vvv>>^^>><>v><^vv<v>>vv<^><v<v^^v>>><v^vv^vvv^>v>v^^<>^^>><^><^v>^>v<<v<^>vv>^<v^<^^vvvv><<v^<^^><v^>^^<^vv>^<v>>v><<^v<v<<>>^><<v^vv<>^v><>^<<>^<<><>v^<><<<^>><>^><v>>>>><^<^<vvvvvv>vv>>vv>^>><v><^<<>>vv^v<^>^^^^v<<v>>>v<<^<<<<v<^vv>^<>><v>^<^v>>v><<>>^v",
//        "<v<<<><vv^^<>^<>^<^v<><^v>>vv^v<^^<v<><^><^>^^^v^v>^><^<<vv>v^v^<^>^^^^<^><^v^vv<^v<v><>>>^^<v^v^<<^^^^^><v<>><>^<v^<<^v<<^>>vv<^<<><<^^^^v>><>^v<^v<>v^^^^<<vv<v<<<<v<<>^<^^<v<^^>^v^^>>vv><<>><<<^^v^v<^v>><^v^>^^^<>^><v^vv>v<v^v>>v^^v^>v>^v^^^<<v^<v>^vv>v>^^v^v>>^^<<v^vv^v^v<v<<<>>><><><<^^>vv^<<<>^v<>v<<<<<>^^^v><v<<^v<<^v^>><v<<>vv><<<v><v^<<vv<v>v^^>^^^<v^v>vv><v<^v^>v<>^^<v>^<<>>>><>>>>v^^v<>^>><<vvv<^>v<^>>vv>^<^^^^<<^>>v<>>^<><^^><><>>^>v<v<>^^>>>v^^<><>^v<<><<^><<v<v<v<><^^v<v<><^>><v>^v>>v<<>vv^>v<<^>>v><^^^>>>^>^>vv^^v<>^>^v<<<v^<<<^v>>v><^>v^>v^>v<<<vv>^>vvv<<v^vv><^vv>^>^^^>>><>v>>>^<>v^v><vv<><^>^>>>^v^vv<<>^>^^^<<><<^^vvv^v>v^><>^^^<<>>>>^vvv><>><vvv<><>^<<^<<>>^^<^^^^>^v<<<^>v>vv^^v^^>^v>^v^vv<^^<>^^^<><^^vv>v<<>^<^vv>>>>>^^<>>vvv^v<<^vv>>>^v<<<vv>vv<^^>^v><>>><>v^v>^>^><vv^><>>><>v>>vvv<><vvv<v^^^<v^>^vv>^v<<><vvvvv^>v>v^^v>><v><>>>v^><^v<>><<>><^><v^vv<^vv<^>v<<<<^<>vv<v><vvvv<>v^v^v^>^><v^>>><<<^>>vv^v>vv^^<^<^v<vv><v>>v>^^^^^v^^><<vv^vv^^>v^vv^<<<><^<<<<^vv>><^>>>><<v",
//        "vvv^^<><<>><v<>v^>vv>>vv><><^<><v><<^>>vvv<><>>^<<>^v>^v>>><v<><v^vvv<^>>>v>v<vv^^^v<^<^v^<><v<<^<^^^><^v<>vvv>>><<^v<v>v<vv<^>^><>v<<v<<^<>vvv>v<^>v^v^vv>^v<<><^>vvv<v<<<^^>^v>>vv>>^v><^^<^^><<v^^>>>>v^><<v><vvv^>v^<v<<v>>><<^<v<v<<<v^^<><v^<<^<vv^^^^vvv^>v^<vv<>v<<<v^<>v<v<^<<^>><>^^^v^>v^v^v>v^<vv<v^<^>v>v<>>>^>><^<<<^v<^v^v^^^^^><v<<>v>><><>^<>>v<v^^v<^><<>><^^v><<v^<v>^v>v>^^><^^^>>^<^<<<<^^>>^^vv^^<vvv^v<^<vv^<^v<>><v^^v>^^^^v<<<v><<<v>v<^^^<^^>v<^>>v^v>^^<v<v>^v<v<<<^v>^^<<>>>>>^<^vv<<>>^vv^>^>>^>v>>>>^>v>^v<<v<^<>^<vv<v<>v^v>>^v^>^<vv<^v^^v^<<<><<<>vv^v^<<^^^v^^<<<>>>vv>v^<<^<><^^>>v>^v<v<<>>^^^v>^>^<<<vv<<^vvv>v<<v^v^><v><^v><<v<vvv<^<v<<vvv>>v^^^<v^<^v>><<^><<<>v>>><<<^<^^^^v<<^^>^><^^vv>v>>><vv><vv^>vvv>^<>>^>^^<vv<^<<v<v^v<v<^v<>><^><^<vvv<>>>^^<>v^vv^>v^>vvv^>^<^><<<<^v>v^v^<<<>>^<>^<v>^v>><<>><^^v<^^^v<>^^<vv<>v><^v^>v^v<v>>>^>v<^v^^v>^v<v^vv^v<<vv>v>>>>^vvv^v<<v>>^vv>v^>><^v^v<><^<v<^^<v^>vvvv>v<>>vvvvv>v<<>v<^<^^>^^v^v<^^^^v<>^^v><^><v>v^<^v<^<v^^^<>vv>>>>>^vv><^^<v<vv>",
//        "<^^><<>>v><<<vv^>vvv^^^>>v^>>^><v^v^>>>^<^<>v>><<<v^<>v^<vv>^<v>^>v^^<^<^v^v><><v<v^<><>^v^><^^>><>v>><v^^^^>v^^v>^>^>^>^^^>v^^<>v^>>v<<^^^^>>^><<v^><v^v<^>^v^<^<v<<<vv<^vv^^<<^v>>v>>^^^>v^v<>v^<^v><^>^<>^v<^>vv^>^v>^^<^>v>^^<>^v^vv><v<v>><^vv><^^vv><><v>><^v>^v^vv>>><v<v><v>v^v<^v<>^^v<>^^>^^^^^>>><^<>>v>vvvv<^<<^^>^><^^<<>v<<v<>v^<^<>>v>^v>>^v>>v>^<^>v>v<vv>^v<v^>v>^vv^v^v>v<<v>>v><>>>^<v><^^^^^^>^><<<^<^><v<>v>>v>v<><<<<>v<v<<^v^^^v><<<>^>^v^><<<^>><^>v^^>><^<^^v<v>v^>v^>vv^v><^<^><>^^v^<<^v>v><><>vv^^<^v<>>^^><>^^^v<v><><vv<v^>>^>v<v^>v^^^><v^v^<v><<><<v^v<>><v^<><^<vv>^v^>>v^<v^vv^v^vv^vv<<>^vvvv^^vv^^<^v>v^v<<<v>>^>v<><vv>>v<v<^vvvv<^v><<v^><>>^^<><><^<v>>^v^v^<<v<>^>v<>>v^>v>v>^v^^><>^<><>^^^><><<^<>^<>^>v<><>><v><v^>v^^vv^><^><<>v>v<^vv<>^v<v>>^^^<v<^v^^^^<<><vv<<v^>vv^><<^^^<<>^>v^>v^vv>>v<<>vv<v^>v^>vvvv^>^<vvvv^<>^<<vv<<<<v<v<>>^<<^<>v>>v><^^<v>>><<v>vv^v^^^^^^^^v>^^^^v<<^v^<^<<<v>><>>v<>^^^^>^><v^<>^v<v<>>v^v^^>vvv^^v><^v>v>v>>^>>v><>>v<^>>><^<<>^>vv<<><v>^^^<<v^<>^^<v^^<v<",
//        "^v^v>v<<^v><v<<<^<v<vv>^><^^>>vv<^<>>>>^^v^>v^^v^^<<<<v<<^vv<<><^<<^<>v><^v<>>>^^v><>>v^v<v>^>v<^^>>^>v^<^<<<v^v^^>^^<<^^^v^<v<^vv>vv>vvv^>^v>v<^v>^vv<><v<><v<>>>>><^<>^>>^vvv>v^>v<<<<^v>>v>^vvv<>^^^v>^v^>>^^<^^^^><^^^v<<><>^v>><v<<v^^<><v^>v<v><><<<v<vv<v>^v^^>vv>^^<^<><<v^^v^><^><^<>^<>^>^^<vvvvv<<v>>>vv>>v>vvv><^<><^<<<>><>vv>>^v^^^^<>v><^^^^>>v^vv^<<>v>v<^<<^v<><^^^^<<^<<^vv><<v>v^v>^<>vv^vv^v^><>v>>>^^^>>vv>^>v<v>v<>v>v><v>^^v^vv><vv^<>^>^<vv<<<^<^<<><>^^><v<>v><vv>v>v<<>^>><<>v<^<vv><><^^vv<vv<^>>v^<>v<^^<^^v^^<<v>v>^><v<><v<^^^>><v>v^vv^>vv^>^^>^<<<<>>><>v<><<v^<><v^^<vv>v^vv<<<^v>>v>v>^>^^><>vv<^vv>>v><<vvvv>vv^>>v><<<^<^^<^<v<v>>>^v^v^v>^>^>>>>^^vv>^>v^^>>><>^^^v<^>v<>^<>v<^<v<v>>><>vv^<<<^^^><^v^vv<^>v<vv>v^>>><v^>>>^<<<v>^^^>^<v>^<<<^v>^^^>v>vv>vv<<^<^><>>v><><^v^>^v><^>^vvvv^<><>^>^vv><>v>>v<>^>>>^v^vv^>^>^<^^<v>v>^^>vv>>vv^<v<<^<v^^vv<^>^^<><<v>^^>v><>v^v^<vv>^v<^^><>^<>>v^vvvvv^^<<<>v>v^^^<v<^<><v>vv^v^<<^<<^v<<^v^v><>^><^<^v>vv<^^v<v<^><v<^v><^vvvvvv<^<^^v^^v>v^>>v>>^<v>",
//        "v>^^<>v>^v^><^><>vv^<v^^^^><<<v<>^<<^^vvv<><^v><>>v><>><v<vv^><><v>v^v^^><><>^^v<<vv<^<v^^<^<v<^<>^^^vv>v^>vvv>^<<^>v<>^v<<v^<><v<v^v><^<v^^^v<>>^^vv>^^v>^vv><v^<v<>>><>v<v<<v>v>>>^><>>>>^^>>^v<vv>>v^><><^^^^vvv<^>vvv^>v<>^<<v<<>^<>v<<v<v<^<^^<^<>v<<<^><vv<>v^v^>v>><>v>v<<<<v^v^<><^><><vv><^^^><v><v>>v<<<^v>vv^^^<v<><<>v<v^<<<<^>>>>^^^v^<><v<<^<^^<^<v>v>v<>^v<<v^v<v<v^>>>>vv^>><v<^v<v<^>v>^>v^v<vv^<v^><><<^>^><<<>><<v><>^<^<<><v><v<>vvv<<<vvv<><v^v^^v^vv<^<<<vv<><>vvv<<vv^^v^>>^<v>>v<<vvv^<>v>^<^<<^>>>^<<><>^^^<>>v>v>>^vv<v><^>>vv><><v>>v>^vv>^v^<v>v^>>v^^^>v^^>v>>v^<>vvvv>v<<<^^v>>vvv<>v>^vv^^<<vv>^<<>vv<^vv<^^<>v^>>>^v^vv<^<^^<>>^^<^<>vvvv^<>^^^<^^vvv^v<<^^>v^>v^>v><v>^^v>^vv>v^vv>>>v>>><<v>^v<v>^v<<<v>^^<v>>>>>>>>^<vv^<<v<vv^v^vv>><^^<>v^<>^vv>><>^<^^>><<^v<>^>^v>v>v^<^v^>><<<>>v>><><v><v>^>^^^><<v>v^<^^>^>^^<<^vv<>v^^>^<^><>^^<v>><^><v<v>^vv<>^v>^^^^<v>><^vv<><>v>v>vv^><>>^v<v^v>v^>^<><vv^vv>v^^^<^>>v^>^<v>^^v><v>^>vv<<<v>^<v^^^<^>v<v>>^^v><>vv>^^><vv^^^>^v^^<<v>>>><vv>v^^<v>vv<><>",
//        ">^><<^^v<<>v^^v<^<^<<vv>^<<v<>>^^^<>^<<vv<^v>^^v<>v^^<^<^v<vv^<>><>v>^<^<v>v^vvvv^^>^v^>><<><v^^>^<<^>^<v><v^><<<vv>vvv>v^>v>^v^<v^><v>^>vv<^^v>^>^v>>vv<<>>><v>>><>^><>vv^v>v><v<<<v<vv<^v>>v>>><<>^>>>^^^><v>^vvv>>v>^^v^<v^^<>vv^v^^<^^>^<v>^>vv^^^v^<<^>>><><<^>>>vv><><^>^v>><>v<vvv^v<vv>^v>v>v^^><<^^>v>vv<<^>v<>v><^<v^>v<vvv>v>v<v^^v<<<v^v^>>><<<>>>v^><>>v>^<^v^>>v>^<v<v<vv<^^^v<><^>>vv>>v<v>>^<^v<>vv>^^vv<>><v<v<^^<>^v<<<^>^v^^v<<v^v><^^^^<^<v>^v^^v<<<><v>^^^v^>^<vv^^v>v<<<^>v>>^^^>v<>><<<><<<^<>v<v<><^^>^v^<<v<^vvv>^>v>v^v<>>^<v<<v<v<^<v>v>^<^<>^^<>>vv^^>v^<v<v>^^<vv<^^^^>^>^<>^^v<^^v<^><vv>>>v^^vv<>>><v>v^v^^><<<v<^v>vv^<^^<<>^><<^>>^^>v^<^^v^v>>^^v<^v<v<>><<>^>^<>>>>><^v^<>^<>>^v>^<><>v<vv^v>v^<<v^v^v^^>><>>^^<v>^v^vv<^vvvvv><>v>><<<><^vvv<<^><<v<v<<><>>><vv>v><>v^>>^v^<>>^^^>>v<^^^>vv>v>^<^>^^>^<<<vv><<v^<<<^v^<>>^<^>vvvvv>^^<<v>>vv^v><^<^v>>^<^><<><<>v><v^>><^^v^^<<<>vv<>^^^v^v<v<<>^^>^^>^>^>v>>^<<<<v><>v^^^<v<><^^^<>v^^<v^v^>vv^>><^v<v^^v^>>^v><^<^^>v<v<>^v>>^<v<^^>>v^<v^><^>^>^v",
//        "vv^^^v>^vv^v^<<^<v<<^><^><^v>>v^<^>vvv>v<>v^<^><^>v^>^<<<^^>>v<^>^>>^>vv<><>^<<^<<^vv<>^>v^^^^v^v>>^><<v^^^v^>>^^^vv>v>^>v>^<<<><v^>^<^^v><v^^vv>vv<v<<^^<<>>^v^>><^>vvv^^<^v>^<<>v<^v^>>>^><<^>^^vv^>vv>^v^<v^<>^<vvv>^v^v>v>^<v>><>><<^>>^<^<vvv>>>>v><<^>vv<v>^vv^<<>^vv<<>>>>vv<<^v^>^>v>vvv^>v<^v>^>^v^<<^<<<>^<v^v>><^>v>>>^>>^v^>v<<v>^>^<^vv^v<<>v><>^v^><<><vv><><vv<^v>^^v^^<>><>^^v<^v>>>vv<><<vv><<^^^<vv>>>^^>^>v^>>^><^<vv>v<^<>v^><<<>vv><<<v>v><>v><vvv>vv>v>v<<<<v>v<^<>v^<^<^>^^^^v>v><^^vv^^^^>>>>>>v^><<^<^v>>v^<v>v>><><v><><>>vv^>>vv<v<^vv<>^^<v>><>^v<>^^v<><>>^^^<v>>v>>v<>>^><<<^v^^>>v<v<^v><^v<^<v^<>vv>>^^>>^v<^><^vvvvv<v<^<><<>v>>v>vvv>>>^v>>>v^v^^v^<vv^^<^><<>^<<>>>vvv^v^<v>>><vvv<v<><v>^^^v<^><v><^^>^v>^^>^<vv<^v<>^v^^^>^^>v>^^<^^^<^^>>v><<<<<>>>v>>>^vv^^>><vv^^>vvvv^vv>^^vv<<<<<vv<v^v>vv^>>v<^<<><v<^vv>>^>vv^vv^<^^^^<^<>>^<<^<><^^<><v<^>vvv^>^>>>>>^v><>^<>^v^<><>^>v><^^<<<v<^^v<^><^<>vv^<>^v<>vv<><>^<^>^^^>><^v^>^<v>><vv^vv>v>>>v^<^<^>v<^v<^v<^>v^^^v><>^>^vv>>>>^<vv>^><<<<<^>>^vv",
//        "v>^vv<>>>>^^<>>^><^<vv>>v<>v<^<>>^>^<<><v<v><v<<>v<^v<<vv^>>>v>^>><v<v<><>v<^>>vv<v><<^<^v^v<^v>>><^^>v<>>><v><v^^>vvvv^<v><<v^<<^^^<v>^<<<<^^v^<^^><^<><>>^^>vv^<v<>^><^v>^<v^><^<v<^v^^^<>>v>v<<>^^>>>^<<^v^>^^<v>v^>^^vvv<^v<><v^<^><^vvv>>v<v<v><v>^<v<><<^v>v>>v>>^vv^v^v^<v^><<^vv<>v^>vv><<<v^^<v^v<>^>v<^v>^v>><vv<<^vv>vvvv^v^^^vv^v>>^^v><^<v^^>v^>^<>^<^v<^vv<v<>v^^vv^^v>^vv>>><^v>^<>>v<^^v<>vv>^>v<^^vv<^<v^^<<^v>>^^<v^^>v>><v^v^^<<^<>v^<>><v>v^^>>vv>^^vvv<v^^>^v><<vv^>>^v>>^^<vv>>^^<v^<>^<^><^>>^^^><v^v<><^^>><vv><>^<^^^>^<vvv<><<^><^vvv<^<^>^<>vv^<<>v^^<vv^<v<v>v^>^<><<v><v<>^vv^>>^<^^>v<<v>>>v^>^>>v<v>v>v<<^<v^^<<<<^>v<>>v<><v<v<v<<<>^><v^^>^<vvvvv>>vv<v<^^<v<^v^^^><<<vv<vv>>v<<^<v<<<<>^vv<<><vv<v>^<<><<>>v<<v>>>^v^>>><>v>v<^<^<vv<<<v><^>^^^<>^^v<vv>v>>>^v>>^>>^><v>><^^<<>>v<^^>><^v>><^>vv^^^<vv^>^^><^<vv><<<><<><>>>v>^v^<v^<^>>^>vv>v<^<>vv><><<^>v>v^vv<>^>vvvv<>^<<v<^v<^>^>>>^v><^<^^^^^>v>v<vv^<>^>vv<<<<<v^^<^<v<^v^v^v>v^><<<^v<v<<^<v^^>v>^>^v^<v>><^v^>v<v^><^>>>^><<^^v^^^v>>><^vv>>",
//        ">>>vvv<^vv>^<>v^vv^><>v^>><>^^^<>>v<^>^v>vv<>^vv^vv^vv>v<^^v^v^<v>>><^>^^^<<<<<^<vv>^<^vv><v>v<>^vv>>^<vv>><><^^<^><^v<vvv<><^<^<^^^>^^>^^^>v>>><^>^<vv<^>><^>>^>^<<^<>v^>^>>>v>^^<vvv><^^>vv^><<<v>><^<v^^>^<v><v^><<^v^^>^<><<<^><>v>^<^>>^v<^^^<>vv>^<^<^^v^v>>vv<>v<v<<^^^vvv><v>vvv^>><v<<v^<<<v^v><>^vv<^><>^v><>v><>v><>^v>vv>><^v^^^<<>v<vv>v<><<^>>v<^^^v^v^vv>>><><vvvv<^<>>vvvv>v^>^vv^v^<v^^>v<v>>v>v><><<>^^^<><>^^<<>^v<v<^^<>^v^^<>vv<v^^^v>>v><<^v>^<^>>^v^>^<<<<v<^<^^vv>v^v^>^<<<<v>v<vv<v<><<^v<<^>><^v<v>><>^<<<^<^^>><<>^^^^^>v<><<<<v^v<v>>^^>>vv^<v><^>>vv^<^<^><v^<vv><vv^^>^^>^^<v<>vv<^v<v>>^>^vv^^v<<>><v<v<<v^<^v^^<v><<>><vv<^v^v<^<v<<^vv<><<<>v^^<v<><<>>vv<^><<^^vv<<<vvv>vvvv>v>v^v^>vvv^^v>^>^^<><v>^<v^>^><^v^<<<>v^^>^^<^^<<v><^vvv>>^^v>^<v<^<^^<v<vvv^vvvv>>^v<>>^<v<>^^^^<<v<v^>^><<<<vv<^v^v<vv^<^v<<>><v^^^v>^v<vv<>>v^><<<><v>^^^<>>^<vvv^^>v^^<^<>v^^<>v<>><>^>vv<<vv<v<vv^v>v<<<>^><vv<v<>>><>>v<><^><>>v^^>><>vv>^<<><^^v<<^^><>>^^>v>v<><<<>^>>^^><<vvv>><^^^<^<<>vv>>v<><^>v>vvv^<><>>v^^",
//        "v^^<<^<<<v^v>>vvv^<<>v^^><^^^v><vvvv^^<>^>>vv<<<^vv<>>^v>^<v<>>^^>^^<<><^v<>v>>>^v<>^vv^^<<<<v^>^>>^^v>><<<^v^^^v><>vv^^><>v>v^<^v<v^v>><^v<><^<^>^<^vvv^<^^^<<<^<>>><<^>^vv^<<<><<^>^<vvv^>v<vvv<<<^v>>^<<v<^<<<><v^^v>>vv^><^>^v>^><^<>^<<^<v^<^v^^v><><<vv^<^><><>^<^<^v>vv^>>vv<>><v^<<v^^<>^v><^^^>^^>>>v><><vv^v>^v>v<>^vvv<^^^^>><^<<v<<^>^<>><vv^v^>^v^v<v^>v>v>>v^^v<>v>v<^<^<v>>^>v<v^v>><<><^<^v>^<^v^v^<v>><<<<vv<><><><>^vv^^^><v<^^><<^^>>v^v^^vv<>v<^vvv^v>^>^<<<^v^^vvv<^^v>^v<^>>>>>>^v^><><<^>^<>vvv^v<<<vv^v>^^^vv^<vvv>^vvv>>^v>^vv>v>v<^v>vvv^^>^<>^>>vvv<>>>^^v^<>^vv^^vv^v>v<^v^>vv<^<<<v^v<^vv<<vv>v>^>v^v<^^v^^^^>v<^v>><^><<^^>^^<^^^<>v>>vv^^^v>v>v^>>^>v>v>^^v<<<<<vv<>^<v><<>^<v>>v<v><vv<<^>^<>>^<v<^>>v>>v<v>vvv^^vv>><^<vvvvvv^>^<^^>^<v>>>>><^>><>>^^>v><>>^>vv>vvv<<<><<v^<^^<>^>^^>^<vv>^^^^>vv>v>^^>v>>v^vv^^^v^>>>^^<<v>v<<>v>^vv>^v><v<^v^^<^<v><>^>>>v^>^v><<v>>^<<><><^>vv>>^^v>^>>^v<><^^v<^v^v>vv<^<><<v>>^><<^<<<<^^v<<<^^^^>^v><^vv>>^>>v^v>v<v<v<<^<v>><<>^<v<^^vv^^^^v^v>><^vvv^>^^v^<^<<<",
//        "<<>^<v>>><^v>v<v>^v^^<<^vv>>>><v>^<^<^v^>^^vv<v<vvv^vvv^^^<v>>>vv<v<<^v^><>><v^<v^v^^^vv^^<<<v>v<^>>v^>^vvv>>^^^v^vvv>>vv^v><v<>>^^vv<<<v<^<^v<v>>v^^>vv^><^<v>^<>^>v^v>v>v<<<<v^v<>^v<>>>v<>^^<><^^<^<<vv><>v^^>>>v><v<^vv<^<<v>v^><<^^vv<>^vvvv>^>v><>^vvv<v^><<^>^><<<vv<v^^<>>^<v>^<v^v^^<<<vv<^>>^^><>><v<><><>v<^^<^vv^>v<^<<vvvv^^vv<>^^><v><<<<^>v<<^<<v><<><v<<<<vv<^<<<>^v^v>>><vv^<<<v>><<^<^^><v<>^>vvv>>^><>vvv><<>^vv><>^<>>>v^>vv>vv^^^v<^v><<vvv<^v^v>v<vv>^<v^>><v^^<<>^><<v^v>^><v^vv>^<vv>^>^vvv^>><v^<^vv^<>>v>^>>>>^v<>vv^^><^v<<><^>v^v^v>>v^<v><v^>^v>>>>v><<<<<vv^<^>v^<^<^v<>>v^<<v^^<^><v^vv<>^vv<^>^v<^^v^v^v<v<^><<^><vvv^v^<><^><^v>vv^^<v^>vv^<>vvvv^vv>>^<>v^<>><><v<v<^v>^>>^^vv<>^<<v^^<v<v^<^>^>>^<v<>v^^^<<v^vvv><<<><^vv<^>vvv^<<^v>^^>^^<>v^>>^^<v^<>v>^^v^^>v>^^>^vv^<v>>^^vv><>v><<^><^<^<^v<>^v<>v^^<^>>^<>^<vv>>^vv^<<^v>>><v^^><v><>>v<v^>>>v^v<v>>v^vv^v>v>^<>v^>^>v><>^vv<^^^^<v^><^<^^><<<<<<vv^v<>^>v<>^v^>v<<<v<<v^^v>v^<v<>>vvvv><<^^<v<^>>v^v^<^v^<<>vv>^>v>^<>v^^v<>>vv^<<^v<<>v<^<<vv",
//        ">^>^>><<<>^<^^^>v>v<v<<^^^^^<^<v<^>>^>>^>vv<v<v>^<>vv^<>^<<>^^v<vv>^^<vv<>v><v^<><v<<^>v>v>^^<<>v>v><v^>v>vv^v^<v^v>^^<<v^vv>>><>^>><v<^<>><><^^>v>>^>vv<^^<<^<<>><^^>v<<>^<^><<vv<vvv><^v><<v^<>vv^v>^>>>vvv><^<<<<<^^>^^<>>^>v>v>>^v>v^<v^v^^v^v^vv>^^^<v^^v^>^v^>^^v>^v>^v>^<^^v>^>^<>v<vv^v>^<vv>^^vv<<^>>>>vvv<<v^>^^<>v^^^v<^><v^^<vv<^v<><>^^^<v>><>^<^v^>v^v>^<v<<<v^><v^<v<^v<v><v<^>><^>>v^v^<^v<v>vv>vv<>>^^vvvv^v^^v<^>>v<>v>^<^>vv<<>^^^v>v<v^v<<<><v>v>vvv>v<<<>^<>><<>^^v>>v<v<><^v>vv>^><<<>^>><v^v>^vv<vv^^>v<^^<<<>v^>v<><^>v>>>^^^v<><<v>>>>>><^v<>><^>^><^^v<^v><>>><^^>^<<<v<<<<>^v^^<v>^>vvv<v><v<^vvv>^^^^^v^><>v^^^<<^^v><v<^^><v^>>v<<<v>>^<<^<v<><<v>>v^<^<>v<^><<v^><<<>>>vvv^^><v^<<^^<vv^^>^><<^>^<v><>v>>><vvv<>v^^^<<v^v<>>><^v<^v<><<^v><^vv^^^^^>vvv<v>v^<>^v^>^^<vv>>>>>>^<^<^<v><v^>^<v>>><<>vv^v>v><><<><<<^<v<>><<v<^v<>v<>^<>^v^^v^>vvv>^<v>^^<<><v<v><^^^><v<^>>v><><^<<^^>>v<^v<v>v>>>v<<v^<<<>^^<v^<>^v<^v^<vv<><v>v><<<>^^^>^^^>^^^<v>^>v^<^^^<^v^<^>^<>v<>>^^>v^<v^v>><^^<v>^v>>^^v<v>>^<<>><",
//        "^<^v><v><^^<><^<^>v^^>>v^^^<^<v^<<v>vv<v^>>vv<><>^v<><^vv<vvv>^>vv>^<>><^<<<^>^^v<<<^<v>v<<>>^>^<<v>>>>>v<>vvv<vv>>^><<v<vv^^>v<<vv>^^<^>v^>v>^^<>><v^^^^<^>v^<^><><^v^^vv^<v><<^>>v>v^vv>><<v<v<v^^<v<><^><^<><>^v>><<>>^^^^^><>v^^<<^^>v<<<<^v<<<>^<v<^v^^<<^^^<^v<>><>>v<v^^v<^v^^>v>^>v^v<^^^v^>><>^>vv^<^>^v>^>v^>>v>v>>^^^>>^>v^<v^^^^>^v^v<<>>v><v^<vv^v<v^<>v>v^>^^<>><<v>>>>><<^vv>^<>^v<v^^<>>v<^>v>><v^>^<><vv>vv>v<><^><v^^v^>v<^<^>>v^>v<<^v^><v^^><^^>><<<>^<<<>v^>^^^^>>^><<^^v^><^^<^>^<<^^>v>>>v^<><^<<<>^<<^^^<v<v<<^^vv>v^><^>^>^^v^>>^>><<>^^>v>>^><v><vvv^<vv^>^<><<><><>^^<<>^^<>^>v><>>v<<>><>^^^<<<v<^>>v^><^^^^^^v^^^^>>v<<<><>^^vvvv<v<>vvv>^>v^^^>vv>>><^>^^<<v^<>>><^^<^^v><>>^<^>>v<v>^^vv^^v<<v^v>^<>v^<^>><^<v>v^<^<>><^^<^^^>^^<^><><^>v><v^vv^<^>>>^>v^^^<><^v><>^<>^v^><^>^^>>>^<vv<<^><>><<^<v>v>vv<<vv^^v>>^^^v^^>^>^^<<<^<><vv>v<v<><<><<^<v^>>^^>>^^vvv<v^<><vv>^<^><v^vvv<<<<v><>^>^<><v^v<^^vv<<>>^^>><<<><^>><<>^>>^<v<<vvv><<v^^v^^><<<vv^v^>^<v<><><>v>^^v>>v^<v>^<<^<vv<v<>>vv<<v^^><v<v>^^v",
//        ">>vvv<v<^v<<v><>^vvv^>^^v<<v<v>v><>^<v<<>v<<<v^^^<^<v^>^<><v>^v>^v>vvv<v^><v<^<^vv^<^^<<<^^<><<v^<v<><>v>v><>^^v><>><^><^^^><<>>vv^v>vv><>v^<<^<>^v^^>vvv<^>v>^v>^v><vv^<^v>v^v<^v<<^><<<>^vvv>>^v<<v>^v>vv<^^>>^vvv><v<><vvv>^>v<^^^^^vv>^<<^<<>^<<<>v>v^<^v>^>v^<<<>><^><>vv^vv^<<vv>^v^>v<<<v>><<^>vvvv^<^>><vvv<^v<v<^^v^v^v><^v>v>v<><><<>vv^^v>><v<><>v<^v>^<>vvv>^>v><<v<^>v>v>>^><^^><<<vv<v^v^vv>><vv<<^^v^vvv<^v>v><><>>v>><><<^>><v<^^^v^>vv><>^^<<^>v>><v^>^><><><^v^v^<<vv><^<vv^vv^^<^^^v>>v^^^>vvvv^<>vvv<<^<^v^^<^v>vv>>^>v^><<<>>^vv<vv<>><v>>v^<v<>v>^vv<>>vv>>v^<^<v<<vvv^^^v<vvv>><<>>^v<<>^v<>v>>><<v<<v<<<>vv>^v<<>>v<<vvv<v>v^>>v<^<>^^v><^^>^vvvv<^^v<^><^^<>v^>>vv>^^^>v>^^>v^>v<<>^<v^>>v^vv<vvv>v<vv>vv<>v><<^^>vvv>>>>v^v>>><^^^v<v^><<^><>><<><^><>><^>^^vv^^>v^><v>v>>vv^>^>^<vv^<v>^<<<^^<>v<<v^><<>v<v^><v^><vv^^>vv^v^^><v^v^<<>v<>^vv^v<<v<>v^v>v^v^>vv><><>>>vvv>v>><>vv<^^<<>v>v>v^v<^<v<>>^v<>v><>><v<<^>^><<<^v>v^vv<vvv<v>^>>>^<vvvv><vv^<^vv<<^>^^v<^>^>v<<^vv<<<>^v>v<><>>^^<<v^><^^>><>v>^^v<<",
//        "<<><^v^<vvv>^>><^^><>^^^<>>^>^>><v^vv^>^^v<>vv>><>v>v>v<^>><>v<v<v^^<<vv>^>v>>vv>>>v^<>^>v^<<>^^^vv<<^vvv>^v<v^<^^vv><v<<<^^<v^>^v<^>^v^<>v<^<^<vvv<v<^^>^v>>>v^^^^>vvv^>>>^v^<>>^<<><vvv<>v>>^^>^<^>>v^v^>>vv^^vv<^<^v<<<v<^^^><<<^^<>v<<>>>^<^>v>^<>v>^^v^v^>>^<>>^<vv<<^<<<<^^vv^v>>^vv>v^^<<<vv^><v^^><<^<>^v<^^>v>><vv^^v>>v^><^v^<^<<v>^><>v^vv^<<<^v^<^<^vvvv^^vv><^^v>>v^vv<<>>>vv><^>^v>>^v<<>>><>v>^>><v^><^><v^v<>>>>v><v^v>v^>vvvv^^^<>^>vv^vv<<v><>>>v<v<v<<>v>^v<>v>^v<^><>>v^vvv<>v<v^>vv<vv^^>><<<<^v^^v^v<>^<^<^<v<^v^><^>v<^>^<^<<<^^>v<^<^v<^v^<v>^<>v<v>^v<^v^>>^><v^^v^v><vv>v<<v<vv^^^<<<>v>^<<<v>v<v<<vv^v^vv>>><>v>v^^<><v><>^>^^^<>^^>^^>>><^<v>^>^>^^^<>v<vv<^^v^^^<^<<^>>>^^^><><v^v^<<v^<><^<v^^v>>^v^<^^>^>>v^><v<^v^>^>>^vv^<v^>^<>^>^v>><>>vvvvv<^<<v^<>v<><><<>^v>>v>>>>^>^<^vv>><<><<><<v<^^<<v^>^<<><v<<<^><^v<vv>^v>^<<v^vv<vv><<v<^vv^v<<^<>^^^v>>v<^^>v<^><<^v>>>><v<<>><>v^>v<v^^v^<><^<^><v>^<>^^>^^^<^>^v^^v>v<>><<v<<v^^v<><^^vvv<<<v<><><v<v>v^^<^v<vv>^<><v<v>^>^vv<>v<<^<>^<v<vv<<<^<<><v<^v",
//        "v^><v>>v><v>^>v><<>v^^>v><^^><^>>^vv><vvv^>v^<^^<>^<<>>>^^>^>><><<>vv<<^<<>v><>>v>^v>>^>>v<v^v<v^^v><<><>v<v>^<^<v^v^>>v^<vv^<^<vv^^><^^^v>vv>>^vvvv^^<<vvv^>^^>vv>^>^^^^^^^^v<v<>>^<v<><^>^vv>vv<<<<v>vvv^<v^v<v>>><<v<<^>>^v>vvv<v>>v<^^v>>v>v>vvv>v<><<<<<<^<^v<^>><><<<>v^>>v<<<^<^><v<<>>>v>v<>^^>>v>^><v<><<<v><>^vv<^^>>^vv^>^v^^^vvv^<<^<^<<v<^v<v<v><>>>^<v>^>>v^vv<>><>v<>><<<<>><v^>v^<^<>v^<v^^^>>^>^v<^^<<>^<^vv>v<<v<v><^^^v>vv<>><>v<v<v^<<v^<vv<<v^^<^vvvv^<>^^<^^<>v<v>^>>^v><<v<^v<v^v>^^>^v^<<<^>vv>>^<v^<<<<^<^^>v^<^><>^v^<><^v>><><<^v<^>v^v<><v^^^<v>v>><v<<^^vvv^><^>>>^<^^^v^><^>>vvv^>^<>v<^^<<<<^^^<<<<^>v<>v<<v^vv<^>>vv<v^<^^v<vvv^vv<v<^vv<v<<v^vv>>v>v<>>>>v^^<^^v><v<>v>vv^v>vv^<v<v<v^v<<>>v^v><^v<^vv^^<>^v^v<v^<<v^<>^^v>><>^v>^<>>v>v>v>^^v>v^>>v^v>^v^>vv^<<^^><^^>>^^vvvv>^v>>^^<<^v><^<^^><>vvv>>^^>^<<^^>vv>><<^<<^v>^>^>^<><>>vvvv^>v<<>>v^v>v^v^<<<<v<<^^>^<v<^^v<>v<^<<^^v^>^<^>v^^^^v^<v^>v^>>>vv^vv<<v<<^v^v^vv^<^<>^<vv^^><^<<<^>^<^^^v<vv<v^^>>v^>^>^<<^>v<>^^^>v^^vv<v<v<vv^>>vv>^^>>>v>",
//        "v<<v>^<>>v>v<><^^v^^<^<>><<><<v<^^>><>^>v<>v^v<v>><^><>^^>v<<^><><v>v<^^>^>^>>vv<<<^^><v<v^<v^><v>v>^^^<^<v>>>^v^>>>><<>^><>>v^v^v>>^^v<>v>>v><>>vv<vv^<^>><<^v>>^^v^<^v^>^<^^<<v^<v>vv^^^<<<>^<^>^^^^>v<<<<^<><<^<^>^v<^v<^v<v>^>^^><v>>^^><>vv^^^<>v<v><<<^vv>vv<<^>>^>><<^v<>^>>^>^<>^>vvv<v<vv<v^<^>vv>v^v>>>v<^<>v^<v>^v^<v^<vvvv>>vv>>^>><>^^><^>^>><^^<>^<<v><<vv<^<>^^^><^^<<>>><^v^>><>v^^<vvv<>vv><^^>><>>v<v^vv><v^>>vv>^><^v<v<<^^^>^<^<v><>v>^<><v^^^^^vvv<>^<<><vv<^v^^>vvv><<^v>^^>^v^vv<^<<><vv^><^^v>^>v^v^vvv>^vv^<^^v>v<vv>^<v^v<^^v^^<>^<vv<vv><<vvv<<<^<>^vvvv^v<^>^<<>v<v>vv<^<vv^^^<^v^v<<v<<v<vv>v^<<v^<^<<>>v^v<^^v<><<<<v<^<<>v<vv<><^v>^v<v^>>v<v^>>v><<<v<v>><>^vv^>>^>v>><>>vvvv<<v^^vv^v>^<^v^<vv<^<>^<^<<^<>>v^><v>v<>><^<>^<>>>v>>vv^^<<^^vv><v><^v<>v<^^v<<^v>^^^v>v^>v>^^^v^<v>>vv>>>^^<v<v^^<^<^^^vv^<^^>^^^><v^^^><^v>^v^>>>^>>v<<><>v^<<<^^v^v<>vv<^v>><>^<>^^<>>><^<>>v^v<<>v<>^v<<<v^v<>vv<<<v<v<><^v>v<<>^^>v>><>>>^><v^v^>v<v>^<^<vv^<<>>>^^^v^<v^v<^^v^vv<><<^v>^^^vvv^<>v>>^^v<^><^v>>v^>v^^v",
//        "<^>vvv^^v^^^vvv>><>^^<v>^^v^<<^v>^vvv^^<<>><>>v^v<^<<^^>^v<>^vv>>>v<^<<^^v^v>^>^<><vv<>>v<>^<^v<^<^<>>v>><^^^^v>>vv<^^v^<>^^><>vvv^<v>v<>>^<vv<v<v^>v>>>^v>^^<vv^<<^>^><vv^v<^<>vv<<^<v^>vv<>^v^v>^^^<>>v<v<^>vvvv^>^^v<>^<<><v<>^<^^<^<^><v^^>v<^v>v^>>v<>vv^<v>^^^><vv^v^v<<v<>^^v>v>^^v>v><v>vv<vv^><v<v^^^><^<v<^^^^^>v^><>v>^v>v^<v<>>v>><^<<^>^^^^>>v>>>^><<>v<^^<<><><>>>>^vv<v<>vv^<<<^>>vv>^<vv><^>><<<^>v<v<^^<^>>v>v>^vv<^>v>>^vv<^>>v^<<<<<^><vvv>^^<>>><^^<<<<><>v^^v^^^>^<><v>><vvv<^><<^<v>^^>v>>^>>>v><^v^vv^>^<v^<^><v<>><^vv^><>>^>vvvv<<v>>>>^^vv^<<vv^v^<>>^v<>^v^>v^><>><<^^<<v<>^^<<^vv>v<<>>>>>><><^^^v<<<<v^<^><>v><vv^^v>v^v>>v<vv>v^v<<v><<^v<^v><>^v^^<>v>v<^^^><^><^vv^^>vv>>^><v><<<<>>^vvvvvv>^^vvvv^v><^>^>v>^>^>^v<^^<^>v>v<v^v><<<v^>^<<<>^^^<v>^>^<^>><^<<vv>^^v><<^^<><><^>v^v^>v^><>^<^<><<<<v<<><v^>^^v^^<<>^v<vv>>>^^<v^^v^><v>vv><>^v^^v^>><v>^^v<^<^><^v>^><v^vv>^^>^>v<<^v<^>v^>^^<v>^>v^^^>^v^v<>^^>^^>v<v<^<^<v^<v<v><v>^<^v>^^^>>><<v<^v^^v^>>v<v^^^^>^^<^<<<v^<v^^^v^v>>^<vv>vv^>>>v>><<<^v",
//        ">v><<v^<<^v^<<vv><>>><v<>>><<>v>^^>^^<v>>>v><><<^<^v>v^vv^v<vv>>vvv^v<<<<v<<<<<^><>^><^v^^<v^>vvv<>^vv<^^^>><^^v^<<<<^^<v>><vv<>^<v>^^v>vv>><<>^>v<>^vv<^^<<<>>^<^>>^<^><vvvv>v^v^<v^^v^<>^<>^v^^v^^<><v<^<>>vvv^v^v>^v^>v>^>><^>^<>v<v<v<>^^<>^vv><<<>^<vv<<vv^<^^v>>^<>v>^^<<><><v<>>v<<v^vv^<>v>>v^<v^<^^<v<^<^<^v<>v^<>vv>^>>><<><^vv<><<v<v>v<^v<^^vv<vv^>^<><^>^v>^>>^>^><<^v^^>v<<^^>v>v>^<>>v<v^><v<^v<v><><<^^vv>>>v^<^v^^>><^>>>><><^^<>^v>^<^><^^^^<>^v^^v>v^>>v>v>^^^v^><>vv<v^^<>>^^>>v<>^^>v<>><><v<>v<^^>^v^><<><<v><^^>v<vvv^^v<^<>><<vv>^>><^vvv>v>>^<v^><<^^>>>><<>v^^>>>>>v><><^^<v<v^v<<v>><>^><vv^^>>>>v^^>^^vvv>><>>^^<>>v^^>vvv<v>>>v^^^<<vv>^<^^<><v^v>vv<<>v^<><<>>^>^^v<v>>><^>vv<^vv>>v><<v^<v<><v^^vv<^<vv<>v<><vv><v>>v<>><^>>v>vv>^vv<^^^><>v^>^v<vv<>^>^vv^>>^><v>v^><^<^>^v^>vvv<<>>vvv<>><>>v^v><^<v>v^<^<<v^v<^^^^^^<vv<>>^>>v><^^<^><>>^^^>v^<>>v><<>>v>^>v<vv>v<<v^vv^v^vv>v>v^^v^^v<^^^<><^v>^v><v<<^^>>>^<v<^<><>^^^^^v^>vv<>>^^^<vv<>v^>^v^>>>vv<v>^>^<>^v^>>vv^>^v<<<^>v><^<<>>^>v>>v>^<v><^v>>^",
//    )