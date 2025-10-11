import java.io.File

fun main() {
    val input = File("input15_real.txt").readText().trimEnd()

    val (mapPart, movesPart) = input.split("\n\n", limit = 2)
    val moves = movesPart.filter { it in "^v<>" }

    val map = mapPart.lines().map { it.toCharArray() }.toMutableList()
    val height = map.size
    val width = map[0].size

    data class Pos(var r: Int, var c: Int)
    val robot = run {
        for (r in 0 until height)
            for (c in 0 until width)
                if (map[r][c] == '@')
                    return@run Pos(r, c)
        error("No robot found")
    }

    fun dir(ch: Char): Pair<Int, Int> = when (ch) {
        '^' -> -1 to 0
        'v' -> 1 to 0
        '<' -> 0 to -1
        '>' -> 0 to 1
        else -> error("bad dir")
    }

    for (m in moves) {
        val (dr, dc) = dir(m)
        val nr = robot.r + dr
        val nc = robot.c + dc

        when (map[nr][nc]) {
            '#' -> {} // wall — do nothing
            '.' -> {
                // empty cell — just move
                map[robot.r][robot.c] = '.'
                map[nr][nc] = '@'
                robot.r = nr; robot.c = nc
            }
            'O' -> {
                // try to push boxes
                var cr = nr
                var cc = nc
                while (map[cr][cc] == 'O') {
                    cr += dr
                    cc += dc
                }
                if (map[cr][cc] == '.') {
                    // can push: shift all boxes forward
                    map[cr][cc] = 'O'
                    map[nr][nc] = '@'
                    map[robot.r][robot.c] = '.'
                    robot.r = nr; robot.c = nc
                }
                // else wall or something → blocked
            }
        }
    }

    // print final map
    map.forEach { println(it.concatToString()) }

//     Example of metric computation (if needed later):
     val score = map.indices.sumOf { r ->
         map[r].indices.sumOf { c -> if (map[r][c] == 'O') 100 * r + c else 0 }
     }
     println(score)
}