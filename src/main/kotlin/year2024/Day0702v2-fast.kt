package year2024

fun main() {
    val start = System.currentTimeMillis()
//    val numbers = sampleInput07.map { it.split(":")[0].toLong() }
//    val values = sampleInput07.map { it.split(":")[1] }.map { it.split(" ").filter { it.isNotEmpty() }.map { it.toLong() } }

//    val numbers = realInput07ros.map { it.split(":")[0].toLong() }
//    val values = realInput07ros.map { it.split(":")[1] }.map { it.split(" ").filter { it.isNotEmpty() }.map { it.toLong() } }

    val numbers = realInput07.map { it.split(":")[0].toLong() }
    val values =
        realInput07.map { it.split(":")[1] }.map { it.split(" ").filter { it.isNotEmpty() }.map { it.toLong() } }
//    println(numbers.size)
//    println(numbers.toSet().size)


    numbers.forEachIndexed { index, expectedNumber ->
        recursion(
            expectedNumberIndex = index.toLong(),
            expectedNumber = expectedNumber,
            operations = operations3,
            values = values[index],
            number = values[index][0],
            index = 1
        )
    }

    println("result:")
    println(result.values.sum())
    println("time  - ${System.currentTimeMillis() - start}")
}

fun recursion(
    expectedNumberIndex: Long,
    expectedNumber: Long,
    operations: ArrayList<String>,
    values: List<Long>,
    number: Long,
    index: Int
) {
    if (index == values.size) {
        if (number == expectedNumber) {
            result[expectedNumberIndex] = expectedNumber
        }
    } else {
        operations.forEach {
            if (it == "+") {
                recursion(expectedNumberIndex, expectedNumber, operations, values, number + values[index], index + 1)
            } else if (it == "*") {
                recursion(expectedNumberIndex, expectedNumber, operations, values, number * values[index], index + 1)

            } else if (it == "|") {
                recursion(
                    expectedNumberIndex,
                    expectedNumber,
                    operations,
                    values,
                    (number.toString() + values[index].toString()).toLong(),
                    index + 1
                )
            } else {
                throw IllegalStateException()
            }
        }

    }
}

val result = mutableMapOf<Long, Long>()