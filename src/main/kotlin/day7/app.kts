package day7

import kotlin.math.abs

fun solve1(crabPositions: List<Int>): Int {
  val median = median(crabPositions.map { it.toDouble() }).toInt()

  return crabPositions.sumOf { abs(it - median) }
}

fun solve2(crabPositions: List<Int>): Int {
  var optimalPosition = Int.MAX_VALUE
  crabPositions.minOf { it }.rangeTo(crabPositions.maxOf { it })
    .forEach { pos ->
      val fuelSpent = crabPositions.sumOf { calcFuel(abs(it - pos)) }
      if (optimalPosition > fuelSpent) {
        optimalPosition = fuelSpent
      }
    }

  return optimalPosition
}

fun median(l: List<Double>) = l.sorted().let { (it[it.size / 2] + it[(it.size - 1) / 2]) / 2 }

fun calcFuel(n: Int): Int = n * (1 + n) / 2

val input = readln()
  .split(',')
  .map { it.toInt() }

println(solve1(input))
println(solve2(input))