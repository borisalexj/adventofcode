package year2024

fun main() {
    val parsedInput = realInput04.map {it.split("").filter{it.isNotEmpty()}}
//    parsedInput.forEach { println(it) }

    var res = 0

    for (x in 0 .. parsedInput[0].size) {
        for (y in 0..parsedInput.size) {
            if (parsedInput.getOrNull(y)?.getOrNull(x) == "A" ) {
                if (parsedInput.getOrNull(y+1)?.getOrNull(x+1) == "S" &&
                    parsedInput.getOrNull(y-1)?.getOrNull(x+1) == "S" &&
                    parsedInput.getOrNull(y-1)?.getOrNull(x-1) == "M" &&
                    parsedInput.getOrNull(y+1)?.getOrNull(x-1) == "M"
                    ) {
                    res = res +1
                }
                if (parsedInput.getOrNull(y+1)?.getOrNull(x+1) == "M" &&
                    parsedInput.getOrNull(y-1)?.getOrNull(x+1) == "M" &&
                    parsedInput.getOrNull(y-1)?.getOrNull(x-1) == "S" &&
                    parsedInput.getOrNull(y+1)?.getOrNull(x-1) == "S"
                    ) {
                    res = res +1
                }
                if (parsedInput.getOrNull(y+1)?.getOrNull(x+1) == "S" &&
                    parsedInput.getOrNull(y-1)?.getOrNull(x+1) == "M" &&
                    parsedInput.getOrNull(y-1)?.getOrNull(x-1) == "M" &&
                    parsedInput.getOrNull(y+1)?.getOrNull(x-1) == "S"
                    ) {
                    res = res +1
                }
                if (parsedInput.getOrNull(y+1)?.getOrNull(x+1) == "M" &&
                    parsedInput.getOrNull(y-1)?.getOrNull(x+1) == "S" &&
                    parsedInput.getOrNull(y-1)?.getOrNull(x-1) == "S" &&
                    parsedInput.getOrNull(y+1)?.getOrNull(x-1) == "M"
                    ) {
                    res = res +1
                }

            }
        }
    }
    println(res)
}