fun main(args: Array<String>) {
//    println("Hello World!")
//
//
//     Try adding program arguments via Run/Debug configuration.
//     Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
//    println("Program arguments: ${args.joinToString()}")
//    val p = "p"
//    println(p)
//    println("p")
//    println("x")

val arr = listOf("1abc2",
"pqr3stu8vwx",
"a1b2c3d4e5f",
"treb7uchet")
    val tmp = arr.joinToString(separator = "").toString()
    println(tmp)
//    val digits = intArrayOf()

//    println("asdf".split(""))
//    tmp.split("")

//    for (letter in tmp.split("")) {
//        if letter..isD
//    }

    val chars = listOf('a', '+', '1')
    val digits = tmp.toCharArray().filter {
        it.isDigit()
    }
    println(digits) // [1]
//    println(notDigits) // [a, +]

    for (i in 0 .. digits.size/2) {
        println(digits[i].toString() + digits[i+1].toString())
    }
}

//1abc2
//pqr3stu8vwx
//a1b2c3d4e5f
//treb7uchet