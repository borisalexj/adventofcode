package day01

fun main(args: Array<String>) {

    val convertedArray = arrayListOf<String>()
    val res = arrayListOf<Int>()

    val globalPairsToReplace = arrayListOf<Pair<Int, String>>()

//    for (line in sampleInput0102) {
    var replacements = 0
    for (line in real01) {
        var lineCopy = line

//        while (digitNamesPairs.any { lineCopy.contains(it) } ) {
        val firstToReplace = digitNamesPairs.map {
            Pair(lineCopy.indexOf(it), it)
        }.filter { it.first != -1 }.sortedBy {
            it.first
        }.firstOrNull()

        val lastToReplace = digitNamesPairs.map {
            Pair(lineCopy.indexOf(it), it)
        }.filter { it.first != -1 }.sortedBy {
            it.first
        }.lastOrNull()

             println(firstToReplace.toString() + " | " + lastToReplace)

        if (firstToReplace != null) {
            lineCopy =
                line.replace(firstToReplace.second, digitNamesPairs.indexOf(firstToReplace.second).toString())
//        } else {
//            lineCopy = line
        }
        if (lastToReplace != null) {
            lineCopy = lineCopy + line.replace(
                lastToReplace.second,
                digitNamesPairs.indexOf(lastToReplace.second).toString()
            )
        }
//        }
        convertedArray.add(lineCopy)

        val digits = lineCopy.toCharArray().filter { it.isDigit() }
        val currRes = if (digits.size > 1) {
            (digits[0].toString() + digits[digits.size - 1]).toInt()
        } else {
            (digits[0].toString() + digits[0]).toInt()
        }
        res.add(currRes)
        println(" | " + line + " | " + lineCopy + " | " + digits + " | " + currRes)
    }

//    for (test in  convertedArray) {
//        if (     digitNamesPairs.any { test.contains(it) } )        {
//            println(test)
//        }
//    }

//    println(real01[0])
//    println(convertedArray[0])

//    for (line in convertedArray){
//        val digits = line.toCharArray().filter { it.isDigit() }
//        if (digits.size >1) {
//            res.add((digits[0].toString() + digits[digits.size-1]).toInt())
//        } else {
//            res.add((digits[0].toString() + digits[0]).toInt())
//        }
//    }
//    println(res[0])
    println("result - " + res.sum())
    println("replacements - " + replacements)

}


//val digitNamesPairs = listOf(
//"zero",
//"one",
//"two",
//"three",
//"four",
//"five",
//"six",
//"seven",
//"eight",
//"nine",
//)


//val sampleInput0102 = listOf(
//"two1nine",
//"eightwothree",
//"abcone2threexyz",
//"xtwone3four",
//"4nineeightseven2",
//"zoneight234",
//"7pqrstsixteen",
//)
//--- Part Two ---
//
//Your calculation isn't quite right. It looks like some of the digits are actually
//spelled out with letters: one, two, three, four, five, six, seven, eight, and nine
//also count as valid "digits".
//
//Equipped with this new information, you now need to find the real first and last
// digit on each line. For example:
//
//two1nine
//eightwothree
//abcone2threexyz
//xtwone3four
//4nineeightseven2
//zoneight234
//7pqrstsixteen
//
//In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76.
//Adding these together produces 281.
//
//What is the sum of all of the
//calibration values?
