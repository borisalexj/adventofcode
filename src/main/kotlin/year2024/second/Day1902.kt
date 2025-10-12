package year2024.second

import year2024.day19real1
import year2024.day19real2
import year2024.day19sample1
import year2024.day19sample2

fun main() {
//        val towels = day19sample1.split(", ").toMutableList()
//    val expected = day19sample2
    val towels = day19real1.split(", ").toMutableList()
    val expected = day19real2
    println(towels)
    println(expected)
    towels.sortBy { it.length }
    val lengths = towels.map { it.length }.toSet().sorted()
    println(lengths)
    val results = arrayListOf<Pair<String, List<Long>>>()

    for (pattern in expected) {
//        results.add()
        val numbers = MutableList<Long>(size = pattern.length+1, init = {0})
        for (pos in 0..pattern.length -1) {
            processPosition(towels, pattern, lengths, pos, numbers)
        }
        val pair = Pair(pattern, numbers)
        results.add(pair)
    }

    results.filter{ it.second.last() > 0 }.forEach {
        println("--------------------------")
        println (it.first)
        println (it.second)
    }
    println("--------------------------")
    results.filter {
        it.second.last() > 0
    }.size.let {
        println("Result 1 : $it") // 263

    }
    results.map {
        it.second.last()
    }.sum().let {
        println("Result 2 : $it") // 723524534506343

    }


}

fun processPosition(
    towels: MutableList<String>,
    pattern: String,
    lengths: List<Int>,
    pos: Int,
    numbers: MutableList<Long>
) {
    if (numbers[pos] == 0L && pos != 0) {
        return
    }
    val trimmed = pattern.subSequence(pos, pattern.length)
    println("----")
    println("$pos - $trimmed")
    for (i in lengths) {
        val fits = towels.filter { it.length == i }.filter { trimmed.startsWith(it) }.size
        val fitslist = towels.filter { it.length == i }.filter { trimmed.startsWith(it) }
        if (fits == 0) continue
            else {
                print(towels.filter { it.length == i }.filter { trimmed.startsWith(it) })
            }
        if (pos == 0) {
            numbers[pos + i] =  numbers[pos + i] + fits
        } else {
            if (pos + i <= numbers.size) {
                numbers[pos + i] = numbers[pos + i] + numbers[pos] * fits
            }
        }

    }
    println()
    println("----")
}