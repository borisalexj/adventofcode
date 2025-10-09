
val map15smallest2 = arrayListOf(
    "#######",
"#...#.#",
"#.....#",
"#..OO@#",
"#..O..#",
"#.....#",
"#######",
)

val move15smallest2 = arrayListOf(
    "<vv<<^^<<^^"
)
fun main() {
    var parsedMap = map15Real.map {
        it.split("").filter {
            it.isNotEmpty()
        }.map {
            when (it) {
            "#" -> "##"
            "O" -> "[]"
            "." -> ".."
            "@" -> "@."
                else -> throw java.lang.IllegalStateException()
            }.split("")
        }.flatten().filter { it.isNotEmpty() }.toMutableList()
    }.toMutableList()

    val parsedPath = move15Real.map {
        it.split("")
    }.flatten().filter {
        it.isNotEmpty()
    }

    parsedMap.forEach {
        println(it.joinToString(""))
    }
    println("---------------")
    println(parsedPath)
    println(parsedPath.size)
    println("---------------")

    for (dir in parsedPath) {
        parsedMap = moveRobot(dir, parsedMap)

    println("- $dir -------------")
    parsedMap.forEach {
        println(it.joinToString(""))
    }
    }

//    val robotPosition = Pair(
//        parsedMap.indexOfFirst { it.contains("@") }!!,
//        parsedMap.find { it.contains("@") }?.indexOfFirst { it == "@" }!!
//    )

    val sizeY = parsedMap.size
    val sizeX = parsedMap[0].size

    var result = 0
    for (y in 0..parsedMap.size-1) {
        var lineREsult = 0
        for (x in 0..parsedMap[0].size-1) {
            if (parsedMap[y][x] == "[") {
                val resY = y // if (y <= sizeY/2) { y } else { (sizeY - (y+1)) }
                val resX = x // if (x <= sizeX/2) { x } else { (sizeX - (x)) }
                lineREsult = lineREsult + resY * 100 + resX
            }
        }
        println("Line - ${parsedMap[y]} Score - $lineREsult")
        result = result + lineREsult
    }

    println(sizeY/2)
    println(sizeX/2)
    println("Result: $result")

}

private fun moveRobot(dir: String, parsedMap: MutableList<MutableList<String>>): MutableList<MutableList<String>> {

    val direction = when (dir){
        "<" -> Pair(0,-1)
        "v" -> Pair(1,0)
        "^" -> Pair(-1,0)
        ">" -> Pair(0,1)
        else -> throw IllegalStateException()
    }
    val robotPosition = Pair(
        parsedMap.indexOfFirst { it.contains("@") }!!,
        parsedMap.find { it.contains("@") }?.indexOfFirst { it == "@" }!!
        )
//    println(robotPosition)
    val (canMove, newMap) = canMove(robotPosition, direction, parsedMap)
    return if (canMove) {
        newMap[robotPosition.first + direction.first][robotPosition.second + direction.second] = "@"
        newMap[robotPosition.first][robotPosition.second] = "."
        newMap
    } else parsedMap
}

private fun canMove(point: Pair<Int, Int>, direction: Pair<Int, Int>, _parsedMap: MutableList<MutableList<String>>): Pair<Boolean, MutableList<MutableList<String>>> {
    return if (direction.first != 0 && direction.second == 0) {
        canMoveVert(point,direction, _parsedMap)
    } else if (direction.first == 0 && direction.second != 0) {
        canMoveHor(point,direction, _parsedMap)
    } else throw IllegalStateException()
}

fun mapCopy(map: MutableList<MutableList<String>>): MutableList<MutableList<String>> {
    return map.map {
        it.map { it }.toMutableList()
    }.toMutableList()
}
private fun canMoveHor(point: Pair<Int, Int>, direction: Pair<Int, Int>, oldMap: MutableList<MutableList<String>>): Pair<Boolean, MutableList<MutableList<String>>> {
    val newMap = mapCopy(oldMap)
    val currentPoint = newMap[point.first][point.second]
    val nextPoint = newMap[point.first + direction.first][point.second + direction.second]
    if (nextPoint == ".") {
        if (currentPoint == "[" || currentPoint == "]") {
            newMap[point.first][point.second] ="."
            newMap[point.first + direction.first][point.second + direction.second] = currentPoint
        }
        return Pair(true, newMap)
    } else if ((nextPoint == "#")) {
        return Pair(false, oldMap)
    } else if (nextPoint == "[" || nextPoint == "]") {
        val (can, newerMap) = canMove(Pair(point.first + direction.first, point.second + direction.second), direction, newMap)
        if (can) {
            if (currentPoint == "[" || currentPoint == "]") {
                newerMap[point.first][point.second] ="."
                newerMap[point.first + direction.first][point.second + direction.second] = currentPoint
            }
            return Pair(true, newerMap)
        } else {
            return Pair(false, oldMap)
        }
        // todo
    } else throw IllegalStateException(nextPoint)
}
private fun canMoveVert(point: Pair<Int, Int>, direction: Pair<Int, Int>, oldMap: MutableList<MutableList<String>>): Pair<Boolean, MutableList<MutableList<String>>> {
    val newMap = mapCopy(oldMap)
    val currentPoint = newMap[point.first][point.second]
    val nextPoint = newMap[point.first + direction.first][point.second + direction.second]
    if (nextPoint == ".") {
        if (currentPoint == "[" || currentPoint == "]") {
            newMap[point.first][point.second] ="."
            newMap[point.first + direction.first][point.second + direction.second] = currentPoint
        }
        return Pair(true, newMap)
    } else if ((nextPoint == "#")) {
        return Pair(false, oldMap)
    } else if (nextPoint == "]" || nextPoint == "[") {
        val (can, newerMap) = if (nextPoint == "]") {
            val (c1, nm) = canMove(Pair(point.first + direction.first, point.second + direction.second), direction, newMap)
            val (c2, nm2) = canMove(Pair(point.first + direction.first, point.second + direction.second-1), direction, nm)
            if (c1 && c2) Pair(true, nm2) else Pair(false, newMap)

        } else if (nextPoint == "[") {
            val (c1, nm) = canMove(Pair(point.first + direction.first, point.second + direction.second), direction, newMap)
            val (c2, nm2) = canMove(Pair(point.first + direction.first, point.second + direction.second+1), direction, nm)
            if (c1 && c2) Pair(true, nm2) else Pair(false, newMap)
        } else throw IllegalStateException()
        if (can) {
            if (currentPoint == "[" || currentPoint == "]") {
                newerMap[point.first][point.second] ="."
                newerMap[point.first + direction.first][point.second + direction.second] = currentPoint
            }
            return Pair(true, newerMap)
        } else {
            return Pair(false, oldMap)
        }
        // todo
    } else throw IllegalStateException(nextPoint)

}


//val map15Smallest = arrayListOf(
//"########",
//"#..O.O.#",
//"##@.O..#",
//"#...O..#",
//"#.#.O..#",
//"#...O..#",
//"#......#",
//"########",
//)
//
//val move15Smallest = arrayListOf(
//    "<^^>>>vv<v>>v<<"
//
//)
//val map15Small = arrayListOf(
//"##########",
//"#..O..O.O#",
//"#......O.#",
//"#.OO..O.O#",
//"#..O@..O.#",
//"#O#..O...#",
//"#O..O..O.#",
//"#.OO.O.OO#",
//"#....O...#",
//"##########",
//)
//
//val move15Small = arrayListOf(
//"<vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^",
//"vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v",
//"><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<",
//"<<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^",
//"^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><",
//"^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^",
//">^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^",
//"<><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>",
//"^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>",
//"v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^",
//)
//
//val map15Real = arrayListOf(
//    "##################################################",
//    "#.....OO..O..O.O..#....OOO..O...OO.....O....O....#",
//    "#.O#...#OO.....O.O.O....#..OO..OO.##.#......O#.#.#",
//    "#.....O.............O..O....OO#...O..O.......O...#",
//    "#...O..#..........#....O..#...O.OOO....OO........#",
//    "#O.##..OO....#O..OO#..#.#...OO.O....O#..O.O.O..OO#",
//    "#......#..O..O#.OOOO.O.OO...O#.OOO...#...#....OOO#",
//    "#.#......O.OO......OO.O.O....#.....O.O.O.OO.##OO.#",
//    "#.....OO..OOO...O........O...OO.O....O.O.....O...#",
//    "#..O#...O#.....OO...OO.OO.OO..........OOO.#.#..O.#",
//    "#....O....#.O......O......O#.OO.O..#O...#......OO#",
//    "#......O.#O...OO.....O..........#......O.O.......#",
//    "#O..#O#O.OO..O.O.#...#.O...O.#OO.....O.OO#OOO..O.#",
//    "#...OO#...OO..O..#.OO.##....O.O....#OO.O.........#",
//    "#..#.........O....OOO..#O..OOO.O....O.O...O......#",
//    "#.OO......O..O#.......O.O...OO........#.....O..O.#",
//    "#OO...O..O..O.O.OOO.........OO.....O.O.......O..##",
//    "#O...OOO......O........O#...........O.....O.OO..O#",
//    "#..OO.....#..O..#.OOO...O..O#....O.OO.#...O......#",
//    "#....#.O##.O..O...OO.O.O#.O.O#.#O.#O.OO...O......#",
//    "#O....O....O..O..##O....O.....O.O..OO..OOO.OO....#",
//    "#O.......O..#O.......O..O...O....O...OO.OO.#.....#",
//    "##.OO.OO#.O#.O.OO..OOO.O...#..#...O...O..#...O...#",
//    "#.O...O..#..OO.O..O..#O.OO...OOO.OO..O....O.....##",
//    "#.O..OO..O.OO#...O...OOO@..O.#O.OO.OO.....OO..O.O#",
//    "#..O....OO..#O..O.O.O...O..OO....OO..OO.#.#.O....#",
//    "#.##...O.O...OOOO.O.O..O.O..#O#.O..O.OO#..O......#",
//    "#OOO#..#.#.O##.....OO.O.O..#O..O...O.....OO....OO#",
//    "#..O.O...#........O..O.#....OOOO.....#..#O...O..##",
//    "#...O....OO......O.OO..O#O.O...#........O.O.#..O.#",
//    "#.....OO...#OO.O.OO.O#.O.O.#..........#...O....O.#",
//    "#..OO..#O.O..OO.O.O.O...O.O..OO.OO..OO.....O...O.#",
//    "##O.....O.#.O........O...OO..O#..........#....#O##",
//    "#.#O.....O.....O.OO.OOO.O............O.O...O#.#O.#",
//    "#.O..#.O....#.........#..OO.OO.#..OO.O.O.O.....O.#",
//    "#....OO#....O.O...O...O..O#..O.....O....O..O...O.#",
//    "#O...OO.O..#..O........O..#O.O#..#O#...O...O..#.##",
//    "#............O..#..#.O...O.##OO.....O.O..#.OO#.O.#",
//    "#....O....#........#O..O.O...............O.#..#..#",
//    "#O....OO...OO...#...O.....O.#O#.............O..O##",
//    "#..OOO......OOOO#.OO...OO#.O..O........OO.O.....O#",
//    "##.OOOO#..O.O......O......O....O...OO.OO.OOO.....#",
//    "#.O.#.#.O.#......O..OOO#O#OO..O...#O.O#.O..#.O...#",
//    "#O.OO..#OO......#.#.O#..O.O....OO....#O...O......#",
//    "#.O#...O...OO.O..O#..OO...#.O.O...O#.##O#....OOOO#",
//    "#..#........O..O...OO.#.....O..O...O#.OO..O.....O#",
//    "#.#.#.O....O..O..O...O.O.O..#..O.O..#...O..O.....#",
//    "#O.O....OO..O..O..O....O..O.OO...O.O.....O.#....##",
//    "#.OO....OO.....OO.......O...O..O.OO#..OO...O.O.#O#",
//    "##################################################",
//)
//
//val move15Real = arrayListOf(
//    "v^^<<<<<^^>v^>><v^<^<v><^^v<^^<>>^^^<^^>v>>>v>v>>^^<^>>^>><^<<v^^<^v><v^><><v^^<<^>v^v^v>v<<vv<^^<vvv>>>v<>v>v<^>>v<>^><vv>^^vv<v<^^vv^v^<v<>>vvv>vv>>v<^v<v<><<^>^<vv^<^>vv>vv<><>vvvv>^v^^<v>vv<^<v>><vv><>><>>v>^v^^v><v>>v>^<vv^v>v<v^>>^><<<<><^>v><v<>>v>^^^><v>^>v<v^>>^^<vv^>^><^>v>><v^>^>><v<^>>>^^<<>^v<>>^>><v<<>^^><<><^>><<>v<^^v>v^<><^><<^v^^>^>vv^^<^^v<v^v><<v<>^vv<<^<>^v^v^<^^v<^^v^<<v>>v>vv<^<>v>v<^^><v<>>^>v^^^^><>>v>^<^>^^^v^>>>vv>^<vv>^<>^<v<v<vv>>v><>>^<v^^^v>>v<<v>^v<v^<>>v<^v<<vvv<^v>v^^><<^^<v^><^v<<>>v><v^<>>^v^^^^^><>><^^^v<^v><v<>>^vv^^<>v<<<<^vv^<<>^^<<>vv^vv<v^<^v<>><<v<v<<^v>>^>vvv>>>^vv<<<v<>v^>^>vv^>v<v<<><v^<^v<>v><<^<v<^<^^v^vvvv^>^v^>vv<v>^v><<vv^<v<<^v>>vv>v<><v^>^<^><^><><^<^<<>^v^^<<v^>^^><><<>v^><>vv<<v^<<vvv>>^^>><>v><^vv<v>>vv<^><v<v^^v>>><v^vv^vvv^>v>v^^<>^^>><^><^v>^>v<<v<^>vv>^<v^<^^vvvv><<v^<^^><v^>^^<^vv>^<v>>v><<^v<v<<>>^><<v^vv<>^v><>^<<>^<<><>v^<><<<^>><>^><v>>>>><^<^<vvvvvv>vv>>vv>^>><v><^<<>>vv^v<^>^^^^v<<v>>>v<<^<<<<v<^vv>^<>><v>^<^v>>v><<>>^v",
//    "<v<<<><vv^^<>^<>^<^v<><^v>>vv^v<^^<v<><^><^>^^^v^v>^><^<<vv>v^v^<^>^^^^<^><^v^vv<^v<v><>>>^^<v^v^<<^^^^^><v<>><>^<v^<<^v<<^>>vv<^<<><<^^^^v>><>^v<^v<>v^^^^<<vv<v<<<<v<<>^<^^<v<^^>^v^^>>vv><<>><<<^^v^v<^v>><^v^>^^^<>^><v^vv>v<v^v>>v^^v^>v>^v^^^<<v^<v>^vv>v>^^v^v>>^^<<v^vv^v^v<v<<<>>><><><<^^>vv^<<<>^v<>v<<<<<>^^^v><v<<^v<<^v^>><v<<>vv><<<v><v^<<vv<v>v^^>^^^<v^v>vv><v<^v^>v<>^^<v>^<<>>>><>>>>v^^v<>^>><<vvv<^>v<^>>vv>^<^^^^<<^>>v<>>^<><^^><><>>^>v<v<>^^>>>v^^<><>^v<<><<^><<v<v<v<><^^v<v<><^>><v>^v>>v<<>vv^>v<<^>>v><^^^>>>^>^>vv^^v<>^>^v<<<v^<<<^v>>v><^>v^>v^>v<<<vv>^>vvv<<v^vv><^vv>^>^^^>>><>v>>>^<>v^v><vv<><^>^>>>^v^vv<<>^>^^^<<><<^^vvv^v>v^><>^^^<<>>>>^vvv><>><vvv<><>^<<^<<>>^^<^^^^>^v<<<^>v>vv^^v^^>^v>^v^vv<^^<>^^^<><^^vv>v<<>^<^vv>>>>>^^<>>vvv^v<<^vv>>>^v<<<vv>vv<^^>^v><>>><>v^v>^>^><vv^><>>><>v>>vvv<><vvv<v^^^<v^>^vv>^v<<><vvvvv^>v>v^^v>><v><>>>v^><^v<>><<>><^><v^vv<^vv<^>v<<<<^<>vv<v><vvvv<>v^v^v^>^><v^>>><<<^>>vv^v>vv^^<^<^v<vv><v>>v>^^^^^v^^><<vv^vv^^>v^vv^<<<><^<<<<^vv>><^>>>><<v",
//    "vvv^^<><<>><v<>v^>vv>>vv><><^<><v><<^>>vvv<><>>^<<>^v>^v>>><v<><v^vvv<^>>>v>v<vv^^^v<^<^v^<><v<<^<^^^><^v<>vvv>>><<^v<v>v<vv<^>^><>v<<v<<^<>vvv>v<^>v^v^vv>^v<<><^>vvv<v<<<^^>^v>>vv>>^v><^^<^^><<v^^>>>>v^><<v><vvv^>v^<v<<v>>><<^<v<v<<<v^^<><v^<<^<vv^^^^vvv^>v^<vv<>v<<<v^<>v<v<^<<^>><>^^^v^>v^v^v>v^<vv<v^<^>v>v<>>>^>><^<<<^v<^v^v^^^^^><v<<>v>><><>^<>>v<v^^v<^><<>><^^v><<v^<v>^v>v>^^><^^^>>^<^<<<<^^>>^^vv^^<vvv^v<^<vv^<^v<>><v^^v>^^^^v<<<v><<<v>v<^^^<^^>v<^>>v^v>^^<v<v>^v<v<<<^v>^^<<>>>>>^<^vv<<>>^vv^>^>>^>v>>>>^>v>^v<<v<^<>^<vv<v<>v^v>>^v^>^<vv<^v^^v^<<<><<<>vv^v^<<^^^v^^<<<>>>vv>v^<<^<><^^>>v>^v<v<<>>^^^v>^>^<<<vv<<^vvv>v<<v^v^><v><^v><<v<vvv<^<v<<vvv>>v^^^<v^<^v>><<^><<<>v>>><<<^<^^^^v<<^^>^><^^vv>v>>><vv><vv^>vvv>^<>>^>^^<vv<^<<v<v^v<v<^v<>><^><^<vvv<>>>^^<>v^vv^>v^>vvv^>^<^><<<<^v>v^v^<<<>>^<>^<v>^v>><<>><^^v<^^^v<>^^<vv<>v><^v^>v^v<v>>>^>v<^v^^v>^v<v^vv^v<<vv>v>>>>^vvv^v<<v>>^vv>v^>><^v^v<><^<v<^^<v^>vvvv>v<>>vvvvv>v<<>v<^<^^>^^v^v<^^^^v<>^^v><^><v>v^<^v<^<v^^^<>vv>>>>>^vv><^^<v<vv>",
//    "<^^><<>>v><<<vv^>vvv^^^>>v^>>^><v^v^>>>^<^<>v>><<<v^<>v^<vv>^<v>^>v^^<^<^v^v><><v<v^<><>^v^><^^>><>v>><v^^^^>v^^v>^>^>^>^^^>v^^<>v^>>v<<^^^^>>^><<v^><v^v<^>^v^<^<v<<<vv<^vv^^<<^v>>v>>^^^>v^v<>v^<^v><^>^<>^v<^>vv^>^v>^^<^>v>^^<>^v^vv><v<v>><^vv><^^vv><><v>><^v>^v^vv>>><v<v><v>v^v<^v<>^^v<>^^>^^^^^>>><^<>>v>vvvv<^<<^^>^><^^<<>v<<v<>v^<^<>>v>^v>>^v>>v>^<^>v>v<vv>^v<v^>v>^vv^v^v>v<<v>>v><>>>^<v><^^^^^^>^><<<^<^><v<>v>>v>v<><<<<>v<v<<^v^^^v><<<>^>^v^><<<^>><^>v^^>><^<^^v<v>v^>v^>vv^v><^<^><>^^v^<<^v>v><><>vv^^<^v<>>^^><>^^^v<v><><vv<v^>>^>v<v^>v^^^><v^v^<v><<><<v^v<>><v^<><^<vv>^v^>>v^<v^vv^v^vv^vv<<>^vvvv^^vv^^<^v>v^v<<<v>>^>v<><vv>>v<v<^vvvv<^v><<v^><>>^^<><><^<v>>^v^v^<<v<>^>v<>>v^>v>v>^v^^><>^<><>^^^><><<^<>^<>^>v<><>><v><v^>v^^vv^><^><<>v>v<^vv<>^v<v>>^^^<v<^v^^^^<<><vv<<v^>vv^><<^^^<<>^>v^>v^vv>>v<<>vv<v^>v^>vvvv^>^<vvvv^<>^<<vv<<<<v<v<>>^<<^<>v>>v><^^<v>>><<v>vv^v^^^^^^^^v>^^^^v<<^v^<^<<<v>><>>v<>^^^^>^><v^<>^v<v<>>v^v^^>vvv^^v><^v>v>v>>^>>v><>>v<^>>><^<<>^>vv<<><v>^^^<<v^<>^^<v^^<v<",
//    "^v^v>v<<^v><v<<<^<v<vv>^><^^>>vv<^<>>>>^^v^>v^^v^^<<<<v<<^vv<<><^<<^<>v><^v<>>>^^v><>>v^v<v>^>v<^^>>^>v^<^<<<v^v^^>^^<<^^^v^<v<^vv>vv>vvv^>^v>v<^v>^vv<><v<><v<>>>>><^<>^>>^vvv>v^>v<<<<^v>>v>^vvv<>^^^v>^v^>>^^<^^^^><^^^v<<><>^v>><v<<v^^<><v^>v<v><><<<v<vv<v>^v^^>vv>^^<^<><<v^^v^><^><^<>^<>^>^^<vvvvv<<v>>>vv>>v>vvv><^<><^<<<>><>vv>>^v^^^^<>v><^^^^>>v^vv^<<>v>v<^<<^v<><^^^^<<^<<^vv><<v>v^v>^<>vv^vv^v^><>v>>>^^^>>vv>^>v<v>v<>v>v><v>^^v^vv><vv^<>^>^<vv<<<^<^<<><>^^><v<>v><vv>v>v<<>^>><<>v<^<vv><><^^vv<vv<^>>v^<>v<^^<^^v^^<<v>v>^><v<><v<^^^>><v>v^vv^>vv^>^^>^<<<<>>><>v<><<v^<><v^^<vv>v^vv<<<^v>>v>v>^>^^><>vv<^vv>>v><<vvvv>vv^>>v><<<^<^^<^<v<v>>>^v^v^v>^>^>>>>^^vv>^>v^^>>><>^^^v<^>v<>^<>v<^<v<v>>><>vv^<<<^^^><^v^vv<^>v<vv>v^>>><v^>>>^<<<v>^^^>^<v>^<<<^v>^^^>v>vv>vv<<^<^><>>v><><^v^>^v><^>^vvvv^<><>^>^vv><>v>>v<>^>>>^v^vv^>^>^<^^<v>v>^^>vv>>vv^<v<<^<v^^vv<^>^^<><<v>^^>v><>v^v^<vv>^v<^^><>^<>>v^vvvvv^^<<<>v>v^^^<v<^<><v>vv^v^<<^<<^v<<^v^v><>^><^<^v>vv<^^v<v<^><v<^v><^vvvvvv<^<^^v^^v>v^>>v>>^<v>",
//    "v>^^<>v>^v^><^><>vv^<v^^^^><<<v<>^<<^^vvv<><^v><>>v><>><v<vv^><><v>v^v^^><><>^^v<<vv<^<v^^<^<v<^<>^^^vv>v^>vvv>^<<^>v<>^v<<v^<><v<v^v><^<v^^^v<>>^^vv>^^v>^vv><v^<v<>>><>v<v<<v>v>>>^><>>>>^^>>^v<vv>>v^><><^^^^vvv<^>vvv^>v<>^<<v<<>^<>v<<v<v<^<^^<^<>v<<<^><vv<>v^v^>v>><>v>v<<<<v^v^<><^><><vv><^^^><v><v>>v<<<^v>vv^^^<v<><<>v<v^<<<<^>>>>^^^v^<><v<<^<^^<^<v>v>v<>^v<<v^v<v<v^>>>>vv^>><v<^v<v<^>v>^>v^v<vv^<v^><><<^>^><<<>><<v><>^<^<<><v><v<>vvv<<<vvv<><v^v^^v^vv<^<<<vv<><>vvv<<vv^^v^>>^<v>>v<<vvv^<>v>^<^<<^>>>^<<><>^^^<>>v>v>>^vv<v><^>>vv><><v>>v>^vv>^v^<v>v^>>v^^^>v^^>v>>v^<>vvvv>v<<<^^v>>vvv<>v>^vv^^<<vv>^<<>vv<^vv<^^<>v^>>>^v^vv<^<^^<>>^^<^<>vvvv^<>^^^<^^vvv^v<<^^>v^>v^>v><v>^^v>^vv>v^vv>>>v>>><<v>^v<v>^v<<<v>^^<v>>>>>>>>^<vv^<<v<vv^v^vv>><^^<>v^<>^vv>><>^<^^>><<^v<>^>^v>v>v^<^v^>><<<>>v>><><v><v>^>^^^><<v>v^<^^>^>^^<<^vv<>v^^>^<^><>^^<v>><^><v<v>^vv<>^v>^^^^<v>><^vv<><>v>v>vv^><>>^v<v^v>v^>^<><vv^vv>v^^^<^>>v^>^<v>^^v><v>^>vv<<<v>^<v^^^<^>v<v>>^^v><>vv>^^><vv^^^>^v^^<<v>>>><vv>v^^<v>vv<><>",
//    ">^><<^^v<<>v^^v<^<^<<vv>^<<v<>>^^^<>^<<vv<^v>^^v<>v^^<^<^v<vv^<>><>v>^<^<v>v^vvvv^^>^v^>><<><v^^>^<<^>^<v><v^><<<vv>vvv>v^>v>^v^<v^><v>^>vv<^^v>^>^v>>vv<<>>><v>>><>^><>vv^v>v><v<<<v<vv<^v>>v>>><<>^>>>^^^><v>^vvv>>v>^^v^<v^^<>vv^v^^<^^>^<v>^>vv^^^v^<<^>>><><<^>>>vv><><^>^v>><>v<vvv^v<vv>^v>v>v^^><<^^>v>vv<<^>v<>v><^<v^>v<vvv>v>v<v^^v<<<v^v^>>><<<>>>v^><>>v>^<^v^>>v>^<v<v<vv<^^^v<><^>>vv>>v<v>>^<^v<>vv>^^vv<>><v<v<^^<>^v<<<^>^v^^v<<v^v><^^^^<^<v>^v^^v<<<><v>^^^v^>^<vv^^v>v<<<^>v>>^^^>v<>><<<><<<^<>v<v<><^^>^v^<<v<^vvv>^>v>v^v<>>^<v<<v<v<^<v>v>^<^<>^^<>>vv^^>v^<v<v>^^<vv<^^^^>^>^<>^^v<^^v<^><vv>>>v^^vv<>>><v>v^v^^><<<v<^v>vv^<^^<<>^><<^>>^^>v^<^^v^v>>^^v<^v<v<>><<>^>^<>>>>><^v^<>^<>>^v>^<><>v<vv^v>v^<<v^v^v^^>><>>^^<v>^v^vv<^vvvvv><>v>><<<><^vvv<<^><<v<v<<><>>><vv>v><>v^>>^v^<>>^^^>>v<^^^>vv>v>^<^>^^>^<<<vv><<v^<<<^v^<>>^<^>vvvvv>^^<<v>>vv^v><^<^v>>^<^><<><<>v><v^>><^^v^^<<<>vv<>^^^v^v<v<<>^^>^^>^>^>v>>^<<<<v><>v^^^<v<><^^^<>v^^<v^v^>vv^>><^v<v^^v^>>^v><^<^^>v<v<>^v>>^<v<^^>>v^<v^><^>^>^v",
//    "vv^^^v>^vv^v^<<^<v<<^><^><^v>>v^<^>vvv>v<>v^<^><^>v^>^<<<^^>>v<^>^>>^>vv<><>^<<^<<^vv<>^>v^^^^v^v>>^><<v^^^v^>>^^^vv>v>^>v>^<<<><v^>^<^^v><v^^vv>vv<v<<^^<<>>^v^>><^>vvv^^<^v>^<<>v<^v^>>>^><<^>^^vv^>vv>^v^<v^<>^<vvv>^v^v>v>^<v>><>><<^>>^<^<vvv>>>>v><<^>vv<v>^vv^<<>^vv<<>>>>vv<<^v^>^>v>vvv^>v<^v>^>^v^<<^<<<>^<v^v>><^>v>>>^>>^v^>v<<v>^>^<^vv^v<<>v><>^v^><<><vv><><vv<^v>^^v^^<>><>^^v<^v>>>vv<><<vv><<^^^<vv>>>^^>^>v^>>^><^<vv>v<^<>v^><<<>vv><<<v>v><>v><vvv>vv>v>v<<<<v>v<^<>v^<^<^>^^^^v>v><^^vv^^^^>>>>>>v^><<^<^v>>v^<v>v>><><v><><>>vv^>>vv<v<^vv<>^^<v>><>^v<>^^v<><>>^^^<v>>v>>v<>>^><<<^v^^>>v<v<^v><^v<^<v^<>vv>>^^>>^v<^><^vvvvv<v<^<><<>v>>v>vvv>>>^v>>>v^v^^v^<vv^^<^><<>^<<>>>vvv^v^<v>>><vvv<v<><v>^^^v<^><v><^^>^v>^^>^<vv<^v<>^v^^^>^^>v>^^<^^^<^^>>v><<<<<>>>v>>>^vv^^>><vv^^>vvvv^vv>^^vv<<<<<vv<v^v>vv^>>v<^<<><v<^vv>>^>vv^vv^<^^^^<^<>>^<<^<><^^<><v<^>vvv^>^>>>>>^v><>^<>^v^<><>^>v><^^<<<v<^^v<^><^<>vv^<>^v<>vv<><>^<^>^^^>><^v^>^<v>><vv^vv>v>>>v^<^<^>v<^v<^v<^>v^^^v><>^>^vv>>>>^<vv>^><<<<<^>>^vv",
//    "v>^vv<>>>>^^<>>^><^<vv>>v<>v<^<>>^>^<<><v<v><v<<>v<^v<<vv^>>>v>^>><v<v<><>v<^>>vv<v><<^<^v^v<^v>>><^^>v<>>><v><v^^>vvvv^<v><<v^<<^^^<v>^<<<<^^v^<^^><^<><>>^^>vv^<v<>^><^v>^<v^><^<v<^v^^^<>>v>v<<>^^>>>^<<^v^>^^<v>v^>^^vvv<^v<><v^<^><^vvv>>v<v<v><v>^<v<><<^v>v>>v>>^vv^v^v^<v^><<^vv<>v^>vv><<<v^^<v^v<>^>v<^v>^v>><vv<<^vv>vvvv^v^^^vv^v>>^^v><^<v^^>v^>^<>^<^v<^vv<v<>v^^vv^^v>^vv>>><^v>^<>>v<^^v<>vv>^>v<^^vv<^<v^^<<^v>>^^<v^^>v>><v^v^^<<^<>v^<>><v>v^^>>vv>^^vvv<v^^>^v><<vv^>>^v>>^^<vv>>^^<v^<>^<^><^>>^^^><v^v<><^^>><vv><>^<^^^>^<vvv<><<^><^vvv<^<^>^<>vv^<<>v^^<vv^<v<v>v^>^<><<v><v<>^vv^>>^<^^>v<<v>>>v^>^>>v<v>v>v<<^<v^^<<<<^>v<>>v<><v<v<v<<<>^><v^^>^<vvvvv>>vv<v<^^<v<^v^^^><<<vv<vv>>v<<^<v<<<<>^vv<<><vv<v>^<<><<>>v<<v>>>^v^>>><>v>v<^<^<vv<<<v><^>^^^<>^^v<vv>v>>>^v>>^>>^><v>><^^<<>>v<^^>><^v>><^>vv^^^<vv^>^^><^<vv><<<><<><>>>v>^v^<v^<^>>^>vv>v<^<>vv><><<^>v>v^vv<>^>vvvv<>^<<v<^v<^>^>>>^v><^<^^^^^>v>v<vv^<>^>vv<<<<<v^^<^<v<^v^v^v>v^><<<^v<v<<^<v^^>v>^>^v^<v>><^v^>v<v^><^>>>^><<^^v^^^v>>><^vv>>",
//    ">>>vvv<^vv>^<>v^vv^><>v^>><>^^^<>>v<^>^v>vv<>^vv^vv^vv>v<^^v^v^<v>>><^>^^^<<<<<^<vv>^<^vv><v>v<>^vv>>^<vv>><><^^<^><^v<vvv<><^<^<^^^>^^>^^^>v>>><^>^<vv<^>><^>>^>^<<^<>v^>^>>>v>^^<vvv><^^>vv^><<<v>><^<v^^>^<v><v^><<^v^^>^<><<<^><>v>^<^>>^v<^^^<>vv>^<^<^^v^v>>vv<>v<v<<^^^vvv><v>vvv^>><v<<v^<<<v^v><>^vv<^><>^v><>v><>v><>^v>vv>><^v^^^<<>v<vv>v<><<^>>v<^^^v^v^vv>>><><vvvv<^<>>vvvv>v^>^vv^v^<v^^>v<v>>v>v><><<>^^^<><>^^<<>^v<v<^^<>^v^^<>vv<v^^^v>>v><<^v>^<^>>^v^>^<<<<v<^<^^vv>v^v^>^<<<<v>v<vv<v<><<^v<<^>><^v<v>><>^<<<^<^^>><<>^^^^^>v<><<<<v^v<v>>^^>>vv^<v><^>>vv^<^<^><v^<vv><vv^^>^^>^^<v<>vv<^v<v>>^>^vv^^v<<>><v<v<<v^<^v^^<v><<>><vv<^v^v<^<v<<^vv<><<<>v^^<v<><<>>vv<^><<^^vv<<<vvv>vvvv>v>v^v^>vvv^^v>^>^^<><v>^<v^>^><^v^<<<>v^^>^^<^^<<v><^vvv>>^^v>^<v<^<^^<v<vvv^vvvv>>^v<>>^<v<>^^^^<<v<v^>^><<<<vv<^v^v<vv^<^v<<>><v^^^v>^v<vv<>>v^><<<><v>^^^<>>^<vvv^^>v^^<^<>v^^<>v<>><>^>vv<<vv<v<vv^v>v<<<>^><vv<v<>>><>>v<><^><>>v^^>><>vv>^<<><^^v<<^^><>>^^>v>v<><<<>^>>^^><<vvv>><^^^<^<<>vv>>v<><^>v>vvv^<><>>v^^",
//    "v^^<<^<<<v^v>>vvv^<<>v^^><^^^v><vvvv^^<>^>>vv<<<^vv<>>^v>^<v<>>^^>^^<<><^v<>v>>>^v<>^vv^^<<<<v^>^>>^^v>><<<^v^^^v><>vv^^><>v>v^<^v<v^v>><^v<><^<^>^<^vvv^<^^^<<<^<>>><<^>^vv^<<<><<^>^<vvv^>v<vvv<<<^v>>^<<v<^<<<><v^^v>>vv^><^>^v>^><^<>^<<^<v^<^v^^v><><<vv^<^><><>^<^<^v>vv^>>vv<>><v^<<v^^<>^v><^^^>^^>>>v><><vv^v>^v>v<>^vvv<^^^^>><^<<v<<^>^<>><vv^v^>^v^v<v^>v>v>>v^^v<>v>v<^<^<v>>^>v<v^v>><<><^<^v>^<^v^v^<v>><<<<vv<><><><>^vv^^^><v<^^><<^^>>v^v^^vv<>v<^vvv^v>^>^<<<^v^^vvv<^^v>^v<^>>>>>>^v^><><<^>^<>vvv^v<<<vv^v>^^^vv^<vvv>^vvv>>^v>^vv>v>v<^v>vvv^^>^<>^>>vvv<>>>^^v^<>^vv^^vv^v>v<^v^>vv<^<<<v^v<^vv<<vv>v>^>v^v<^^v^^^^>v<^v>><^><<^^>^^<^^^<>v>>vv^^^v>v>v^>>^>v>v>^^v<<<<<vv<>^<v><<>^<v>>v<v><vv<<^>^<>>^<v<^>>v>>v<v>vvv^^vv>><^<vvvvvv^>^<^^>^<v>>>>><^>><>>^^>v><>>^>vv>vvv<<<><<v^<^^<>^>^^>^<vv>^^^^>vv>v>^^>v>>v^vv^^^v^>>>^^<<v>v<<>v>^vv>^v><v<^v^^<^<v><>^>>>v^>^v><<v>>^<<><><^>vv>>^^v>^>>^v<><^^v<^v^v>vv<^<><<v>>^><<^<<<<^^v<<<^^^^>^v><^vv>>^>>v^v>v<v<v<<^<v>><<>^<v<^^vv^^^^v^v>><^vvv^>^^v^<^<<<",
//    "<<>^<v>>><^v>v<v>^v^^<<^vv>>>><v>^<^<^v^>^^vv<v<vvv^vvv^^^<v>>>vv<v<<^v^><>><v^<v^v^^^vv^^<<<v>v<^>>v^>^vvv>>^^^v^vvv>>vv^v><v<>>^^vv<<<v<^<^v<v>>v^^>vv^><^<v>^<>^>v^v>v>v<<<<v^v<>^v<>>>v<>^^<><^^<^<<vv><>v^^>>>v><v<^vv<^<<v>v^><<^^vv<>^vvvv>^>v><>^vvv<v^><<^>^><<<vv<v^^<>>^<v>^<v^v^^<<<vv<^>>^^><>><v<><><>v<^^<^vv^>v<^<<vvvv^^vv<>^^><v><<<<^>v<<^<<v><<><v<<<<vv<^<<<>^v^v>>><vv^<<<v>><<^<^^><v<>^>vvv>>^><>vvv><<>^vv><>^<>>>v^>vv>vv^^^v<^v><<vvv<^v^v>v<vv>^<v^>><v^^<<>^><<v^v>^><v^vv>^<vv>^>^vvv^>><v^<^vv^<>>v>^>>>>^v<>vv^^><^v<<><^>v^v^v>>v^<v><v^>^v>>>>v><<<<<vv^<^>v^<^<^v<>>v^<<v^^<^><v^vv<>^vv<^>^v<^^v^v^v<v<^><<^><vvv^v^<><^><^v>vv^^<v^>vv^<>vvvv^vv>>^<>v^<>><><v<v<^v>^>>^^vv<>^<<v^^<v<v^<^>^>>^<v<>v^^^<<v^vvv><<<><^vv<^>vvv^<<^v>^^>^^<>v^>>^^<v^<>v>^^v^^>v>^^>^vv^<v>>^^vv><>v><<^><^<^<^v<>^v<>v^^<^>>^<>^<vv>>^vv^<<^v>>><v^^><v><>>v<v^>>>v^v<v>>v^vv^v>v>^<>v^>^>v><>^vv<^^^^<v^><^<^^><<<<<<vv^v<>^>v<>^v^>v<<<v<<v^^v>v^<v<>>vvvv><<^^<v<^>>v^v^<^v^<<>vv>^>v>^<>v^^v<>>vv^<<^v<<>v<^<<vv",
//    ">^>^>><<<>^<^^^>v>v<v<<^^^^^<^<v<^>>^>>^>vv<v<v>^<>vv^<>^<<>^^v<vv>^^<vv<>v><v^<><v<<^>v>v>^^<<>v>v><v^>v>vv^v^<v^v>^^<<v^vv>>><>^>><v<^<>><><^^>v>>^>vv<^^<<^<<>><^^>v<<>^<^><<vv<vvv><^v><<v^<>vv^v>^>>>vvv><^<<<<<^^>^^<>>^>v>v>>^v>v^<v^v^^v^v^vv>^^^<v^^v^>^v^>^^v>^v>^v>^<^^v>^>^<>v<vv^v>^<vv>^^vv<<^>>>>vvv<<v^>^^<>v^^^v<^><v^^<vv<^v<><>^^^<v>><>^<^v^>v^v>^<v<<<v^><v^<v<^v<v><v<^>><^>>v^v^<^v<v>vv>vv<>>^^vvvv^v^^v<^>>v<>v>^<^>vv<<>^^^v>v<v^v<<<><v>v>vvv>v<<<>^<>><<>^^v>>v<v<><^v>vv>^><<<>^>><v^v>^vv<vv^^>v<^^<<<>v^>v<><^>v>>>^^^v<><<v>>>>>><^v<>><^>^><^^v<^v><>>><^^>^<<<v<<<<>^v^^<v>^>vvv<v><v<^vvv>^^^^^v^><>v^^^<<^^v><v<^^><v^>>v<<<v>>^<<^<v<><<v>>v^<^<>v<^><<v^><<<>>>vvv^^><v^<<^^<vv^^>^><<^>^<v><>v>>><vvv<>v^^^<<v^v<>>><^v<^v<><<^v><^vv^^^^^>vvv<v>v^<>^v^>^^<vv>>>>>>^<^<^<v><v^>^<v>>><<>vv^v>v><><<><<<^<v<>><<v<^v<>v<>^<>^v^^v^>vvv>^<v>^^<<><v<v><^^^><v<^>>v><><^<<^^>>v<^v<v>v>>>v<<v^<<<>^^<v^<>^v<^v^<vv<><v>v><<<>^^^>^^^>^^^<v>^>v^<^^^<^v^<^>^<>v<>>^^>v^<v^v>><^^<v>^v>>^^v<v>>^<<>><",
//    "^<^v><v><^^<><^<^>v^^>>v^^^<^<v^<<v>vv<v^>>vv<><>^v<><^vv<vvv>^>vv>^<>><^<<<^>^^v<<<^<v>v<<>>^>^<<v>>>>>v<>vvv<vv>>^><<v<vv^^>v<<vv>^^<^>v^>v>^^<>><v^^^^<^>v^<^><><^v^^vv^<v><<^>>v>v^vv>><<v<v<v^^<v<><^><^<><>^v>><<>>^^^^^><>v^^<<^^>v<<<<^v<<<>^<v<^v^^<<^^^<^v<>><>>v<v^^v<^v^^>v>^>v^v<^^^v^>><>^>vv^<^>^v>^>v^>>v>v>>^^^>>^>v^<v^^^^>^v^v<<>>v><v^<vv^v<v^<>v>v^>^^<>><<v>>>>><<^vv>^<>^v<v^^<>>v<^>v>><v^>^<><vv>vv>v<><^><v^^v^>v<^<^>>v^>v<<^v^><v^^><^^>><<<>^<<<>v^>^^^^>>^><<^^v^><^^<^>^<<^^>v>>>v^<><^<<<>^<<^^^<v<v<<^^vv>v^><^>^>^^v^>>^>><<>^^>v>>^><v><vvv^<vv^>^<><<><><>^^<<>^^<>^>v><>>v<<>><>^^^<<<v<^>>v^><^^^^^^v^^^^>>v<<<><>^^vvvv<v<>vvv>^>v^^^>vv>>><^>^^<<v^<>>><^^<^^v><>>^<^>>v<v>^^vv^^v<<v^v>^<>v^<^>><^<v>v^<^<>><^^<^^^>^^<^><><^>v><v^vv^<^>>>^>v^^^<><^v><>^<>^v^><^>^^>>>^<vv<<^><>><<^<v>v>vv<<vv^^v>>^^^v^^>^>^^<<<^<><vv>v<v<><<><<^<v^>>^^>>^^vvv<v^<><vv>^<^><v^vvv<<<<v><>^>^<><v^v<^^vv<<>>^^>><<<><^>><<>^>>^<v<<vvv><<v^^v^^><<<vv^v^>^<v<><><>v>^^v>>v^<v>^<<^<vv<v<>>vv<<v^^><v<v>^^v",
//    ">>vvv<v<^v<<v><>^vvv^>^^v<<v<v>v><>^<v<<>v<<<v^^^<^<v^>^<><v>^v>^v>vvv<v^><v<^<^vv^<^^<<<^^<><<v^<v<><>v>v><>^^v><>><^><^^^><<>>vv^v>vv><>v^<<^<>^v^^>vvv<^>v>^v>^v><vv^<^v>v^v<^v<<^><<<>^vvv>>^v<<v>^v>vv<^^>>^vvv><v<><vvv>^>v<^^^^^vv>^<<^<<>^<<<>v>v^<^v>^>v^<<<>><^><>vv^vv^<<vv>^v^>v<<<v>><<^>vvvv^<^>><vvv<^v<v<^^v^v^v><^v>v>v<><><<>vv^^v>><v<><>v<^v>^<>vvv>^>v><<v<^>v>v>>^><^^><<<vv<v^v^vv>><vv<<^^v^vvv<^v>v><><>>v>><><<^>><v<^^^v^>vv><>^^<<^>v>><v^>^><><><^v^v^<<vv><^<vv^vv^^<^^^v>>v^^^>vvvv^<>vvv<<^<^v^^<^v>vv>>^>v^><<<>>^vv<vv<>><v>>v^<v<>v>^vv<>>vv>>v^<^<v<<vvv^^^v<vvv>><<>>^v<<>^v<>v>>><<v<<v<<<>vv>^v<<>>v<<vvv<v>v^>>v<^<>^^v><^^>^vvvv<^^v<^><^^<>v^>>vv>^^^>v>^^>v^>v<<>^<v^>>v^vv<vvv>v<vv>vv<>v><<^^>vvv>>>>v^v>>><^^^v<v^><<^><>><<><^><>><^>^^vv^^>v^><v>v>>vv^>^>^<vv^<v>^<<<^^<>v<<v^><<>v<v^><v^><vv^^>vv^v^^><v^v^<<>v<>^vv^v<<v<>v^v>v^v^>vv><><>>>vvv>v>><>vv<^^<<>v>v>v^v<^<v<>>^v<>v><>><v<<^>^><<<^v>v^vv<vvv<v>^>>>^<vvvv><vv^<^vv<<^>^^v<^>^>v<<^vv<<<>^v>v<><>>^^<<v^><^^>><>v>^^v<<",
//    "<<><^v^<vvv>^>><^^><>^^^<>>^>^>><v^vv^>^^v<>vv>><>v>v>v<^>><>v<v<v^^<<vv>^>v>>vv>>>v^<>^>v^<<>^^^vv<<^vvv>^v<v^<^^vv><v<<<^^<v^>^v<^>^v^<>v<^<^<vvv<v<^^>^v>>>v^^^^>vvv^>>>^v^<>>^<<><vvv<>v>>^^>^<^>>v^v^>>vv^^vv<^<^v<<<v<^^^><<<^^<>v<<>>>^<^>v>^<>v>^^v^v^>>^<>>^<vv<<^<<<<^^vv^v>>^vv>v^^<<<vv^><v^^><<^<>^v<^^>v>><vv^^v>>v^><^v^<^<<v>^><>v^vv^<<<^v^<^<^vvvv^^vv><^^v>>v^vv<<>>>vv><^>^v>>^v<<>>><>v>^>><v^><^><v^v<>>>>v><v^v>v^>vvvv^^^<>^>vv^vv<<v><>>>v<v<v<<>v>^v<>v>^v<^><>>v^vvv<>v<v^>vv<vv^^>><<<<^v^^v^v<>^<^<^<v<^v^><^>v<^>^<^<<<^^>v<^<^v<^v^<v>^<>v<v>^v<^v^>>^><v^^v^v><vv>v<<v<vv^^^<<<>v>^<<<v>v<v<<vv^v^vv>>><>v>v^^<><v><>^>^^^<>^^>^^>>><^<v>^>^>^^^<>v<vv<^^v^^^<^<<^>>>^^^><><v^v^<<v^<><^<v^^v>>^v^<^^>^>>v^><v<^v^>^>>^vv^<v^>^<>^>^v>><>>vvvvv<^<<v^<>v<><><<>^v>>v>>>>^>^<^vv>><<><<><<v<^^<<v^>^<<><v<<<^><^v<vv>^v>^<<v^vv<vv><<v<^vv^v<<^<>^^^v>>v<^^>v<^><<^v>>>><v<<>><>v^>v<v^^v^<><^<^><v>^<>^^>^^^<^>^v^^v>v<>><<v<<v^^v<><^^vvv<<<v<><><v<v>v^^<^v<vv>^<><v<v>^>^vv<>v<<^<>^<v<vv<<<^<<><v<^v",
//    "v^><v>>v><v>^>v><<>v^^>v><^^><^>>^vv><vvv^>v^<^^<>^<<>>>^^>^>><><<>vv<<^<<>v><>>v>^v>>^>>v<v^v<v^^v><<><>v<v>^<^<v^v^>>v^<vv^<^<vv^^><^^^v>vv>>^vvvv^^<<vvv^>^^>vv>^>^^^^^^^^v<v<>>^<v<><^>^vv>vv<<<<v>vvv^<v^v<v>>><<v<<^>>^v>vvv<v>>v<^^v>>v>v>vvv>v<><<<<<<^<^v<^>><><<<>v^>>v<<<^<^><v<<>>>v>v<>^^>>v>^><v<><<<v><>^vv<^^>>^vv^>^v^^^vvv^<<^<^<<v<^v<v<v><>>>^<v>^>>v^vv<>><>v<>><<<<>><v^>v^<^<>v^<v^^^>>^>^v<^^<<>^<^vv>v<<v<v><^^^v>vv<>><>v<v<v^<<v^<vv<<v^^<^vvvv^<>^^<^^<>v<v>^>>^v><<v<^v<v^v>^^>^v^<<<^>vv>>^<v^<<<<^<^^>v^<^><>^v^<><^v>><><<^v<^>v^v<><v^^^<v>v>><v<<^^vvv^><^>>>^<^^^v^><^>>vvv^>^<>v<^^<<<<^^^<<<<^>v<>v<<v^vv<^>>vv<v^<^^v<vvv^vv<v<^vv<v<<v^vv>>v>v<>>>>v^^<^^v><v<>v>vv^v>vv^<v<v<v^v<<>>v^v><^v<^vv^^<>^v^v<v^<<v^<>^^v>><>^v>^<>>v>v>v>^^v>v^>>v^v>^v^>vv^<<^^><^^>>^^vvvv>^v>>^^<<^v><^<^^><>vvv>>^^>^<<^^>vv>><<^<<^v>^>^>^<><>>vvvv^>v<<>>v^v>v^v^<<<<v<<^^>^<v<^^v<>v<^<<^^v^>^<^>v^^^^v^<v^>v^>>>vv^vv<<v<<^v^v^vv^<^<>^<vv^^><^<<<^>^<^^^v<vv<v^^>>v^>^>^<<^>v<>^^^>v^^vv<v<v<vv^>>vv>^^>>>v>",
//    "v<<v>^<>>v>v<><^^v^^<^<>><<><<v<^^>><>^>v<>v^v<v>><^><>^^>v<<^><><v>v<^^>^>^>>vv<<<^^><v<v^<v^><v>v>^^^<^<v>>>^v^>>>><<>^><>>v^v^v>>^^v<>v>>v><>>vv<vv^<^>><<^v>>^^v^<^v^>^<^^<<v^<v>vv^^^<<<>^<^>^^^^>v<<<<^<><<^<^>^v<^v<^v<v>^>^^><v>>^^><>vv^^^<>v<v><<<^vv>vv<<^>>^>><<^v<>^>>^>^<>^>vvv<v<vv<v^<^>vv>v^v>>>v<^<>v^<v>^v^<v^<vvvv>>vv>>^>><>^^><^>^>><^^<>^<<v><<vv<^<>^^^><^^<<>>><^v^>><>v^^<vvv<>vv><^^>><>>v<v^vv><v^>>vv>^><^v<v<<^^^>^<^<v><>v>^<><v^^^^^vvv<>^<<><vv<^v^^>vvv><<^v>^^>^v^vv<^<<><vv^><^^v>^>v^v^vvv>^vv^<^^v>v<vv>^<v^v<^^v^^<>^<vv<vv><<vvv<<<^<>^vvvv^v<^>^<<>v<v>vv<^<vv^^^<^v^v<<v<<v<vv>v^<<v^<^<<>>v^v<^^v<><<<<v<^<<>v<vv<><^v>^v<v^>>v<v^>>v><<<v<v>><>^vv^>>^>v>><>>vvvv<<v^^vv^v>^<^v^<vv<^<>^<^<<^<>>v^><v>v<>><^<>^<>>>v>>vv^^<<^^vv><v><^v<>v<^^v<<^v>^^^v>v^>v>^^^v^<v>>vv>>>^^<v<v^^<^<^^^vv^<^^>^^^><v^^^><^v>^v^>>>^>>v<<><>v^<<<^^v^v<>vv<^v>><>^<>^^<>>><^<>>v^v<<>v<>^v<<<v^v<>vv<<<v<v<><^v>v<<>^^>v>><>>>^><v^v^>v<v>^<^<vv^<<>>>^^^v^<v^v<^^v^vv<><<^v>^^^vvv^<>v>>^^v<^><^v>>v^>v^^v",
//    "<^>vvv^^v^^^vvv>><>^^<v>^^v^<<^v>^vvv^^<<>><>>v^v<^<<^^>^v<>^vv>>>v<^<<^^v^v>^>^<><vv<>>v<>^<^v<^<^<>>v>><^^^^v>>vv<^^v^<>^^><>vvv^<v>v<>>^<vv<v<v^>v>>>^v>^^<vv^<<^>^><vv^v<^<>vv<<^<v^>vv<>^v^v>^^^<>>v<v<^>vvvv^>^^v<>^<<><v<>^<^^<^<^><v^^>v<^v>v^>>v<>vv^<v>^^^><vv^v^v<<v<>^^v>v>^^v>v><v>vv<vv^><v<v^^^><^<v<^^^^^>v^><>v>^v>v^<v<>>v>><^<<^>^^^^>>v>>>^><<>v<^^<<><><>>>>^vv<v<>vv^<<<^>>vv>^<vv><^>><<<^>v<v<^^<^>>v>v>^vv<^>v>>^vv<^>>v^<<<<<^><vvv>^^<>>><^^<<<<><>v^^v^^^>^<><v>><vvv<^><<^<v>^^>v>>^>>>v><^v^vv^>^<v^<^><v<>><^vv^><>>^>vvvv<<v>>>>^^vv^<<vv^v^<>>^v<>^v^>v^><>><<^^<<v<>^^<<^vv>v<<>>>>>><><^^^v<<<<v^<^><>v><vv^^v>v^v>>v<vv>v^v<<v><<^v<^v><>^v^^<>v>v<^^^><^><^vv^^>vv>>^><v><<<<>>^vvvvvv>^^vvvv^v><^>^>v>^>^>^v<^^<^>v>v<v^v><<<v^>^<<<>^^^<v>^>^<^>><^<<vv>^^v><<^^<><><^>v^v^>v^><>^<^<><<<<v<<><v^>^^v^^<<>^v<vv>>>^^<v^^v^><v>vv><>^v^^v^>><v>^^v<^<^><^v>^><v^vv>^^>^>v<<^v<^>v^>^^<v>^>v^^^>^v^v<>^^>^^>v<v<^<^<v^<v<v><v>^<^v>^^^>>><<v<^v^^v^>>v<v^^^^>^^<^<<<v^<v^^^v^v>>^<vv>vv^>>>v>><<<^v",
//    ">v><<v^<<^v^<<vv><>>><v<>>><<>v>^^>^^<v>>>v><><<^<^v>v^vv^v<vv>>vvv^v<<<<v<<<<<^><>^><^v^^<v^>vvv<>^vv<^^^>><^^v^<<<<^^<v>><vv<>^<v>^^v>vv>><<>^>v<>^vv<^^<<<>>^<^>>^<^><vvvv>v^v^<v^^v^<>^<>^v^^v^^<><v<^<>>vvv^v^v>^v^>v>^>><^>^<>v<v<v<>^^<>^vv><<<>^<vv<<vv^<^^v>>^<>v>^^<<><><v<>>v<<v^vv^<>v>>v^<v^<^^<v<^<^<^v<>v^<>vv>^>>><<><^vv<><<v<v>v<^v<^^vv<vv^>^<><^>^v>^>>^>^><<^v^^>v<<^^>v>v>^<>>v<v^><v<^v<v><><<^^vv>>>v^<^v^^>><^>>>><><^^<>^v>^<^><^^^^<>^v^^v>v^>>v>v>^^^v^><>vv<v^^<>>^^>>v<>^^>v<>><><v<>v<^^>^v^><<><<v><^^>v<vvv^^v<^<>><<vv>^>><^vvv>v>>^<v^><<^^>>>><<>v^^>>>>>v><><^^<v<v^v<<v>><>^><vv^^>>>>v^^>^^vvv>><>>^^<>>v^^>vvv<v>>>v^^^<<vv>^<^^<><v^v>vv<<>v^<><<>>^>^^v<v>>><^>vv<^vv>>v><<v^<v<><v^^vv<^<vv<>v<><vv><v>>v<>><^>>v>vv>^vv<^^^><>v^>^v<vv<>^>^vv^>>^><v>v^><^<^>^v^>vvv<<>>vvv<>><>>v^v><^<v>v^<^<<v^v<^^^^^^<vv<>>^>>v><^^<^><>>^^^>v^<>>v><<>>v>^>v<vv>v<<v^vv^v^vv>v>v^^v^^v<^^^<><^v>^v><v<<^^>>>^<v<^<><>^^^^^v^>vv<>>^^^<vv<>v^>^v^>>>vv<v>^>^<>^v^>>vv^>^v<<<^>v><^<<>>^>v>>v>^<v><^v>>^",
//)