package year2024

import kotlin.math.pow
import kotlin.math.roundToInt

fun main() {
  val regA: Int
  val regB: Int
  val regC: Int

  val insPoi:Int

  val comp = ComputerSample2()
//  println(comp.parsedProgram)

  while (true) {
      val inst = comp.getInstruction()
      var needToExecute = true
      while (needToExecute) {

          when (inst.first) {
              0 -> {
                  comp.regA = (comp.regA.toDouble()/(2.toDouble().pow(inst.second))).roundToInt()
                  needToExecute = false
              }
              1 -> {
                  comp.regB = comp.regB.xor(inst.second)
                  needToExecute = false
              }
              2 -> {
                  comp.regB = inst.second % 8
                  needToExecute = false
              }
              3 -> {
                  if (comp.regA == 0) {
                      // nothing
                      needToExecute = false
                  } else {
                      val inst1 = inst.second
                      val inst2 = comp.getInstructionNext().first
                      // run when again
                      needToExecute = true
                  }
              }
              4 -> {
                  comp.regB = comp.regB.xor(comp.regC)
                  needToExecute = false
              }
              5 -> {
                  val output = inst.second %8
                  println("$output,")
                  needToExecute = false
              }
              6 -> {
                comp.regB = (comp.regA.toDouble()/(2.toDouble().pow(inst.second))).roundToInt()
                           needToExecute = false
              }
              7 -> {
                  comp.regC = (comp.regA.toDouble()/(2.toDouble().pow(inst.second))).roundToInt()
                            needToExecute = false
              }
          }
      }
      println(comp)
  }
}

class ComputerSample1 : ComputerSample(){
  override var regA: Int = 729
  override var regB: Int = 0
  override var regC: Int = 0

  override var insPoi:Int = 0

    override val program = "0,1,5,4,3,0"
init {
    initialize()
}

}
class ComputerSample2 : ComputerSample(){
  override var regA: Int = 9
  override var regB: Int = 0
  override var regC: Int = 0

  override var insPoi:Int = 0

    override val program = "2,6"

init {
    initialize()
}
}
abstract class ComputerSample{
  abstract var regA: Int
  abstract var regB: Int
  abstract var regC: Int

  abstract var insPoi:Int

  fun getInstruction() = parsedProgram[insPoi]

fun getInstructionNext() = parsedProgram[insPoi+1]
fun next() {
    insPoi = insPoi +1
}

  abstract val program :String

  var parsedProgram = arrayListOf<Pair<Int,Int>>()

  fun initialize() {
      val split = program.split(",").map { it.toInt()}
      for (i in 0..(split.size-1)/2) {
        parsedProgram.add(Pair(split[i*2], split[i*2+1]))
       }
  }override fun toString(): String {
return "ComputerSample(regA=$regA, regB=$regB, regC=$regC, insPoi=$insPoi, program='$program', parsedProgram=$parsedProgram)"
}

}