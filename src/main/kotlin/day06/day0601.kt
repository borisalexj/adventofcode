package day06
fun main(args: Array<String>) {
//    val totalVariants = arrayListOf<ArrayList<Pair<Int,Int>>>()
    var totalVariants = 1L
    for (i in 0..(Time.size -1)) {
        var passedCountForRace = 0L
        val t = Time[i]
        val d = Distance[i]
        for (speed in 0..t) {
            var passed = false
            if (speed*(t - speed) > d) {
                passedCountForRace = passedCountForRace + 1
            }
        }
        totalVariants = totalVariants * passedCountForRace
    }
//    println(totalVariants[0])
    println("result = " + totalVariants)
}

//val Time = arrayListOf(      7,  15,   30,)
//val Distance = arrayListOf(  9,  40,  200,)
val Time = arrayListOf(44,     70,     70,     80)
val Distance = arrayListOf(283,   1134,   1134,   1491)

//val Time = arrayListOf(      71530,)
//val Distance = arrayListOf(  940200)
//val Time = arrayListOf(44707080)
//val Distance = arrayListOf(283113411341491)
//Time:        44     70     70     80
//Distance:   283   1134   1134   1491