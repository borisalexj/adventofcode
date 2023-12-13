package day12

import java.lang.IllegalStateException
import java.sql.RowId
import java.util.*
import java.util.regex.Pattern
import java.util.stream.Collectors
import kotlin.collections.ArrayList




fun main() {
    val parsedInput = ArrayList<Row>()
     realInput12_.forEach { inRow ->
         val r = Row().also {
            it.line = inRow.split(" ")[0] + inRow.split(" ")[0] + inRow.split(" ")[0] + inRow.split(" ")[0] + inRow.split(" ")[0]
            it.numbers = ArrayList(inRow.split(" ")[1].split(",").map { it.toInt() })
            it.numbers.addAll(inRow.split(" ")[1].split(",").map { it.toInt() })
            it.numbers.addAll(inRow.split(" ")[1].split(",").map { it.toInt() })
            it.numbers.addAll(inRow.split(" ")[1].split(",").map { it.toInt() })
            it.numbers.addAll(inRow.split(" ")[1].split(",").map { it.toInt() })
          }
          parsedInput.add(r)
     }
//    println(parsedInput[0])
//    throw IllegalStateException()

val start = System.currentTimeMillis()
    for ( row in parsedInput) { //.subList(1,2)
        println(row)
        generateAndCheck("", row)
    //          println(variants[0].split("."))
        println("result 1202 - temp - " + result2)
    }

    println("result 1202 - " + result2)
    println((System.currentTimeMillis() - start )/100)

}


