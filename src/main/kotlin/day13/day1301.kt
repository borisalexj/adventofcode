package day13

import java.lang.IllegalStateException
fun <T> List<List<T>>.transpose(): List<List<T>> {
    if (isEmpty() || this[0].isEmpty()) return this

    val width = this[0].size

    return (0 until width).map { i ->
        this.map { it[i] }
    }
}
fun main() {
    val parsedInput01 = sampleInput12011.map { it.split("").filter { it.isNotEmpty() }.toMutableList() }.toMutableList()
    val parsedInput02 = sampleInput12012.map { it.split("").filter { it.isNotEmpty() }.toMutableList() }.toMutableList()
//    parsedInput01.forEach { println(it) }
//    println("-----------------")
//    parsedInput01.transpose().forEach { println(it) }


    val inputs = arrayListOf(parsedInput01, parsedInput02)
    var result = 0
    inputs.forEach {parsedInput ->
        println("---------------")
        parsedInput.forEach { println(it) }
//        parsedInput.transpose().forEach { println(it) }
        val tempRes = calculate(parsedInput)
        if (tempRes.first) {
            println("is Row - $tempRes")
            result =result +  tempRes.second *100
        } else {
            val tempRes2 = calculate(parsedInput.transpose())
            println("is column - $tempRes2")
            if (tempRes2.first) {
                result = result + tempRes2.second
            } else {
                throw IllegalStateException()
            }
        }
     }
     println(result)
}

fun calculate(parsedInput: List<List<String>>):Pair<Boolean, Int> {
        var isRow = true
                var amountOnTop = 0
                for (i in 0..parsedInput.size-2) {
//                    println("$i -  ${parsedInput[i]} ${parsedInput[i+1]} ${parsedInput[i] == parsedInput[i+1]}")
                    if (parsedInput[i] == parsedInput[i+1]) {
                        var tempEqual = true
//                        println("--------------------")
                        for (c in 1..parsedInput.size) {
                            if (i-c <0 || i+c+1 >= parsedInput.size)  {
                                return  Pair(isRow, i+1)
                            }
//                            println("$i $c ${parsedInput[i-c]} ${parsedInput[i+c+1]} == ${parsedInput[i-c] == parsedInput[i+c+1]}")
                            if (parsedInput[i-c] == parsedInput[i+c+1]) {
                                tempEqual = true
                            } else {
                                tempEqual = false
                                break
                            }
                        }
                        amountOnTop = i+1
                        isRow = tempEqual
            //            println(i)
                    }
                }
//                println("$isRow - $amountOnTop")
return Pair(isRow, amountOnTop)
}

//fun <E> transpose(matrix: Matrix<E>): Matrix<E> {
//    if (matrix.width < 1 || matrix.height < 1) return matrix
//    val result = createMatrix(height = matrix.width, width = matrix.height, e = matrix[0, 0])
//    for (i in 0 until matrix.width) {
//        for (j in 0 until matrix.height) {
//            result[i, j] = matrix[j, i]
//        }
//    }
//    return result
//}

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