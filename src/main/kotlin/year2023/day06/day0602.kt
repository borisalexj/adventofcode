package year2023.day06
fun main(args: Array<String>) {
//    val totalVariants = arrayListOf<ArrayList<Pair<Int,Int>>>()
    var totalVariants = 1L
    var c1 = 0
    var c2 = 0
        var passedCountForRace = 0L
        val t = Time2
        val d = Distance2
        c1 = c1+1
        for (speed in 0..t) {
            c2 = c2 +1
            var passed = false
            if (speed*(t - speed) > d) {
                passedCountForRace = passedCountForRace + 1
            }
        }
        totalVariants = totalVariants * passedCountForRace
//    println(totalVariants[0])
println("$c1 + $c2")
    println("result = " + totalVariants)
}

//val Time = arrayListOf(      7,  15,   30,)
//val Distance = arrayListOf(  9,  40,  200,)
//val Time = arrayListOf(44,     70,     70,     80)
//val Distance = arrayListOf(283,   1134,   1134,   1491)

//val Time = arrayListOf(      71530,)
//val Distance = arrayListOf(  940200)
val Time2 = 44707080L
val Distance2 = 283113411341491L
//Time:        44     70     70     80
//Distance:   283   1134   1134   1491