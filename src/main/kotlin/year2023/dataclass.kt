package year2023

data class Customer(var name: String, var id: Int)

fun main(agrs: Array<String>) {
    val firstObject = Customer("John", 1122)
    val thirdObject = Customer("John", 1122)
    val secondObject = Customer("Patrick", 1245)
    println(firstObject == secondObject) // Output: true
    println(firstObject == thirdObject) // Output: true
    println(firstObject.equals(thirdObject)) // Output: true}
    println(firstObject.equals(secondObject)) // Output: true}
}

    class Three(val x: Int, val y: Int) : SealedClass()
sealed class SealedClass {
    class One(val value: Int) : SealedClass()
    class Two(val x: Int, val y: Int) : SealedClass()


    fun eval(e: SealedClass): Int =
        when (e) {
            is One -> e.value
            is Two -> e.x + e.y
            is Three -> {
                println(e)
                123
            }
        }
}