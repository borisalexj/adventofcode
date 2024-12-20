package year2024

fun main() {
//        val bufferedReader: BufferedReader = File("input06ros.txt").bufferedReader()
//    val ros = bufferedReader.use { it.readLines() }
//    val parsedInput = ros.map { it.split("").filter { it.isNotEmpty()}.toMutableList() }.toMutableList()
//    val parsedInput = sampleInput06.map { it.split("").filter { it.isNotEmpty()}.toMutableList() }.toMutableList()
    val parsedInput = realInput06.map { it.split("").filter { it.isNotEmpty() }.toMutableList() }.toMutableList()
//    parsedInput.forEach { println(it) }

    day06Part01(parsedInput).size.let { println(it) }

}

fun day06Part01(parsedInput: MutableList<MutableList<String>>): Set<Pair<Int, Int>> {
    var y = parsedInput.indexOfFirst { it.contains("^") }
    var x = parsedInput.getOrNull(y)!!.indexOfFirst { it == "^" }

    var directionX = 0
    var directionY = -1
    var visitedPositions = arrayListOf<Pair<Int, Int>>()

    while (true) {
        parsedInput[y][x] = "X"
//        println("--------------------------")
//        parsedInput.forEach { println(it) }
        val newY = y + directionY
        val newX = x + directionX
        if (parsedInput[newY][newX] == "#") {
            if (directionY == -1) {
                directionY = 0
                directionX = +1
            } else if (directionX == +1) {
                directionX = 0
                directionY = +1
            } else if (directionY == +1) {
                directionY = 0
                directionX = -1
            } else if (directionX == -1) {
                directionX = 0
                directionY = -1
            } else {
                throw IllegalStateException()
            }
//            if (parsedInput[newY+directionY][newX+directionX] == "#") {
//                        if (directionY == -1) {
//                            directionY = 0
//                            directionX = +1
//                        } else if (directionX == +1) {
//                            directionX = 0
//                            directionY = +1
//                        } else if (directionY == +1) {
//                            directionY = 0
//                            directionX = -1
//                        } else if (directionX == -1) {
//                            directionX = 0
//                            directionY = -1
//                        } else {
//                            throw IllegalStateException()
//                        }
//                    }
        }


        y = y + directionY
        x = x + directionX
        visitedPositions.add(Pair(y, x))
//        parsedInput.forEach { println(it) }
//        println("${y + directionY} - ${x + directionX}")

        if (x == 0 || y == 0 || x + directionX > parsedInput[0].size - 1 || y + directionY > parsedInput.size - 1) {
            parsedInput[y][x] = "X"
            break
        }
//        println("-------------")
//        parsedInput.forEach { println(it) }
    }

    parsedInput.map { it.filter { it == "X" }.size }.sum().let { println("part1 - $it") }
    return visitedPositions.toSet()
}

val sampleInput06 = arrayListOf(
    "....#.....",
    ".........#",
    "..........",
    "..#.......",
    ".......#..",
    "..........",
    ".#..^.....",
    "........#.",
    "#.........",
    "......#...",
)


val realInput06 = arrayListOf(
    ".........#...#................................#.........#............#.....#....#.....................................#...........",
    "............................................................................................#...............#.......#.............",
    "....#.......#......................................#.........................#..#....#....#.........#.............................",
    "............##..#............#........................#........#........#.......#...........................#................#....",
    "..................................#.............#....#..........................#................#..#....##........#..............",
    "..................##.#...#....#...................##.....#.................#......##.....................##............#..........",
    "..........#..................................#....................................................................................",
    "......#...........#.......................................#........#........................#........#............................",
    "..................#................................#........................#.............................#.......................",
    ".............#............................................................................................##.................#....",
    "........#..............................................................................................#..............#.#.......#.",
    "..#...........#..............#..............#..#.....#.......#......#.......................#....#...................##..........#",
    "..............##................................#..............##....................#...#........................................",
    "......................#.................#.......#.......##.##....................................#................................",
    "...........#...#.#..................#...#.....................................................................#...................",
    ".....#....#...#..............................................................................................#.......#............",
    "...............................#...........#.#.............#.........#.....................................#.#....................",
    "........#...........#.......#............................#.....#.............................#.........................#..........",
    "...................................................#...........#.....#..#....#.............#.....#..#..#.............#............",
    "..............#.........#..#..........#.#.......................#...................................#.............................",
    "..#.............#..#..#.#...........#...................................................#...........................#...........#.",
    "........##....##..........................................................................#.......................................",
    ".......................................................#.......#.............................#...................#........#.......",
    "#.....#.#......#...............................................................#..........#........#..............................",
    ".#.#............#..............#..........................#.#.....................................................#...............",
    ".................#...#........#....#.....#...#...................................#....#.................#..........#...#.#...#....",
    "...#..##........................#..#..................................................................................#.......#...",
    ".................#...#.#..................................................#..#........#.................#...................#.....",
    ".......................#.......#....................#......................................#...............................#......",
    "...............................................................................................................................#..",
    "...........##............................#....................#...................................................................",
    "..#........................................#..#.............................#............................#........................",
    "..#.#....#..................................#...................................................................................#.",
    ".................#...#...............#..........#..............................#...#..............................#.#..#..........",
    "............#..........#..........#...................................#.....#....................#................##.............#",
    "....#...........#.......#......................................#........................................#........#......#...#.....",
    ".............#.....#...................#....#.....................................................................................",
    ".....................#.................................#.#...................#.......#.#...............#..........................",
    "........#..............#.......#.............#...........................................................#........#..............#",
    "............#............................................#.......##................#..............................................",
    "...............................#..................................................................................................",
    "#....................#......#.......................#..........#.........................#................#...............#..#...#",
    "..................................................#.....#...........#...#..#......#...........................#...................",
    "............#......................#.....#.........#....................................................................#....#....",
    ".......#...................#...#...........................#.......#......#......................................#.....#..........",
    ".................................................#.............#....................................#.............................",
    "......#.................#.............................................................................#..##.......................",
    "....#..............................................#..............#..........#.......#....#............................#..........",
    "........#....#............#....#.................#........................................................#.....#.................",
    "..#......................#.......................................................#................................................",
    ".#.....................#..........................................................#.............................#.................",
    ".............#..............#.#.....#.........................................#...............................#.........#.........",
    ".............................................................................#............................#....#...#..............",
    "#.#.#...................................................................#...........................................#.........#...",
    "..................................................#.#............#..#...#..............#.....#....................................",
    "......#..................................#............................##.........................................#................",
    "..#....................................#............#.........#.........#...........#.....#..............................#........",
    "......................................#.............#..................#.................#..........................##............",
    "...............#.#........#.......................#..........#...............................#.............................#.#....",
    ".................#....................................................................................#...........................",
    "#...............................................#.#........#...#...................#............#.................#......#.#......",
    "................#........................#......#.............................................#.#..............#....#.............",
    ".......#..........#..........................#......#.............................................................................",
    "....#.......#.................................................#..............#...........#.....#............#............#........",
    ".....................#...................................#...............................................................#........",
    "#..........................#..#...........................................#...............#............#............#.............",
    ".............................#...................................#...#.......................................#......#.............",
    "............#........#.................................................#.................#...................#....................",
    ".........................................#................#.....................................#.................................",
    "...........................................................................................#.....................#................",
    ".....................#.........#..#.#......................................................#........#.............................",
    "..#......#.........#.#...................................................#......................#.................................",
    ".#...........#.........................................................................................#.....................#....",
    "..........#....##.#..#..........#........#.#.................................#.....................#..............................",
    ".....#........#.......#....#...........#...................#...............#..#....#..............................#..............#",
    ".......#..............................#............#.................................................#........#................#..",
    "...............................#..............................................#...................................................",
    "#..............................................................#.........................................................#........",
    ".......#....................................................#..............................#..................#..........##.......",
    ".......................................#...............................................^.............#............................",
    "..#...........#.........#............................#...................................#.#.......###.....#......................",
    "........#............................................................................................................#............",
    "...#...#...........#.................................................................#...#.......#........#.......................",
    "...............#.....................................#..........#.......#.#.....#.#..................#............................",
    ".....#..#..............#.#.......................#.................................................................#..............",
    "#.....#.........................................#..##....#..........................#...................#..#......#........#......",
    "...................##................#..#......................................#.....#............................................",
    "....#...............................................................................................#...........................#.",
    "....#..........#....#........................#....................................................................................",
    "..................#.....................................................#.....................................#...................",
    "...........................#......#.......................................................................#..............#........",
    "...............#....................#...#...#.......#................#.......#.......#............................................",
    "#.....##......#.............................................#...#.................................#.................#....#..##..#.",
    "..........#.......................................#..................................#...#....#......................#............",
    "......#................#.#........#....#......##.........#........................................#...............................",
    "......#...#............#...#.........................................................#....#.....#..............#..................",
    ".........................#...............#........#...#......#....................................................#..#.#..........",
    "...##............#........#.#.........................#.............................#............#..............#.................",
    ".......#.........#..............................#...................................#.......................#.#...................",
    "#..........#.............#..............................................#.....................................................#...",
    "........#..#...#.....................................#............................................................................",
    "..................#...............................#..........................................#.............#..............#..#....",
    "....#.#..#...#............................#..............#...#..........#......#....#.....................#..........#......#.....",
    "................#..............#........................#......#..............#.......................##..........................",
    "..........#.....#.............................................#.......#...........................................#...............",
    ".........#....#........#............#...............#.............................................................................",
    "...........#..#..........#..............#..........#......#.......................................................................",
    "..........#....................#........................................#.....#..................#..#.#..#...#.....#..............",
    "........#...........#...#..................##.........#......................#...#............#......................#............",
    "...................................................#........................#.....#.............#.................................",
    "........#................#..........................#.#...#......#........................................#.......................",
    ".............#.........................................##....................#...................#.#..................#...........",
    "..................#.............................................#...............................................................#.",
    ".....#............#................#..............................#...##....#...#.................................................",
    "#.......................###........#..............#.....#.............#...............#.............#......................#......",
    "...........#.............................#........................#.........................................................#.....",
    "............#.....#.....#...#....#........#...............................#.....................#..................#....#.........",
    "...............#.#..................................#.................#.#......#...........#...#................#.................",
    ".....#............................................#.....#...............#.....#.............#.......#..........#..................",
    ".....................................#................................................#.........#..#..............................",
    ".....................#...............................................#.#...........#........#.....................................",
    ".............................................#....................#.............#......................#..........................",
    "......................................#.........#.........................##........#................#...............#............",
    "........................#.....#.....................................#....#........#......#..............#.......##...........#.#..",
    "....................#........................................#.........#.........#......#....#............#...........#...........",
    "...........##......#...........#................#.......##.....#........#.........#...........................................#...",
    "...................#...#....#.............#..#......................................................#............#................",
    "....#.........................#....#..................#..............#.............#...................................#..........",
    "..................................#.......#.....................#.................................................................",
    ".............................................#......#.......................................................#.....................",

    )