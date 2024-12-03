package year2024

import java.io.BufferedReader
import java.io.File
import java.io.FileWriter

fun main() {
    val pairs = ArrayList<Pair<Int,Int>>()

//val fileName = "example.txt"
//val content = "This is the content of the text file."
//val file = File(fileName)
//val writer = FileWriter(file)
//writer.write(content)
//writer.close()


    val bufferedReader: BufferedReader = File("input03.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readText() }
//    println(inputString)

    val parsedString = inputString
    val strings = ArrayList<Pair<String, String>>()


    inputString.split("don't()", "do()" ).map {
        val index = inputString.indexOf(it)
            if (index == 0) {
                strings.add(Pair("do", it))
            } else if (inputString.substring(index-4,index) == "do()") {
                    strings.add(Pair("do", it))
            } else if (inputString.substring(index-7,index) == "don't()") {
                                    strings.add(Pair("dont", it))
            } else {
                throw IllegalStateException()
            }
     }
        strings.forEach { println(it) }
        strings.filter { it.first == "do" }.map { it.second }.forEach {
            println(it)
            it.split("mul").filter { it.isNotEmpty() }.forEach {
                println(it)
                            if (it[0] == '(') {
                                if (it.subSequence(1, it.length).contains(')')) {
                                    if (it.contains(",") && (
                                        it.indexOf (",") > it.indexOf("(") &&
                                            it.indexOf(",") < it.indexOf(")")
                                            )
                                        ) {
//                                                              println(it)
                                                              val first = it.split(',')[0].split('(')[1]
                                                              val second = it.split(',')[1].split(')')[0]
//                                                              println("$first --- $second")
                                                              pairs.add(Pair(first.toIntOrNull() ?:0, second.toIntOrNull() ?: 0))
                                                                  }
                                }
                            }
                         }
         }
             pairs.map { it.first * it.second }.sum().let { println(it) }
}

//public fun CharSequence.split2(vararg delimiters: String, ignoreCase: Boolean = false, limit: Int = 0): List<String> {
//    if (delimiters.size == 1) {
//        val delimiter = delimiters[0]
//        if (!delimiter.isEmpty()) {
//            return split(delimiter, ignoreCase, limit)
//        }
//    }
//
//    return rangesDelimitedBy(delimiters, ignoreCase = ignoreCase, limit = limit).asIterable().map { substring(it) }
//}

//val sampleData03 =
//    "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
