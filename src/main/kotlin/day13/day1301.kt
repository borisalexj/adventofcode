package day13

fun main() {
    val parsedInput01 = sampleInput12011.map { it.split("").filter { it.isNotEmpty() }.toMutableList() }.toMutableList()
    val parsedInput02 = sampleInput12012.map { it.split("").filter { it.isNotEmpty() }.toMutableList() }.toMutableList()

    for (i in 0..parsedInput01.size-1) {
        if (parsedInput01.all { it[i] == it[i+1] }) {
            println(i)
        }
    }
}

val sampleInput12011 = arrayListOf(
"#.##..##.",
"..#.##.#.",
"##......#",
"##......#",
"..#.##.#.",
"..##..##.",
"#.#.##.#.",
)

val sampleInput12012 = arrayListOf(
"#...##..#",
"#....#..#",
"..##..###",
"#####.##.",
"#####.##.",
"..##..###",
"#....#..#",
)