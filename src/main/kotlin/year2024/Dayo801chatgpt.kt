package year2024

fun main() {
    // Example input map
    val inputMap = listOf(
        "............",
        "........0...",
        ".....0......",
        ".......0....",
        "....0.......",
        "......A.....",
        "............",
        "............",
        "........A...",
        ".........A..",
        "............",
        "............"
    )

    // Parse antennas from the map
    val antennas = mutableListOf<Triple<Char, Int, Int>>()
    for (y in inputMap.indices) {
        for (x in inputMap[y].indices) {
            val char = inputMap[y][x]
            if (char.isLetterOrDigit()) {
                antennas.add(Triple(char, x, y))
            }
        }
    }

    // Set to store unique antinode locations
    val antinodes = mutableSetOf<Pair<Int, Int>>()

    // Find antinodes for each pair of antennas with the same frequency
    for (i in antennas.indices) {
        for (j in i + 1 until antennas.size) {
            val (freq1, x1, y1) = antennas[i]
            val (freq2, x2, y2) = antennas[j]

            // Only consider antennas with the same frequency
            if (freq1 == freq2) {
                // Compute distance vector
                val dx = x2 - x1
                val dy = y2 - y1

                // Midpoint between antennas
                val midX = (x1 + x2) / 2.0
                val midY = (y1 + y2) / 2.0

                // Antinode positions (only valid if midpoint is integer)
                if (midX % 1 == 0.0 && midY % 1 == 0.0) {
                    val ax = midX.toInt()
                    val ay = midY.toInt()

                    // Antinodes on either side of the midpoint
                    antinodes.add(Pair(ax - dx / 2, ay - dy / 2))
                    antinodes.add(Pair(ax + dx / 2, ay + dy / 2))
                }
            }
        }
    }

    // Filter antinodes to keep only those within map bounds
    val mapWidth = inputMap[0].length
    val mapHeight = inputMap.size
    val validAntinodes = antinodes.filter { (x, y) ->
        x in 0 until mapWidth && y in 0 until mapHeight
    }

    // Output the number of unique antinodes
    println("Unique antinode locations: ${validAntinodes.size}")
}