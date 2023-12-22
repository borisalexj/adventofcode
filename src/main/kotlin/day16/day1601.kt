package day16

import java.io.File

fun main() {
  val input = File("sampleInput16").readLines().map {
      it.split("").filter { it.isNotEmpty() }
   }
   println(input)
}