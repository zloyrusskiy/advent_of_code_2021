package day2

data class Position(
  val horizontal: Long = 0,
  val depth: Long = 0,
  val aim: Long = 0,
)

fun solve1(instructions: List<Pair<String, Long>>): Long {
  return instructions
    .fold(Position()) { res, (command, units) ->
      when (command) {
        "forward" -> res.copy(horizontal = res.horizontal + units)
        "down" -> res.copy(depth = res.depth + units)
        "up" -> res.copy(depth = res.depth - units)
        else -> throw Error("Unknown command $command")
      }
    }
    .run { horizontal * depth }
}

fun solve2(instructions: List<Pair<String, Long>>): Long {
  return instructions
    .fold(Position()) { res, (command, units) ->
      when (command) {
        "forward" -> res.copy(horizontal = res.horizontal + units, depth = res.depth + res.aim * units)
        "down" -> res.copy(aim = res.aim + units)
        "up" -> res.copy(aim = res.aim - units)
        else -> throw Error("Unknown command $command")
      }
    }
    .run { horizontal * depth }
}

val input = generateSequence(::readLine).toList()
  .map { it.split(' ', limit = 2) }
  .map { Pair(it[0], it[1].toLong()) }

println(solve1(input))
println(solve2(input))
