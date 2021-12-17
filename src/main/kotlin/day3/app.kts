package day3

typealias BitsList = List<Int>

fun solve1(numbers: List<BitsList>): Long {
  return numbers
    .reduce { res, item -> res.zip(item) { a, b -> a + b } }
    .let { freqList ->
      val halfSize = numbers.size / 2
      val gammaRate: Int = freqList
        .map { if (it >= halfSize) '1' else '0' }
        .joinToString("")
        .let { Integer.valueOf(it, 2) }
      val epsilonRate: Int = freqList
        .map { if (it < halfSize) '1' else '0' }
        .joinToString("")
        .let { Integer.valueOf(it, 2) }

      gammaRate.toLong() * epsilonRate
    }
}

data class FindRatingInput(
  val values: List<BitsList>,
  val mostCommonNeeded: Boolean,
  val charIfEquals: Int,
  val bitIndex: Int = 0
)

fun solve2(reportValues: List<BitsList>): Long {
  return findRating(
    FindRatingInput(
      reportValues,
      true,
      1
    )
  ).times(
    findRating(
      FindRatingInput(
        reportValues,
        false,
        0
      )
    )
  )
}

fun findRating(input: FindRatingInput): Long =
  when (input.values.size) {
    0 -> throw Error("Rating number not found $input")
    1 -> input.values
      .first()
      .joinToString("")
      .let { Integer.valueOf(it, 2) }
      .toLong()
    else -> {
      input.values
        .map { it[input.bitIndex] }
        .let { freqList ->
          val halfSize = input.values.size / 2.0
          val onesQty = freqList.count { it == 1 }
          val filterByValue = when (halfSize.compareTo(onesQty)) {
            -1 -> if (input.mostCommonNeeded) 1 else 0
            1 -> if (input.mostCommonNeeded) 0 else 1
            else -> input.charIfEquals
          }

          findRating(
            input.copy(
              values = input.values.filter { it[input.bitIndex] == filterByValue },
              bitIndex = input.bitIndex + 1
            )
          )
        }
    }
  }

val input = generateSequence(::readLine).toList()
  .map { line -> line.toList().map { it.code - 48 } }

println(solve1(input))
println(solve2(input))
