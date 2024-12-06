package year2024

fun main() {
//    var parsedInput = sampleInput06.map { it.split("").filter { it.isNotEmpty() }.toMutableList() }.toMutableList()
//    var parsedInput2 = sampleInput06.map { it.split("").filter { it.isNotEmpty() }.toMutableList() }.toMutableList()
    val parsedInput = realInput06.map { it.split("").filter { it.isNotEmpty() }.toMutableList() }.toMutableList()
    var parsedInput2 = realInput06.map { it.split("").filter { it.isNotEmpty() }.toMutableList() }.toMutableList()
//    parsedInput.forEach { println(it) }

    var y = parsedInput.indexOfFirst { it.contains("^") }
    var x = parsedInput.getOrNull(y)!!.indexOfFirst { it == "^" }

    var directionX = 0
    var directionY = -1

    var stuckRoutes = 0
    val stuckPoints = arrayListOf<Pair<Int, Int>>()

    for ((stuckY, stuckX) in day06Part01(parsedInput2)) {

//        println("$stuckY $stuckX - $stuckRoutes")
        y = parsedInput.indexOfFirst { it.contains("^") }
        x = parsedInput.getOrNull(y)!!.indexOfFirst { it == "^" }

        if (//(stuckX == x && stuckY == y) ||
            parsedInput[stuckY][stuckX] != "."
        ) {
            continue
        }

        directionX = 0
        directionY = -1
        var stuckEncounterMap = mutableMapOf<String, Int>()

        while (true) {
//                parsedInput[y][x] = "X"
            val one = parsedInput[y + directionY]
            val two = parsedInput[y + directionY][x + directionX]
//                if ( (y+directionY == stuckY && x+directionX == stuckX)) {
//                    stuckEncounter = stuckEncounter + 1
//                }
//                val encounter = stuckEncounter.getOrDefault("${y},${x},$directionY,$directionX",0)
//                stuckEncounter.put("${y},${x},$directionY,$directionX",encounter +1)

            if (parsedInput[y + directionY][x + directionX] == "#" ||
                (y + directionY == stuckY && x + directionX == stuckX)
            ) {
                val encounter =
                    stuckEncounterMap.getOrDefault("${y + directionY},${x + directionX},$directionY,$directionX", 0)
                stuckEncounterMap.put("${y + directionY},${x + directionX},$directionY,$directionX", encounter + 1)

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
            }
//                if ( (y+directionY == stuckY && x+directionX == stuckX)) {
//                    stuckEncounter = stuckEncounter + 1
//                }

            if (stuckEncounterMap.values.any { it > 50 }) {
//                    println("encounter $stuckY $stuckX")
                stuckRoutes = stuckRoutes + 1
                stuckPoints.add(Pair(stuckY, stuckX))
                break
            }
            y = y + directionY
            x = x + directionX

//                parsedInput.forEach { println(it) }
//                println("${y + directionY} - ${x + directionX} - $stuckEncounter - || - ${parsedInput.size-1} ${parsedInput[0].size-1}")

            if (x == 0 || y == 0 || x == parsedInput[0].size - 1 || y == parsedInput.size - 1) {
//                    parsedInput[y][x] = "X"
                break
            }

        }
    }


//    stuckPoints.forEach {   point ->
//        parsedInput[point.first][point.second] = "0"
//
//    }
//    parsedInput.forEach { println(it) }


    println("stuck routes - $stuckRoutes")
    println("stuck points - ${stuckPoints.size}")
    println("stuck points - ${stuckPoints.toSet().size}")

//    parsedInput.map { it.filter { it == "X" }.size }.sum().let { println(it) }
}