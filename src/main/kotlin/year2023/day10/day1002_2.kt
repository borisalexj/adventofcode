package year2023.day10

import java.awt.Point
import kotlin.IllegalStateException

//    var startPoint = Pair<Int, Int> (-1, -1)
fun main(args: Array<String>) {
//    val input = sampleEnclosed2.map { it.split("").filter { it.isNotEmpty() }.toMutableList() }.toMutableList()
    val input = realinput10.map { it.split("").filter { it.isNotEmpty() }.toMutableList() }.toMutableList()
    println(input)
    val y = input.indexOfFirst {
        it.contains("S")
    }
    val x = input[y].indexOfFirst{

    it == "S"
    }
    println(x)
    println(y)
    startPoint = Pair(x,y)

    input[y][x] = "F"
    println(input)
    var nextX = x
    var nextY = y
    println(tempRouteLength)
//    println(iterations)
input.forEach { println(it) }
    var old2x = -1
    var old2y = -1
    var oldx = -1
    var oldy = -1

    val polygon = java.awt.Polygon()


    while (true) {
        var newCoord = try {
            getDirections2(input, old2x, old2y, oldx, oldy, nextX, nextY, tempRouteLength)
        } catch (e: IllegalStateException) {
            println(e)
            println("result (part 1) - " + ((tempRouteLength +1)/2))
            input[nextY][nextX] = "*"
            polygon.addPoint(nextX, nextY)
            break
        }
//        println("$x $y - $oldx $oldy - $old2x $old2y")
        old2x = oldx
        old2y = oldy
        oldx = nextX
        oldy = nextY
        nextX = newCoord.first
        nextY = newCoord.second
        input[oldy][oldx] = "*"
        polygon.addPoint(oldx, oldy)
//        println("$x $y - $oldx $oldy - $old2x $old2y")
        tempRouteLength = tempRouteLength +1
//        if (tempRouteLength >3) break
    }
//    input.forEach { println(it) }

    var enclosed = 0


//    for (tempy in 0..(input.size-1)) {
//        for (tempx in 0..(input[0].size-1)) {
//            if (tempx == 0 || tempy ==0 || tempx == (input[0].size-1) || tempy == (input.size-1)) {
//                if (input[tempy][tempx] != "*")
//                    input[tempy][tempx] = "X"
//            } else if (input[tempy][tempx] != "*" &&
//                (input.getOrNull(tempy-1)?.getOrNull(tempx-1) == "X"
//                || input.getOrNull(tempy-1)?.getOrNull(tempx) == "X"
//                || input.getOrNull(tempy-1)?.getOrNull(tempx+1) == "X"
//                || input.getOrNull(tempy)?.getOrNull(tempx-1) == "X"
//                || input.getOrNull(tempy)?.getOrNull(tempx+1) == "X"
//                || input.getOrNull(tempy+1)?.getOrNull(tempx-1) == "X"
//                || input.getOrNull(tempy+1)?.getOrNull(tempx) == "X"
//                || input.getOrNull(tempy+1)?.getOrNull(tempx+1) == "X")
//                ) {
//                    input[tempy][tempx] = "X"
//                }
//
//        }
//    }
//      for (tempy in (input.size-1) downTo 0) {
//        for (tempx in (input[0].size-1) downTo 0) {
//            if (tempx == 0 || tempy ==0 || tempx == (input[0].size-1) || tempy == (input.size-1)) {
//                if (input[tempy][tempx] != "*")
//                    input[tempy][tempx] = "X"
//            } else if (input[tempy][tempx] != "*" &&
//                (input.getOrNull(tempy-1)?.getOrNull(tempx-1) == "X"
//                || input.getOrNull(tempy-1)?.getOrNull(tempx) == "X"
//                || input.getOrNull(tempy-1)?.getOrNull(tempx+1) == "X"
//                || input.getOrNull(tempy)?.getOrNull(tempx-1) == "X"
//                || input.getOrNull(tempy)?.getOrNull(tempx+1) == "X"
//                || input.getOrNull(tempy+1)?.getOrNull(tempx-1) == "X"
//                || input.getOrNull(tempy+1)?.getOrNull(tempx) == "X"
//                || input.getOrNull(tempy+1)?.getOrNull(tempx+1) == "X")
//                ) {
//                    input[tempy][tempx] = "X"
//                }
//
//        }
//    }
//    println("================================")
//    input.forEach { println(it) }
    println("================================")
    // this doesnt work
//    for (tempy in 0..(input.size-1)) {
//        for (tempx in 0..(input[0].size-1)) {
//                if (input[tempy][tempx] != "X" && input[tempy][tempx] != "*" ){
//                    enclosed = enclosed +1
//                    input[tempy][tempx] = "I"}
//            }
//            }


//    for (tempy in 0..(input.size-1)) {
//            for (tempx in 0..(input[0].size-1)) {
//                    if (input[tempy][tempx] == "*" ){
//                        polygon.addPoint(tempx, tempy)
//                    }
//            }
//
//    }
//    println("polygon points - ${polygon.npoints}")
//        for (tempy in 0..(input.size-1)) {
//                for (tempx in 0..(input[0].size-1)) {
//                        if (input[tempy][tempx] == "*" ){
//                            polygon.addPoint(tempx, tempy)
//                        }
//                }
//
//        }
        for (tempy in 0..(input.size-1)) {
                for (tempx in 0..(input[0].size-1)) {
                    val point = Point(tempx, tempy)
//                    polygon.
//                        if (input[tempy][tempx] == ".") {
//                            println("dot")
//                        }
                        if (polygon.contains(tempx, tempy) && input[tempy][tempx] != "*" ){
                            enclosed = enclosed +1
                        }
                }

        }

println(polygon)
    input.forEach { println(it) }
    println("enclosed (part 2 result) - $enclosed")

}
/*
part 1 - 7086
        7402 - bad
        317 - correct
*/

val sampleEnclosed2_ = arrayListOf(
".F----7F7F7F7F-7....",
".|F--7||||||||FJ....",
".||.FJ||||||||L7....",
"FJL7L7LJLJ||LJ.L-7..",
"L--J.L7...LJS7F-7L7.",
"....F-J..F7FJ|L7L7L7",
"....L7.F7||L7|.L7L7|",
".....|FJLJ|FJ|F7|.LJ",
"....FJL-7.||.||||...",
"....L---J.LJ.LJLJ...",
)
val sampleEnclosed_ = arrayListOf(
"FF7FSF7F7F7F7F7F---7",
"L|LJ||||||||||||F--J",
"FL-7LJLJ||||||LJL-77",
"F--JF--7||LJLJIF7FJ-",
"L---JF-JLJIIIIFJLJJ7",
"|F|F-JF---7IIIL7L|7|",
"|FFJF7L7F-JF7IIL---7",
"7-L-JL7||F7|L7F-7F7|",
"L.L7LFJ|||||FJL7||LJ",
"L7JLJL-JLJLJL--JLJ.L",
)

// the last number befo exception plus on and divided by 2 is the answer - 14171+1 = 7086
//    val result = 0
//    var tempRouteLength = 0
//    var iterations = 0

fun getDirections22(input: MutableList<MutableList<String>>,
 old2x: Int, old2y: Int, oldx:Int, oldy:Int, x:Int, y:Int, routeLength: Int) : Pair<Int, Int> {
//    if (x == old2x && y == old2y) return
    if (x == startPoint.first &&  y == startPoint.second && old2x != -1 && old2y != -1) {
        println("start - ${routeLength/2}")
        if (tempRouteLength < routeLength) {
                    tempRouteLength = routeLength
//                    println(tempRouteLength /2)
                }
                throw IllegalStateException("reachedStart")
    }

    iterations = iterations +1
//    val tempRouteLength = routeLength

val current = input[y][x]
//    if (current == "*") {
//        println("start - ${routeLength/2}")
//        if (tempRouteLength < routeLength) {
//                    tempRouteLength = routeLength
////                    println(tempRouteLength /2)
//                }
//                throw IllegalStateException("reached route")
//    }
//println("$current - $routeLength $y:$x $oldy:$oldx $old2y:$old2x:" )
    if (current == "F") {
        if (input[y+1][x] == "|" || input[y+1][x] == "L" || input[y+1][x] == "J")
            if (x != oldx || y+1 != oldy)
                return Pair(x, y+1)
//                getDirections(input, oldx, oldy,  x, y,x, y+1, routeLength +1)
        if (input[y][x+1] == "-" || input[y][x+1] == "7" || input[y][x+1] == "J" || input[x+1][y] == "F")
            if (x+1 != oldx || y != oldy)
                return Pair(x+1, y)
//                getDirections(input, oldx, oldy, x, y, x+1, y, routeLength +1)
    }
    else if (current == "-") {
        if (input[y][x+1] == "7" || input[y][x+1] == "J" || input[y][x+1] == "-" )
            if (x+1 != oldx || y != oldy)
                return Pair(x+1, y)
//                getDirections(input, oldx, oldy,  x, y,x+1, y, routeLength +1)
        if (input[y][x-1] == "F" || input[y][x-1] == "L" || input[y][x-1] == "-" )
            if (x-1 != oldx || y != oldy)
                return Pair(x-1, y)
//                getDirections(input, oldx, oldy,  x, y,x-1, y, routeLength +1)
    }
    else if (current == "|") {
//        println(input[y+1][x])
//        println(input[y-1][x])
        if (input[y+1][x] == "L" || input[y+1][x] == "J" || input[y+1][x] == "|" )
            if (x != oldx || y+1 != oldy)
                return Pair(x, y+1)
//                getDirections(input, oldx, oldy,  x, y,x, y+1, routeLength +1)
        if (input[y-1][x] == "F" || input[y-1][x] == "7" || input[y-1][x] == "|" )
            if (x != oldx || y-1 != oldy)
                return Pair(x, y-1)
//                getDirections(input, oldx, oldy,  x, y,x, y-1, routeLength +1)
    }
    else if (current == "L") {
        if (input[y-1][x] == "F" || input[y-1][x] == "7" || input[y-1][x] == "|" )
            if (x != oldx || y-1 != oldy)
                return Pair(x, y-1)
//                getDirections(input, oldx, oldy,  x, y,x, y-1, routeLength +1)
        if (input[y][x+1] == "J" || input[y][x+1] == "7" || input[y][x+1] == "-" )
            if (x+1 != oldx || y != oldy)
                return Pair(x+1, y)
//                getDirections(input, oldx, oldy,  x, y,x+1, y, routeLength +1)
    }
    else if (current == "7") {
        if (input[y+1][x] == "J" || input[y+1][x] == "L" || input[y+1][x] == "|" )
            if (x != oldx || y+1 != oldy)
                return Pair(x, y+1)
//            getDirections(input, oldx, oldy,  x, y,x, y+1, routeLength +1)
        if (input[y][x-1] == "F" || input[y][x-1] == "L" || input[y][x-1] == "-" )
            if (x-1 != oldx || y != oldy)
                return Pair(x-1, y)
//            getDirections(input, oldx, oldy,  x, y,x-1, y, routeLength +1)
    }
    else if (current == "J") {
//        println(input[y-1][x])
        if (input[y-1][x] == "F" || input[y-1][x] == "7" || input[y-1][x] == "|" )
            if (x != oldx || y-1 != oldy)
                            return Pair(x, y-1)
//            getDirections(input, oldx, oldy,  x, y,x, y-1, routeLength +1)
        if (input[y][x-1] == "F" || input[y][x-1] == "L" || input[y][x-1] == "-" )
            if (x-1 != oldx || y != oldy)
                            return Pair(x-1, y)
//                            getDirections(input, oldx, oldy,  x, y,x-1, y, routeLength +1)
    } else {
        if (tempRouteLength < routeLength) {
            tempRouteLength = routeLength
            println(tempRouteLength)
            throw IllegalStateException()
        }
    }
    throw IllegalStateException("no way")
    }

//val sampleInput102 = arrayListOf(
//"..F7.",
//".FJ|.",
//"SJ.L7",
//"|F--J",
//"LJ...",
//)
//
//val sampleInput10 = arrayListOf(
//".....",
//".S-7.",
//".|.|.",
//".L-J.",
//".....",
//)
//
//val realinput10 = arrayListOf(
//"F.777F-L7-LL7.F77FF7F77--7|7L-F7-7.FF---7F77.FFJ--FL77.|77L.|F7.F7FF7--7FFL-J--F|--F7FF77FL---F7.FF7..FF7JJ-LF7JJF-L7LJ.|.L-F.F.|.F-.FL-7L.F",
//"LF7L-77L7FL|7FL|7L777.77|FJJ..L|.|-FL-7FJ||7--LJL-7.|--777.FF7L-.77.F-|-|7...|.F||7|F-JF-F--.F.LFFJL7F7||J.F|||LL|JFL7|F77.L|.|.L-|F|--L7.FJ",
//"|L|F|7-L|7L||-|L7FJJJFL7-77|F7-L7JF7F7||FJL777|77FFF77|.|F7|.77JFL--7-|7FF.7-FJ-LF7|JJ||7L7JFFFF7L-7||||L7-FFJL-7|||L77.L|J.-7F..FJ|||FL|FF7",
//"L77F|7J-J|FFJ.L7L-||L-J|L7JJJ|||F-JLJLJLJF7L--7---FJL77-FL-F7L|-F7|7|L|-|LFJF||.JLF||FL7FFJ-FF-J|FFJLJLJFJ|LL7F-JJFJF||7..L7|-|F7|FLJJL.|F--",
//".|L|L|-F-LFJJ|-7JJ||-J|L.J.J--7FL----7F--J|F--J7||L7FJ-F7LFJ|-|-LF7|L--7|FF77F777LFL77|L--7.F|F7L7L----7L-7-FJL77.7JL-7-F7-|LL|7L|.7.|.F|-7J",
//"J-JJFL--7.-J.|FL|FL-J.|7|JJF|.LJF7-LFJL--7LJF-7F77L||F7|L7L7L-7F-||7JJLFLJ|777.JJ.J|F.JLL|77FLJL7L-----JF-JFJF-J-F7LFL|LJ7-|F-7FFJLJFFF-7L7.",
//"JJJ.|L7.L7.F-L||LL7|FJ-J|7.JF7JF||F7L7F--J.||FJ|L7FJ||||FJFJF-J-.||J|FF77.L-F-7|.7|7LF..|.77LLJLL------7L7FJFJ7F-JL7-7|J-J7|JLJLJ.||7F-.|J|.",
//"J.77J.--.F-JLLFJ|F77J.LJ-J.F|..L|LJL7||F7F7FJ|FJFJL7|||||FJFJ7|FFJ|JLFJJ-F|-L7|77FFJ|F77.7LJ7JFFF7F7F7.|FJ|FJF-JF--J|LF-7-LJ-||..FFLFJF|.L||",
//"L7L7JLJLFLF7FFJF--LJLF.L|--F.F7|L7F7LJLJLJ|L7|L7|F7||||||L7L-7F-JFJ7|J||.F7F7||F7F7F7||||.7J77.FJLJLJL-JL-J|7L7FJ7JLFJ|F.F7L7LJ-7F|.|FLJ-FL7",
//"|F.|..FF-J.J7|J|..LJ.LF.---F-JL7FLJL7F7F--J-||FJ||LJLJ|||.|F-JL7FJF7F7-|-|LJLJ||LJ|||||F7F7-F|7L-------7F-7L--JL-7.F7J|7FL-7|.F-7JJF77.|FL||",
//"|J.FF7F77FLJ|7-|J77-FJJ7|-FL-7FJFF7|LJ|L7JF7|LJFJL-7F7||L7|L7F-JL-J|||7L|L-7F-JL7FJ|||||LJ|F7F-77F-7F--JL7L-----7|FJL7J||..|F|-LL7.F.|L--LFJ",
//"-F7|L7LJ7F.FLF7JL|FFL7FFF7JJJ|L77|L7F7L7L7|||F7L7F-J|LJ|FJ|FJL-7F-7||L7.F7FJL77FJL-JLJ|L7FJ|LJFJ-L7|L-7F-J.F---7LJL-7L7JLJ-J7JFJLL7LFL-|JF||",
//".L-J-L|FF7|J|LJ-FF-7-F7JF7LF7|FJFJFJ|L-JFJ||LJ|FJL-7L--J|.|L7F7|L7LJL7|FJ|L-7L7L7F-7F-JFJ|LL-7|F|FJ|F-JL-7FJF--JF7F7|FJL||-.7-LF7|--7JJ.F77J",
//"F7J..FJ-LLJ-7FL7-7LF.F7.||FJLJ|.L7L7L--7|FJL7FJL7F7|F7F7|FJFJ||L7L7F7|||FJF7L7L-JL7LJF7L7|F7FJ|F7L7|L-7F-JL7L7F7||||||7F-J|JLL|.L.FFL.F-|||J",
//"-J|7FL7-F7L|JLF-J|7LFJL7||L--7L7JL7|F7FJLJF-JL7FJ||||LJLJL7L7|L-JFJ||||||FJL-JF---JJFJL7||||L7||L7||F-JL7F7|FJ|LJLJLJL7FLL-.F|L7--F7JL7.L-JJ",
//"L.FFF.L.|7.-7||.F|-F|F7LJL--7L7|F7|||||F--JF--JL-J||L7F--7L7|L--7|-|||LJ|L---7L77F7FJF-J||||FJ|L7|||L--7LJLJL7|F---7F-J7||LL-7LF77.|.FF|7JF-",
//"FL-JLJJ.F77LFJ7-7LFLLJL-7F--JFJLJLJLJLJL-7-L7F7F-7||FJL-7|FJL-7FJ|FJ|L-7L-7F7L7L7||L7|F7|||LJFJF|LJ|7F-JF---7LJ|F-7|L----7JJ-F-7J|7.JJ-J|.|.",
//"-7.|..-7.|F-JJF.F--JJF7L|||F7|F------7F--JF7LJLJFJLJ|F77||L7F-J|FJ|FJF7|F7||L-JFJ||FJ|||||L-7|F7L-7|FJF-JF7-L-7LJFJ|F7F--JJ|FJLF.J||-L|JJ|-F",
//"||.FL-F7F-L7-F-7||J.FJL-J|FJLJ|F-7F--JL-7F||F---JF-7||L7||FJL7FJL7|L7||||LJL--7|FJ|L7LJ|||.FJ|||F7|LJFJF-JL--7L--J-LJLJJL-L|7|FLL7FJ.F-7-|-J",
//"J-.FLF|7J.||.|L|JLF7L---7|L---JL7|L---7FJFJ||F-7FJFJLJFJ||L7FJL-7||FJ|||L7F---J||FJFJF7||L7L7|||||L7FJFJF-7F-JF7|F7.F-7.|-||||7.|LJFJ7LLJ.|J",
//"|..LJ||L7FJ|FLJF..||F---JL77F7F7||LF77|L-JFJLJFJ||L7F-JL||L|L--7|||L7|LJFJL---7|||FJFJLJL7L7|LJLJL-JL7|FJJLJ7FJL7||FJFJF7F7F7.LFFJ.77L7|F|-F",
//"|7FJFLJ.77.|FJ-LF-JLJF---7|FJ||LJL-J|FJF--JJF7L-JF-JL7F7|L7|F--J|LJFJ|F-JFF7F-JLJLJFJF-7FJFJL-7F7F-7FJ|L--7F-JF7LJLJFJ7|||||L7-7-7FJF-LL-|J|",
//"|F|.|7.|.L.F|.|.L7F7FJF7FJ||FJL7F--7|L7|F77FJ|F77L--7||||FJ||F-7L-7L-JL7F-J|L--7F-7L7L7|L7L7F-J|LJFJ|FJF7FJL-7|L-7F7|F7|LJLJFJ||-F|L--J..777",
//"F|JJJF7|F7FLF.FF|||LJFJ|L7|||F7||F-JL-JLJL7|FJ|L7JF-JLJ||L7|||FJF7L7F--JL-7L-7|LJLL7L7|L7|FJL-7L7|L7LJFJLJF7FJL-7LJLJ|||F---JF7.F77FLFJL|L-L",
//".|7F7L7-JJ7-JFFF7LJF7L7L7|LJLJ|LJL--7F-7F7LJL7L7L7L---7LJFJ||||FJ|FJL-7F7-L7FJF7.F7|FJ|FJ||.F7L-JF7L-7|F7FJ|L7F-J|F-7||||F---JL-JL-7LLJFJJF|",
//"FJFL7FF7JJF.LFL|L--J||L7|L---7|F7F-7|L7LJ|F--JFJFJFF--JF-JJ|||||FJL7F7LJ|F-J|FJL7|LJ|J|L-JL7|L---JL--JLJ|L7L7|L7F7|FJ|LJLJF-7F7F-7FJ7.F-J-FJ",
//"|F7||7LF--JJ--.L---7L--J|F7F-J||||FJL-JF7|L---JFJF7L-7FJF7FJ||LJ|F7||L--JL-7LJF-JL-7L7|F--7||F-------7F-JFJFJ|FJ||||FJF7F-JFLJ|L7LJ|L-LJJ.||",
//"J-J--|.L..|FLLF--7FL---7||||F7LJ||L--7FJLJF----JFJ|F7||F||L7|L-7||||L7F-7F7|F7L7F7LL7LJL-7LJLJF--7-F-J|7FJFJ.|L-J||LJFJ|L-7F-7L7|J.L||J|.-7-",
//"|-7|.L-.F7-JLFL-7L---7FJLJ|LJ|F-JL--7|L--7L--7F-JFJ|||L7||.||F-J||||FJL7LJ|LJ|FJ|L7FJF7F-JF7F7L-7L7|F7L7L7L7FJF-7LJF7L7L--JL7L7LJ77.F7FF||J|",
//"|JFJ7J-FJF--FF7-L---7|L--7|LFJ|F7F7FJL7F-JF--JL-7L-J|L7|||FJ|L-7||LJ|.|L7FJ|FJL7L7LJFJLJF7|||L7FJFJLJL7L7|FJ|FJFJF7|L7L----7|FJ7|FFFJ7-LJ|7J",
//"7.7LF-FJ7F-FF||F----J|.F7|L7L7||LJLJF-JL-7|LF--7L-7FJFJ|||L7L-7||L-7L7F7|L7FJF-JLL-7L7F-J|||L7LJFJ|FF7L7LJL-JL7L-J|||L-----J|L7F7.LFF7L|7.|J",
//"|7||L-J|-JFF-J|L----7L-J|L7L-J|L---7L7F--JL7L-7|F7|L7|FJ||L|F-J|L--JFJ|||FJL7L-7|F7|FJ|F7LJL-JF-JF7FJL-JF--7F7L--7LJF7F7F7F7|FJ|L7F|||.FJ--7",
//"7-F|..FJFLF|F7L7JF-7L-7FJJL--7|F7F7|FJL--7FJF7|||||FJ||F||FJL-7L---7|7||||F7|F-JFJLJL7LJL7F---JF-J|L---7L-7|||F-7L7FJLJLJLJLJL-JFJF7|L77JJL7",
//"L.|LF-J.|..LJL7L7L7L--J|FF7F7|LJLJ||L7F7FJL7|||LJ||L7|L-J|L7F-JF-7FJL7|LJ||LJ|F7L---7L7F7||F7F-JF-JF---JF-JLJLJ-|FJ|F----------7L7|||FJ--FL7",
//".|77J7FFL7F--7L7||L---7L7|LJ||F7F7LJFJ||L7FJ||L7FJ|FJL-7FJ7||F7L7|L7FJ|F-JL7FJ||FF77|FJ||||||L7FJF7L---7|.F7F7F7LJFJL--7F7F7.F7L-J|LJ||..|J|",
//"LLLJL-7JF-L-7L-J|F7F7JL7LJF7|LJLJL-7L7|L-JL7||FJL7|L7F-J|F7|||L7|L7||FJL7F7|L7||FJ|FJ|FJ||||L7|L7||F7F7|L7|||||L7FJF---J|LJL7||F7J|F7L-77JF7",
//"F|7L7-7JF7L|L--7||||L7LL-7|LJF-7F--J7LJ-F7|LJ||F-JL7|L7FJ||||L7||FJ|||F-J||L7||||FJ|FJ|L|||L7|L7||||LJLJ7LJLJ||FJ|FJ-F7FJF--J|LJL7||L7FJLFL|",
//"JL|J|.LF|7L-F--JLJLJFJF--J|F7L7|L-------JL7F-J|L7F7||FJ|FJ|||FJLJL7|LJ|F7||FJ||||L7|L7L7||L7||FJ|||L-7F---7F7LJL-JL-7|LJFJF7FJF--J|L7LJL-J7L",
//"|.|-FL7.|F7|L------7L7L7F7|||FJL--7F7F---7||F7L7|||||L7SL7LJ|L-7F-JL7FJ||||L7|||L7|L7|FJLJFJ|||FJ||F-JL--7|||F--7F-7LJF-JL|LJFJF-7|FJF77JJFJ",
//"L|--7.-J-F-------7LL7||LJ||||L---7|||L--7||LJL7|LJ|||FJ|FJF-JF-JL-7FJL7|LJL7|LJL7|L7||L--7L7|||L7||L-7F--J|||L-7|L7|F7L--7L7FJF|FJ||||L-77-.",
//"L7.-J-J7LL---7F-7L--JL---J||L----JLJ|F7FJ|L-7FJL-7||||LLJ-|F7L7F--JL-7|L7F-JL7-FJ|J|||F7FJFJ||L7LJ|F7|L7F7LJL--JL-JLJ|F-7L-JL--JL-JL-JF-JLL-",
//"LL-|.|L7.F|F-JL7L--7F7F--7|L---7F7F7LJLJFL7FJL7F-J|||L--7FJ||FJL-7F-7||FJL7F7L7L7|FJ||||L7L7|L7L7|||LJ|LJL7F---7F7LF7|L7|F-7F-------7FJ7L|7.",
//"|LJL-|-|F7-L---JF--J|||F-JL----J|LJL--7F-7LJF7|L7FJ||F-7|L7|LJ.F-JL7|||L7FJ|L7L7|||FJ||L7L7||-L7|FJL7F7F--J|F--J||FJ|L7||L7|L------7|L7F7F7-",
//"---J-LFJ|J|F--77L---JLJL-7F7F7F-JF--7FJ|FJF7|||FJ|FJ||FJ|FJL7F-JF7FJ|||FJL-J.|FJ||||7LJ7|FJ||F7LJL--J||L-7FJL---JLJFJ7LJ|FJL7F--7F-JL7LJLJ|J",
//"L7||7FJ-|-FJF7L----------J|LJ||F7L-7LJFJL7|LJ|LJ.|L7||L7||F7|L-7|||-LJ|||F--7|L7||||F---JL7LJ||F7F7F7|L-7LJF-7F7F-7L---7LJF-JL-7LJF--JF--7|7",
//"|||7-||7|-L-JL--7F7F7F7F7FJF7LJ|L7.L--JF7LJF7L7F7|FJLJJLJLJLJ.FJ|||F--JL7|F7|L7|||||L-7F7FJF-JLJLJLJLJF7|F7L7LJ|L7L---7L-7|F-7FJF7L--7L7|LJJ",
//"F7L7-F-7F-7F7JF7LJLJLJLJLJFJ|F7L7L-----JL--JL-J||LJ7F7F7.F7|F-JFJ|||F7F7LJ|LJLLJLJ||F-J||L7|F-7F-7F---J|LJL-JF7L7L---7L--JLJFJL-J|F7-L7L-7|J",
//"LLJL7L7LL7LJL-JL7J-F-----7L7LJL-JF-7F----------JL7F7||||FJL7L--JLLJLJLJ|F7L-----7FJ||F7||FJ||FJL7LJF7F-JF77F7||-|F7F7L-7F--7L----J||FF|F-J7J",
//"7J|.--|7.L-----7L7FJF---7|FJF7F7FJ|LJF---7F-7F7F7||||||||F-JF7F77F----7|||F-7F-7|L7|LJLJLJ7LJL--JF-J|L--JL-JLJL7LJLJL--JL7FJ-F7F7FJL-7|L-7JJ",
//"F-77JFLF7.LF7F7L7||FJFF-JLJFJLJLJF7F7L--7|L7||LJLJ|||||LJL--JLJL7L-7F7LJ|LJ7LJ.LJ-||F-----7LF7-F-JF7L--7|F-7F7FJF-7F7F--7||F7|||||F--JL--J7.",
//"JJF7FJLLF7-|LJL-J|LJF-JF7F-JF7F--J|||F7FJL-J|L---7||||L7F--7F---JFFJ||F7L---7|F7F7LJL----7L-JL-JF7|L7F7L-J-LJ|L-JFJ|||F-J|||||LJLJL-7FF7J-J7",
//".|JL7..FLJ-|F7F-7|F7L-7|||F-JLJF-7LJLJ|L7F-7L7F--J|LJ|FJL7FJL---7FJFJLJL7F-7L-JLJL7F-----JF----7|LJJLJL--7F-7L7F7L-JLJL7||||||F-----JFJ|J.|7",
//"FL7FF77L|.LLJLJ||LJL--J|||L----JFL---7|FJ|FJLLJ.F7L7FJL7FJL--7F-JL7|.F--JL7L---7F-JL---7F7|F---J|F7F7F7F7LJFJFJ|L7F---7L-JLJLJL------JFJ.7.7",
//"F|-FJ7J7LJ.F7|F7L7F-7F7|LJF----------JLJFJL7F--7||L|L7FJL---7LJF-7|L7L7F7FJF--7|L7F----J|||L7F7FJ|LJLJLJ|F7L7L-JFLJ7F7L--7F-7F-------7L-7-.|",
//"L7-|JJFJFFFJL-JL-JL7|||L7|L--7F-7F--7F7FJF7LJF-J|L7|FJL7F--7|F7|FJL-JLLJ|L-JF-J|FJL--7F-J|L7LJLJFJF----7LJL-JF-7F7F7||F7JLJFJL------7L--JJFJ",
//"FL..FFL-L-L--------JLJL-JF7F7||FLJFFJ|||FJL--JF7L7LJ|F7||F7LJ||||F--7F--JF-7L7FLJF---J|F-JFJ-F-7L-J7F7LL----7|FJ|LJLJLJL--7L7F--7F--JF-7.L|J",
//"LJ7-7JF7|7.F-------77F7JFJLJ|LJF--7L-JLJL7F7F7||FJF7LJLJLJL7||||||F-JL-7FJLL7|7||L--7FJL--JF7L7L-77FJL------J|L-JF7F7F7F--JFLJF7LJF7FJFJ77.|",
//"|7L.|.JJF7FL-7F-7F7L-JL7L--7|F7L-7|F--7F-J|LJLJLJFJ|F7F-7F7L7|||||L--7FJL--7|L---7LFJ|F----JL-JF7L7L-7F---7F-JF7FJLJLJLJ-F7F7FJL--JLJFJ.L7-7",
//".L|.LFJ.|--.FJ|FLJL-7F7L---JLJL--JLJF7LJF7L7F----JJLJ|L7||L7LJLJLJF7FJL7F7FJL----J-L-JL-7F-----JL7L--J|F--J|F-JLJ7F7.F7F7|LJ||F------J||7L-J",
//"|F-7J|-7J.JFL-JF7F7JLJ|F7F---7F-7F--JL--JL-JL---7F7F7|FJLJFJF7F-7FJ|L-7|||L---7F7FFF-7F7LJF------JF7F7||F7F||F----JL-J||||F-J|L-7F7F7F7JF|J|",
//"--L--|.7FJ-F7.FJLJL7F-J|||F--J|FJ|F--7F-7F7F7F-7LJLJ|LJF7LL7|||FJL7|F-J||L-7F7LJ|7-L7LJL--JF7F7F7FJ||||||L-JLJF---7F-7LJLJL7.|F-J|LJLJL7F7.7",
//"|7|.|.F--JLLF7|F--7|L--JLJL7F-JL-JL77LJJ||LJLJ-L7F-7|F-JL7F|||||F7||L-7|L7FJ||F-JF-7L------J|||||L7LJLJLJF---7L--7|L7L7F7F7L-JL--JF7F-7LJ|F7",
//".JLFJ-FJJJ.FJ|||F-J|F77F7F7LJFF---7L---7|L-----7LJFJLJF-7L7||||||||L-7|L-J|FJ|L7FL7|F7JF7F--J|LJL-J-F7F7FL-7FJF7FJ|FJFJ||||F-7F7F7|||FJF7|||",
//"7|7LJ..FFF-L7|LJL-7LJL7|LJL--7L-7FJF---JL7F7F7FJF7L-7FJFJFJ||||||LJF-JL--7||FJFJFFJLJL7||L-7FJLF----JLJL---JL7|LJLLJFL7||||L7LJLJ||LJ|FJ|LJ|",
//"LJJJL-.FFF--JL----JF-7|L7F7F7L--JL7L---7FJ|LJ|L-JL7FJ|||FJ.LJLJ||7FL--7F7|||L-JF-|F7F7||L7FJ|F7L--7F7F-7F7F-7|L---7F-7LJLJL7L--7J|L7FJL7L7FJ",
//"F7F-7FFF7L--------7L7|L7LJLJL----7L----JL7L7JL-7F7|L7L7|L-7JF7FJL--7J-||LJLJL|L|.LJLJ|LJFJL-J|L---J|LJJLJLJFJL---7|L7L----7L7F7L7|FJL7FJFJL7",
//"LLFJFLFJL-7.F7FF--JFJL7L7-F------JF-----7L-JF-7||LJ||FJL-7L-J|L--7FJJ.LJJ||--F--7F7FFJF7L----JF7F7FJF7F7.F-JF7F-7|L-JF7F7FJL|||FJ|L-7LJ.L7FJ",
//".L-F.LL7F7L-JL-JF--JF7L7L-JF-7F7F7|F----J7F7L7|LJF-7LJ7F7L--7|J7F|L7|||JJ|-7LL-7LJL-JFJ|F-7F-7|LJLJFJLJL7L--J||FJL---JLJLJF7LJ|L7|F-J-LJ7|L7",
//".L|L7FFJ|L--7F-7|7F7|L-JF-7|JLJ||LJL7F7JF7|L-JL7.|FJF-7|L--7LJF--|FJ7LJ.FL7|-JFL----7L7LJ7LJFJ|F--7L-7F7L----J||LF-----7F7|L7-L-JLJ|.FF-LL-J",
//"|-F|.FL-J7F-JL7||FJ|L---JFJ|F-7|L7F-J|L7|LJF---JFJL-JFJL7F-JLFJJ-LJJ.F7.J7F7.LF7F-7FJFJF-7FFJFJ|F7L-7LJL------JL7L7F-7FJ|||FJF7F7F-7---7J7||",
//".FJF-|JJ|F|F7FJLJL7L-----JFJL7LJFJL--JFJ|F-JF--7L7F--JF7|L-7.|-J-|FL7F7.FFJLJ.||L7|L-J|L7L7L-J7LJL7FJF7F-------7L-JL7|L7||||J|LJLJFJ7.L---L7",
//"-F-L.-7.F-J|LJF7F7L-7F-7F7|F-JF7|F---7L-JL--JF7L-JL7F-JLJF7L-77|7LF|JLL-7JLL-FJL-JL7F-77L7|F7F-7.FJL-J|L---7-F7L----JL-J|||L7|F---JJ.FJJ|.L7",
//"L|-L7|F7L7FJF-JLJL7FJ|FJ||||F7|LJL--7|F7F--7FJ|F7F7LJF7F7||F-JF7-7FJ|FL.|JFFFL----7|L7L7FJLJLJFJFJF7F7L---7L7|L------7F7||L7LJL----77LL|L--J",
//"|L.LF7LJ7LJ7L----7LJFJL-J||LJLJF----JLJ|L7LLJ-|||||F7|LJ|||L--J|||.L|.LFL-|-FF7F--J|FJFJL----7L-JFJLJ|F--7|FJ|F-----7||||L-JF7F----JJ|L|.F|7",
//"|J7L--7|7-|FF7|F7L-7L7F--J|F--7L-7F7F7LL7L-7F7LJLJLJLJF7LJ|F7F-JFJ7F|.F77J|LFJ|L--7|L7|F7F--7L7F7L--7LJF7||L7|L----7LJ|||F--JLJ|F7||.F-7F7J7",
//"F7L7J|F|L-F-JL-JL-7L-JL---JL-7L-7LJLJL-7|F7LJL--7F7F7L|L-7||LJLF7.|.F7FL|-F-L7|F7FJL-JLJ||F7L-J|L7F-JF-J||L7LJF7F-7L7J|LJL7F-7F7||F7FJFJ-L-F",
//"J7||FJL|77L--7F--7|F7F7F7F7F7L-7L-7F---JLJL-----J|LJL-JF-JLJF7FJ|-J|L|J||.LF-J|||L-----7|LJ|F7FJ|LJF7|F7LJ.L7FJ||7L-JFJF-7LJFJ|LJLJLJFJJ7.||",
//"LJ-7||FLFLF--J|JFJLJLJ||LJLJL-7L-7LJLF7JF--------JF7F-7L7JF-JLJFJ77JF7-F-7JL-7LJL------J|F7LJLJF-7FJ|LJL-7F7LJ.LJF7F7|FJ|L-7L7|F7F7F7|JL77-F",
//"-JJLJ-FJF.L7F7L7L----7|L-7F--7|F7L---JL-JF---7F--7|LJFL7L7|F7F7L77F7||JL7L7F7L----7F-7F7LJ|F7F7L7|L7|F7F7LJL--7F7||||||LF-7L7LJ|LJLJLJ7-J7-|",
//"|JJ|7.L-LJ7LJL-JF---7|L-7||F-JLJL--------JF7-|L-7|L---7L-J||LJ|FJF|||||LL7LJ|LF--7LJ7LJL-7|||||FJL7|LJLJ|F7F--J|LJLJLJL-JFJFJF7L--7F7LJ7JFJ|",
//"F7FJ77|.|.F7F7F7L--7|L7FJLJL-7F7F7F7F-----JL7L--JL----JF7|LJLFJ|F7|||L7F-JF-J-L-7|F------JLJLJLJF7LJF--7LJLJF7JL7F--7F-7FJ|L7|L7F7LJ|JL|J.F7",
//"L|-LJ-J-F.|LJLJL7F-JL-JL-7|F7LJLJLJLJF7F7F--JF7F7F7F--7|L7F7FJFJ|LJ||FJ|F-JF7FF7||L------7F--7F7|L7FJF7L----JL--JL7LLJ7LJF7FJ|FJ||F-J.FF7LJJ",
//".|F|.|J7|.L---7FJL7F7F-7FJFJL7F-----7|LJLJF--JLJLJ||F-J|FJ|||FJFL-7LJL-JL--J|FJ|||F7F----JL7FJ|LJJLJFJL----------7L------J|L-J|FJ||F777F7J.F",
//"L.77-L--J-F---JL-7||LJFJL7L-7|L----7||F7F7|F7F----J|L-7|L7|LJ|F7F7L-7F----7FJL7|||||L------JL-JF-7F7L------7F--7-L----7F7FJF7-|L7|LJ|F77L-FJ",
//"|F.|F-7LL7L--7F7FJLJF7L-7L--J|.F7F-J|LJLJLJ|LJF7|F-JF-JL7|L-7LJLJ|F-JL-7F7LJF7|||||L-7LF--7F--7L7||L-7FF7F7LJF7L----7.LJ|L-JL7L-JL-7LJL7JL7|",
//"|-LJ|LJ-.F-7JLJ|||F-JL7FJF7F7L-J||F7L--7F7FJ.FJL7|F-JLF7|L7FJF---JL--7FJ||F7|LJ|||L-7L7L-7||F-JFJ|L-7L7|||L7FJ|F-7F7L--7L--7FJF7JF7L7F7|L-JF",
//"L7|.FJ.LFL7L---JL7L7F7LJFJLJL--7|LJL--7|||L--JF7LJL7F7||L7||FJ7F----7||FJ|||L-7|||F-JFJF-J|||F7L7L-7L7LJLJJLJF|L7|||F-7L--7|L7|L-JL7LJLJ.FF|",
//"|L-|7FF--7L-----7L7LJL--JF-----J|F7F7FJLJL7F7FJ|F7FJ||||FJLJL-7L---7||||FJ||F7||||L-7|FJF7LJLJL-JF7L-JF----7F7L-JLJ||FJF7FJL7LJF7F-JJ-|LFL7|",
//"F|F|JFL-7L----7JL7L7.F-7J|F----7LJLJ||F-7JLJLJ.LJLJ-|||LJF----JF7FFJLJ||L7||||||||F-J||FJ|F-----7|L7F7|F---J||F---7||L7|LJ7|L7FJLJ7FL-|LLJFJ",
//"F--|-7|L|F---7|F7L7L7L7L7LJF--7L7F-7LJ|FJF7F-----7F7||L-7|F---7|L7|F-7LJFJ||||||||L7FJLJFJ|F-7F7LJJLJLJL----JLJF7FJLJ-|L7F7F7LJJL-|JJ.J7JJ|.",
//"7-LL7F7LLJLF-JLJ|JL7L-JFJF7|F7L-J|FJF-JL7|||F----J|LJ|JFJLJF--J|FJLJFJF7|FJLJLJ|||FJ|F7-L-JL7LJL-7-F---7F-----7|LJF7FFJFJ|||L--7J7|7-J||JFFJ",
//".F|.JJ|LJ-L|F7F7L-7L--7|FJLJ|L---JL7L--7LJLJL-7F7-L-7L-JF--J-F-JL77FJFJLJL-7F7FJ|LJFJ|L7-F7FJF7F7L7L--7|L----7|L--JL7L-JFJLJF-7L7F7J7-77.L|J",
//"F7LFJF7JF7.LJLJL-7|-F7||L--7|F----7L7F-JF--7F-J|L7F7L--7L7F-7L--7L7L7L-7F-7LJ|L7|F7L7L7L7||L-JLJL7L---JL-----JL7F--7L---JF7FJ7L7LJ|LF-LF-777",
//".F7-7FJ.FJ7FF7F7FJL7|LJL-7FJ|L-7F7L-J|F7L-7LJF-JFJ||F-7|FJ|FJF7L|FJFJF-JL7L7FJFJ||L-JJL7LJL---7F-JF----7F-----7|L-7L-----J|L--7|F-J-|-JJ||L-",
//".||L---F-7.FJLJ|L-7LJF-7FJL7|F-J||F--J|L--JF-JF-JFJ|L7LJ|FJL7||FJL7L7L7F7|FJL7L7|L--7F-JF---7FJL7FJJF-7||F----JL--JF-----7L---J|L-7J.LLF-|7J",
//".7JFJFF|J7.L--7L--JF7|FJL-7LJL--JLJF-7L----JF7L-7L7L7L-7||F-J||L-7L-JFJ|LJL-7L7||F--JL-7L7F7LJF7||F7L7LJ|L----7F7F7|F-7F7L7F7F7|F7|JFJFLFL-7",
//"FFFL-|-FJF-|.FJF7F-JLJL--7L---7F---JFL-7F---JL-7|FJFJF7|||L7FJ|F7L--7|.|F---JFJ||L7F7JFJFJ||JFJ|LJ|L-JF7|F---7LJLJLJL7LJL-J||||LJLJ-J.FF--L-",
//"FJ|.L|L|.LFF7|FJLJLF--7F7L---7|L-7F7F-7|L-----7LJL7|FJLJ||FJ|FJ|L7F-JL-JL---7L7LJFJ||FJFJFJL7|FJF7L7F-JLJL--7|F7F-7F7L-7JF-JLJL---77.FJ-7.L|",
//"7-F7FF-F7.FJ|LJF7F7L-7||L----JL77LJLJFJ|F-----JF-7||L-7FJ|L7|L7L7|L----7F7F-JLL-7|-||L7|.L-7||L7|||LJF------JLJ||LLJ|F-JFJF7F7F7F-JJ7|.|LJJ|",
//"JFF7FJJLJFL7L--JLJL--J|L------7|F----J-LJF-77F7L7LJ|FFJ|L|FJ|FJ7||.F7F7||LJF7-F-JL7||FJL7F-J||FJ||F7FJF----7F-7LJF7-LJF7|FJLJLJLJ|-FL77FJJ-L",
//"F7-J-7-|JF-JF--7F7F--7L-------J|L-----7F7|FJFJL7L-7L7L7|FJL7||F7|L-JLJLJL7FJL7L-7FJ|||F-JL-7LJL7||||L7|F---J|FJF7|L7F7|LJL---7F--7--J7-J7L|.",
//"JJF7777F7L--JF7||||F-JF----7F-7L------J|LJL7|F7L7FJFJFJ||F7LJ|||L-----7F-J|F7|F7|L7||||-F--JF--J|||L7LJL--7FJL7|||FJ|||F-----J|F-J7|JL7L-.FJ",
//"FFJ|F-FJL----JLJ|LJL-7|F---JL7L------7|L-7FJLJL7||FJFJFJLJ|F-J||F7F7F-JL--J||||LJFJ|LJL7L--7|FF7|||FJ|F---J|F-J|||L-JLJL7F7F--JL7|L77|7|LF..",
//"LJ.L--L-7F--7F7FJF---J||F----J-F7F--7L7F7||F7F7|||L7|FJF7FJ|F-J||LJ|L-7F---JLJL-7|FJF7FJF-7|L7|||LJ|F7L----JL--JLJF-7F7FJ|||F--7L---7F7J7L-J",
//"|LF7|F|.LJJFJ|||FJF7F7|LJF--7JFJLJF7L-J|||||||||||FJ||-|||FJL-7|L7FJF7||FF7JJF7L|||FJLJ||FJ|FJ||L7FJ||F7FF7F7F-7F7L7LJ|L-JLJL-7L-7F-J||-7-J|",
//"J.F-F|77LFFL7|LJL-J||||F-JF7L-JF--JL7F7|||||||LJLJ|FJ|FJ|||F7FJL7||FJ|||FJL7FJL7|||L7F7FJ|FJL7|L-JL7||||FJ||||FJ|L7L7FJF-7F7F7L-7LJF-J|7|LF|",
//"L|7-7.F--F--J|F--7|LJLJL--JL-7FJF---J|||||LJ|L-7|FJL7||FLJ||||F-J|||FJ||L-7||F7LJLJFJ|||FJL7FJL-7F-J||||L7LJLJL-JFJFJL7L7||LJL--JF7L7FJ-|LJ|",
//"FJ|FJ-|J7L--7|L-7L-------7|F7|L7|F-7FJ||||F-JF-JFJF-J|L-7FJ|LJL7FJ|||FJ|F7||LJ|F---JFJ||L7FJ|F7FJ|F7|||L7L-----7JL7|F7L-JLJF---77|L-J|F7J.|-",
//"L.F.FLL7JLLFJ|F-JF-7F---7L-JLJFJ|||LJFJ|||L-7L-7L7|LFJF-JL7L--7||FJ||L7|||||F7|L7F7FJ7||.|||LJ||FJ|||LJFJF-7F-7|F-JLJL-----JF-7L7|F--J||JF-.",
//"|FJ-J|||FF7L-JL--J-LJF-7L--7F7L7|L7F-JFJ|L-7|F7|FJL7|FJF--JF-7|||L7|L7|||||||LJFJ||L-7LJFJL--7||L7||L-7L7L7|L7|||F7F7F7F-7F7L7L-J||F--J|-||7",
//"FFJ7F-LJF|-F-7F7LF7F7L7L---J||FJ|FJL7FJFJF7|||||L-7|||JL--7|7LJ|L7||FJ|LJ||||F-JFJ|F-JF-JF7F7|LJ|||L7FJF|FJL7||||||||||L7||L7L---JLJF-7|JL77",
//"JJJ||F|-LJ7L7LJ|FJ||L7L7F--7||L7|L7FJL7L-J|||||L7FJ||L7F--JL-7FJFJ||L7L-7|||||F7|FJL-7L-7|||LJF--J|FJL-7LJF-J|LJ||||||L7|LJFJF-7F7F7|-LJJFJ7",
//".JLF7F7-|FF7L-7|L7||FJ.LJF-J||FJ|FJL7FJFF-J||||FJ|FJ|FJ|F-7F7|L7|FJL7|F7|||||||LJL7F-JF-J||L7-L-7FJL7F-JF7L-7|F-J|LJ||FJL-7L7|LLJ||LJ7||L7||",
//"F7JL-JJ-7F|L--JL-J||||F--JF-J||FJ|F-JL-7|F7|||||F||F||FJ||LJ||FJ|L7FJ||||||||LJF7||L-7|F7|L7L--7|L7FJL--JL-7|||F7L-7|||F7FJFJL-7LLJJJJ-J7J|J",
//"LL-FJ-F7JFL7F7F7F7LJL-JF-7L7JLJL7|L7F7FJLJ||||||FJ|FJ|L7L--7|||FJFJ|-LJLJ||LJF-JL-JF7|LJ|L7|F-7|L7|L-7F----JLJ|||F7|||||LJFJF7FJF7JJ-|.J|LJ.",
//"-J|F7FF--J7||LJLJL---7FJJL7L---7||F|||L--7||LJLJL7||FJFL7F7|||||JL7L---7FJL7.L--7F7|||JFJFJLJ|LJ|LJF-JL------7||||||LJLJF7L7||L-J|J-77.LJJ||",
//"L-LFJJJ|7F-LJF----7F7LJF--JF7F-J|L7LJ|F7FJ||F7|F7||LJF7|LJ|||||L-7L7F7FJ|F-JF---J||||L7L7L-----7F--JF7F7F-7F-J||LJ|L----JL7|||F-7|.F777||FLJ",
//".FFJJ|.|FF7|FJF--7LJL--JF-7||L-7L7L7FJ|||FJLJL7|LJL7FJL---J|LJ|F-J-||||JLJF-JF-7FJ|||FJL|F----7||F-7|||LJFJL7.LJLFJF-7F7F-J|||L7LJ.|LF-L-JJ7",
//"FFJ||--777-LL-J.LL-----7|FJ||F-JJL-JL7|||L-7F-JL7F7|L---7F7L-7|L7F-J||L--7L--JFJL7||LJF-JL---7||LJFJ||L-7L-7L---7L7|FLJLJF7LJ|FJJ|FL-JJ.F-FJ",
//"|JJL77F|||--JL|7.F7F7F7|||FJ|L-7.F---J|LJF-JL--7|||L7F--J||F7||FJ|F7||F-7L7F--JF7||L7LL7F-7F7||L-7L7||F7L-7|F7F7|FJL-----JL7.LJJJFF7FF----|7",
//"|F-F-F7LJ|||7FFF-JLJLJLJ|LJFJF7L7|F7F7L-7L-7F--J|||FJL-7FJ||||LJFJ||||L7L7||F7FJLJL7L7|||FJ|||L--JFJ|LJL7FJ||||||L-7F-7F7F7L7JJJL|LF-LJFLLLL",
//"F|JLJL|JFJ--FL|L7F7F-7F7L-7|FJ|FJ|||||F7|F-JL-7LLJ||F77|||LJ|L-7L-J|||FJL|||||L-7|FJFJFJ|L7||L--7LL7L-7-|L7||||||F-JL7LJLJL-JJJ.|.777J-J7-|J",
//"F|FJ.7JF-J-F7.LL||||FJ||F7|LJ7||7LJ||||LJ|F7F7L--7LJ|L-JL--7L-7L-7FJ|||F-J|LJL--JFJFJFJFJFJ||F-7L7||F7L7|FJLJ|||||F-7L---77|-|LLJJLLJ.-JLF7|",
//"LL--.L7-J|L-J-.LLJLJL7||||L--7|L7|FJ||L7FJ||||F7FJF-JF7F7F7L-7|F7|L-J|||F-JF-----JFJ7L7|7L7||L7L7|FJ||FJ|L7F-J|LJ||JL7F-7|-F7JLJJ7|.|.LF-|.|",
//"LLJJ.LLF-J77F--.F|LF7||LJ|F7FJL-JFJFJ|FJL7|||||LJJL7FJ||||L-7|LJ|L7F7LJLJF7L--7F-7|F--JL-7||L7L7LJ|FJLJFL-JL-7L7.LJF-JL7LJL-77-L--F7.|.J7|F-",
//"J7FF|7-F7JFL77F-|7.|LJL7FJ||L7JL|L7L7|L7-LJ|||L---7LJFJ||L-7|L-7L7||L----JL---JL7||L7F--7||L7|FJF-JL--------7|FJ.L7L7F7L-77|LL.F7.-L.J-J|7JJ",
//"F-7..JJ.LJ|L-77-LFFJF-7|L7|L-JFFF-JFJL-J-F-J||F7F-JF-JFJ|F7|L7FJL||L7F7F7F7F--7FJ||FJL-7|||FJLJFJF7F-7F---7FJLJJ7-FL|||F-JJ77LFJ-7-LL.L7||--",
//"7L-JJ7--|-|LLLJ7.FL-J-|L7|L-7LL|L7FJJJL|LL-7|LJLJF-JF7L7LJLJ-LJJ.LJ-LJ||||LJF-J|J||L7F7|LJ||F-7|FJ||.|L--7LJ.F|L|JFFLJLJLJ|J|..LJ|F77L--J7-L",
//"-.LJLJ..L.L7-LF-7L||LFJFJ|F7L7.L-||JJFFJ-JLLJ..FL|F7||FJJJLLF7|J.L.FF-J|||F-JF7L7||F||LJ.FJLJFJ||FJL7L7F-J..JJFF-7.LJ.|-L|-7FF7|-L7|--J7.L.|",
//".F|.LL.F7FJ..F77LJ||7|FJ-LJL-J-|JLJ.FLJ|J77|J.JFLLJLJLJJJ..LF-|J.LF-JF7|||L-7|L7|LJFJL--7|F-7L7LJL7FJFJL7J7.L7F|F77JLJJ7LJ|FFJ.L7L|LJFLFFF-L",
//"--F77L7J7|.F.-JF7LFJ-LJJ.|.JFJ7JLL7.F-FLF---77-LJ|FJJL|J|..-|J|-.FL7FJ||||F-JL7|L7FJF7F7|||FJFJJJFJ|J|F-J.L7|7L||LJ-.FL|.LFLJJ.L-J|L|7-7|LJ|",
//"|-LJ7|F7FF-L-FFJJJ.L.||-F----JLF--|7L-F.LJJ.FJ..FFJ7.FJ.F|FFJ.J7-JJLJJ||LJL7F7||FJL7|||LJLJ|FJL|FL-JLLJLJ7FJ777F-..|.77|7-F..LF.LJ.F|-F--J.7",
//"L-|L-7--.7JJF7LL.-|L-7L.||.FF-7J.FL7|FJ|FL7-||F-7J.--7JJL7FFFL-LJ|.LJ|LJ.F-J|||LJJ-|||L7JJ7LJJJF--JJ.LJF7-|-JJLJ-F7FFJFLF-L7-FF7-F-|.L|FJF7.",
//"|7F7LJ||FJJ.-F7-7JL7LJF77|.-J7J7F||FL-7F7-L.F7F-7F.|||.FF|FF7JJJ-J7.|-||-L-7||L-7..LJL7|J7LJ7LF||F||FJ.|L-777LLLJ|-F-L7F7J||LJ|.|L7-JLFL-J--",
//"|-|7.LJ-|-7||FL7L--7.F7J---|FL7|F77LL-JJ|J.F|.|-L|7F-----LFL.|.F-LF7LF|J|LL|||F-J--J.FLJ-FJ.JLFJL|L7J.FJ7L|JF-.F--JF-LL7L.|-F.|J7||JJ.|-LL7L",
//"|7LJ.7.L|JFL7L7|JL7|F|L|-L-JF--7F7-F-7|.F.FFF7||LJFJ|.|-|FJ7FF-|..LJ-FJFL7|LJ||7J-|JFFJ.L|77--..L|-|F|7LJFJ.-7FJ-JJL-||FL|J.JF|.FFJ--7|..||J",
//"LFJ.FL7LL-LJ|J-7.FFF-L-L-7J7||LL||.||-F---|7|-JFF7F|7-|7F7JFJL-----J-|77LFJJJLJ-J.J|F77|7L7J.|F7LLF-LJJ.LF7F|7|-FJJ.L7-77|..FJ77L|-7-FLF--J|",
//"|LF..LJF-J7.|.FJ77FF-.FJFF-7FF7J|7-JJ|LJ-JLFJFFJ.|-J-7L7.|.|FFL||J.LF|JLFL7-FJLJ7L.-|FJ|-||.F|7|L|L...FJ|J|L77LLLJLL-J-|LJF7J||F-J|L-7.L--.J",
//"L7|FJ7L-77|J7--L|-FFFJLFFJ-LJ7.LLL7|.FJ||L7J|7|7J|F|-F7LJ7.FJ|LF--J.F||.|-7.L-||L7.FLJ|FFFJ7L--|FJL77F|LJL-L-J7.J7F.FL-LJ|JJ.L|-7J||.L-|-L7.",
//"LLJ7LL7-JJJLL-|JJLL-JJLF|J|7.|-7.L---J---7JJF7JF-L-|-F-7LL|JJ-LLJLL7LL-LL-L7.--JL7.LFLJ.FJ.-J.J.|JJ|J-|J.7-|JL-7.L.FJ--J-|-7-JJLF--|J..-.LLJ",
//
//)