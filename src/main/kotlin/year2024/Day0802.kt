package year2024

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    var parsed = realDaata08.map { it.split("").filter { it.isNotEmpty() }.toMutableList() }.toMutableList()
//    println(parsed)
//    println(parsed.map { it.joinToString("") })
//    println(parsed.map { it.joinToString("") }.joinToString(""))
//    println(parsed.map { it.joinToString("") }.joinToString("").split(""))
//    println(parsed.map { it.joinToString("") }.joinToString("").split("").filter { it.isNotEmpty() })
    val antiCoordinates = arrayListOf<Pair<Int, Int>>()
    val uniquefrequencies = parsed.map { it.joinToString("") }.joinToString("").split("")
        .filter { it.isNotEmpty() && it != "." && it != "#" }.toSet()
    println(uniquefrequencies)
    uniquefrequencies.forEach { frequency ->
        val coordinatePairs = parsed
//                .filter { it.contains(frequency) }
            .mapIndexed { indexY, line ->
                line.mapIndexed { indexX, s ->
                    Pair<Int, Int>(
                        if (s == frequency) indexY else -1,
                        if (s == frequency) indexX else -1
                    )
                }

            }.flatten().filter { it.first != -1 }.filter { it.second != -1 }
        println(coordinatePairs)
//    }
//                .let{ println(it) }

//        println("$frequency - $coordinatePairs")
//
        for (i in 0..coordinatePairs.size - 1) {
            for (c in 1..coordinatePairs.size - 1) {
                val coord1 = coordinatePairs[i]
                val coord2 = coordinatePairs[c]
                if (coord1 == coord2) continue
                val a1DeltaY = coord1.first - coord2.first
                val a1DeltaX = coord1.second - coord2.second
                antiCoordinates.add(coord1)
                antiCoordinates.add(coord2)
                val a1 = (1..max(parsed.size, parsed[0].size)).map { multiplier ->
                    Pair(
                        if (a1DeltaY < 0) {
                            min(coord1.first, coord2.first) + a1DeltaY * multiplier
                        } else {
                            max(coord1.first, coord2.first) + a1DeltaY * multiplier
                        },
                        if (a1DeltaX < 0) {
                            min(coord1.second, coord2.second) + a1DeltaX * multiplier
                        } else {
                            max(coord1.second, coord2.second) + a1DeltaX * multiplier
                        }
                    )
                }
                antiCoordinates.addAll(a1)
                val a2 = (1..max(parsed.size, parsed[0].size)).map { multiplier ->
                    Pair(
                        if (a1DeltaY < 0) {
                            max(coord1.first, coord2.first) + abs(a1DeltaY) * multiplier
                        } else {
                            min(coord1.first, coord2.first) - a1DeltaY * multiplier
                        },
                        if (a1DeltaX < 0) {
                            max(coord1.second, coord2.second) + abs(a1DeltaX) * multiplier
                        } else {
                            min(coord1.second, coord2.second) - a1DeltaX * multiplier
                        }
                    )
                }
//                Any()
                antiCoordinates.addAll(a2)
//                println("$a1 $a2")
            }
        }
    }
    //        antiCoordinates.toSet().forEach { println(it) }
    println("---------------")
    println("My calculation")
    antiCoordinates
        .filterNot { it.first > parsed.size - 1 || it.second > parsed[0].size - 1 }
        .filterNot { it.first < 0 || it.second < 0 }
        .toSet()
        .sortedBy { it.second }
        .sortedBy { it.first }
        .forEach { println(it) }

    antiCoordinates
        .filterNot { it.first > parsed.size - 1 || it.second > parsed[0].size - 1 }
        .filterNot { it.first < 0 || it.second < 0 }
        .toSet()
        .sortedBy { it.second }
        .sortedBy { it.first }
        .size.let { println(it) }

//        println("-----------------")
//        println("Should be")
//        sampleoutput08.mapIndexed { indexY, s ->
//            s.mapIndexed { indexX, c ->
//                Pair(
//                    if (c.toString() == "#") indexY else -1,
//                    if (c.toString() == "#") indexX else -1
//                )
//            }
//        }
//            .flatten()
//            .filterNot { it.first < 0 || it.second < 0 }
//            .forEach { println(it) }
}

val sample08T = arrayListOf(
    "T....#....",
    "...T......",
    ".T....#...",
    ".........#",
    "..#.......",
    "..........",
    "...#......",
    "..........",
    "....#.....",
    "..........",
)

////(0, 6)
////(0, 11)
////(1, 3)
////(2, 4)
////(2, 10)
////(4, 9)
////(5, 1)
////(6, 3)
////(7, 7)
////(10, 10)
////
////+(0, 6)
////+(0, 11)
////+(1, 3)
////+(2, 4)
////+(2, 10)
////(3, 2)
////+(4, 9)
////(5, 1)
////(5, 6)
////(6, 3)
////(7, 0)
////+(7, 7)
////+(10, 10)
////(11, 10)
//
//val sampleData08 = arrayListOf(
//    "............",
//    "........0...",
//    ".....0......",
//    ".......0....",
//    "....0.......",
//    "......A.....",
//    "............",
//    "............",
//    "........A...",
//    ".........A..",
//    "............",
//    "............",
//)
//val sampleRes = 14
//val sampleoutput08 = arrayListOf(
//    "......#....#",
//    "...#....0...",
//    "....#0....#.",
//    "..#....0....",
//    "....0....#..",
//    ".#....#.....",//".#....A.....",
//    "...#........",
//    "#......#....",
//    "........A...",
//    ".........A..",
//    "..........#.",
//    "..........#.",
//)
//
//
//val sampleData0801 = arrayListOf(
//    "..........",
//    "..........",
//    "..........",
//    "....a.....",
//    "..........",
//    ".....a....",
//    "..........",
//    "..........",
//    "..........",
//    "..........",
//
//    )
//
//val sampleData0802 = arrayListOf(
//    "..........",
//    "...#......",
//    "#.........",
//    "....a.....",
//    "........a.",
//    ".....a....",
//    "..#.......",
//    "......#...",
//    "..........",
//    "..........",
//
//    )
//
//val realDaata08 = arrayListOf(
//    ".......b..........................................",
//    ".A....s...................3V...P....I.............",
//    "..s.........b........v.P..................z.......",
//    ".p..........A..uS.l...........................8...",
//    ".......B...i...................z...............8..",
//    ".............s..........E.......m........J........",
//    ".c............L...k.P........E....................",
//    "........b.....................a...................",
//    ".m.s.....V....l....u...S..........O.............8.",
//    "...B..............L..1Dm...S....u.z...............",
//    "......A......3...e....f..a........................",
//    "...................3.......I...............6.....8",
//    "....v..l...................5..........I...........",
//    "...v........k.0......5..P....z....................",
//    ".....A....................VJ...T.......D..........",
//    ".i..B..............L......W...........5...........",
//    "...........p.....k............u.D..IX.............",
//    "...c.......k..........VG.D.........W..............",
//    "......i.c.....G....W........5....jJ...............",
//    "...........l........................J....E........",
//    "..........E..G..t.................................",
//    "........i........h.................a....O........C",
//    "......K..t.L........m...W......0..j...........2...",
//    "................1.......j..0.......gC..M....2.....",
//    ".........K............3...........M........U...g..",
//    "..K.......p.....G.c...................q.....6.T...",
//    "..................1h...............M..C...6f......",
//    "............tj..h.......................f.........",
//    "....................Y.h............O.........6.C..",
//    "...........K....X....t......MfY..O....Q...........",
//    "..............p.......0.................g.........",
//    "..............n...............g...................",
//    "....a..................................wQq.H.2....",
//    ".o..................................v.....H..7.2..",
//    "........N1..........F.......q....Yw.........H.....",
//    "...n..d....H..F....................Y.......e......",
//    "...................d..............................",
//    "..y....N.....d..Z......9..........................",
//    ".N......T.n................497....................",
//    ".y....o....Z.........x.............T.............Q",
//    ".......y...X.........9..................7....Q....",
//    "...............F......................e...........",
//    ".n...............F.Z..........................e...",
//    "......................9U..............w...........",
//    "o.......y...................4.U...................",
//    "..x..............X.........w..4.............7.....",
//    ".......oZ...d.....................x...............",
//    ".............qU...................................",
//    "..................................................",
//    "..................................................",
//
//    )
//
//

