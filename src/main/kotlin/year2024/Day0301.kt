package year2024

import java.io.BufferedReader
import java.io.File

fun main() {
    val pairs = ArrayList<Pair<Int, Int>>()

//val fileName = "example.txt"
//val content = "This is the content of the text file."
//val file = File(fileName)
//val writer = FileWriter(file)
//writer.write(content)
//writer.close()


    val bufferedReader: BufferedReader = File("input03.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readText() }
    println(inputString)

    inputString.split("mul").forEach {
        if (it[0] == '(') {
            if (it.subSequence(1, it.length).contains(')')) {
                if (it.contains(",") && (
                            it.indexOf(",") > it.indexOf("(") &&
                                    it.indexOf(",") < it.indexOf(")")
                            )
                ) {
                    println(it)
                    val first = it.split(',')[0].split('(')[1]
                    val second = it.split(',')[1].split(')')[0]
                    println("$first --- $second")
//                                          first.toIntOrNull()
                    pairs.add(Pair(first.toIntOrNull() ?: 0, second.toIntOrNull() ?: 0))
                }
            }
        }
    }
    pairs.map { it.first * it.second }.sum().let { println(it) }
}

val sampleData03 =
    "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
