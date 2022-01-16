package day4

import day4.App.Board
import day4.App.MutableBoard

fun solve1(input: Input): Long {
  val boardMarks: List<MutableBoard<Boolean>> = input.boards.map { boardRows ->
    boardRows.map { boardCol ->
      MutableList(boardCol.size) { false }
    }.toMutableList()
  }

  input.seqNumbers
    .forEach { num ->
      input.boards.forEachIndexed { index, board ->
        markNumOnBoard(num, board, boardMarks[index])
        if (checkBoardBingo(boardMarks[index])) {
          return calcWinningBoardScore(num, board, boardMarks[index])
        }
      }
    }

  return -1
}

fun markNumOnBoard(
  num: Int,
  boardWithNums: Board<Int>,
  boardWithMarks: MutableBoard<Boolean>
) {
  boardWithNums.forEachIndexed { rowIndex, row ->
    row.forEachIndexed { colIndex, value ->
      if (value == num) {
        boardWithMarks[rowIndex][colIndex] = true
        return
      }
    }
  }
}

fun checkBoardBingo(boardWithMarks: MutableBoard<Boolean>): Boolean {
  return boardWithMarks.any { row -> // check rows
    row.all { it }
  }
    ||
    boardWithMarks.indices.any { ind -> // check cols
      boardWithMarks.all { it[ind] }
    }
}

fun calcWinningBoardScore(
  curNum: Int, boardWithNums: Board<Int>,
  boardWithMarks: MutableBoard<Boolean>
): Long {
  return boardWithMarks.flatMapIndexed { rowIndex, row ->
    row.mapIndexedNotNull { colIndex, value ->
      if (value) null else boardWithNums[rowIndex][colIndex]
    }
  }
    .sumOf { it.toLong() }
    .let { it * curNum }
}


fun solve2(input: Input): Long {
  val boardMarks: List<MutableBoard<Boolean>> = input.boards.map { boardRows ->
    boardRows.map { boardCol ->
      MutableList(boardCol.size) { false }
    }.toMutableList()
  }

  val boardsToCheck = input.boards.toMutableSet()

  input.seqNumbers
    .forEach { num ->
      input.boards
        .forEachIndexed { index, board ->
          if (!boardsToCheck.contains(board)) {
            return@forEachIndexed
          }

          markNumOnBoard(num, board, boardMarks[index])

          if (checkBoardBingo(boardMarks[index])) {
            if (boardsToCheck.size == 1) {
              return calcWinningBoardScore(num, board, boardMarks[index])
            }
            boardsToCheck.remove(board)
          }
        }
    }

  return -1
}

data class Input(
  val seqNumbers: List<Int>,
  val boards: List<Board<Int>>
)

typealias Board<T> = List<List<T>>
typealias MutableBoard<T> = MutableList<MutableList<T>>

val seqNumbers = readln().split(",").map { it.toInt() }
var boards = generateSequence(::readLine)
  .chunked(6)
  .map { lines ->
    lines
      .drop(1)
      .map { line ->
        line.split("""\s+""".toRegex())
          .filter { it.isNotBlank() }
          .map { it.toInt() }
      }
  }
  .toList()

println(solve1(Input(seqNumbers, boards)))
println(solve2(Input(seqNumbers, boards)))
