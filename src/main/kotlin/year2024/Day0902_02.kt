package year2024

import java.io.BufferedReader
import java.io.File

fun main() {
    val bufferedReader: BufferedReader = File("input09.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readText() }
    val parsed = inputString.split("").filter { it.isNotEmpty() }.toMutableList()
//    val parsed = sampleInput09.split("").filter { it.isNotEmpty() }.toMutableList()
//    println(parsed)

    val output = arrayListOf<String>()

    parsed.mapIndexed { index, s ->
        if (index % 2 == 0) { // парне
            output.addAll(
                (1..s.toInt()).map { (index/2).toString() }
            )
//                .repeat().split("").filter { it.isNotEmpty() })
        } else { //  непарне
            output
                .addAll(
                    ".".toString()
                        .repeat(s.toInt())
                        .split("")
                        .filter { it.isNotEmpty() }
                )
        }
    }

//    println(output)

    fun separateToFiles(arr: List<String>): ArrayList<ArrayList<String>> {
        val result = arrayListOf<ArrayList<String>>()
        var file = arrayListOf<String>()
        for (i in 0..arr.size-1) {
            if (file.size == 0) {
                file.add(arr[i])
            } else if (file.last() == arr[i]) {
                file.add(arr[i])
            } else if (file.last() != arr[i]) {
                result.add(file)
                file = arrayListOf<String>()
                file.add(arr[i])
            }
        }
        result.add(file)
        return result

    }
    var separatedByFiles = separateToFiles(output)

//    separatedByFiles.forEach { println(it) }
//    separatedByFiles.flatten().joinToString("").let { println(it) }

//    fun findFirstSpace(inp : ArrayList<ArrayList<String>>) : Int  {
//        return inp.indexOfFirst {
//            it.all { it == "." }
//        }
//    }
////
//    fun findLastFile(inp : ArrayList<ArrayList<String>>, size : Int) : Int  {
//        return inp.indexOfLast { it.all { it != "."} && it.size <= size }
//    }

 fun findFirstSpace(inp : ArrayList<ArrayList<String>>, size: Int) : Int  {
        return inp.indexOfFirst {
            it.all { it == "." } && it.size >= size
        }
    }

    fun findLastFile(inp : MutableList<ArrayList<String>>) : Int  {
        return inp.indexOfLast { it.all { it != "."} }
    }

    fun findLastFile(inp : MutableList<ArrayList<String>>, digit : String) : Int  {
        return inp.indexOfLast { it.all { it == digit} }
    }


    val digits = separatedByFiles.filter { it.all {it != "."} }.map { it[0] }.reversed()
    var digitNumber = 0
//    println(digits)
//    return

    while (digitNumber < digits.size) {
//        separatedByFiles.flatten().joinToString("").let { println("${digits[digitNumber]} - $it") }
        val right = findLastFile(separatedByFiles, digits[digitNumber])
        val file = separatedByFiles[right]
//        println(file)

        val left = findFirstSpace(separatedByFiles, file.size)
        if (left == -1 || left >right ) {
            digitNumber = digitNumber + 1
            continue
        }
        val space = separatedByFiles[left]
//        println(space)

        for (i in 0.. file.size-1) {
            val digit = file[i]
            val dot = space[i]
            file[i] = dot
            space[i] = digit
        }

        separatedByFiles = separateToFiles(separatedByFiles.flatten())

        digitNumber = digitNumber + 1
    }

    println("----------------------------")
//    separatedByFiles.flatten().joinToString("").let { println(it) }

    separatedByFiles.flatten()
        .map { if (it == ".") 0 else it.toInt() }
        .mapIndexed { index, s ->
            index.toBigInteger() * s.toBigInteger()
        }.sumOf{ it }.let { println(it) } // 6250605700557

}