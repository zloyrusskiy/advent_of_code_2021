package day1

fun solve1(reportValues: List<Int>): Int {
  return reportValues.zipWithNext { prev, next -> next > prev }
    .count { it }
}

fun solve2(reportValues: List<Int>): Int {
  return reportValues.windowed(3)
    .map { it.sum() }
    .zipWithNext { prev, next -> next > prev }
    .count { it }
}

val input = generateSequence(::readLine).toList()
  .map { it.toInt() }

println(solve1(input))
println(solve2(input))
