import java.io.File

fun main() {
    val input = File("input15.txt").readText().trimEnd()
    val (mapPart, movesPart) = input.split("\n\n", limit = 2)
    val moves = movesPart.filter { it in "^v<>" }

    // Expand map horizontally
    val expanded = mapPart.lines().map { line ->
        buildString {
            for (ch in line) append(
                when (ch) {
                    '#' -> "##"
                    'O' -> "[]"
                    '.' -> ".."
                    '@' -> "@."
                    else -> error("bad char $ch")
                }
            )
        }
    }.map { it.toCharArray() }.toMutableList()

    val height = expanded.size
    val width = expanded[0].size

    data class Pos(var r: Int, var c: Int)
    val robot = run {
        for (r in 0 until height)
            for (c in 0 until width)
                if (expanded[r][c] == '@')
                    return@run Pos(r, c)
        error("no robot found")
    }

    fun dir(ch: Char): Pair<Int, Int> = when (ch) {
        '^' -> -1 to 0
        'v' -> 1 to 0
        '<' -> 0 to -1
        '>' -> 0 to 1
        else -> error("bad dir")
    }

    fun canPush(r: Int, c: Int, dr: Int, dc: Int, visited: MutableSet<Pair<Int, Int>>): Boolean {
        if (!visited.add(r to c)) return true
        val ch = expanded[r][c]
        if (ch == '#') return false
        if (ch == '.') return true
        if (ch == '[') {
            val right = c + 1
            return canPush(r + dr, c + dc, dr, dc, visited) &&
                    canPush(r + dr, right + dc, dr, dc, visited)
        }
        if (ch == ']') {
            val left = c - 1
            return canPush(r + dr, left + dc, dr, dc, visited) &&
                    canPush(r + dr, c + dc, dr, dc, visited)
        }
        return true
    }

    fun push(r: Int, c: Int, dr: Int, dc: Int, visited: MutableSet<Pair<Int, Int>>) {
        if (!visited.add(r to c)) return
        val ch = expanded[r][c]
        if (ch == '[') {
            val right = c + 1
            push(r + dr, c + dc, dr, dc, visited)
            push(r + dr, right + dc, dr, dc, visited)
            expanded[r + dr][c + dc] = '['
            expanded[r + dr][right + dc] = ']'
            expanded[r][c] = '.'
            expanded[r][right] = '.'
        } else if (ch == ']') {
            val left = c - 1
            push(r + dr, left + dc, dr, dc, visited)
            push(r + dr, c + dc, dr, dc, visited)
            expanded[r + dr][left + dc] = '['
            expanded[r + dr][c + dc] = ']'
            expanded[r][left] = '.'
            expanded[r][c] = '.'
        }
    }

    for (m in moves) {
        val (dr, dc) = dir(m)
        val nr = robot.r + dr
        val nc = robot.c + dc
        val ch = expanded[nr][nc]

        when (ch) {
            '#' -> {}
            '.' -> {
                expanded[robot.r][robot.c] = '.'
                expanded[nr][nc] = '@'
                robot.r = nr; robot.c = nc
            }
            '[', ']' -> {
                val visited = mutableSetOf<Pair<Int, Int>>()
                if (canPush(nr, nc, dr, dc, visited)) {
                    push(nr, nc, dr, dc, mutableSetOf())
                    expanded[robot.r][robot.c] = '.'
                    expanded[nr][nc] = '@'
                    robot.r = nr; robot.c = nc
                }
            }
        }
    }

    expanded.forEach { println(it.concatToString()) }

    // GPS coordinate sum (for AoC verification)
     val score = expanded.indices.sumOf { r ->
         expanded[r].indices.sumOf { c ->
             if (expanded[r][c] == '[') 100 * r + c else 0
         }
     }
     println(score)
}