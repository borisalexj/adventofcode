package year2024

fun main() {

    val parsedRules = realOrderingRUles.map { it.split("|") }
    val parsedPageNumbers = realPageNumbers.map { it.split(",") }

//    parsedRules.forEach { println(it) }
//    parsedPageNumbers.forEach { println(it) }

    val correctOrders = arrayListOf<List<String>>()
    val incorrectOrders = arrayListOf<List<String>>()

    parsedPageNumbers.forEach { numbers ->
        var correct = true
        for (i in 0..numbers.size - 2) {
            parsedRules.find {
                it[0] == numbers[i + 1] && it[1] == numbers[i]
            }?.let {
                println("$numbers - it")
                correct = false
            }
        }
        if (correct) correctOrders.add(numbers) else incorrectOrders.add(numbers)
    }
    println("------------------")
    incorrectOrders.forEach { println(it) }

    val correctedIncorrectOrders = arrayListOf<List<String>>()
    incorrectOrders.forEach { numbers ->
        println("*******************")
        println(numbers)
        println("*******************")
        val order = arrayListOf<String>()
        order.addAll(numbers)
        var correct = true
        for (c in 0..order.size - 3) {
            for (i in 0..order.size - 2 - c) {
                parsedRules.find {
                    it[0] == order[i + 1] && it[1] == order[i]
                }?.let {
                    val orderI = order[i]
                    val orderIPlus1 = order[i + 1]
                    order[i + 1] = orderI
                    order[i] = orderIPlus1
//                    println("$order - $it")
                    correct = false
                }
            }
        }
        correctedIncorrectOrders.add(order)
    }
    println("=====================")
    correctedIncorrectOrders.forEach { println(it) }
    correctedIncorrectOrders.map {
//        println("$it - ${it.size / 2}")
        it.getOrNull(it.size / 2)
    }.filterNotNull().map { it.toInt() }.sum().let { println(it) }
//    correctOrders.map {
//        println("$it - ${it.size / 2}")
//        it.getOrNull(it.size / 2)
//    }.filterNotNull().map { it.toInt() }.sum().let { println(it) }
}
