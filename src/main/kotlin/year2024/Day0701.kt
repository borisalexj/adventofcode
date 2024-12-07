package year2024

fun main() {
    val numbers = sampleInput07.map { it.split(":")[0] }
    val values = sampleInput07.map { it.split(":")[1] }.map { it.split(" ") }

    println(numbers)
    println(values)
}

val sampleInput07 = arrayListOf(
    "190: 10 19",
    "3267: 81 40 27",
    "83: 17 5",
    "156: 15 6",
    "7290: 6 8 6 15",
    "161011: 16 10 13",
    "192: 17 8 14",
    "21037: 9 7 18 13",
    "292: 11 6 16 20",
)