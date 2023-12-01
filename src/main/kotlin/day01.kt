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
    val tmp = arr.joinToString(separator = "")
    println(tmp)
    val digits = intArrayOf()

    for (letter in tmp.chars()) {
        print(letter)
    }
}

//1abc2
//pqr3stu8vwx
//a1b2c3d4e5f
//treb7uchet