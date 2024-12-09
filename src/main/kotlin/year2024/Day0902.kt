package year2024

import java.io.BufferedReader
import java.io.File

fun main() {
    val bufferedReader: BufferedReader = File("input09.txt").bufferedReader()
    val inputString = bufferedReader.use { it.readText() }
//    val parsed = inputString.split("").filter { it.isNotEmpty() }.toMutableList()
    val parsed = sampleInput09.split("").filter { it.isNotEmpty() }.toMutableList()
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

    separatedByFiles.forEach { println(it) }
    separatedByFiles.flatten().joinToString("").let { println(it) }

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

    var c = 0
    var processedFileIndex = 0
    val processedDigits = arrayListOf<String>()

    while (c<10) {
        separatedByFiles.flatten().joinToString("").let { println(it) }
        val right = findLastFile(separatedByFiles.subList(0, if (processedFileIndex == 0) separatedByFiles.size else processedFileIndex -1))
        processedFileIndex = right
        val file = separatedByFiles[right]
        println(file)
        val left = findFirstSpace(separatedByFiles, file.size)
        if (left > right) continue
//        if (left == -1) processedFileIndex = processedFileIndex -1
        val space = separatedByFiles[left]
        println(space)
        if (processedDigits.any { it == file[0] }) continue

        for (i in 0.. file.size-1) {
            val digit = file[i]
            val dot = space[i]
            file[i] = dot
            space[i] = digit
            processedDigits.add(digit)
        }

        separatedByFiles = separateToFiles(separatedByFiles.flatten())

        if (separatedByFiles.filter {
            it.all { it == "." }}.size == 2
        ) break

        c = c+1

    }

    println("----------------------------")
    println(output)

    val result = output.filter { it != "." }.mapIndexed { index, s ->
        index.toBigInteger() * s.toBigInteger()
    }

    println(result.sumOf { it })

//    println(0.toString().repeat(3))
//
//    println(0 % 2)
//    println(1 % 2)
//    println(2 % 2)
//    println(3 % 2)
//    println(4 % 2)
}

//val sampleInput09 = "2333133121414131402"
//
//val samplestInput09 = "12345"