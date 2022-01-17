package day5

fun solve1(lines: List<Line>): Int {
  return lines
    .filter { it.p1.x == it.p2.x || it.p1.y == it.p2.y }
    .fold(mutableMapOf<Point, Int>()) { res, line ->
      line.getPoints()
        .forEach { res[it] = (res[it] ?: 0) + 1 }
      res
    }
    .filter { entry -> entry.value > 1 }
    .count()
}

fun solve2(lines: List<Line>): Int {
  return lines
    .fold(mutableMapOf<Point, Int>()) { res, line ->
      line.getPoints()
        .forEach { res[it] = (res[it] ?: 0) + 1 }
      res
    }
    .filter { entry -> entry.value > 1 }
    .count()
}

data class Point(
  val x: Int,
  val y: Int,
)

data class Line(
  val p1: Point,
  val p2: Point,
) {
  fun getPoints(): List<Point> {
    val points = mutableSetOf<Point>()
    val diffs = Pair(p2.x.compareTo(p1.x), p2.y.compareTo(p1.y))
    var curPoint = p1
    do {
      points += curPoint
      curPoint = Point(curPoint.x + diffs.first, curPoint.y + diffs.second)
    } while (curPoint != p2)
    points += p2
    return points.toList()
  }
}

fun String.toPoint() =
  this.split(""",""".toRegex(), 2)
    .map { it.toInt() }
    .let { Point(it.first(), it.last()) }

val input = generateSequence(::readLine).toList()
  .map { line -> line.split(""" -> """.toRegex(), 2) }
  .map { line -> Line(line.first().toPoint(), line.last().toPoint()) }

println(solve1(input))
println(solve2(input))