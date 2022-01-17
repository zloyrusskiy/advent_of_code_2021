package day6

import java.util.*

fun solve1(ages: List<Byte>): Long {
  val fishByAges = ages.fold(MutableList<Long>(9) { 0L }) { res, age ->
    res[age.toInt()] += 1L
    res
  }

  return breedFishes(fishByAges, 80).sum()
}

fun solve2(ages: List<Byte>): Long {
  val fishByAges = ages.fold(MutableList<Long>(9) { 0L }) { res, age ->
    res[age.toInt()] += 1L
    res
  }

  return breedFishes(fishByAges, 256).sum()
}

val RESET_VALUE = 6
val NEW_FISH_VALUE = 8

tailrec fun breedFishes(fishByAges: MutableList<Long>, daysLeft: Int): List<Long> {
  if (daysLeft == 0) {
    return fishByAges.toList()
  }

  Collections.rotate(fishByAges, -1)
  fishByAges[RESET_VALUE] += fishByAges[NEW_FISH_VALUE]

  return breedFishes(fishByAges, daysLeft - 1)
}

val input = generateSequence(::readLine).toList()
  .flatMap { line ->
    line.split(""",""".toRegex())
      .map { it.toByte() }
  }

println(solve1(input))
println(solve2(input))